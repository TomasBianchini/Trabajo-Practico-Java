package logic;

import java.util.LinkedList;

import data.DataAeropuerto;
import entities.Aeropuerto;

public class CtrlAeropuerto {
	private DataAeropuerto da;

	public CtrlAeropuerto() {
		da = new DataAeropuerto();
	}

	public LinkedList<Aeropuerto> getAll() {
		return da.getAll();
	}

	public Aeropuerto getById(Aeropuerto a) {
		return da.getById(a);
	}

	public Aeropuerto getByNombre(Aeropuerto a) {
		return da.getByNombre(a);
	}

	public void add(Aeropuerto a) {
		da.add(a);
	}

	public void edit(Aeropuerto a) {
		da.edit(a);
	}

	public void delete(Aeropuerto a) {
		da.delete(a);
	}
}
