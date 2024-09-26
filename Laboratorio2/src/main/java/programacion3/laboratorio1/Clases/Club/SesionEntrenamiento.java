package programacion3.laboratorio1.Clases.Club;

import programacion3.laboratorio1.Clases.Herramientas.Alerta;
import programacion3.laboratorio1.Clases.Personas.Entrenador;
import programacion3.laboratorio1.Clases.enums.Estado;

import java.io.IOException;
import java.time.LocalDate;

import java.io.Serializable;

public class SesionEntrenamiento implements Serializable {
    private LocalDate fecha;
    private int duracion;
    private Deporte deporte;
    private Entrenador entrenador;
    private Estado estado;

    private static final long serialVersionUID = 1L;


    public SesionEntrenamiento(LocalDate fecha, int duracion, Deporte deporte, Entrenador entrenador) {
        this.fecha = fecha;
        this.duracion = duracion;
        this.deporte = deporte;
        this.entrenador = entrenador;
        actualizarEstado();

    }

    public SesionEntrenamiento() {
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public int getDuracion() {
        return duracion;
    }

    public Deporte getDeporte() {
        return deporte;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public Estado getEstado() {
        return estado;
    }


    public void gestionarFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void gestionarDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void gestionarDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public void gestionarEntrenador(Entrenador entrenador) throws IOException {

        if(entrenador.getEspecialidad().equals(deporte.getNombre())){
            this.entrenador.eliminarEntrenamiento(this);
            entrenador.aniadirEntrenamiento(this);
            this.entrenador = entrenador;
        }else {
            Alerta.mostrarError("El entrenador no tiene la especialidad del deporte");
        }

    }

    public void actualizarEstado() {
        if(fecha.equals(LocalDate.now())||fecha.isAfter(LocalDate.now())) {
            this.estado = Estado.PROGRAMADA;
        }else{
                this.estado = Estado.COMPLETADA;
        }
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public void setDeporte(Deporte deporte) {
        this.deporte = deporte;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "SesionEntrenamiento{" +
                "fecha=" + fecha +
                '}';
    }
}
