package eda1.practicas.practica02.parte01;

import eda1.practicas.auxiliar.Format;
import eda1.practicas.practica01.Mascota;

import java.util.ArrayList;
import java.util.Iterator;

public class AsignaturaNotas implements Comparable<AsignaturaNotas>, Iterable<String>{
    private final Asignatura asignatura; 
    private final ArrayList<String> notas; 

    public AsignaturaNotas(Asignatura asignatura) {
    	this.asignatura= asignatura;
    	this.notas= new ArrayList<>();
  
    }

    public AsignaturaNotas(String asignaturaId) {
    	this.asignatura= new Asignatura(asignaturaId);
    	this.notas= new ArrayList<>();
    }
    
    public Asignatura getAsignatura() {
    	return this.asignatura;
    }

	public void addNotas(Double... notas) {
		for (Double nota : notas) {
			Double nota1 = (nota != null) ? nota : 0.00;
			this.notas.add(Format.formatDouble(nota1)); //
		}
	}

    public String getNotaMedia() {
        if (notas.isEmpty()) {
            return "0.00";
        }
        double suma = 0.00;
        double divisor=0.00;
		for (String nota : notas) {
			suma+=Double.parseDouble(nota);
			divisor++;
		}
		String media= Format.formatDouble(suma/divisor);
        return media;
    }

    public void clear() {
        this.notas.clear();
    }

    @Override
    public String toString() {
        return asignatura.toString() +" -> "+ notas+ " <" + getNotaMedia()+">";
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (this == other) return true;
        if (!(other instanceof AsignaturaNotas)) return false;
        return this.compareTo((AsignaturaNotas)other) == 0; 
    }

	@Override
	public int compareTo(AsignaturaNotas other) {
		return this.asignatura.compareTo(other.asignatura);
	}

	@Override
	public Iterator<String> iterator() {
		return this.asignatura.iterator();
	}
}