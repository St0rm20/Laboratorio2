package programacion3.laboratorio1.Clases.Personas;

import programacion3.laboratorio1.Clases.Club.SesionEntrenamiento;
import programacion3.laboratorio1.Clases.Herramientas.Alerta;
import programacion3.laboratorio1.Clases.Interfaces.AdministradorSesionesEntrenador;

import java.io.IOException;
import java.util.ArrayList;
import java.io.Serializable;
import java.beans.Transient;

public class Entrenador extends Persona implements AdministradorSesionesEntrenador, Serializable {
    private transient String especialidad;
    private ArrayList<SesionEntrenamiento> sesiones;
    private static final long serialVersionUID = 1L;

    public Entrenador(String nombre, String ID, String especialidad) {
        super(nombre, ID);
        this.especialidad = especialidad;
        this.sesiones = new ArrayList<>();
    }
    public Entrenador(String nombre, String ID) {
        super(nombre, ID);
        this.sesiones = new ArrayList<>();
    }

    public Entrenador(){

    }
    @Transient
    public String getEspecialidad() {
        return especialidad;
    }

    public ArrayList<SesionEntrenamiento> getSesiones() {
        return sesiones;
    }

    @Override
    public void aniadirEntrenamiento(SesionEntrenamiento sesion) {
        sesiones.add(sesion);
    }

    public void validarExiste(SesionEntrenamiento sesion) throws IOException {
        if(!sesiones.contains(sesion)){
            Alerta.mostrarError("La sesion no existe");
        }
    }

    @Override
    public void eliminarEntrenamiento(SesionEntrenamiento sesion) throws IOException {
        validarExiste(sesion);
        sesiones.remove(sesion);
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setSesiones(ArrayList<SesionEntrenamiento> sesiones) {
        this.sesiones = sesiones;
    }



    @Override
    public String toString() {
        return getNombre();
    }
}
