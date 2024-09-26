package programacion3.laboratorio1.Clases.Interfaces;

import programacion3.laboratorio1.Clases.Club.Deporte;
import programacion3.laboratorio1.Clases.Club.SesionEntrenamiento;
import programacion3.laboratorio1.Clases.Personas.Entrenador;

import java.io.IOException;
import java.time.LocalDate;

public interface AdministradorSesiones {

    public void programarSesion(LocalDate fecha, int duracion, Deporte deporte,Entrenador entrenador) throws IOException;

    public void gestionarFecha(LocalDate fecha, SesionEntrenamiento sesion);

    public void gestionarDuracion(int duracion, SesionEntrenamiento sesion);

    public void gestionarEntrenador(Entrenador entrenadorNuevo,Entrenador entrenadorAntiguo, SesionEntrenamiento sesion) throws IOException;
}
