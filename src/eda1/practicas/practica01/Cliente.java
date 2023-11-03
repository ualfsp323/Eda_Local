package eda1.practicas.practica01;

import eda1.practicas.auxiliar.Format;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class Cliente implements Comparable<Cliente>,Iterable<Mascota> {
    private static int numClientes = 0;
    private final String nombre;
    private final ArrayList<Mascota> mascotas;
    
    public static void inicializaNumClientes() {
        numClientes = 0;
    }
    
    public Cliente(String nombre) {
      
    	this.nombre = Format.formatInt(++numClientes, 5)+ ".- " + ( nombre==null  || nombre.isEmpty()? "sinNombre" :nombre.toLowerCase().trim());
    	this.mascotas = new ArrayList<>();
    }

    public boolean addMascota(String nombre, String especie) {
    	int index = this.mascotas.indexOf(new Mascota(nombre,especie));
    	if(index >= 0 )return false;
    	mascotas.add(new Mascota(nombre, especie));
    	return true;
    }

    public Cita addCita(String nombre, String especie) {
    	int index = this.mascotas.indexOf(new Mascota(nombre,especie));
        return (index < 0 ) ? null :  this.mascotas.get(index).addCita();

    }

	public void clear() {
		int size = size();
		for (int i = 0; i < size; i++) {
			Mascota mascota = mascotas.get(i);
			mascota.clear();
		}
		this.mascotas.clear();
	}

    public int size() {
        return this.mascotas.size();
    }
     
    public ArrayList<ArrayList<Integer>> getCitasId(){
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (Mascota mascota : mascotas) {
            ArrayList<Integer> aux = new ArrayList<>();
            for (Cita cita : mascota) {
                aux.add(cita.getCitaId());
            }
            result.add(aux);
        }
        return result;
    }
	
	public ArrayList<ArrayList<Integer>> getCitasId(String palabra) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();
		for (Mascota mascota : mascotas) {
			ArrayList<Integer> aux = new ArrayList<>();
			for (Cita cita : mascota) {
				if (cita.contienePalabra(palabra)) {
					aux.add(cita.getCitaId());
				}
			}
			result.add(aux);
		}
		return result;
	}

    public ArrayList<Integer> getCitasId(String nombre, String especie){
        ArrayList<Integer> result = new ArrayList<>();
    	int index = this.mascotas.indexOf(new Mascota(nombre,especie));
    	if(index < 0 )return null; 
	    Mascota mascota=mascotas.get(index);
        for (Cita cita : mascota) {
			result.add(cita.getCitaId());
        }
        return result;
    }

    public Cita getCita(int citaId){
        Cita result = null;
        for (Mascota mascota : mascotas) {
            Cita cita = mascota.getCita(citaId);
            if (cita != null) {
                result = cita;
                break;
            }
        }
        return result;
    }

	public Mascota getMascota(int citaId){
    	 for (Mascota mascota : mascotas) {
    		 Cita cita = mascota.getCita(citaId);
    		 if (cita==null) {
    			 continue;
    		 }
    	     return mascota;
    	 }
    	 return null;
    }

    @Override
    public String toString() {
        return this.nombre + " -> " + this.mascotas.toString();
    }

	public String toStringExtended() {
		String result = this.nombre + " -> {";
		int index = 0;
		for (Mascota mascota : mascotas) {
			if (index == mascotas.size() - 1) {
				result += mascota.toStringExtended();
				break;
			}
			result += mascota.toStringExtended() + ", ";
			index++;
		}
		return result + "}";
	}

    public boolean load(String nombreArchivo) {
    	Scanner scan;
        try {
            scan = new Scanner(new File(nombreArchivo));
        }catch(Exception e) {
            return false;
        }
        while (scan.hasNextLine()) {
            String linea = scan.nextLine(); 
            if (linea.isEmpty()) continue;
            if (linea.startsWith("%")) continue;
            String[] items = linea.split("[ ]+"); 
            int posInicial = items[0].trim().isEmpty() ? 1 : 0;
            Mascota mascota = new Mascota(items[posInicial], items[posInicial+1]);
         	int index = this.mascotas.indexOf(new Mascota(items[posInicial], items[posInicial+1]));
			if (index < 0) {
				this.mascotas.add(mascota);
				int numCitas = Integer.parseInt(items[posInicial + 2]); 
				for (int i = 0; i < numCitas; i++) {
					mascota.addCita();
				}
			}
        }
        scan.close();
        return true;
    }
  
	@Override
	public boolean equals(Object other) {
		/* Test 00
		 * Creo el metodo equals para poder comparar sis dos clientes son iguales o no,
		 * ya que lo usa el metodo contains, para así poder tener en el array
		 * ArrayList<Cliente> solo los clientes no repetidos.
		 */
		if (this == other)
			return true;
		if (!(other instanceof Cliente))
			return false;
		/*
		 * Para ello separo el id del cliente de su nombre para poder solo comparar los
		 * nombres de los clientes.
		 */
		Cliente otro = (Cliente) other;
		/* Uso el metodo "split" ,puede que lo cambia a otro más simple. */
		String[] partes1 = nombre.split(".- ");
		String This_nombre = partes1[1];

		String[] partes2 = otro.nombre.split(".- ");
		String Otro_nombre = partes2[1];

		return This_nombre.equals(Otro_nombre);
	}


	@Override
	public Iterator<Mascota> iterator() {
		/* Test 02
		 * creo el metodo Iterador<Mascota> ya que estamos iterando
		 * sobre mascotas ya que tiene el private final LinkedList<Cita> historial;
		 * es decir, si iteras sobre mascotas, iteras tambien sobre cita
		 * 
		 * Para constestar // ¿Por qué solo 1 mascota y 10 citas y no 10 mascotas y 100 citas?*
		 * esto se debe que la clase mascota tiene en el metodo addMascota las lineas
		 *     	int index = this.mascotas.indexOf(new Mascota(nombre,especie));
    	        if(index >= 0 )return false;
    	 *donde si ya existe una mascota, este no se volvera a añadir,
    	 *creando solamente una mascota con sus 10 citas
    	 */
		
		return this.mascotas.iterator();
	}
	
	@Override
    public int compareTo(Cliente o) {
		/* Test 01
		 * Implemento el metodo comperTo para poder ordenar los clientes
		 * y así poder usar el metodo "Sort", lo hago en la misma forma 
		 * que el metodo equals.
		 */
		String[] partes1 = o.nombre.split(".- "); 
        Integer o_id =Integer.parseInt( partes1[0]);
        
	    String[] partes2 = this.nombre.split(".- "); 
        Integer this_id =Integer.parseInt( partes2[0]);
        
		return o_id.compareTo(this_id);
	}

	
}