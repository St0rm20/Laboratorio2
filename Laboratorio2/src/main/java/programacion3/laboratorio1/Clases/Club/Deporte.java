package programacion3.laboratorio1.Clases.Club;

import programacion3.laboratorio1.Clases.Herramientas.Alerta;
import programacion3.laboratorio1.Clases.Personas.Entrenador;
import programacion3.laboratorio1.Clases.Personas.Miembro;
import programacion3.laboratorio1.Clases.enums.Dificultad;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;

public class Deporte implements Serializable {
    private String nombre;
    private String descripcion;
    private Dificultad dificultad;
    private ArrayList<Entrenador> entrenadores;
    private ArrayList<Miembro> miembros;
    private static final long serialVersionUID = 1L;

    public Deporte(String nombre, String descripcion, Dificultad dificultad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.entrenadores = new ArrayList<>();
        this.miembros = new ArrayList<>();
    }

    public Deporte() {
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Dificultad getDificultad() {
        return dificultad;
    }



    public ArrayList<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public ArrayList<Miembro> getMiembros() {
        return miembros;
    }


    public void aniadirEntrenador(Entrenador entrenador)throws IOException {
        boolean existeEntrenador = validarEntrenadorExiste(entrenador);
        if (!existeEntrenador) {
            entrenadores.add(entrenador);
        }
    }

    public void eliminarEntrenador(Entrenador entrenador){
        entrenadores.remove(entrenador);
    }

    public boolean validarEntrenadorExiste(Entrenador entrenador) throws IOException {
        boolean existeEntrenador =buscarEntrenadorPorNombre(entrenador.getNombre()).isPresent();
        if (existeEntrenador) {
            Alerta.mostrarError("El Entrenador ya existe");
            return true;
        }
        else {
            return false;
        }
    }
    public Optional<Entrenador> buscarEntrenadorPorNombre(String nombre){
        Predicate<Entrenador> condicion = entrenador->entrenador.getNombre().equals(nombre);
        return entrenadores.stream().filter(condicion).findAny();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDificultad(Dificultad dificultad) {
        this.dificultad = dificultad;
    }

    public void setEntrenadores(ArrayList<Entrenador> entrenadores) {
        this.entrenadores = entrenadores;
    }

    public void setMiembros(ArrayList<Miembro> miembros) {
        this.miembros = miembros;
    }

    @Override
    public String toString() {
        return nombre ;
    }
}
