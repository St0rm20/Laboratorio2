package programacion3.laboratorio1.Clases.Herramientas;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import jdk.jshell.execution.Util;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Alerta {

    public static  Utilities utilities = Utilities.getInstance();


    // Muestra un diálogo de confirmación con el mensaje especificado y devuelve true si el usuario hace clic en OK
    public static boolean mostrarMensajeConfirmacion(String message) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación de acción");
        alert.setContentText(message);
        Optional<ButtonType> action = alert.showAndWait();
        utilities.escribirLogInfo(message);
        return action.isPresent() && action.get() == ButtonType.OK;
    }
    // Muestra una advertencia con el contenido especificado
    public static void mostrarAdvertencia(String contenido) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle("Advertencia");
        alert.setContentText(contenido);
        utilities.escribirLogWarning(contenido);
        alert.showAndWait();
    }

    public static void mostrarInformacion(String contenido)throws IOException{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Informacion");
        alert.setContentText(contenido);
        utilities.escribirLogInfo(contenido);
        alert.showAndWait();
    }

    public static void mostrarError(String contenido) throws IOException {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(contenido);
        utilities.escribirLogError(contenido);
        alert.showAndWait();
    }
    // Muestra una alerta con el título, encabezado, contenido y tipo de alerta especificados
    public static void mostrarAlerta(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
