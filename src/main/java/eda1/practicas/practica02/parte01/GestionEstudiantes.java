package eda1.practicas.practica02.parte01;


import eda1.practicas.auxiliar.AVLTree;
import eda1.practicas.auxiliar.Format;
import eda1.practicas.auxiliar.Par;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

public class GestionEstudiantes implements Iterable<Par<Integer, ArrayList<Par<String, String>>>> {
    private final String centroId; 
    private final AVLTree<Asignatura> asignaturasOfertadas;
    private final AVLTree<Estudiante> estudiantesMatriculados;

    public GestionEstudiantes(String centroId) {
        this.centroId = centroId.trim();
        this.asignaturasOfertadas = new AVLTree<>();
        this.estudiantesMatriculados = new AVLTree<>();
    }

    public void clear() {
        this.asignaturasOfertadas.clear();
        this.estudiantesMatriculados.clear();
    }

    public void addAsignaturas(Asignatura... asignaturas) {
        for (Asignatura asignatura: asignaturas) {
        	asignaturasOfertadas.add(asignatura);
        }
    }

    public void addEstudiantes(Estudiante... estudiantes){
        for (Estudiante estudiante: estudiantes) {
        	estudiantesMatriculados.add(estudiante);
        }
    }

    public boolean addMatricula(String estudianteId, String...asignaturasId) {
         Estudiante estudiante = this.estudiantesMatriculados.find(new Estudiante(estudianteId));
         if (estudiante == null) return false;

         for (String asignaturaId : asignaturasId) {
             Asignatura asignatura = this.asignaturasOfertadas.find(new Asignatura(asignaturaId));
             if (asignatura != null) {
                 estudiante.addAsignaturas(asignatura);
             }
         }
        return true;
    }

    public boolean addNotas(String estudianteId, String asignaturaId, Double... notas){
        Estudiante estudianteCurr = estudiantesMatriculados.find(new Estudiante(estudianteId));
        return (estudianteCurr==null)? false : estudianteCurr.addNotas(asignaturaId, notas);
    }

    public String getNotaMedia(String estudianteId) {
        Estudiante estudianteCurr = estudiantesMatriculados.find(new Estudiante(estudianteId));
        return (estudianteCurr==null)? null : estudianteCurr.getNotaMedia();
    }

    public String getNotaMedia(String estudianteId, String asignaturaId) {
    	Estudiante estudianteCurr = estudiantesMatriculados.find(new Estudiante(estudianteId));
        return (estudianteCurr==null)? null : estudianteCurr.getNotaMedia(asignaturaId);
    }

	public String getNotaMediaAsignatura(String asignaturaId) {
		int cont = 0;
		double suma = 0.0;
		for (Estudiante estudiante : estudiantesMatriculados) {
			String notaMedia = estudiante.getNotaMedia(asignaturaId);
			if (notaMedia != null) {
				suma += Double.parseDouble(notaMedia);
				cont++;
			}
		}
		return cont == 0 ? null : Format.formatDouble(suma / cont);
	}


	public String getEquipoDocenteEstudiante(String estudianteId) {
		ArrayList<String> result = new ArrayList<>();
		Estudiante estudiante = estudiantesMatriculados.find(new Estudiante(estudianteId));
		if (estudiante == null) return "[]";
		
		for (AsignaturaNotas asignaturaNotas : estudiantesMatriculados.find(new Estudiante(estudianteId))) {
			for (String asignatura :  asignaturaNotas.getAsignatura()) {
				if (!result.contains(asignatura)) {
					result.add(asignatura);
				}
			}
		}
		Collections.sort(result);
		return result.toString();
	}

