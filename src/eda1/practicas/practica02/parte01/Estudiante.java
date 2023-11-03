package eda1.practicas.practica02.parte01;

import java.util.*;

import eda1.practicas.auxiliar.Format;
import eda1.practicas.practica01.Cliente;
import eda1.practicas.practica01.Mascota;

public class Estudiante implements Comparable<Estudiante>, Iterable<AsignaturaNotas>{
    private final String alumnoId; 
    private final ArrayList<AsignaturaNotas> matricula;

    public Estudiante(String alumnoId) {
    	this.alumnoId=alumnoId.toLowerCase().trim();
    	this.matricula=new ArrayList<>();

    }

    public void addAsignaturas(Asignatura... asignaturas) {
    	for (Asignatura asignatura : asignaturas) {
    		matricula.add(new AsignaturaNotas(asignatura));
    	}
    }

    public boolean addNotas(String asignaturaId, Double... notas) {
    	int index = matricula.indexOf(new AsignaturaNotas(asignaturaId));
    	if (index >= 0) matricula.get(index).addNotas(notas);
    	return index >= 0;
    }

	public String getNotaMedia() {
        if (matricula.isEmpty()) {
            return "0.00";
        }
		ArrayList<Asignatura> asignaturasProcesadas = new ArrayList<>();

        double suma = 0.00;
        double divisor = 0.00;

        for (AsignaturaNotas asignaturaNota : matricula) {
        	Asignatura asignatura = asignaturaNota.getAsignatura();
			if (!asignaturasProcesadas.contains(asignatura)) {
				asignaturasProcesadas.add(asignatura);
				double nota=Double.parseDouble(asignaturaNota.getNotaMedia());
                suma += nota;
                divisor++;
			}
        }
        
        String media = Format.formatDouble(suma / divisor);
        return media;
    }

	public String getNotaMedia(String asignaturaId) {
    	  int index = this.matricula.indexOf(new AsignaturaNotas(asignaturaId)); 
    	  return (index < 0 )? null: matricula.get(index).getNotaMedia();  
    }

    public void clear() {
       for (AsignaturaNotas asignaturaNotas : matricula) {
    	   asignaturaNotas.clear();
        }
        this.matricula.clear();
    }

    @Override
	public String toString() {
		ArrayList<Asignatura> asignaturasProcesadas = new ArrayList<>();
		this.matricula.sort(new AsignaturaNotasComp());
		
		String result = "Estudiante con id = " + this.alumnoId;

		for (AsignaturaNotas asignaturaNotas : matricula) {
			Asignatura asignatura = asignaturaNotas.getAsignatura();
			if (!asignaturasProcesadas.contains(asignatura)) {
				asignaturasProcesadas.add(asignatura);
				result += "\n" + "\t" + asignaturaNotas.toString();
			}
			
		}
		return result + "\n";

	}

    @Override
    public int compareTo(Estudiante other) {
        return this.alumnoId.compareTo(other.alumnoId);
    }

    @Override
    public Iterator<AsignaturaNotas> iterator() {
        return this.matricula.iterator();
    }
    
}