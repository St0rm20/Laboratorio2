package programacion3.laboratorio1.Clases.Personas;

import java.io.Serializable;

public class Persona implements Serializable {
    private String nombre;
    private String ID;
    private static final long serialVersionUID = 1L;

    public Persona(String nombre, String ID) {
        this.nombre = nombre;
        this.ID = ID;
    }

    public Persona(){

    }

    public String getNombre() {
        return nombre;
    }
    public String getID() {
        return ID;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
