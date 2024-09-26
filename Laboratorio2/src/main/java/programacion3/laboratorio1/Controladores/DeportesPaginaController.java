package programacion3.laboratorio1.Controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import programacion3.laboratorio1.Clases.AppClub;
import programacion3.laboratorio1.Clases.Club.Club;
import programacion3.laboratorio1.Clases.Club.Deporte;
import programacion3.laboratorio1.Clases.Club.SesionEntrenamiento;
import programacion3.laboratorio1.Clases.Herramientas.Alerta;
import programacion3.laboratorio1.Clases.Herramientas.Utilities;
import programacion3.laboratorio1.Clases.enums.Dificultad;

public class DeportesPaginaController {

    private Club club = Club.getInstance();
    private ObservableList<Deporte> deportes;
    private FilteredList<Deporte> filteredDeporte;
    private Deporte deporteSeleccionado;
    Utilities utilities = Utilities.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView AvatarAdmin;

    @FXML
    private CheckBox CheckBajo;

    @FXML
    private CheckBox CheckDificil;

    @FXML
    private CheckBox CheckMedio;

    @FXML
    private Text textDificultad;

    @FXML
    private Text TextFielDesccripcion;

    @FXML
    private Text TextFieldDeportes;

    @FXML
    private Text TextFieldNombre;

    @FXML
    private TableColumn<Deporte, String> colDescripcion;

    @FXML
    private TableColumn<Deporte, String> colDificultad;

    @FXML
    private TableColumn<Deporte, String> colNombre;

    @FXML
    private Text consultarPorNombreTextField;

    @FXML
    private TextField getTextConsultaPorNombre;

    @FXML
    private TextField getTextDescripcion;

    @FXML
    private TextField getTextNombre;

    @FXML
    private Button onActualizar;

    @FXML
    private Button onAgregar;

    @FXML
    private Button onAtras;

    @FXML
    private Button onCerrarSesion;

    @FXML
    private Button onConsultar;

    @FXML
    private Button onEliminar;

    @FXML
    private TableView<Deporte> tablaDeportes;

    @FXML
    void onEliminar() throws IOException {
        eliminarDeporte();
    }

    @FXML
    void onAgregar()throws IOException {
        agregarDeporte();
    }


    @FXML
    void OnCheckBajo(ActionEvent event) {

    }

    @FXML
    void OnCheckDificil(ActionEvent event) {

    }

    @FXML
    void OnCheckMedio(ActionEvent event) {

    }

    @FXML
    void onActualizar()throws IOException {
        actualizarDeporte();
    }

    @FXML
    void initialize() {
        cargarIdioma();
        deportes = FXCollections.observableArrayList(club.getDeportes());
        filteredDeporte = new FilteredList<>(deportes, p -> true);
        tablaDeportes.setItems(filteredDeporte);
        inicializarTabla();
        inicializarValores();
        verSeleccion();
    }

