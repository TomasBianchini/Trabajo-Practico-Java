package entities;


public class Aeropuerto {
	private int idAeropuerto;
	private String nombre;
	private String descAeropuerto; 
	private Ciudad ciudad;
	
	
	public int getIdAeropuerto() {
		return idAeropuerto;
	}
	public void setIdAeropuerto(int idAeropuerto) {
		this.idAeropuerto = idAeropuerto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescAeropuerto() {
		return descAeropuerto;
	}
	public void setDescAeropuerto(String descAeropuerto) {
		this.descAeropuerto = descAeropuerto;
	}
	public Ciudad getCiudad(){
		return ciudad;
	}
	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}
	
}
