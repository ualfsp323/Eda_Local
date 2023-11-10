package eda1.practicas.practica02.parte02;

import java.util.ArrayList;

public class Cliente {
    private final String nombre;
    private final ArrayList<Par<Mascota, ArrayList<ArrayList<String>>>> data;

    public Cliente(String nombre) {
        this.nombre = nombre.trim().toLowerCase();
        this.data = new ArrayList<>();
    }

    public boolean addMascota(String nombre, String especie) {
        Par<Mascota, ArrayList<ArrayList<String>>> mascotaAux = new Par<>(new Mascota(nombre, especie), new ArrayList<>());
        for (Par<Mascota, ArrayList<ArrayList<String>>> mascotaCurr : data) {
            if (mascotaCurr.getKey().equals(mascotaAux.getKey())) {
                return false; // Mascota ya existe
            }
        }
        return data.add(mascotaAux);
    }

    public Integer addCita(String nombre, String especie) {
        Par<Mascota, ArrayList<ArrayList<String>>> mascotaCurr = findMascota(nombre, especie);
        if (mascotaCurr == null) return null;
        ArrayList<String> citas = new ArrayList<>();
        mascotaCurr.getValue().add(citas);
        return mascotaCurr.getValue().size() - 1;
    }

    public boolean addValoracion(String nombre, String especie, int citaId, String valoracion) {
        Par<Mascota, ArrayList<ArrayList<String>>> mascotaCurr = findMascota(nombre, especie);
        if (mascotaCurr == null || citaId < 0 || valoracion == null || valoracion.isEmpty() || (citaId >= mascotaCurr.getValue().size()))
            return false;
        ArrayList<String> citas = mascotaCurr.getValue().get(citaId);
        citas.add(valoracion);
        return true;
    }

    public ArrayList<String> getValoraciones(String nombre, String especie, int citaId) {
        if (citaId < 0) return null;
        Par<Mascota, ArrayList<ArrayList<String>>> mascotaCurr = findMascota(nombre, especie);
        if (mascotaCurr == null || citaId >= mascotaCurr.getValue().size()) return null;
        return mascotaCurr.getValue().get(citaId);
    }

    public void clear() {
        data.clear();
    }

    public int size() {
        return this.data.size();
    }

    @Override
    public String toString() {
        return this.nombre + " -> " + (this.data.isEmpty() ? "<>" : this.data.toString());
    }

    private Par<Mascota, ArrayList<ArrayList<String>>> findMascota(String nombre, String especie) {
        for (Par<Mascota, ArrayList<ArrayList<String>>> mascotaCurr : data) {
            if (mascotaCurr.getKey().equals(new Mascota(nombre, especie))) {
                return mascotaCurr;
            }
        }
        return null;
    }
}
