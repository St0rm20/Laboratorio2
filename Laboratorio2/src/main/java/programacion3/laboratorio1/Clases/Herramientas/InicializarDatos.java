package programacion3.laboratorio1.Clases.Herramientas;

import programacion3.laboratorio1.Clases.Club.Club;
import programacion3.laboratorio1.Clases.enums.Categoria;
import programacion3.laboratorio1.Clases.enums.Dificultad;

import java.io.IOException;
import java.time.LocalDate;

public class InicializarDatos {
    static Club club = Club.getInstance();
    public static void inicializarDatos() throws IOException {
        //Deportes
        club.aniadirDeporte("Futbol", "Deporte de equipo", Dificultad.MEDIO);
        club.aniadirDeporte("Tenis", "Deporte de raqueta", Dificultad.ALTO);
        club.aniadirDeporte("Basquet", "Deporte de equipo", Dificultad.ALTO);
        club.aniadirDeporte("Voley", "Deporte de equipo", Dificultad.MEDIO);
        club.aniadirDeporte("Boxeo", "Deporte de contacto", Dificultad.ALTO);



        //Entrenadores

        club.aniadirEntrenador("Cristiano", "1234", "Futbol");
        club.aniadirEntrenador("Lee", "1235", "Boxeo");
        club.aniadirEntrenador("Novak", "1236", "Tenis");
        club.aniadirEntrenador("Brenda", "1237", "Voley");
        club.aniadirEntrenador("Curry", "1238", "Basquet");
        club.aniadirEntrenador("Lebron", "1239", "Basquet");


        //Miembros
        club.aniadirMiembro("Jose", "1237", "jose@gmail.com", Categoria.JUVENIL) ;
        club.aniadirMiembro("Helen", "1238", "helen@gmail.com",Categoria.ADULTO);
        club.aniadirMiembro("Miguel", "1239", "miguel@gmail.com",Categoria.ADULTO);

        //Administadores
        club.aniadirAdministrador("Xiomara", "1230");
        club.aniadirAdministrador("Angel", "1231");
        club.aniadirAdministrador("Maria", "1232");

        //Sesiones
        club.aniadirSesion(LocalDate.now().plusDays(7), 2, "Futbol", "Cristiano");
        club.aniadirSesion(LocalDate.now().plusDays(8), 3, "Futbol", "Cristiano");
        club.aniadirSesion(LocalDate.now().plusDays(9), 4, "Futbol", "Cristiano");
        club.aniadirSesion(LocalDate.now().plusDays(10), 1, "Futbol", "Cristiano");
        club.aniadirSesion(LocalDate.now().plusDays(2),1,"Basquet", "Curry");
        club.aniadirSesion(LocalDate.now().plusDays(18),4,"Voley", "Brenda");
    }
}