package entities;

import java.util.HashMap;

public class Avion {
	private int idAvion;
	private String marca;
	private String modelo;
	private String anio;
	private HashMap<String, Asiento> asientos;

	public HashMap<String, Asiento> getAsientos() {
		return asientos;
	}

	public void setAsientos(HashMap<String, Asiento> asientos) {
		this.asientos = asientos;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public int getIdAvion() {
		return idAvion;
	}

	public void setIdAvion(int idAvion) {
		this.idAvion = idAvion;
	}

}
