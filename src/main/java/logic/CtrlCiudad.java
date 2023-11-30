package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataCiudad;
import entities.Ciudad;

public class CtrlCiudad {
	private DataCiudad dc;

	public CtrlCiudad() {
		dc = new DataCiudad();
	}

	public LinkedList<Ciudad> getAll() throws SQLException {
		return dc.getAll();
	}

	public Ciudad getById(Ciudad c) throws SQLException {
		return dc.getById(c);
	}

	public Ciudad getByNombre(Ciudad c) throws SQLException {
		return dc.getByNombre(c);
	}

	public void add(Ciudad c) throws SQLException {
		dc.add(c);
	}

	public void edit(Ciudad c) throws SQLException {
		dc.edit(c);
	}

	public void delete(Ciudad c) throws SQLException {
		dc.delete(c);
	}

}
