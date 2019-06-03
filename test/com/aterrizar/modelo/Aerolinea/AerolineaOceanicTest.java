package com.aterrizar.modelo.Aerolinea;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;


import com.aterrizar.modelo.Ubicacion;
import com.aterrizar.modelo.Asiento.AsientoDTO;
import com.aterrizar.modelo.Asiento.AsientoEjecutivo;
import com.aterrizar.modelo.Asiento.AsientoPrimeraClase;
import com.aterrizar.modelo.Asiento.AsientoTurista;
import com.aterrizar.util.DateHelper;

public class AerolineaOceanicTest {
	//Creo mock Aerolinea OCeanic
	@Mock private AerolineaOceanic mockOceanic;

	@Before
    public void setUp() throws Exception {
	
    	//Inicio el mock oceanic para poder usarlo
         MockitoAnnotations.initMocks(this); 
 		
        //Asientos disponibles con vuelos desde Buenos Aires
 		when(mockOceanic.asientosDisponiblesParaOrigen("BUE", "31/12/1990"))
 			.thenReturn(this.generarAsientosOrigen());
 		
 		//Asientos disponibles con vuelos desde Buenos Aires a Mexico
 		when(mockOceanic.asientosDisponiblesParaOrigenYDestino("BUE", "31/12/1990","MEX"))
			.thenReturn(this.generarAsientosDeBUEaMEX());
 		
 		//Asientos disponibles con vuelos desde Buenos Aires a Los Angeles
 		when(mockOceanic.asientosDisponiblesParaOrigenYDestino("BUE", "31/12/1990","SLA"))
			.thenReturn(this.generarAsientosDeBUEaSLA());
 		
	
 		//Se indican todos los asientos reservados
 		when(mockOceanic.estaReservado("ABC010", 1))
			.thenReturn(true);
 		
 		when(mockOceanic.estaReservado("ABD004", 2))
			.thenReturn(true);
 		
 		when(mockOceanic.estaReservado("ABF001", 7))
			.thenReturn(true);
 		
 		
 		//Se indican asientos que se pueden comprar si no se compro nunca antes
 		when(mockOceanic.comprarSiHayDisponibilidad(anyString(), eq("ABC001"), eq(1)))
			.thenReturn(true);
 		
 		when(mockOceanic.comprarSiHayDisponibilidad(anyString(), eq("ABC002"), eq(1)))
			.thenReturn(true);
 		
 		when(mockOceanic.comprarSiHayDisponibilidad(anyString(), eq("ABC003"), eq(1)))
			.thenReturn(true);
 		
 		when(mockOceanic.comprarSiHayDisponibilidad(anyString(), eq("ABC004"), eq(1)))
			.thenReturn(true);
 		
 		//Se indican asientos que se pueden reservar si no se reservo nunca antes
 		when(mockOceanic.reservar(anyString(), eq("ABC001"), eq(1)))
			.thenReturn(true);
 		
 		when(mockOceanic.reservar(anyString(), eq("ABC002"), eq(1)))
			.thenReturn(true);
 		
 		when(mockOceanic.reservar(anyString(), eq("ABC003"), eq(1)))
			.thenReturn(true);
 		
 		when(mockOceanic.reservar(anyString(), eq("ABC004"), eq(1)))
			.thenReturn(true);
	}


	@Test
	public void asientosDisponiblesParaOrigen_ObtenerTodosAsientosDesdeBUE() {
		List<AsientoDTO> asientosOrigen = mockOceanic.asientosDisponiblesParaOrigen("BUE", "31/12/1990");
		//Todos los asientos disponibles para Buenos Aires
		System.out.println("Todos los asientos disponibles para Buenos Aires:");
		for (AsientoDTO asiento: asientosOrigen) {
			System.out.println(asiento.getAsiento());
		}
	}

	@Test
	public void asientosDisponiblesParaOrigenYDestino_ObtenerAsientosDesdeBUEaMEX() {
		List<AsientoDTO> asientos = mockOceanic.asientosDisponiblesParaOrigenYDestino("BUE", "31/12/1990","MEX");
		//Todos los asientos disponibles desde Buenos Aires a Mexico
		System.out.println("Todos los asientos disponibles desde Buenos Aires a Mexico:");
		for (AsientoDTO asiento: asientos) {
			System.out.println(asiento.getAsiento());
		}
	}