	public boolean load(String fileName) {
		Scanner scan;
		String line;
		String[] items;
		this.asignaturasOfertadas.clear();
		this.estudiantesMatriculados.clear();
		try {
			scan = new Scanner(new File(fileName));
		} catch (IOException e) {
			return false;
		}
		while (scan.hasNextLine()) {
			line = scan.nextLine().trim();
			if (line.isEmpty())
				continue;
			if (line.startsWith("#"))
				continue;
			if (line.startsWith("@")) {
				items = line.split("[ ]+");
				switch (items[0]) {
				case "@Asignaturas":
					int ciclos = Integer.parseInt(items[1]);
					for (int i = 1; i <= ciclos; i++) {
						line = scan.nextLine().trim();
						items = line.split("[ ]+");
						String asignaturaId = items[0].trim();
						int cuatrimestre = Integer.parseInt(items[1].trim());
						String[] docentesArray = Arrays.copyOfRange(items, 2, items.length);
						Asignatura asignatura = new Asignatura(asignaturaId, cuatrimestre);
						asignaturasOfertadas.add(asignatura);
						asignatura.addDocentes(docentesArray);
					}
					break;
				case "@Estudiantes":
					ciclos = Integer.parseInt(items[1]);
					for (int i = 1; i <= ciclos; i++) {
						line = scan.nextLine().trim();
						items = line.split("[ ]+");
						String estudianteID = items[0].trim();
						Estudiante estudiante = new Estudiante(estudianteID);
						estudiantesMatriculados.add(estudiante);
					}
					break;
				case "@Matriculas":
					ciclos = Integer.parseInt(items[1]);
					for (int i = 1; i <= ciclos; i++) {
						line = scan.nextLine().trim();
						items = line.split("[ ]+");
						String estudianteId = items[0];
						String[] asignaturasMatriculadas = Arrays.copyOfRange(items, 1, items.length);
						for (int j = 0; j < asignaturasMatriculadas.length; j++) {
							addMatricula(estudianteId, asignaturasMatriculadas[j]);
						}
					}
					break;
				case "@Notas":
					ciclos = Integer.parseInt(items[1]);
					for (int i = 1; i <= ciclos; i++) {
						line = scan.nextLine().trim();
					    items = line.split("\\s+"); 
						String estudianteId = items[0];
						String asignaturaId = items[1];
						String[] notasArray = Arrays.copyOfRange(items, 2, items.length);
						Double[] notas = new Double[notasArray.length];
						for (int j = 0; j < notasArray.length; j++) {
							notas[j] = Double.parseDouble(notasArray[j].trim());
						}
						addNotas(estudianteId, asignaturaId, notas);
					}
					break;
				default:
					return false;
				}
			}
		}
		scan.close();
		return true;
	}

	@Override
	public String toString() {
		return this.centroId;
	}
	
	/*Creamos el Iterator que nos piden
	 * Como nos piden las notas medias de cada asignatura de su cuatrimestre que les corresponden 
	 * hacemos un for de todos los cuatrimestres que haya, en nuestro caso 7
	 * Empezamos "armando" nuestro Par en un AVL, donde primero creamos la parte del Value que nos interesa
	 * Iteramos sobre todas las asignaturas que hay
	 * Guardamos en una variable el Nº Cuatrimestre que pertenece esa asignatura 
	 * Usamos el metodo getNotaMediaAsignatura para consegir la nota media de esa asignatura 
	 * Si la asignatura no es igual al cuatrimestre que nos interesa, pasamos ala siguiente asignatura con "contunue"
	 * Añadimos los datos al Par<String, String> (asignatura, su nota media)
	 * Añadimos los datos al Par<Integer, ArrayList<Par<String, String>>> (Cuatrimestre, asignaturasPorCuatrimestre)
	 * Devolvemos los datos */
	
	@Override
	public Iterator<Par<Integer, ArrayList<Par<String, String>>>> iterator() {
		AVLTree<Par<Integer, ArrayList<Par<String, String>>>> result = new AVLTree<>();
     
		for (int cuatrimestre = 1; cuatrimestre <= 7; cuatrimestre++) {		    
			ArrayList<Par<String, String>> asignaturasPorCuatrimestre = new ArrayList<>();
			for (Asignatura asignatura : asignaturasOfertadas) {
				int igual = asignatura.getCuatrimestre(); 
				String nota = getNotaMediaAsignatura(asignatura.getAsignaturaId());//
				if (igual != cuatrimestre)continue;  
				asignaturasPorCuatrimestre.add(new Par<>(asignatura.getAsignaturaId(), nota));// 
			}
			result.add(new Par<>(cuatrimestre, asignaturasPorCuatrimestre));
		}

		return result.iterator(); 
	}

}
