package logic;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

import data.DataVuelo;
import entities.Asiento;
import entities.Pasaje;
import entities.Vuelo;

public class CtrlVuelo {
	private DataVuelo dv;

	public CtrlVuelo() {
		dv = new DataVuelo();
	}

	public LinkedList<Vuelo> getAll() throws SQLException {
		return dv.getAll();
	}

	public Vuelo getById(Vuelo v) throws SQLException {
		return dv.getById(v);
	}

	public LinkedList<Vuelo> getByOrigenYDestino(Vuelo v) throws SQLException {
		return dv.getByOrigenYDestino(v);
	}

	public void add(Vuelo v) throws SQLException {
		dv.add(v);
	}

	public void edit(Vuelo v) throws SQLException {
		dv.edit(v);
	}

	public void delete(Vuelo v) throws SQLException {
		dv.delete(v);
	}

	public HashMap<String, Asiento> getAsientosDisponibles(Vuelo vue) throws SQLException {
		CtrlPasaje cPasaje = new CtrlPasaje();
		HashMap<String, Asiento> asientosDisponibles = new HashMap<>();
		Vuelo vuelo = getById(vue);
		LinkedList<Pasaje> pasajes = cPasaje.getByVuelo(vuelo);
		asientosDisponibles = vuelo.getAvion().getAsientos();

		if (pasajes != null) {
			for (Pasaje pas : pasajes) {
				String clave = pas.getAsiento().getFila() + pas.getAsiento().getNumero();
				asientosDisponibles.remove(clave);
			}
		}
		return asientosDisponibles;
	}

}
