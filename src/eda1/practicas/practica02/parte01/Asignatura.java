package eda1.practicas.practica02.parte01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import eda1.practicas.auxiliar.Format;

public class Asignatura implements Comparable<Asignatura>, Iterable<String>{
    private final String asignaturaId;  
    private final int cuatrimestre; 
    private final ArrayList<String> docentesId; 
    
    public Asignatura(String asignaturaId, int cuatrimestre) {
    	this.asignaturaId = ( asignaturaId==null  || asignaturaId.isEmpty()? "sinNombre" :asignaturaId.toLowerCase().trim());
    	this.cuatrimestre = ( cuatrimestre < 1  || cuatrimestre>8 )? 0 :cuatrimestre;
    	this.docentesId = new ArrayList<>();
    }

    public Asignatura(String asignaturaId) {
    	this.asignaturaId = ( asignaturaId==null  || asignaturaId.isEmpty()? "sinNombre" :asignaturaId.toLowerCase().trim());
    	this.cuatrimestre = 0;
    	this.docentesId = null;
    }

    public int getCuatrimestre() {
        return this.cuatrimestre;
    }

    public String getAsignaturaId() {
        return this.asignaturaId;
    }

    
    public void addDocentes(String... docentesId) {
    	if (docentesId != null) {
            for (int i = 0; i < docentesId.length; i++) {
                String docente = docentesId[i].toLowerCase();
                if (!this.docentesId.contains(docente)) {
                    this.docentesId.add(docente);
                }
            }
        }
   
    }

    public boolean esDocente(String docenteId) {
        if (docenteId == null || docenteId.isEmpty()) return false;
        return this.docentesId.contains(docenteId.toLowerCase());
    }

    public void clear() {
        this.docentesId.clear();
    }

    public String toStringDocentes(Comparator<String> comp) {
    	docentesId.sort(comp);
        return this.docentesId.toString();
    }

    @Override
    public String toString() {
        String cuatrimestre;
        switch (this.cuatrimestre) {
            case 1: cuatrimestre = "1º-1C"; break;
            case 2: cuatrimestre = "1º-2C"; break;
            case 3: cuatrimestre = "2º-1C"; break;
            case 4: cuatrimestre = "2º-2C"; break;
            case 5: cuatrimestre = "3º-1C"; break;
            case 6: cuatrimestre = "3º-2C"; break;
            case 7: cuatrimestre = "4º-1C"; break;
            case 8: cuatrimestre = "4º-2C"; break;

            default: cuatrimestre = "?º-?C";
        }
        return asignaturaId+ " (" + cuatrimestre +")"; 
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Asignatura)) return false;
        return this.compareTo((Asignatura)other) == 0;
    }

    @Override
    public int compareTo(Asignatura other) {
	 return this.asignaturaId.compareTo(other.asignaturaId);
    }

	@Override
	public Iterator<String> iterator() {
		return this.docentesId.iterator();
	}
}