package logic;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.LinkedList;

import Service.EmailService;
import Service.PdfService;
import data.DataPasaje;
import entities.Asiento;
import entities.Pasaje;
import entities.Usuario;
import entities.Vuelo;

public class CtrlPasaje {
	private DataPasaje dp;

	public CtrlPasaje() {
		dp = new DataPasaje();
	}

	public Pasaje add(Pasaje pasaje) throws Exception {

		CtrlUsuario cUsuario = new CtrlUsuario();
		CtrlVuelo cVuelo = new CtrlVuelo();
		CtrlAsiento cAsiento = new CtrlAsiento();

		Usuario usuario = cUsuario.getById(pasaje.getUsuario());
		Vuelo vuelo = cVuelo.getById(pasaje.getVuelo());
		Asiento asiento = cAsiento.getOne(pasaje.getAsiento());
		Pasaje p = new Pasaje();

		if (usuario != null && vuelo != null && asiento != null) {
			p.setUsuario(usuario);
			p.setVuelo(vuelo);
			p.setAsiento(asiento);
			p.setEstado("Confirmado");
			if (this.isAsientoDisponible(vuelo, asiento)) {
				try {
					p = dp.add(p);
					this.enviarMail(p);
				} catch (Exception e) {
					throw e;
				}
			} else {
				p = null;
			}
		}
		return p;
	}

	public boolean isAsientoDisponible(Vuelo v, Asiento asi) throws Exception {
		LinkedList<Pasaje> pasajes = this.getByVuelo(v);
		return pasajes.stream()
				.noneMatch(pas -> pas.getAsiento().getNumero().equalsIgnoreCase(asi.getNumero())
						&& pas.getAsiento().getFila().equalsIgnoreCase(asi.getFila())
						&& pas.getAsiento().getAvion().getIdAvion() == asi.getAvion().getIdAvion());
	}

	public LinkedList<Pasaje> getByVuelo(Vuelo vue) throws SQLException {
		return dp.getByVuelo(vue);
	}

	public LinkedList<Pasaje> getByIdUsuario(Usuario usu) throws SQLException {
		LinkedList<Pasaje> pasajes = dp.getByIdUsuario(usu);
		Collections.sort(pasajes, (p1, p2) -> Integer.compare(p2.getIdPasaje(), p1.getIdPasaje()));
		return pasajes;
	}

	public void cancelarPasaje(Pasaje pas) throws Error, Exception {
		LocalDateTime currentDate = LocalDateTime.now();
		long diferenciaEnMinutos = ChronoUnit.MINUTES.between(currentDate, pas.getVuelo().getFechaHoraSalida());
		if (diferenciaEnMinutos >= 360) {
			pas.setEstado("Cancelado");
			dp.cambiarEstado(pas);
		} else
			throw new Error("error : No se puede cancelar fuera del plazo permitido (hasta 6 horas antes del vuelo)");
	}

	public void finalizarPasaje(Pasaje pas) throws Error, Exception {
		LocalDateTime currentDate = LocalDateTime.now();
		long diferenciaEnMinutos = ChronoUnit.MINUTES.between(currentDate, pas.getVuelo().getFechaHoraSalida());
		if (diferenciaEnMinutos >= 15 && diferenciaEnMinutos <= 120) {
			pas.setEstado("Finalizado");
			dp.cambiarEstado(pas);
		} else
			throw new Error(
					"error : No se puede finalizar fuera del plazo permitido (entre 15 minutos y 2 horas antes del vuelo)");
	}

	public Pasaje getById(Pasaje pas) throws SQLException {
		return dp.getById(pas);
	}

	public void enviarMail(Pasaje p) throws Exception {
		try {
			Pasaje pas = this.getById(p);

			PdfService ps = new PdfService();
			ps.crearPdf(pas);

			EmailService em = new EmailService();
			em.sendEmail("Gracias por su compra!", pas, pas.getUsuario().getEmail());
		} catch (Exception e) {
			throw e;
		}

	}

}
