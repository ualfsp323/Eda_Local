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
        //Atención al formato del identificador de Cliente (this.nombre). Observa el test...
    	//El nombre del cliente será el identificador (con un formato de 5 dígitos) + .- + el valor del parámetro nombre (id.- nombre)
        //Si el parámetro nombre es nulo o está vacío, el cliente se llamará "id.- sinNombre"
    	//completa la siguiente línea
        this.nombre = Format... + ".- " + ( ?  :);
        this.mascotas = new ArrayList<>();
    }

    public boolean addMascota(String nombre, String especie) {
        //Devolvemos false si la mascota ya existe en this.mascotas; en caso contrario, se añade y fin (true)
        //Hacemos uso de indexOf()
        //4 líneas
        //...
    }

    public Cita addCita(String nombre, String especie) {
        //Se añade una nueva cita (devolviendo su referencia)
        //Si este cliente no tiene la mascota con clave (nombre, especie) --> se devuelve null
        //2 líneas
        //...
    }

    public void clear() {
        //1 for() 
        //...
        this.mascotas.clear();
    }

    public int size() {
        return this.mascotas.size();
    }

    public ArrayList<ArrayList<Integer>> getCitasId(){
    	//Hacemos uso de estas dos variables locales
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> aux;
        //2 for() anidados tipo forEach
        //En el primer for() se itera sobre las mascotas y, para cada mascota, se itera sobre las citas
        //...
        return result;
    }

    public ArrayList<ArrayList<Integer>> getCitasId(String palabra) {
    	//Hacemos uso de estas dos variables locales
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> aux;
        //2 for() anidados tipo forEach
        //Muy similar al anterior, solo que en este caso queremos únicamente las citas que incluyan entre sus valoraciones la palabra que se indica como parámetro de entrada
        //...
        return result;
    }

    public ArrayList<Integer> getCitasId(String nombre, String especie){
        //Si la mascota con clave (nombre, especie) no existe, se devuelve null;
        ArrayList<Integer> result = new ArrayList<>();
        //1 for() tipo forEach
        //...
        return result;
    }

    public Cita getCita(int citaId){
        Cita result = null;
        //1 for()
        //Interesa el uso de break, ¿verdad?
        //...
        return result;
    }

    public Mascota getMascota(int citaId){
        //1 for() con 1 único if
        //...
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