package eda1.practicas.practica02.parte02;

import java.util.ArrayList;
import java.util.Iterator;

import eda1.practicas.auxiliar.AVLTree;
import eda1.practicas.auxiliar.Format;
import eda1.practicas.auxiliar.Par;

public class GestionClientes implements Iterable<Par<String, ArrayList<Par<String, Integer>>>> { 
	private final AVLTree<Cliente> data;
    private final ArrayList<Mascota> mascotas;

	public GestionClientes() {
		this.data = new AVLTree<>();
        this.mascotas = new ArrayList<>();


	}

	public boolean addCliente(String clienteId) {
		Cliente cliente = new Cliente(clienteId);
		return (cliente!=null)?this.data.add(cliente) :false;
	}

	public boolean addMascota(String clienteId, String mascotaId, String especieId) {
		Cliente cliente = data.find(new Cliente(clienteId));
		getEspecie(mascotaId, especieId);
		return (cliente != null) ? cliente.addMascota(mascotaId, especieId) : false;
	}

	public Integer addCita(String clienteId, String mascotaId, String especieId) {
		Cliente cliente = data.find(new Cliente(clienteId));
	    return (cliente != null) ? cliente.addCita(mascotaId, especieId) : null;

	}

	public boolean addValoracion(String clienteId, String mascotaId, String especieId, int citaId, String valoracion) {
		 Cliente cliente = data.find(new Cliente(clienteId));
	     return (cliente != null) ? cliente.addValoracion(mascotaId, especieId, citaId, valoracion) : false;

	}
    public void getEspecie(String mascotaId, String especieId) {
    	 Mascota mascota = new Mascota(mascotaId, especieId);
    	    if (mascota != null && !mascotas.contains(mascota)) {
    	        mascotas.add(mascota);
    	    }
    }

	public int size() {
		return this.data.size();
	}


	@Override
	public Iterator<Par<String, ArrayList<Par<String, Integer>>>> iterator() {
	    AVLTree<Par<String, ArrayList<Par<String, Integer>>>> result = new AVLTree<>();
	    int id=0;
	    for (Cliente cliente : data) {
	    	id++;
	        ArrayList<Par<String, Integer>> mascotasInfo = new ArrayList<>();
	        for (Mascota mascota : mascotas) {
	            String[] partes = mascota.toString().split("-");
	            mascotasInfo.add(new Par<>(mascota.toString(),  cliente.addCita(partes[0], partes[1])));
	        }

	        result.add(new Par<>("cliente" + Format.formatInt(id, 3), mascotasInfo));
	    }

	    return result.iterator();
	}

	public void clear() {
		data.clear();
	}
}
