package entities;

import java.time.LocalDateTime;

public class Vuelo {
	private int idvuelo;
	private LocalDateTime fechaHoraSalida;
	private LocalDateTime fechaHoraLlegada;
	private Aeropuerto aeropuertoOrigen;
	private Aeropuerto aeropuertoDestino;
	private Avion avion;
	private double precioPrimeraClase;
	private double precioGeneral;

	public double getPrecioPrimeraClase() {
		return precioPrimeraClase;
	}

	public void setPrecioPrimeraClase(double precioPrimeraClase) {
		this.precioPrimeraClase = precioPrimeraClase;
	}

	public double getPrecioGeneral() {
		return precioGeneral;
	}

	public void setPrecioGeneral(double precioGeneral) {
		this.precioGeneral = precioGeneral;
	}

	public int getIdvuelo() {
		return idvuelo;
	}

	public void setIdvuelo(int idvuelo) {
		this.idvuelo = idvuelo;
	}

	public LocalDateTime getFechaHoraSalida() {
		return fechaHoraSalida;
	}

	public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
		this.fechaHoraSalida = fechaHoraSalida;
	}

	public LocalDateTime getFechaHoraLlegada() {
		return fechaHoraLlegada;
	}

	public void setFechaHoraLlegada(LocalDateTime fechaHoraLlegada) {
		this.fechaHoraLlegada = fechaHoraLlegada;
	}

	public Aeropuerto getAeropuertoOrigen() {
		return aeropuertoOrigen;
	}

	public void setAeropuertoOrigen(Aeropuerto aeropuertoOrigen) {
		this.aeropuertoOrigen = aeropuertoOrigen;
	}

	public Aeropuerto getAeropuertoDestino() {
		return aeropuertoDestino;
	}

	public void setAeropuertoDestino(Aeropuerto aeropuertoDestino) {
		this.aeropuertoDestino = aeropuertoDestino;
	}

	public Avion getAvion() {
		return avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

}
