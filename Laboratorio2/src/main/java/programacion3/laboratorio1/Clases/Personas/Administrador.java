package programacion3.laboratorio1.Clases.Personas;

import programacion3.laboratorio1.Clases.Club.Club;
import programacion3.laboratorio1.Clases.Club.Deporte;
import programacion3.laboratorio1.Clases.Club.SesionEntrenamiento;
import programacion3.laboratorio1.Clases.Interfaces.AdministradorDeportes;
import programacion3.laboratorio1.Clases.Interfaces.AdministradorSesiones;
import programacion3.laboratorio1.Clases.enums.Dificultad;

import java.io.IOException;
import java.time.LocalDate;

public class Administrador extends Persona implements AdministradorSesiones, AdministradorDeportes {

    private Club club;

    public Administrador(String nombre, String ID) {
        super(nombre, ID);
        this.club = Club.getInstance();
    }

    @Override
    public void programarSesion(LocalDate fecha, int duracion, Deporte deporte, Entrenador entrenador) throws IOException {
        club.aniadirSesion(fecha, duracion, deporte, entrenador);

    }

    public Club getClub() {
        return club;
    }

    @Override
    public void gestionarFecha(LocalDate fecha, SesionEntrenamiento sesion) {
        sesion.gestionarFecha(fecha);
    }

    @Override
    public void gestionarDuracion(int duracion, SesionEntrenamiento sesion) {
        sesion.gestionarDuracion(duracion);
    }

    @Override
    public void gestionarEntrenador(Entrenador entrenadorNuevo,Entrenador entrenadorAntiguo, SesionEntrenamiento sesion) throws IOException {
        entrenadorAntiguo.eliminarEntrenamiento(sesion);
        entrenadorNuevo.aniadirEntrenamiento(sesion);
    }

    @Override
    public void aniadirDeporte(String nombre, String descripcion, Dificultad dificultad) throws IOException {
        club.aniadirDeporte(nombre, descripcion, dificultad);
    }
    @Override
    public void eliminarDeporte(Deporte deporte){
        club.eliminarDeporte(deporte);
    }
}
