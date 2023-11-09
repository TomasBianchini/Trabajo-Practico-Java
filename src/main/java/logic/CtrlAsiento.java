package logic;

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

	public void add(Asiento a) {
		da.add(a);
	}

	public void delete(Asiento a) {
		da.delete(a);
	}
}