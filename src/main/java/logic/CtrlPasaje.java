package logic;

import java.sql.SQLException;
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
			p.setEstado("Comprado");
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
			}

		}
		return p;
	}

	public LinkedList<Pasaje> getByVuelo(Vuelo vue) {
		return dp.getByVuelo(vue);
	}

}
