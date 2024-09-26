module programacion3.laboratorio1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;
    requires jdk.jshell;

    opens programacion3.laboratorio1.Clases to javafx.fxml;
    opens programacion3.laboratorio1.Controladores to javafx.fxml;

    exports programacion3.laboratorio1.Clases;
    exports programacion3.laboratorio1.Controladores;
    exports programacion3.laboratorio1.Clases.Club;
    exports programacion3.laboratorio1.Clases.Personas; // Exportar el paquete necesario
}