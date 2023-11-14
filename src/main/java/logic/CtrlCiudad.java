package logic;

import java.util.LinkedList;

import data.DataCiudad;
import entities.Ciudad;

public class CtrlCiudad {
	private DataCiudad dc;

	public CtrlCiudad() {
		dc = new DataCiudad();
	}

	public LinkedList<Ciudad> getAll() {
		return dc.getAll();
	}

	public Ciudad getById(Ciudad c) {
		return dc.getById(c);
	}

	public Ciudad getByNombre(Ciudad c) {
		return dc.getByNombre(c);
	}

	public void add(Ciudad c) throws Exception {
		dc.add(c);

	}

	public void edit(Ciudad c) {
		dc.edit(c);
	}

	public void delete(Ciudad c) {
		dc.delete(c);
	}

}
