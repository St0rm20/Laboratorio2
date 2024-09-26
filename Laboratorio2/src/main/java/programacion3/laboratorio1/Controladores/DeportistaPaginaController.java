// DeportistaPaginaController.java
package programacion3.laboratorio1.Controladores;

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
import programacion3.laboratorio1.Clases.Club.SesionEntrenamiento;
import programacion3.laboratorio1.Clases.Herramientas.UsuarioActual;
import programacion3.laboratorio1.Clases.Herramientas.Utilities;
import programacion3.laboratorio1.Clases.Personas.Miembro;
import programacion3.laboratorio1.Clases.enums.Estado;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class DeportistaPaginaController {

    private Club club = Club.getInstance();
    private Miembro miembro = (Miembro) UsuarioActual.getUsuarioActual();
    private ObservableList<SesionEntrenamiento> sesionesMiembro;
    private FilteredList<SesionEntrenamiento> filteredSesionesMiembro;
    private SesionEntrenamiento sesionSeleccionada;
    Utilities utilities = Utilities.getInstance();


    @FXML
    private Text NombreMiembroTextField;

    @FXML
    private TableColumn<SesionEntrenamiento, String> colDeporte;

    @FXML
    private TableColumn<SesionEntrenamiento, Integer> colDuracion;

    @FXML
    private TableColumn<SesionEntrenamiento, String> colEntrenador;

    @FXML
    private TableColumn<SesionEntrenamiento, String> colFecha;

    @FXML
    private TableColumn<SesionEntrenamiento, String> colDeporte1;

    @FXML
    private TableColumn<SesionEntrenamiento, String> colProgramadaCompletada;

    @FXML
    private Text consultarPorDeporteTextField;

    @FXML
    private TextField getTextConsultaPorNombre;

    @FXML
    private Button onBack;

    @FXML
    private Button onConsultar;

    @FXML
    private Button buttonInscribir;

    @FXML
    private TableView<SesionEntrenamiento> tablaSesionesMiembro;

    @FXML
    void OnTextConsultaPorNombre() {
        // Implementation here
    }

    @FXML
    void OnButtonInscribir()throws IOException {
        verSeleccion();
        miembro.aniadirSesion(sesionSeleccionada);
    }

    @FXML
    void initialize() {
        Miembro miembro = (Miembro) UsuarioActual.getUsuarioActual();
        NombreMiembroTextField.setText(miembro.getNombre());
        sesionesMiembro = FXCollections.observableArrayList(club.getSesionEntrenamientos());
        inicializarTabla();
        inicializarValores();
        verSeleccion();
    }

    @FXML
    void onBack() throws IOException {
        AppClub.setRoot("/com/programacion3/laboratorio1/DEPORTISTAINICIOSESION.fxml");
    }

    @FXML
    void onConsultar() {
        String consulta = getTextConsultaPorNombre.getText().toLowerCase();
        filteredSesionesMiembro.setPredicate(sesionEntrenamiento ->
                sesionEntrenamiento.getDeporte().getNombre().toLowerCase().contains(consulta));
    }

    private void inicializarTabla() {
        colDeporte.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDeporte().getNombre()));
        colDuracion.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDuracion()).asObject());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().format(formato)));
        colEntrenador.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEntrenador().getNombre()));
        colDeporte1.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getDeporte().getDificultad())));

        colProgramadaCompletada.setCellValueFactory(cellData -> {
            Estado estado = cellData.getValue().getEstado(); // Obtener el estado de la sesión
            String estadoTexto;

            if (estado == Estado.COMPLETADA) {
                estadoTexto = "Completado";
            } else if (estado == Estado.PROGRAMADA) {
                estadoTexto = "Programado";
            } else {
                estadoTexto = "Desconocido";
            }

            return new SimpleStringProperty(estadoTexto);
        });
        filteredSesionesMiembro = new FilteredList<>(sesionesMiembro);
        tablaSesionesMiembro.setItems(filteredSesionesMiembro);
    }


    private void verSeleccion() {
        tablaSesionesMiembro.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            sesionSeleccionada = newSelection;
        });
    }
    private void inicializarValores() {
        cargarIdioma();
        filteredSesionesMiembro = new FilteredList<>(sesionesMiembro, p -> true);
        tablaSesionesMiembro.setItems(filteredSesionesMiembro);
    }

    public void cargarIdioma() {
        consultarPorDeporteTextField.setText(utilities.getIdioma().getString("mensajeConsultaPorDeporte"));
        buttonInscribir.setText(utilities.getIdioma().getString("botonInscribir"));
        colDeporte.setText(utilities.getIdioma().getString("columnaDeporte"));
        colDuracion.setText(utilities.getIdioma().getString("columnaDuracion"));
        colEntrenador.setText(utilities.getIdioma().getString("columnaEntrenador"));
        colFecha.setText(utilities.getIdioma().getString("columnaFecha"));
        colDeporte1.setText(utilities.getIdioma().getString("columnaNivel"));
        colProgramadaCompletada.setText(utilities.getIdioma().getString("columnaProgramadaCompletada"));
        onConsultar.setText(utilities.getIdioma().getString("botonConsultar"));
    }
}