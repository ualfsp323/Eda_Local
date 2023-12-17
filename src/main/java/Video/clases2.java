package Video;

import eda1.practicas.practica02.parte02.Cliente;

public class clases2 extends Cliente{

	public clases2(String nombre) {
		super(nombre);
	}

	@Override
	public String toString() {
 
		return this.nombre + " -> " + (this.data.isEmpty() ? "<>" : this.data.toString());
	}

}
