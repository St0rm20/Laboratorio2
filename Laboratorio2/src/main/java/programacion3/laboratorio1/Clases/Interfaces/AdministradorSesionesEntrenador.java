package programacion3.laboratorio1.Clases.Interfaces;

import programacion3.laboratorio1.Clases.Club.SesionEntrenamiento;

import java.io.IOException;

public interface AdministradorSesionesEntrenador {

    public  void aniadirEntrenamiento(SesionEntrenamiento sesion);
    public void eliminarEntrenamiento(SesionEntrenamiento sesion) throws IOException;

}
