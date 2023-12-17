 package eda1.practicas.practica03.parte02;

 import java.io.File;
 import java.io.IOException;
 import java.util.Arrays;
 import java.util.Map.Entry;
 import java.util.Scanner;
 import java.util.TreeMap;
 import java.util.TreeSet;

public class GestionRepositorios {
	private final TreeMap<String, TreeMap<String, Integer>> articulos;
	private final TreeMap<String, TreeMap<String, TreeSet<String>>> datos;
	
	public GestionRepositorios() {
		this.articulos = new TreeMap<>();
		this.datos = new TreeMap<>();
	}
 
	public boolean add(String repositorioId, String articuloId, String ...autoresId) {		
		String repositorioExistente = getRepositorio(articuloId);
	    if (repositorioExistente != null && !repositorioExistente.equals(repositorioId)) return false;
	    datos.putIfAbsent(repositorioId, new TreeMap<>());
	    datos.get(repositorioId).putIfAbsent(articuloId, new TreeSet<>());
	    articulos.putIfAbsent(articuloId, new TreeMap<>());

		for (String autor: autoresId) {
		    datos.get(repositorioId).get(articuloId).add(autor);
		} 
		return true;
	}
	
	public String getRepositorio(String articuloId) {
		for (Entry<String, TreeMap<String, TreeSet<String>>>  repositorioEntry : datos.entrySet()) {

	        if (repositorioEntry.getValue().containsKey(articuloId)) {
	            return repositorioEntry.getKey();
	        }
	    }

		return null;
	}

	public boolean loadArticulo(String articuloId, String file) {
		if (!articulos.containsKey(articuloId))return false;
		TreeMap<String, Integer> palabrasFrecuencia = new TreeMap<>();
		Scanner scan = null;
		try {
			scan = new Scanner(new File(file));
		} catch (IOException e) {
			return false;
		}
		while (scan.hasNextLine()) {
			String linea = scan.nextLine();
			if (linea.isEmpty())
				continue;
			for (String palabra : linea.split("[0123456789/(/)+-;,.¿?¡! ]+")) {
				palabra = palabra.toLowerCase().trim();
		        if (!palabra.isEmpty() && !StopWords.isStopWord(palabra)) {
		            palabrasFrecuencia.put(palabra, palabrasFrecuencia.getOrDefault(palabra, 0) +1 );
		        }

			}
		}
	    articulos.put(articuloId, palabrasFrecuencia);
		return true;
	}

	public boolean load(String directorioEntrada, String file) {
		Scanner scan = null;
		String repositorioId = "";
		this.articulos.clear();
		this.datos.clear();

		try {
			scan = new Scanner(new File(directorioEntrada + file));
		} catch (IOException e) {
			return false;
		}

		while (scan.hasNextLine()) {
			String lineIn = scan.nextLine().trim();
			if (lineIn.isEmpty())
				continue;
			if (lineIn.startsWith("%"))
				continue;
			if (lineIn.startsWith("@")) {
				repositorioId = lineIn.substring(1);
				continue;
			}
			String[] items = lineIn.split("[ ]+");
			if (items.length >= 2) {
				add(repositorioId, items[0], Arrays.copyOfRange(items, 2, items.length));
				loadArticulo(items[0], directorioEntrada + items[1]);
			}
	
		}
		scan.close();
		return true;
	}
	
	public TreeSet<String> getArticulosId(String autorId) {
		TreeSet<String> result = new TreeSet<>();
		for (Entry<String, TreeMap<String, TreeSet<String>>> repositorioEntry : datos.entrySet()) {
			TreeMap<String, TreeSet<String>> articulosId = repositorioEntry.getValue();

			for (Entry<String, TreeSet<String>> articuloEntry : articulosId.entrySet()) {
				if (articuloEntry.getValue() != null && articuloEntry.getValue().contains(autorId)) result.add(articuloEntry.getKey());
			}
		}
		return result;
	}

	public TreeSet<String> getCoAutores(String autorId) {
		TreeSet<String> result = new TreeSet<>();
		for (Entry<String, TreeMap<String, TreeSet<String>>> repositorioEntry : datos.entrySet()) {
			TreeMap<String, TreeSet<String>> articulosId = repositorioEntry.getValue();

			for (Entry<String, TreeSet<String>> articuloEntry : articulosId.entrySet()) {
				if (articuloEntry.getValue() != null && articuloEntry.getValue().contains(autorId)) {
	                TreeSet<String> subAutores = new TreeSet<>(articuloEntry.getValue());
	                subAutores.remove(autorId);
	                result.addAll(subAutores);
				}
			}
		}
		return result;
	}

	public TreeSet<String> getPalabrasClave(String autorId, int minFrec) {
		TreeMap<String, Integer> macroPalabrasFrecuencia = new TreeMap<>();
		TreeSet<String> resultado = new TreeSet<>();

	    for (Entry<String, TreeMap<String, TreeSet<String>>> repositorioEntry : datos.entrySet()) {
	        TreeMap<String, TreeSet<String>> articulosId = repositorioEntry.getValue();

	        for (Entry<String, TreeSet<String>> articuloEntry : articulosId.entrySet()) {

	            if (articuloEntry.getValue() != null && articuloEntry.getValue().contains(autorId)&& articulos.get(articuloEntry.getKey()) != null) {
	            	for (Entry<String, Integer> palabraEntry : articulos.get(articuloEntry.getKey()).entrySet()) {	             
	            		macroPalabrasFrecuencia.put(palabraEntry.getKey(), macroPalabrasFrecuencia.getOrDefault(palabraEntry.getKey(), 0) + palabraEntry.getValue());
	                 } 
	            }
	        }
	    }
	    
	    for (Entry<String, Integer> palabraEntry : macroPalabrasFrecuencia.entrySet()) {
	        if (palabraEntry.getValue() >= minFrec) resultado.add( palabraEntry.getKey()+"="+palabraEntry.getValue());
	    }
	    
	    return resultado;
	}
	
	public int size() {
		return this.datos.size();
	}
	
	public void clear() {
		this.articulos.clear();
		this.datos.clear();
	}
	
	@Override			
	public String toString() {
		String result = "";
	    for (Entry<String, TreeMap<String, TreeSet<String>>> repositorioEntry : datos.entrySet()) {
			result += repositorioEntry.getKey() + "\n";
	        TreeMap<String, TreeSet<String>> articulosId = repositorioEntry.getValue();

	        for (Entry<String, TreeSet<String>> articuloEntry : articulosId.entrySet()) {
	            
	            result += "\t" + articuloEntry.getKey() + " <" + articulos.get(articuloEntry.getKey()).size()+ " palabras> [";

				if (datos.get(repositorioEntry.getKey()).get(articuloEntry.getKey()) != null) {
					result += String.join(", ", datos.get(repositorioEntry.getKey()).get(articuloEntry.getKey()));
				}

				result += "]\n";
			}
		}

		return result;
	}
}