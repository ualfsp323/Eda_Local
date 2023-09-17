package eda1.practicas.practica01;

import eda1.practicas.auxiliar.Format;

import java.util.ArrayList;

public class Cita {
    private static int numCitas = 0; //atención al uso de la palabra clave static
    private final int citaId; //identificador de cita
    private final ArrayList<String> valoraciones; //colección de valoraciones que realiza el profesional durante la cita

    public static void inicializaNumCitas() {
        //Inicialización del atributo estático
        numCitas = 0; //se podría escribir this.numCitas = 0 ?????
    }

    public Cita() {
        //Inicializamos los atributos this.citaId (cada vez que se crea una cita incrementamos en 1 el atributo estático numCitas, empezando por 1) y this.valoraciones
        //2 líneas
    	//...
    }

    public Cita(int citaId) {
        //Igual que el anterior, aunque tenemos en cuenta que el identificador de cita se pasa como parámetro; En este caso no hay que asignar espacio a this.valoraciones (== null)
        //2 líneas
    	//...
    }	

    public int getCitaId() {
        return this.citaId;
    }

    public boolean addValoracion(String valoracion) {
        //Devolvemos false si el parámetro valoracion es nulo o está vacío
        //En caso contrario añadimos la valoración a nuestra colección (cuidado con las mayúsculas y minúsculas, así como los espacios iniciales y finales de la cadena)
    	//Consulta el uso de los métodos toLowerCase() y trim()
        //...
        return true;
    }

    public boolean contienePalabra(String palabra){
        //Devolvemos false si el parámetro palabra es nulo o está vacío
        //Cuidado con las mayúsculas y minúsculas, así como los espacios iniciales y finales de la cadena
        //1 for() tipo forEach ¿+ break?
        //...
        return false;
    }

    public void clear() {
        this.valoraciones.clear();
    }

    @Override
    public String toString() {
        //Observad el formato de salida que muestra el test: #0001, #0002. Se le añaden tantos ceros a la izquierda como sean necesarios hasta rellenar una cadena con 4 dígitos
        //Para temas de formato vamos a hacer uso de los métodos de la clase Format que encontraréis en la carpeta auxiliar; 
        //Escribe correctamente la siguiente línea
        return "Cita #" + Format.f... + " -> " + ( ?  : );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; //Si this y o son sinónimos (apuntan al mismo objeto), está claro que son iguales
        if (!(o instanceof  Cita)) return false; //Si el parámetro no es de tipo Cita, devuelve false
        //2 citas son iguales sii (si y solo si) sus identificadores son idénticos
        //Completa la siguiente línea
        return //...
    }
}	