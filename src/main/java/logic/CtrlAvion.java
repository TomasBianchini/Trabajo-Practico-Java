package logic;

import java.util.LinkedList;

import data.DataAvion;
import entities.Avion;

public class CtrlAvion {
	private DataAvion da;

	public CtrlAvion() {
		da = new DataAvion();
	}

	public LinkedList<Avion> getAll() {
		return da.getAll();
	}

	public Avion getById(Avion a) {
		return da.getById(a);
	}

	public Avion getByCantAsientos(Avion a) {
		return da.getById(a);
	}

	public void add(Avion a) {
		da.add(a);
	}

	/*
	 * public void edit(Avion a) { da.edit (a); }
	 */

	public void delete(Avion a) {
		da.delete(a);
	}
}
