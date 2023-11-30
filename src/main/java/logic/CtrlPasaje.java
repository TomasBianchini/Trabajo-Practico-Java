package logic;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;

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

	public Pasaje add(Pasaje pasaje) throws SQLException {
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
			int bandera = 0;
			LinkedList<Pasaje> pasajes = this.getByVuelo(vuelo);
			if (!pasajes.isEmpty()) {
				for (Pasaje pas : pasajes) {
					if (pas.getAsiento().getNumero().equalsIgnoreCase(p.getAsiento().getNumero())
							&& pas.getAsiento().getFila().equalsIgnoreCase(p.getAsiento().getFila())
							&& pas.getAsiento().getAvion().getIdAvion() == p.getAsiento().getAvion().getIdAvion()) {
						bandera = 1;
					}
				}
			}
			if (bandera == 0) {
				dp.add(p);
			} else {
				p = null;
			}

		}
		return p;
	}

	public LinkedList<Pasaje> getByVuelo(Vuelo vue) throws SQLException {
		return dp.getByVuelo(vue);
	}

	public LinkedList<Pasaje> getByIdUsuario(Usuario usu) throws SQLException {
		return dp.getByIdUsuario(usu);
	}

	public void cambiarEstado(Pasaje pas) throws SQLException {
		String[] estados = { "Confirmado", "Finalizado", "Cancelado" };
		boolean estadoValido = Arrays.asList(estados).contains(pas.getEstado());
		if (estadoValido) {
			dp.cambiarEstado(pas);
		}
	}

}
