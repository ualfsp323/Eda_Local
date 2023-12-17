package eda1.practicas.prueba01;

import java.util.ArrayList;
import eda1.practicas.auxiliar.AVLTree;
import eda1.practicas.auxiliar.Par;

public class ArrayListPar_vs_TreePar {
	
	private ArrayList<Par<String, AVLTree<Integer>>> array;
	private AVLTree<Par<String, ArrayList<Integer>>> arbol;
	
	public ArrayListPar_vs_TreePar() {
		this.array = new ArrayList<>();
		this.arbol = new AVLTree<>();
	}
	
	public void addArbol(String clave, Integer...datos) {
		Par<String, ArrayList<Integer>> current = this.arbol.find(new Par<>(clave, null));
		if (current == null) {
			this.arbol.add(current = new Par<>(clave, new ArrayList<>()));
		}
		for (Integer dato: datos) {
			if (current.getValue().contains(dato)) continue;
			current.getValue().add(dato);
		}
		current.getValue().sort(null);
	}
	
	public void addArray(String clave, Integer... datos) {
/*
		Par<String, AVLTree<Integer>> current = null;

		for (Par<String, AVLTree<Integer>> par : this.array) {
			if (par.getKey().equals(clave)) {
				current = par;
				break;
			}
		}	
		if (current == null) {
			this.array.add(current = new Par<>(clave, new AVLTree<>()));
		}
*/
		 int existe=-1;
		 for (int i = 0; i < this.array.size(); i++) {
		        if (this.array.get(i).getKey().equals(clave)) {
		        	existe= i;
		        }
		    }  
		 
		 if (existe == -1) {
		        this.array.add(new Par<>(clave, new AVLTree<>()));
		        existe = this.array.size() - 1;
		    }

		for (Integer dato : datos) {
			if ( this.array.get(existe).getValue().contains(dato))continue;
			 this.array.get(existe).getValue().add(dato);
		}
		array.sort(null);
	}

	public String arbolToString() {
		return this.arbol.toString();
	}
	
	public String arrayToString() {
		return this.array.toString();
	}
	
	public static void main(String[] args) {
		ArrayListPar_vs_TreePar prueba = new ArrayListPar_vs_TreePar();
		
		//Insertamos elementos en el árbol
		prueba.addArbol("clave02", 0, 5, 2, 3, 8, 4, 4, 5);
		prueba.addArbol("clave01", 3, 1, 2, 4, 2, 4);
		prueba.addArbol("clave02", 0, 8, 6, 7, 8);
		prueba.addArbol("clave01", 1, 5, 3, 5, 8);
		//Igual pero con el array
		prueba.addArray("clave02", 0, 5, 2, 3, 8, 4, 4, 5);
		prueba.addArray("clave01", 3, 1, 2, 4, 2, 4);
		prueba.addArray("clave02", 0, 8, 6, 7, 8);
		prueba.addArray("clave01", 1, 5, 3, 5, 8);
	
		System.out.println(prueba.arbolToString());
		System.out.println(prueba.arrayToString());
	
		System.out.println(prueba.arbolToString().equals(prueba.arrayToString()) ? "¡¡¡OK!!!" : "¡¡¡Error!!!");
	}

}
