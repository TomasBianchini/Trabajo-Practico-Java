package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataAvion;
import entities.Avion;

public class CtrlAvion {
	private DataAvion da;

	public CtrlAvion() {
		da = new DataAvion();
	}

	public LinkedList<Avion> getAll() throws SQLException {
		return da.getAll();
	}

	public Avion getById(Avion a) throws SQLException {
		return da.getById(a);
	}

	public Avion getByCantAsientos(Avion a) throws SQLException {
		return da.getById(a);
	}

	public void add(Avion a) throws SQLException {
		da.add(a);
	}

	public void delete(Avion a) throws SQLException {
		da.delete(a);
	}
}
