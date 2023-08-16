package logic;

import entities.Pasajero;
import data.DataPasajero;

public class CtrlLogin {
	private DataPasajero dp;
	
	public CtrlLogin() {
		dp=new DataPasajero();
	}
	
	
	public Pasajero validate(Pasajero p) {
		return dp.getByEmail(p);
	}
	
}
