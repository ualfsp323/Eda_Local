package eda1.practicas.practica02.parte02;

import java.util.ArrayList;
import java.util.Iterator;

import eda1.practicas.auxiliar.AVLTree;
import eda1.practicas.auxiliar.Par;

public class GestionClientes implements Iterable<Par<String, ArrayList<Par<String, Integer>>>> { 
	private final AVLTree<Par<String, AVLTree<Par<Mascota, ArrayList<ArrayList<String>>>>>> data;

	public GestionClientes() {
		this.data = new AVLTree<>();
	}

	public boolean addCliente(String clienteId) {
		Par<String, AVLTree<Par<Mascota, ArrayList<ArrayList<String>>>>> clientePar = new Par<>(clienteId, new AVLTree<>());
		return  this.data.add(clientePar);
	}

	public boolean addMascota(String clienteId, String mascotaId, String especieId) {
		Par<String, AVLTree<Par<Mascota, ArrayList<ArrayList<String>>>>> clientePar = this.data.find(new Par<>(clienteId, null));
		if (clientePar != null) {
			Par<Mascota, ArrayList<ArrayList<String>>> mascota = new Par<>(new Mascota(mascotaId, especieId),new ArrayList<>());
			clientePar.getValue().add(mascota);
			return true;
		}
		return false;
	}

     public Integer addCita(String clienteId, String mascotaId, String especieId) {
    	 Par<String, AVLTree<Par<Mascota, ArrayList<ArrayList<String>>>>> clientePar = data.find(new Par<>(clienteId, null));
         if (clientePar == null)  return null;
       	 Par<Mascota, ArrayList<ArrayList<String>>> mascotaPar = clientePar.getValue().find(new Par<>(new Mascota(mascotaId, especieId), null));
         if (mascotaPar == null)  return null;
         ArrayList<String> citas = new ArrayList<>();
       	 mascotaPar.getValue().add(citas); 
       	 return mascotaPar.getValue().size() - 1;
    }

     public boolean addValoracion(String clienteId, String mascotaId, String especieId, int citaId, String valoracion) {
         Par<String, AVLTree<Par<Mascota, ArrayList<ArrayList<String>>>>> clientePar = data.find(new Par<>(clienteId, null));
       	 Par<Mascota, ArrayList<ArrayList<String>>> mascotaPar = clientePar.getValue().find(new Par<>(new Mascota(mascotaId, especieId), null));
         if (clientePar == null || mascotaPar == null || citaId <  0 || valoracion == null || valoracion.isEmpty()|| (citaId >= mascotaPar.getValue().size())) return false;
         ArrayList<String> citas = mascotaPar.getValue().get(citaId);
         citas.add(valoracion);
         return true;
	}
	
	 public int size() {
      	  	return this.data.size();
    	}

	 /*Creamos el iterator que nos piden */
	@Override
	public Iterator<Par<String, ArrayList<Par<String, Integer>>>> iterator() {
		AVLTree<Par<String, ArrayList<Par<String, Integer>>>> result = new AVLTree<>();	 // Creamos la estructura AVLtree que nos interesa para luego guardar los datos que nos piden en ella
		for (Par<String, AVLTree<Par<Mascota, ArrayList<ArrayList<String>>>>> clientePar : data) {	// Iteramos sobre los datos y agregamos los pares (clienteId, lista de mascotas) al AVLTree
			String clienteId = clientePar.getKey(); // Guardamos el cliente en un String, Seria la clave del primer Par.
			ArrayList<Par<String, Integer>> mascotas = new ArrayList<>(); // Construimos la estructura value del primer Par
			for (Par<Mascota, ArrayList<ArrayList<String>>> mascotaPar : clientePar.getValue()) { // Iteramos sobre las mascotas y agregamos los pares (mascotaId, cantidad de citas) al AVLTree
				String mascotaId = mascotaPar.getKey().toString();
				int cantidadCitas = mascotaPar.getValue().size();
				mascotas.add(new Par<>(mascotaId, cantidadCitas)); // Con esta linea de codigo añadimos los valores del segundo Par
			}
			result.add(new Par<>(clienteId, mascotas)); // Añadimos los datos que hemos estructurado en la variable result
		}
		return result.iterator();
	}

	public void clear() {
		data.clear();
	}
}
