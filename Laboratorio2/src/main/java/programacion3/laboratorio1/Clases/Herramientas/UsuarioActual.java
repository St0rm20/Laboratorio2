package programacion3.laboratorio1.Clases.Herramientas;

import programacion3.laboratorio1.Clases.Personas.Persona;

public class UsuarioActual
{
    private static Persona usuarioActual;

    public static Persona getUsuarioActual() {
        return usuarioActual;
    }

    public static void setUsuarioActual(Persona usuarioActual) {
        UsuarioActual.usuarioActual = usuarioActual;
    }
}
