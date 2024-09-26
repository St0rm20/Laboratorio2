package programacion3.laboratorio1.Controladores;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import programacion3.laboratorio1.Clases.AppClub;
import programacion3.laboratorio1.Clases.Club.Club;
import programacion3.laboratorio1.Clases.Club.Deporte;
import programacion3.laboratorio1.Clases.Club.SesionEntrenamiento;
import programacion3.laboratorio1.Clases.Herramientas.Alerta;
import programacion3.laboratorio1.Clases.Herramientas.UsuarioActual;
import programacion3.laboratorio1.Clases.Herramientas.Utilities;
import programacion3.laboratorio1.Clases.Personas.Administrador;
import programacion3.laboratorio1.Clases.Personas.Entrenador;
import programacion3.laboratorio1.Clases.enums.Estado;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AdministradorPaginaController {

    private Club club = Club.getInstance();
    private ObservableList<SesionEntrenamiento> sesionesAdministrador;
    private FilteredList<SesionEntrenamiento> filteredSesionesAdministrador;
    private SesionEntrenamiento sesionSeleccionada;
    Utilities utilities = Utilities.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView AvatarAdmin;

    @FXML
    private ChoiceBox<Deporte> ChoseDeporte;

    @FXML
    private ChoiceBox<Entrenador> ChoseEntrenador;

    @FXML
    private Text NombreAdministradorTextField;

    @FXML
    private Text TextFieldDeporte;

    @FXML
    private Text TextFieldDuracion;

    @FXML
    private Text TextFieldFecha;

    @FXML
    private DatePicker fechaPicker;

    @FXML
    private TableColumn<SesionEntrenamiento, String> colEntrenador;

    @FXML
    private TableColumn<SesionEntrenamiento, String> colDeporte;

    @FXML
    private TableColumn<SesionEntrenamiento, Integer> colDuracion;

    @FXML
    private TableColumn<SesionEntrenamiento, String> colEstado;

    @FXML
    private TableColumn<SesionEntrenamiento, String> colFecha;

    @FXML
    private Text consultarPorDeporteTextField;

    @FXML
    private TextField getTextConsultaPorNombre;

    @FXML
    private Text TextFieldEntrenador;

    @FXML
    private TextField getTextDuracion;

    @FXML
    private Button onActualizar;

    @FXML
    private Button onAgregar;

    @FXML
    private Button onBack;

    @FXML
    private Button onConsultar;

    @FXML
    private Button onDeportesGo;

    @FXML
    private Button onEliminar;

    @FXML
    private TableView<SesionEntrenamiento> tablaSesiones;

    @FXML
    void OnCheckCompletada(ActionEvent event) {

    }

    @FXML
    void OnCheckProgramada(ActionEvent event) {

    }

    @FXML
    void OnChoseDeporte(MouseEvent event) {

    }

    @FXML
    void OnChoseEntrenador(MouseEvent event) {

    }

    @FXML
    void OnTextNombre(ActionEvent event) {

    }

    @FXML
    void getTextConsultaPorNombre(ActionEvent event) {

    }

    @FXML
    void onActualizar() throws IOException{
        actualizarSesion();

    }

    @FXML
    void onAgregar()throws IOException {
        agregarSesion();
    }


    @FXML
    void onConsultar() {
        String consulta = getTextConsultaPorNombre.getText().toLowerCase();
        filteredSesionesAdministrador.setPredicate(sesionEntrenamiento ->
                sesionEntrenamiento.getDeporte().getNombre().toLowerCase().contains(consulta));
    }

    @FXML
    void onDeportesGo(ActionEvent event) throws IOException {
        AppClub.setRoot("/com/programacion3/laboratorio1/DEPORTESPAGINA.fxml");
    }

    @FXML
    void onEliminar() throws IOException {
        eliminarSesion();

    }

    @FXML
    void initialize() {
        cargarIdioma();
        Administrador administrador = (Administrador) UsuarioActual.getUsuarioActual();
        NombreAdministradorTextField.setText(administrador.getNombre());
        sesionesAdministrador = FXCollections.observableArrayList(club.getSesionEntrenamientos());
        actualizarDeportes();
        actualizarEntrenadores();
        inicializarTabla();
        inicializarValores();
        verSeleccion();
    }

    @FXML
    void onBack() throws IOException {
        AppClub.setRoot("/com/programacion3/laboratorio1/ADMININICIODESESION.fxml");
    }



    private void inicializarTabla() {
        colDeporte.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDeporte().getNombre()));
        colDuracion.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getDuracion()).asObject());
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().format(formato)));
        colEntrenador.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEntrenador().getNombre()));
        colEstado.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEstado().toString()));
        filteredSesionesAdministrador = new FilteredList<>(sesionesAdministrador);
        tablaSesiones.setItems(filteredSesionesAdministrador);
    }

    private void inicializarValores() {
        filteredSesionesAdministrador = new FilteredList<>(sesionesAdministrador, p -> true);
        tablaSesiones.setItems(filteredSesionesAdministrador);
    }

    private void agregarSesion() throws IOException{
        if (validarFormato()) {
            SesionEntrenamiento sesionEntrenamiento = crearSesion();
            if (sesionEntrenamiento != null) {
                sesionesAdministrador.add(sesionEntrenamiento);
                club.aniadirSesion(sesionEntrenamiento);
            }
        }else {
            Alerta.mostrarError("Por favor llene todos los campos");
        }
    }

    private void eliminarSesion() throws IOException {
        boolean confirmacion = Alerta.mostrarMensajeConfirmacion("Confirmar eliminar");
        if (!confirmacion) {
            return;
        }
        SesionEntrenamiento sesionEntrenamiento = tablaSesiones.getSelectionModel().getSelectedItem();
        if (sesionEntrenamiento != null ) {
            sesionesAdministrador.remove(sesionEntrenamiento);
            club.eliminarSesion(sesionEntrenamiento);
        }
        limpiarDatos();
    }

    private void actualizarSesion() throws IOException{
        verSeleccion();
        if (sesionSeleccionada != null) {
            SesionEntrenamiento sesionActualizada = crearSesion();
            if(sesionActualizada != null) {
                actualizarLista(sesionSeleccionada, sesionActualizada);
            }
        }
    }

    private void actualizarLista(SesionEntrenamiento sesionActual, SesionEntrenamiento sesionActualizada) {
        int i = sesionesAdministrador.indexOf(sesionActual);
        sesionesAdministrador.set(i, sesionActualizada);
    }

    private void verSeleccion() {
        tablaSesiones.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            sesionSeleccionada = newSelection;
            mostrarInformacionSesion(sesionSeleccionada);
        });
    }

    private void mostrarInformacionSesion(SesionEntrenamiento sesionEntrenamiento) {
        if (sesionEntrenamiento != null) {
            getTextDuracion.setText(String.valueOf(sesionEntrenamiento.getDuracion()));
            fechaPicker.setValue(sesionEntrenamiento.getFecha());

            // Seleccionar el deporte correspondiente en el ChoiceBox
            ChoseDeporte.setValue(sesionEntrenamiento.getDeporte());
            ChoseEntrenador.setValue(sesionEntrenamiento.getEntrenador());

            Estado estado = sesionEntrenamiento.getEstado();

        }
    }

    private SesionEntrenamiento crearSesion() throws IOException{
        Entrenador entrenador = ChoseEntrenador.getValue();
        Deporte deporte = ChoseDeporte.getValue();
        if(entrenador.getEspecialidad().equals(deporte.getNombre())){
            SesionEntrenamiento sesionEntrenamiento = new SesionEntrenamiento(fechaPicker.getValue(), Integer.parseInt(getTextDuracion.getText()), ChoseDeporte.getValue(), ChoseEntrenador.getValue());
            entrenador.aniadirEntrenamiento(sesionEntrenamiento);
            return sesionEntrenamiento;
        }else{
            Alerta.mostrarError("El entrenador no tiene la especialidad del deporte");
            return null;
        }

    }

    private void actualizarDeportes(){
        // Poblar el ChoiceBox con los deportes disponibles
        ObservableList<Deporte> deportes = FXCollections.observableArrayList(club.getDeportes());
        ChoseDeporte.setItems(deportes);

        // Seleccionar el primer deporte como predeterminado si hay deportes disponibles
        if (!deportes.isEmpty()) {
            ChoseDeporte.setValue(deportes.get(0));
        }
    }

    private void actualizarEntrenadores(){
        // Poblar el ChoiceBox con los entrenadores disponibles
        ObservableList<Entrenador> entrenadores = FXCollections.observableArrayList(club.getEntrenadores());
        ChoseEntrenador.setItems(entrenadores);

        // Seleccionar el primer deporte como predeterminado si hay deportes disponibles
        if (!entrenadores.isEmpty()) {
            ChoseEntrenador.setValue(entrenadores.get(0));
        }
    }

    private void limpiarDatos() {
        getTextDuracion.setText("");
        fechaPicker.setValue(null);
        ChoseDeporte.setValue(null);
        ChoseEntrenador.setValue(null);
    }

    private boolean validarFormato() {
        return !getTextDuracion.getText().isEmpty()
                && fechaPicker.getValue() != null
                && ChoseDeporte.getValue() != null
                && ChoseEntrenador.getValue() != null;

    }

    public void cargarIdioma() {
        TextFieldDeporte.setText(utilities.getIdioma().getString("mensajeDeporte"));
        TextFieldDuracion.setText(utilities.getIdioma().getString("mensajeDuracion"));
        TextFieldFecha.setText(utilities.getIdioma().getString("mensajeFecha"));
        consultarPorDeporteTextField.setText(utilities.getIdioma().getString("mensajeConsultaPorDeporte"));
        onActualizar.setText(utilities.getIdioma().getString("botonActualizar"));
        onAgregar.setText(utilities.getIdioma().getString("botonAgregar"));
        onBack.setText(utilities.getIdioma().getString("botonCerrarSesion"));
        onConsultar.setText(utilities.getIdioma().getString("botonConsultar"));
        onDeportesGo.setText(utilities.getIdioma().getString("botonDeportes"));
        onEliminar.setText(utilities.getIdioma().getString("botonEliminar"));
        TextFieldEntrenador.setText(utilities.getIdioma().getString("mensajeEntrenador"));
        colDeporte.setText(utilities.getIdioma().getString("columnaDeporte"));
        colDuracion.setText(utilities.getIdioma().getString("columnaDuracion"));
        colFecha.setText(utilities.getIdioma().getString("columnaFecha"));
        colEntrenador.setText(utilities.getIdioma().getString("columnaEntrenador"));
        colEstado.setText(utilities.getIdioma().getString("columnaEstado"));
    }
}

