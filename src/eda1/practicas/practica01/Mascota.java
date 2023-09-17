package eda1.practicas.practica01;

import java.util.Iterator;
import java.util.LinkedList;

public class Mascota implements Comparable<Mascota>, Iterable<Cita> {
    private final String nombre;
    private final String especie;
    private final LinkedList<Cita> historial;

    public Mascota(String nombre, String especie) {
        //Si nombre es nulo o está vacío, la mascota se llamará "sinNombre"; igual con el atributo especie
        //Atención a las mayúsculas, minúsculas y espacios en blanco (delante y detrás del texto)
        //3 líneas
    	//...
    }	

    public Cita addCita() {
        //Creamos una nueva cita y, por comodidad, devolvemos su referencia para poder añadir valoraciones
        //3 líneas
    	//...
    }

    public Cita getCita(int citaId) {
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
        //Habría que tener en cuenta que solo se escribe "cita" (sin s) si el tamaño del historial es igual a 1; en caso contrario, se escribe "citas"
        //0 citas, 2 citas, etc. etc.
        //Completa la siguiente línea
        return //...
    }

    public String toStringExtended() {
        return this.toString() + ": " + this.historial.toString();
    }

    @Override
    public int compareTo(Mascota other) {
        //Orden natural: clave principal -> nombre (ascendente); clave secundaria -> especie (ascendente)
        //2 líneas
    	//...
    }

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (!(other instanceof Mascota)) return false;
        return this.compareTo((Mascota)other) == 0; //Hacemos uso del método compareTo()
    }

    @Override
    public Iterator<Cita> iterator() {
        //Iterar sobre 1 mascota equivale a iterar sobre la colección this.historial (sus citas)
        return //...
    }
}