    private void inicializarTabla() {
        colNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        colDescripcion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescripcion()));
        colDificultad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDificultad().toString()));
    }

    private void inicializarValores() {
        filteredDeporte = new FilteredList<>(deportes, p -> true);
        tablaDeportes.setItems(filteredDeporte);
    }


    private void eliminarDeporte() throws IOException {
        boolean confirmacion = Alerta.mostrarMensajeConfirmacion("Confirmar eliminar");
        if (!confirmacion) {
            return;
        }
        Deporte deporte = tablaDeportes.getSelectionModel().getSelectedItem();
        if (deporte != null) {
            deportes.remove(deporte);
            club.eliminarDeporte(deporte);
            limpiarDatos();
        }
    }

    private void actualizarDeporte()throws IOException {
        if(validarFormato()){
            verSeleccion();
            if (deporteSeleccionado != null) {
                Deporte deporteActualizado = crearDeporte();
                if(deporteActualizado != null) {
                    actualizarLista(deporteSeleccionado, deporteActualizado);
                }
            }
        }else {
            Alerta.mostrarError("Por favor llene todos los campos");
        }

    }

    private void actualizarLista(Deporte deporteActual, Deporte deporteActualizado) {
        int i = deportes.indexOf(deporteActual);
        deportes.set(i, deporteActualizado);
    }
    private void verSeleccion() {
        tablaDeportes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            deporteSeleccionado = newSelection;
            mostrarInformacionDeporte(deporteSeleccionado);
        });
    }

    private void mostrarInformacionDeporte(Deporte deporte) {
        if (deporte != null) {
            getTextNombre.setText(deporte.getNombre());
            getTextDescripcion.setText(deporte.getDescripcion());
            Dificultad dificultad = deporte.getDificultad();
            if (dificultad.equals(Dificultad.BAJO)) {
                CheckBajo.setSelected(true);
                CheckMedio.setSelected(false);
                CheckDificil.setSelected(false);
            } else if (dificultad.equals(Dificultad.MEDIO)) {
                CheckBajo.setSelected(false);
                CheckMedio.setSelected(true);
                CheckDificil.setSelected(false);
            } else {
                CheckBajo.setSelected(false);
                CheckMedio.setSelected(false);
                CheckDificil.setSelected(true);
            }
        }
    }

    private Deporte crearDeporte()throws IOException {
        if (validarFormato()) {
            if (CheckBajo.isSelected()) {
                return new Deporte(getTextNombre.getText(), getTextDescripcion.getText(), Dificultad.BAJO);
            }
            if (CheckMedio.isSelected()) {
                return new Deporte(getTextNombre.getText(), getTextDescripcion.getText(), Dificultad.MEDIO);
            } else {
                return new Deporte(getTextNombre.getText(), getTextDescripcion.getText(), Dificultad.ALTO);
            }
        }else{
            Alerta.mostrarError("Por favor llene todos los campos");
            return null;
        }


    }

    private void agregarDeporte()throws IOException {
        if (validarFormato()) {
            Deporte deporte = crearDeporte();
            if (deporte != null) {
                deportes.add(deporte);
                club.aniadirDeporte(deporte);
            }
        }else {
            Alerta.mostrarError("Por favor llene todos los campos");
        }
    }

    @FXML
    void onAtras() throws IOException {
        AppClub.setRoot("/com/programacion3/laboratorio1/ADMINISTRADORPAGINA.fxml");
    }

    @FXML
    void onCerrarSesion() throws IOException {
        AppClub.setRoot("/com/programacion3/laboratorio1/ADMININICIODESESION.fxml");
    }

    @FXML
    void onConsultar() {
        String consulta = getTextConsultaPorNombre.getText().toLowerCase();
        filteredDeporte.setPredicate(deporte -> deporte.getNombre().toLowerCase().contains(consulta));
    }

    private void limpiarDatos() {
        getTextDescripcion.setText("");
        getTextNombre.setText("");
        CheckBajo.setSelected(false);
        CheckMedio.setSelected(false);
        CheckDificil.setSelected(false);
    }

    private boolean validarFormato() {
        return !getTextDescripcion.getText().isEmpty()
                && !getTextNombre.getText().isEmpty()
                && (CheckBajo.isSelected() || CheckMedio.isSelected() || CheckDificil.isSelected())
                && !(CheckBajo.isSelected() && CheckMedio.isSelected() && CheckDificil.isSelected())
                && !(CheckBajo.isSelected() && CheckMedio.isSelected())
                && !(CheckBajo.isSelected() && CheckDificil.isSelected())
                && !(CheckMedio.isSelected() && CheckDificil.isSelected());
    }

    public void cargarIdioma(){
        TextFieldDeportes.setText(utilities.getIdioma().getString("mensajeDeportes"));
        consultarPorNombreTextField.setText(utilities.getIdioma().getString("mensajeConsultaPorNombre"));
        colNombre.setText(utilities.getIdioma().getString("columnaNombre"));
        colDescripcion.setText(utilities.getIdioma().getString("columnaDescripcion"));
        colDificultad.setText(utilities.getIdioma().getString("columnaDificultad"));
        onAgregar.setText(utilities.getIdioma().getString("botonAgregar"));
        onActualizar.setText(utilities.getIdioma().getString("botonActualizar"));
        onEliminar.setText(utilities.getIdioma().getString("botonEliminar"));
        onConsultar.setText(utilities.getIdioma().getString("botonConsultar"));
        //onAtras.setText(utilities.getIdioma().getString("botonAtras"));
        onCerrarSesion.setText(utilities.getIdioma().getString("botonCerrarSesion"));
        TextFielDesccripcion.setText(utilities.getIdioma().getString("mensajeDescripcion"));
        TextFieldNombre.setText(utilities.getIdioma().getString("mensajeNombre"));
        textDificultad.setText(utilities.getIdioma().getString("mensajeDificultad"));
        CheckBajo.setText(utilities.getIdioma().getString("botonBajo"));
        CheckMedio.setText(utilities.getIdioma().getString("botonMedio"));
        CheckDificil.setText(utilities.getIdioma().getString("botonDificil"));
    }
}
