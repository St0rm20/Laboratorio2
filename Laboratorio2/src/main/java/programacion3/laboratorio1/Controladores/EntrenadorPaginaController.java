package programacion3.laboratorio1.Controladores;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import programacion3.laboratorio1.Clases.AppClub;
import programacion3.laboratorio1.Clases.Club.Club;
import programacion3.laboratorio1.Clases.Club.Deporte;
import programacion3.laboratorio1.Clases.Club.SesionEntrenamiento;
import programacion3.laboratorio1.Clases.Herramientas.UsuarioActual;
import programacion3.laboratorio1.Clases.Herramientas.Utilities;
import programacion3.laboratorio1.Clases.Personas.Entrenador;
import programacion3.laboratorio1.Clases.enums.Dificultad;

public class EntrenadorPaginaController {

    private Club club = Club.getInstance();
    private ObservableList<SesionEntrenamiento> sesionesEntrenador;
    private FilteredList<SesionEntrenamiento> filteredSesionesEntrenador;
    private final Utilities utilities = Utilities.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text NombreEntrenadorTextField;

    @FXML
    private TableColumn<SesionEntrenamiento, String> colDeporte;

    @FXML
    private TableColumn<SesionEntrenamiento, Integer> colDuracion;

    @FXML
    private TableColumn<SesionEntrenamiento, String> colFecha;

    @FXML
    private Text consultarPorDuracionTextField;

    @FXML
    private TextField getTextConsultaPorNombre;

    @FXML
    private Button onBack;

    @FXML
    private Button onConsultar;

    @FXML
    private TableView<SesionEntrenamiento> tablaSesionesEntrenador;

    @FXML
    void OnTextConsultaPorNombre() {

    }

    @FXML
    void initialize() {

        NombreEntrenadorTextField.setText(UsuarioActual.getUsuarioActual().getNombre());
        sesionesEntrenador = FXCollections.observableArrayList(((Entrenador) UsuarioActual.getUsuarioActual()).getSesiones());
        inicializarTabla();
        inicializarValores();

    }

    @FXML
    void onBack() throws IOException {
        AppClub.setRoot("/com/programacion3/laboratorio1/ENTRENADORINICIOSESION.fxml");

    }

    @FXML
    void onConsultar() {
        String consulta = getTextConsultaPorNombre.getText().toLowerCase();
        filteredSesionesEntrenador.setPredicate(sesionEntrenamiento ->
                String.valueOf(sesionEntrenamiento.getDuracion()).contains(consulta));

    }

    private void  inicializarTabla(){
        cargarIdioma();
        colDeporte.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDeporte().getNombre()));
        colDuracion.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDuracion()).asObject());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().format(formato)));

        filteredSesionesEntrenador = new FilteredList<>(sesionesEntrenador);
        tablaSesionesEntrenador.setItems(filteredSesionesEntrenador);
    }
    private void inicializarValores(){
        filteredSesionesEntrenador = new FilteredList<>(sesionesEntrenador, p -> true);
        tablaSesionesEntrenador.setItems(filteredSesionesEntrenador);
    }

    public void cargarIdioma() {

        colDeporte.setText(utilities.getIdioma().getString("columnaDeporte"));
        colDuracion.setText(utilities.getIdioma().getString("columnaDuracion"));
        colFecha.setText(utilities.getIdioma().getString("columnaFecha"));
        consultarPorDuracionTextField.setText(utilities.getIdioma().getString("mensajeConsultaPorDuracion"));
        onConsultar.setText(utilities.getIdioma().getString("botonConsultar"));
    }
}
