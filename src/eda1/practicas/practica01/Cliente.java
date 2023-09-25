package eda1.practicas.practica01;

import eda1.practicas.auxiliar.Format;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Cliente {
    private static int numClientes = 0;
    private final String nombre;
    private final ArrayList<Mascota> mascotas;

    public static void inicializaNumClientes() {
        numClientes = 0;
    }

    public Cliente(String nombre) {
        this.nombre = Format.formatInt(++numClientes, 5)+ ".- " + ( nombre==null ? "sinNombre" :nombre);
        this.mascotas = new ArrayList<>();
    }

    public boolean addMascota(String nombre, String especie) {
        //Devolvemos false si la mascota ya existe en this.mascotas; en caso contrario, se añade y fin (true)
        //Hacemos uso de indexOf()
        //4 líneas
        //...
    	if(this.mascotas.indexOf(nombre)!=-1 && this.mascotas.indexOf(especie)!=-1)return false;
    	mascotas.add(new Mascota(nombre, especie));
    	return true;
    	    
    }

    public Cita addCita(String nombre, String especie) {
        Mascota mascota = null;
        return ( mascotas.contains(nombre) && mascotas.contains(especie)) ? mascota.addCita() : null;

        //Se añade una nueva cita (devolviendo su referencia)
        //Si este cliente no tiene la mascota con clave (nombre, especie) --> se devuelve null
        //2 líneas
        //...
    }

	public void clear() {
		int size = size();
		for (int i = 0; i < size; i++) {
			Mascota mascota = mascotas.get(i);
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
        //Si la mascota con clave (nombre, especie) no existe, se devuelve null;
        ArrayList<Integer> result = new ArrayList<>();
        for (Mascota mascota : mascotas) {
            if () {
                for (Cita cita : mascota) {
                    result.add(cita.getCitaId());
                }
                return result;
            }
        }
        return null; // 
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
    	     if (cita.getCitaId() == citaId) {
    	         return mascota;
    	     }  
    	 }
    	 return null;
    }

    @Override
    public String toString() {
        return this.nombre + " -> " + this.mascotas.toString();
    }

    public String toStringExtended() {
        String result = this.nombre + " -> {";
        //1 for() tipo forEach
        //Hacemos uso del método toStringExtended() de mascota
        //Cuidado con la última coma...
        //...
        for (Mascota mascota : mascotas) {
            result+=mascota.toStringExtended()+", "; 
            if (!mascotas.isEmpty()) {
            result+=mascota.toStringExtended();
           }
        }
        return result + "}";
    }

    public boolean load(String nombreArchivo) {
        //Repasa el código que se proporciona; es esencial que lo entiendas a la perfección, línea a línea...
    	Scanner scan;
        try {
            scan = new Scanner(new File(nombreArchivo));
        }catch(Exception e) {
            return false;
        }
        while (scan.hasNextLine()) {
            String linea = scan.nextLine(); //¿Es correcto crear la variable linea dentro del bucle while()?
            if (linea.isEmpty()) continue;
            if (linea.startsWith("%")) continue;
            String[] items = linea.split("[ ]+"); //¿Qué hace, exactamente, el método split()? ¿Qué nos indica la expresión que especificamos como parámetro de entrada "[ ]+"?
            //Cuidado con la posibilidad de que se añada un espacio en blanco en la primera posición
            int posInicial = items[0].isEmpty() ? 1 : 0;
            Mascota mascota = new Mascota(items[posInicial], items[posInicial+1]);
            //Si la mascota ya existe, pasamos a la siguiente línea;
            //En caso contrario, la añadimos a la colección this.mascotas
            //1 for(); tantas como número de citas se indica en el archivo
            //...
        }
        scan.close();
        return true;
    }
}