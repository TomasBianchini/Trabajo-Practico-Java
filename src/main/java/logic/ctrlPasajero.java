package logic;


import java.util.LinkedList;
import data.DataPasajero;
import entities.Pasajero; 

public class ctrlPasajero {
		private DataPasajero dp;

		public ctrlPasajero() {
			dp=new DataPasajero();
		}
		
		public LinkedList<Pasajero> getAllPasajero(){
			return dp.getAllPasajero();
		}

		public Pasajero getByEmail(Pasajero pas) {
			return dp.getByEmail(pas);
			
		}
	
		
		public Pasajero getByDni(Pasajero pas) {
			return dp.getByDni(pas);
		}
	

		public void addPasajero(Pasajero p){
			dp.addPasajero(p);
		}
	
			
		public void editPasajero(Pasajero p) {
			dp.editPasajero(p);
		}
		
		public void deletePasajero(Pasajero p) {
			dp.deletePasajero(p);
		}

	

}
