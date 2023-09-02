package logic;

import java.util.LinkedList;

import data.DataVuelo;
import entities.Vuelo;

public class CtrlVuelo {
private DataVuelo dv; 
	
	public CtrlVuelo() {
		dv = new DataVuelo();
	}
		
	public LinkedList<Vuelo> getAll(){
		return dv.getAll();
	}

	public Vuelo getById(Vuelo v) {
		return dv.getById(v);
	}
	
	public Vuelo getByOrigenyDestino(Vuelo v) {
		return dv.getById(v);
	}

	public void add(Vuelo v){
		dv.add(v);
	}
		
	public void edit(Vuelo v) {
		dv.edit (v);
	}
		
	public void delete(Vuelo v) {
		dv.delete(v);
	}

}
