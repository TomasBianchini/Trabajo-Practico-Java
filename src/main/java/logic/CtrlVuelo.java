package logic;

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

	public LinkedList<Vuelo> getAll() {
		return dv.getAll();
	}

	public Vuelo getById(Vuelo v) {
		return dv.getById(v);
	}

	public Vuelo getByOrigenyDestino(Vuelo v) {
		return dv.getById(v);
	}

	public void add(Vuelo v) {
		dv.add(v);
	}

	public void edit(Vuelo v) {
		dv.edit(v);
	}

	public void delete(Vuelo v) {
		dv.delete(v);
	}

	public HashMap<String, Asiento> getAsientosDisponibles(Vuelo vue) {
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
