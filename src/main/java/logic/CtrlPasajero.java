package logic;


import java.util.LinkedList;
import data.DataPasajero;
import entities.Pasajero; 

public class CtrlPasajero {
		private DataPasajero dp;

		public CtrlPasajero() {
			dp=new DataPasajero();
		}
		
		public LinkedList<Pasajero> getAll(){
			return dp.getAll();
		}

		public Pasajero getByEmail(Pasajero pas) {
			return dp.getByEmail(pas);
			
		}
	
		
		public Pasajero getByDni(Pasajero pas) {
			return dp.getByDni(pas);
		}
	

		public void add(Pasajero p){
			dp.add(p);
		}
	
			
		public void edit(Pasajero p) {
			dp.edit(p);
		}
		
		public void delete(Pasajero p) {
			dp.delete(p);
		}

	

}
