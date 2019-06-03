package com.aterrizar.modelo.Asiento;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.aterrizar.modelo.Ubicacion;

public class AsientoDTO {
	protected String codigoVuelo;
	protected Integer numeroAsiento;
	protected Date fechaSalida;
	protected Time horaSalida;
    protected double precio;
    protected Asiento claseAsiento;
	protected Ubicacion ubicacion;

	public AsientoDTO(String codigoVuelo,
					  Integer numeroAsiento,
					  Date fechaSalida,
					  Time horaSalida,
					  double precio,
					  Asiento claseAsiento,
					  Ubicacion ubicacion) {
		
		this.codigoVuelo = codigoVuelo;
		this.numeroAsiento = numeroAsiento;
		this.fechaSalida = fechaSalida;
		this.horaSalida = horaSalida;
		this.precio = precio;
		this.claseAsiento = claseAsiento;
		this.ubicacion = ubicacion;
	}
   
	public String getCodigoVuelo() {
		return codigoVuelo;
	}
	public Integer getNumeroAsiento() {
		return numeroAsiento;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public Time getHoraSalida() {
		return horaSalida;
	}
	public double getPrecio() {
		return precio;
	}
    public Asiento getClaseAsiento() {
		return claseAsiento;
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	
	public List<String> getAsiento(){
		
		return Arrays.asList(codigoVuelo.toString(),
							 numeroAsiento.toString(),
							 fechaSalida.toString(),
							 //horaSalida.toString(),
							 String.format("%1$,.2f", precio),
							 claseAsiento.toString(),
							 ubicacion.toString());
	}
}