	@Test
	public void asientosDisponiblesParaOrigenYDestino_ObtenerAsientosDesdeBUEaSLA() {
		List<AsientoDTO> asientos = mockOceanic.asientosDisponiblesParaOrigenYDestino("BUE", "31/12/1990","SLA");
		//Todos los asientos disponibles desde Buenos Aires a Mexico
		System.out.println("Todos los asientos disponibles desde Buenos Aires a Los Angeles:");
		for (AsientoDTO asiento: asientos) {
			System.out.println(asiento.getAsiento());
		}
	}

	@Test
	public void estaReservado_asientoABC001_01NoEstaReservado() {
		//El asiento ABC001 1 esta disponible
		assertFalse("El asiento ABC001 01 esta reservado",mockOceanic.estaReservado("ABC001", 1));
	}

	@Test
	public void estaReservado_asientoABC010_01EstaReservado() {
		//El asiento ABC010 1 esta reservado
		assertTrue("El asiento ABC010 01 no esta reservado",mockOceanic.estaReservado("ABC010", 1));
	}

	@Test
	public void comprarSiHayDisponibilidad_SeCompraAsientoDisponible() {
		//El asiento ABC001 1 se pudo comprar
		assertTrue("El asiento ABC001 1 no se pudo comprar",
					mockOceanic.comprarSiHayDisponibilidad("40854236", "ABC001", 1));
	}

	@Test
	public void comprarSiHayDisponibilidad_NoSeCompraAsientoNoDisponible() {
		//El asiento ABC007 1 no se pudo comprar
		assertFalse("El asiento ABC007 1  se pudo comprar",
					mockOceanic.comprarSiHayDisponibilidad("40854236", "ABC007", 1));
	}

	@Test
	public void reservar_SeReservaAsientoDisponible() {
		//El asiento ABC001 1 se pudo reservar
		assertTrue("El asiento ABC001 1 no se pudo reservar",
					mockOceanic.reservar("40854236", "ABC001", 1));
	}

	@Test
	public void reserva_NoSeReservaAsientoNoDisponible() {
		//El asiento ABC007 1 no se pudo reservar
		assertFalse("El asiento ABC007 1  se pudo reservar",
					mockOceanic.reservar("40854236", "ABC007", 1));
	}
	
	
	//Generar asientos  Origen de Buenos Aires a Los Angeles
	public List<AsientoDTO> generarAsientosDeBUEaSLA(){
		List<AsientoDTO> asientos = new ArrayList<AsientoDTO>();
		Date fechaSalida = DateHelper.parseFromISO8601("31/12/1990");
		
		asientos.add(new AsientoDTO("ABC001", 1, fechaSalida, null, 100, new AsientoEjecutivo(), Ubicacion.Centro));
		asientos.add(new AsientoDTO("ABC002", 1, fechaSalida, null, 110, new AsientoTurista(), Ubicacion.Pasillo));
		return asientos;
	}
	
	//Generar asientos  Origen de Buenos Aires a Mexico
	public List<AsientoDTO> generarAsientosDeBUEaMEX(){
		List<AsientoDTO> asientos = new ArrayList<AsientoDTO>();
		Date fechaSalida = DateHelper.parseFromISO8601("31/12/1990");
		
		asientos.add(new AsientoDTO("ABC003", 1, fechaSalida, null, 340, new AsientoPrimeraClase(), Ubicacion.Pasillo));
		asientos.add(new AsientoDTO("ABC004", 1, fechaSalida, null, 200, new AsientoEjecutivo(), Ubicacion.Ventanilla));
		return asientos;
	}

	//Generar asientos  Origen desde Buenos Aires con fecha "31/12/1990"
	public List<AsientoDTO> generarAsientosOrigen(){
		List<AsientoDTO> asientos = new ArrayList<AsientoDTO>();
		asientos.addAll(this.generarAsientosDeBUEaMEX());
		asientos.addAll(this.generarAsientosDeBUEaSLA());
		return asientos;
	}
}
