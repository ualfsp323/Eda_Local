package eda1.practicas.practica01;

import eda1.practicas.auxiliar.Format;

import java.util.ArrayList;

public class Cita {
	private static int numCitas = 0; 
	private final int citaId; 
	private final ArrayList<String> valoraciones; 
													

	public static void inicializaNumCitas() {
		numCitas = 0; // se podría escribir this.numCitas = 0 ?????
	}

	public Cita() {
		this.citaId = ++numCitas;
		this.valoraciones = new ArrayList<String>();
	}

	public Cita(int citaId) {
		this.citaId = ++citaId;
		this.valoraciones = null;
	}

	public int getCitaId() {
		return this.citaId;
	}

	public boolean addValoracion(String valoracion) {
		if (valoracion == null || valoracion.isEmpty() ) return false;
		valoraciones.add(valoracion.toLowerCase().trim());
		return true;
	}

	public boolean contienePalabra(String palabra) {
		if (palabra == null || palabra.isEmpty() ) return false;
		for (String valorStr : valoraciones) {
			if (valorStr.contains(palabra.toLowerCase().trim()))return true;
		}
		return false;
		// 1 for() tipo forEach ¿+ break?
	}

	public void clear() {
		this.valoraciones.clear();
	}

	@Override
	public String toString() {
		
		return "Cita #" + Format.formatInt(citaId, 4) + " -> " + (valoraciones.isEmpty() ? "<sin texto>" : valoraciones);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true; 
		if (!(o instanceof Cita))
			return false; 
		Cita otro = (Cita) o;
		return citaId == otro.citaId ;
	
	}
}