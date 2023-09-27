package logic;

import java.util.LinkedList;

import data.DataPasaje;
import entities.Asiento;
import entities.Pasaje;
import entities.Pasajero;
import entities.Vuelo;

public class CtrlPasaje {
	private DataPasaje dp;

	public CtrlPasaje() {
		dp = new DataPasaje();
	}

	public Pasaje add(Pasaje pasaje) {
		Pasaje p = new Pasaje();
		CtrlPasajero cPasajero = new CtrlPasajero();
		CtrlVuelo cVuelo = new CtrlVuelo();
		CtrlAsiento cAsiento = new CtrlAsiento();
		Pasajero pasajero = cPasajero.getByDni(pasaje.getPasajero());
		Vuelo vuelo = cVuelo.getById(pasaje.getVuelo());
		Asiento asiento = cAsiento.getOne(pasaje.getAsiento());
		if (pasajero != null || vuelo != null) {
			p.setPasajero(pasajero);
			p.setVuelo(vuelo);
			p.setAsiento(asiento);
			p.setEstado("Comprado");
			LinkedList<Pasaje> pasajes = vuelo.getPasajes();

			if (pasajes != null) {
				for (Pasaje pas : pasajes) {
					if (pas.getAsiento().getNumero() == p.getAsiento().getNumero()
							&& pas.getAsiento().getFila() == p.getAsiento().getFila()
							&& pas.getAsiento().getAvion().getIdAvion() == p.getAsiento().getAvion().getIdAvion()) {
						return p;
					}
				}
				dp.add(p);
			}

		}
		return p;

	}

}
