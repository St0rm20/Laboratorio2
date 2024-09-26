package programacion3.laboratorio1.Clases.Interfaces;

import programacion3.laboratorio1.Clases.Club.Deporte;
import programacion3.laboratorio1.Clases.enums.Dificultad;

import java.io.IOException;

public interface AdministradorDeportes {

    public void aniadirDeporte(String nombre, String descripcion, Dificultad dificultad) throws IOException;
    public void eliminarDeporte(Deporte deporte);
}
