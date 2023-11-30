package logic;

import java.sql.SQLException;
import java.util.LinkedList;

import data.DataPais;
import entities.Pais;

public class CtrlPais {
	private DataPais dp;

	public CtrlPais() {
		dp = new DataPais();
	}

	public LinkedList<Pais> getAll() throws SQLException {
		return dp.getAll();
	}

	public Pais getByNombre(Pais p) throws SQLException {
		return dp.getByNombre(p);
	}

	public void add(Pais p) throws SQLException {
		dp.add(p);
	}

	public Pais getById(Pais p) throws SQLException {
		return dp.getById(p);
	}

	public void edit(Pais p) throws SQLException {
		dp.edit(p);
	}

	public void delete(Pais p) throws SQLException {
		dp.delete(p);
	}
}
