package logic;

import java.sql.SQLException;

import data.DataAsiento;
import entities.Asiento;

public class CtrlAsiento {
	private DataAsiento da;

	public CtrlAsiento() {
		da = new DataAsiento();
	}

	public Asiento getOne(Asiento a) {
		return da.getOne(a);
	}

	public void add(Asiento a) throws SQLException {
		da.add(a);
	}

	public void delete(Asiento a) throws SQLException {
		da.delete(a);
	}
}