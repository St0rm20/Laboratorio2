package programacion3.laboratorio1.Clases;

import programacion3.laboratorio1.Clases.Club.Club;
import programacion3.laboratorio1.Clases.Club.Deporte;
import programacion3.laboratorio1.Clases.Club.SesionEntrenamiento;
import programacion3.laboratorio1.Clases.Herramientas.InicializarDatos;
import programacion3.laboratorio1.Clases.Herramientas.Utilities;
import programacion3.laboratorio1.Clases.Personas.Entrenador;


import javax.swing.*;


import java.io.IOException;
import java.util.ArrayList;


public class Pruebas {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {

        Club club = Club.getInstance();
        InicializarDatos.inicializarDatos();



        try{
            Utilities.serializarObjetoBinario("deportes.dat", club.getDeportes());
            JOptionPane.showMessageDialog(null,"Lista original:" + club.getDeportes());

            ArrayList<Deporte> nuevaLista = (ArrayList<Deporte>) Utilities.deserializarObjetoBinario("deportes.dat");
            JOptionPane.showMessageDialog(null, "Lista deserializada binario: " + nuevaLista);


            Utilities.serializarObjetoXML("deportes.xml", club.getDeportes());
            nuevaLista = (ArrayList<Deporte>) Utilities.deserializarObjetoXML("deportes.xml");
            JOptionPane.showMessageDialog(null, "Lista deserializada xml: " + nuevaLista);

        }catch(Exception e) {
            e.printStackTrace();
        }
        ArrayList<String>  cadenas = new ArrayList<String>();
        cadenas.add("Entidad 1");
        cadenas.add("Entidad 2");
        cadenas.add("Entidad 3");
        cadenas.add("Entidad 4");
        cadenas.add("Entidad 5");
        cadenas.add("Entidad 6");
        cadenas.add("Entidad 7");
        cadenas.add("Entidad 8");
        cadenas.add("Entidad 9");
        cadenas.add("Entidad 10");
        ArrayList<String>  cadenas2 = new ArrayList<String>();
        cadenas2.add("Entidad 1");
        cadenas2.add("Entidad 2");
        cadenas2.add("Entidad 3");
        cadenas2.add("Entidad 4");
        cadenas2.add("Entidad 5");
        cadenas2.add("Entidad 6");
        cadenas2.add("Entidad 7");
        cadenas2.add("Entidad 8");
        cadenas2.add("Entidad 9");
        cadenas2.add("Entidad 10");

        Utilities.escribriArchivo("archivo.txt", cadenas );
        Utilities.escribriArchivo("archivo2.txt", cadenas2);

        ArrayList<String> archivo= Utilities.leerArchivo("archivo.txt");
        JOptionPane.showMessageDialog(null, archivo);
        ArrayList<String> archivo2 = Utilities.leerArchivo("archivo2.txt");
        JOptionPane.showMessageDialog(null, archivo2);
    }
}
