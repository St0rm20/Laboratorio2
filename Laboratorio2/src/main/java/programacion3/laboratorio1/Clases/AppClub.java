package programacion3.laboratorio1.Clases;

import programacion3.laboratorio1.Clases.Club.Club;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class AppClub extends Application {

    private static Scene scene;
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/com/programacion3/laboratorio1/INICIOCLUB.fxml"));
        stage.setTitle("CLUB");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Cambia la raíz de la escena actual a un nuevo diseño FXML.
     *
     * @param fxml la ruta del archivo FXML
     * @throws IOException si ocurre un error al cargar el archivo FXML
     */
    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        Stage stage = (Stage) scene.getWindow();
        stage.setWidth(600);
        stage.setHeight(450);
    }

    /**
     * Cambia la raíz de la escena actual a un nuevo diseño FXML con dimensiones personalizadas.
     *
     * @param fxml la ruta del archivo FXML
     * @param width el ancho deseado del escenario
     * @param height la altura deseada del escenario
     * @throws IOException si ocurre un error al cargar el archivo FXML
     */
    public static void setRoot(String fxml, double width, double height) throws IOException {
        scene.setRoot(loadFXML(fxml));
        Stage stage = (Stage) scene.getWindow();
        stage.setWidth(width);
        stage.setHeight(height);
    }

    /**
     * Carga un archivo FXML y retorna el nodo raíz.
     *
     * @param fxml la ruta del archivo FXML
     * @return el nodo raíz del archivo FXML cargado
     * @throws IOException si ocurre un error al cargar el archivo FXML
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppClub.class.getResource(fxml));
        return fxmlLoader.load();
    }


    public static void main(String[] args) {
        Club club = Club.getInstance();

        launch();

    }
}
