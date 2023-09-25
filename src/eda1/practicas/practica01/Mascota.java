package eda1.practicas.practica01;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


import eda1.practicas.auxiliar.Format;

public class Mascota implements Comparable<Mascota>, Iterable<Cita> {
    private final String nombre;
    private final String especie;
    private final LinkedList<Cita> historial;

    public Mascota(String nombre, String especie) {
    	this.nombre = (nombre == null || nombre.isEmpty()) ? "sinNombre" : nombre.toLowerCase().trim();
    	this.especie=(especie == null || especie.isEmpty()) ? "sinEspecie" : especie.toLowerCase().trim();
        this.historial=new LinkedList<Cita>();
    }	

    public Cita addCita() {
    	Cita cita = new Cita();
    	historial.add(cita);
    	return cita;
    }

    public Cita getCita(int citaId) {
        if (citaId < 0) return null;
        if (historial.indexOf(citaId) != -1) return historial.get(citaId);
        return null;
     
    
        //Si el parámetro citaId es menor que cero, se devuelve null
    	//Tendremos que buscar la cita con identificador citaId en la colección historial.
        //Para buscar un elemento en una colección tipo LinkedList<T> hacemos uso del método indexOf(), ¿verdad? (nada de iterar para buscar un elemento, ¡uf!)
        //3 líneas
    	//...
    }
       

    public void clear() {
        //1 for() tipo forEach
        this.historial.clear();
    }

    public int size() {
        return this.historial.size();
    }

    @Override
    public String toString() {
        return nombre + " <"+especie +"> "+ ( size()==1 ? "["+ size()+" cita"+"]":"["+ size()+" citas" +"]");
    }

    public String toStringExtended() {
        return this.toString() + ": " + this.historial.toString();
    }

    @Override
    public int compareTo(Mascota other) {
    	int resultado = this.nombre.compareTo(other.nombre);
        if  (resultado==0)  resultado = this.especie.compareTo(other.especie);
        return resultado;
        //Orden natural: clave principal -> nombre (ascendente); clave secundaria -> especie (ascendente)
        //2 líneas
    	//...  
         
    }

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (!(other instanceof Mascota)) return false;
        return this.compareTo((Mascota)other) == 0; 
    }

    @Override
    public Iterator<Cita> iterator() {
        return this.historial.iterator();
    }
}