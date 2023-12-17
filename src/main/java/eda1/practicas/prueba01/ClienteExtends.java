package eda1.practicas.prueba01;

import java.util.ArrayList;

import eda1.practicas.auxiliar.Format;
import eda1.practicas.auxiliar.Par;
import eda1.practicas.practica02.parte02.Cliente;
import eda1.practicas.practica02.parte02.Mascota;

public class ClienteExtends extends Cliente{

	public ClienteExtends(String nombre) {
		super(nombre);
	}
	
	/* Iteramos sobre  data que contiene información sobre las mascotas y sus citas
	 * y luego iteramos por los values del par de data, es donde contiene la información que nos interesa
	 * creamos la variable local totalValoraciones para poner todas las citas que tiene una mascota con citas.size
	 * por ultimo guardamos la informacion del la mascota con sus citas en el resul
	 * */
	public String resume() {
		String result = "[";
		for (Par<Mascota, ArrayList<ArrayList<String>>> mascotaCurr :super.data) {
			int totalValoraciones = 0;
			for (ArrayList<String> citas : mascotaCurr.getValue()) {
				totalValoraciones += citas.size();
			}
			result += mascotaCurr.getKey().toString() + " <" + totalValoraciones + ">, ";
		}
		if (result.length() > 1) {
			result = result.substring(0, result.length() - 2); 
		}
		result += "]";
		return result.toString();
	}

	public static void main(String[] args) {
		ClienteExtends cliente = new ClienteExtends("pepe");
		cliente.addMascota("mascota03", "especie01");
		cliente.addMascota("mascota01", "especie02");
		cliente.addMascota("mascota04", "especie01");
		cliente.addMascota("mascota02", "especie01");
		
		for (int i=0; i<10; i++) {
			cliente.addCita("mascota01", "especie02");
			for (int j=0; j<10; j++) {
				cliente.addValoracion("mascota01", "especie02", i, "valoracion" + Format.formatInt(j));
			}
			cliente.addCita("mascota02", "especie01");
			for (int j=0; j<20; j++) {
				cliente.addValoracion("mascota02", "especie01", i, "valoracion" + Format.formatInt(j));
			}
			cliente.addCita("mascota03", "especie01");
			for (int j=0; j<30; j++) {
				cliente.addValoracion("mascota03", "especie01", i, "valoracion" + Format.formatInt(j));
			}
			cliente.addCita("mascota04", "especie01");
			for (int j=0; j<40; j++) {
				cliente.addValoracion("mascota04", "especie01", i, "valoracion" + Format.formatInt(j));
			}
		}

		String salidaEsperada = "[mascota01-especie02 <100>, mascota02-especie01 <200>, mascota03-especie01 <300>, mascota04-especie01 <400>]";

		cliente.resume();
		
		System.out.println(salidaEsperada.equals(cliente.resume()) ? "¡¡¡OK!!!" : "¡¡¡Error!!!");
	}
}
