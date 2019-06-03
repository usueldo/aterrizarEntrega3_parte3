package com.aterrizar.modelo.Aerolinea;

import java.util.List;

import com.aterrizar.modelo.Asiento.AsientoDTO;

public interface AerolineaOceanic {
	//Obtener asientos disponibles indicando Ciudad Origen
	public List<AsientoDTO> asientosDisponiblesParaOrigen(
			String codigoOrigenOceanic,
			String fechaSalida);

	//Obtener asientos disponibles indicando Ciudad Origen y Destino
	public List<AsientoDTO> asientosDisponiblesParaOrigenYDestino(
			String codigoOrigenOceanic,
			String fechaSalida,
			String codigoDestinoOceanic);
	
	//Indica si un asiento esta reservado
	public boolean estaReservado(String codigoDeVuelo, Integer numeroDeAsiento);
	
	//Indica si se pudo comprar o no un Asiento
	public boolean comprarSiHayDisponibilidad(String dni, String codigoVuelo, Integer numeroDeAsiento);
	
	//Indica si se pudo reservar o no un Asiento
	public boolean reservar(String dni, String codigoVuelo, Integer numeroDeAsiento);
}

