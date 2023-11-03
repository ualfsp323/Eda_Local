package eda1.practicas.practica02.parte03;


import eda1.practicas.auxiliar.Format;
import java.util.ArrayList;

public class GestionEstudiantes {
    private final String centroId;
    private final MyTreeMap<String, MyTreeMap<String, ArrayList<Double>>> matriculas; //Clave prinicpal: estudianteId; clave secundaria: asignaturaId

    public GestionEstudiantes(String centroId) {
        this.centroId = centroId.trim(); 
        this.matriculas = new MyTreeMap<>();
    }

    public void clear() {
        this.matriculas.clear();
    }
    
    
	public boolean addNotas(String estudianteId, String asignaturaId, Double... notas) {
		MyTreeMap<String, ArrayList<Double>> notasAsignaturas = this.matriculas.get(estudianteId);
		if (notasAsignaturas == null) {
			this.matriculas.put(estudianteId, notasAsignaturas = new MyTreeMap<>());

		}
		ArrayList<Double> notasAsignatura = notasAsignaturas.get(asignaturaId);
		if (notasAsignatura == null) {

			notasAsignaturas.put(asignaturaId, notasAsignatura = new ArrayList<>());

		}
		for (Double nota : notas) {
			notasAsignatura.add((nota == null) ? .0 : nota);
		}
		return true;
	}

    public String getNotaMedia(String estudianteId) {
        MyTreeMap<String, ArrayList<Double>> notasAsignaturas = this.matriculas.get(estudianteId);
        if (notasAsignaturas == null)  return null;
        
        double suma = 0.0;
        int cont = 0;

        for (ArrayList<Double> listaNotas : notasAsignaturas.valueSet()) {
            for (Double nota : listaNotas) {
                suma += nota;
                cont++;
            }
        }
        return Format.formatDouble(suma/cont);
    }

	public String getNotaMedia(String estudianteId, String asignaturaId) {
		MyTreeMap<String, ArrayList<Double>> notasAsignaturas = this.matriculas.get(estudianteId);
		if (notasAsignaturas == null || !notasAsignaturas.containsKey(asignaturaId))return null;
		ArrayList<Double> notas = notasAsignaturas.get(asignaturaId);
		double suma = 0.0;

		for (Double nota : notas) {
			suma += nota;
		}

		return Format.formatDouble(suma / notas.size());
	}

    public String getNotaMediaAsignatura(String asignaturaId) {
    	int cont = 0;
    	double suma = .0;
        for (MyTreeMap<String, ArrayList<Double>> notasAsignaturas : this.matriculas.valueSet()) {
            if (notasAsignaturas.containsKey(asignaturaId)) {
                ArrayList<Double> notasAsignatura = notasAsignaturas.get(asignaturaId);
                
                for (Double nota : notasAsignatura) {
                    suma += nota;
                    cont++;
                }
            }
        }
        
        return cont == 0 ? null : Format.formatDouble(suma/cont);
    }
 
    @Override
    public String toString() {
        return this.centroId;
    }
}