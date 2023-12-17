package eda1.practicas.practica03.parte01;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class GestionEmpresas {

	private final TreeMap<String, TreeMap<String, TreeSet<String>>> datos = new TreeMap<>();

	public boolean load(String fileName) {
		Scanner scan = null;
		this.datos.clear();
		try {
			scan = new Scanner(new File(fileName));
		} catch (IOException e) {
			return false;
		}
		while (scan.hasNextLine()) {
			String line = scan.nextLine().trim();
	        if (line.isEmpty() || line.startsWith("@")) continue;
	        String[] parts = line.split(" - ");
	        if (parts.length == 3) {
	            add(parts[0],  parts[1], parts[2]);
	        }
		}
		scan.close();
		return true;
	}

	public boolean add(String empresaId, String proyectoId, String ciudad) {
		TreeMap<String, TreeSet<String>> proyCurr = datos.get(empresaId);
		if (proyCurr == null) datos.put(empresaId, proyCurr= new TreeMap<>());
		TreeSet<String> ciudadesCurr = proyCurr.get(proyectoId);
		if (ciudadesCurr == null) proyCurr.put(proyectoId, ciudadesCurr= new TreeSet<>());		
		return ciudadesCurr.add(ciudad);
	}

	public int size() {
		return this.datos.size();
	}

	public Integer getNumProyectosEmpresa(String empresaId) {
		TreeMap<String, TreeSet<String>> proyectoCurr =  datos.get(empresaId);
		return (proyectoCurr == null) ? null :proyectoCurr.size();
	}

	public Integer getNumCiudadesProyecto(String proyectoId) {
		int count = 0;
		for (TreeMap<String, TreeSet<String>> proyectos : datos.values()) {
			if (proyectos.containsKey(proyectoId)) {
				count = proyectos.get(proyectoId).size();
			}
			
		}
		return (count ==0)? null :count;
	}

	public Integer getNumCiudadesEmpresa(String empresaId) {
		int count = 0;

		TreeMap<String, TreeSet<String>> proyectos = datos.get(empresaId);
		if (proyectos == null)return null;
		TreeSet<String> todasLasCiudades = new TreeSet<>();
		for (TreeSet<String> ciudades : proyectos.values()) {
			todasLasCiudades.addAll(ciudades);
		}
		count = todasLasCiudades.size();

		return count;

	}

	@Override
	public String toString() {
		String result = "";

	    for (Entry<String, TreeMap<String, TreeSet<String>>> empresaEntry : datos.entrySet()) {
	        TreeMap<String, TreeSet<String>> proyectos = empresaEntry.getValue();
	        result += empresaEntry.getKey() + " -> [";
	        
	        for (Entry<String, TreeSet<String>> proyectoEntry : proyectos.entrySet()) {
	            result += proyectoEntry.getKey() + ": " + proyectoEntry.getValue() + ", ";
	        }
	        if (result.endsWith(", ")) result = result.substring(0, result.length() - 2);
	        result += "]\n";
	    }
		return result;
	}

	public TreeSet<String> getEmpresasCiudad(String ciudad) {
		TreeSet<String> empresasIdAux = new TreeSet<>();
		for (Entry<String, TreeMap<String, TreeSet<String>>> empresaEntry : datos.entrySet()) {
			TreeMap<String, TreeSet<String>> proyectos = empresaEntry.getValue();
			for (TreeSet<String> ciudades : proyectos.values()) {
				if (ciudades.contains(ciudad)) {
					empresasIdAux.add(empresaEntry.getKey());
					break;
				}
			}
		}

		return empresasIdAux;
	}

	public ArrayList<String> getProyectosCiudad(String ciudad) {
		ArrayList<String> proyectosIDaux = new ArrayList<>();
		for (Entry<String, TreeMap<String, TreeSet<String>>> empresaEntry : datos.entrySet()) {
			TreeMap<String, TreeSet<String>> proyectos = empresaEntry.getValue();

			for (Entry<String, TreeSet<String>> proyectoEntry : proyectos.entrySet()) {
				if (proyectoEntry.getValue().contains(ciudad)) {
					proyectosIDaux.add(proyectoEntry.getKey());
				}
			}
		}

		return proyectosIDaux;
	}

	public TreeSet<String> getCiudadesEmpresa(String empresaId) {
		TreeSet<String> ciudadesEmpresa = new TreeSet<>();
		TreeMap<String, TreeSet<String>> proyectos = datos.get(empresaId);
		if (proyectos == null)	return null;
		for (TreeSet<String> ciudades : proyectos.values()) {
			ciudadesEmpresa.addAll(ciudades);
		}
		return ciudadesEmpresa;
	}
}