package logic;

import java.util.LinkedList;

import data.DataPais;
import entities.Pais;

public class CtrlPais {
	private DataPais dp; 
	
	public CtrlPais() {
		dp = new DataPais();
	}
		
	public LinkedList<Pais> getAll(){
		return dp.getAll();
	}

	public Pais getByNombre(Pais p) {
		return dp.getByNombre(p);
	}

	public void add(Pais p){
		dp.add(p);
	}
	
	public Pais getById(Pais p) {
		return dp.getById(p);
	}
	public void edit(Pais p) {
		dp.edit(p);
	}
		
	public void delete(Pais p) {
		dp.delete(p);
	}
}


