package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataAeropuerto;
import entities.Aeropuerto;

public class CtrlAeropuerto {
	private DataAeropuerto da;

	public CtrlAeropuerto() {
		da = new DataAeropuerto();
	}

	public LinkedList<Aeropuerto> getAll() throws SQLException {
		return da.getAll();
	}

	public Aeropuerto getById(Aeropuerto a) throws SQLException {
		return da.getById(a);
	}

	public Aeropuerto getByNombre(Aeropuerto a) throws SQLException {
		return da.getByNombre(a);
	}

	public void add(Aeropuerto a) throws SQLException {
		da.add(a);
	}

	public void edit(Aeropuerto a) throws SQLException {
		da.edit(a);
	}

	public void delete(Aeropuerto a) throws SQLException {
		da.delete(a);
	}
}
