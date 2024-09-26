package programacion3.laboratorio1.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import programacion3.laboratorio1.Clases.AppClub;
import programacion3.laboratorio1.Clases.Club.Club;
import programacion3.laboratorio1.Clases.Herramientas.Alerta;
import programacion3.laboratorio1.Clases.Herramientas.Utilities;
import programacion3.laboratorio1.Clases.enums.Categoria;

import java.io.IOException;
import java.util.Optional;

public class UnirtePaginaController {
    Club club = Club.getInstance();
    Utilities utilities = Utilities.getInstance();
    @FXML
    private Button ButtonAtras;

    @FXML
    private Button ButtonUnirte;

    @FXML
    private Text TextFieldID;

    @FXML
    private Text TextFieldNombre;

    @FXML
    private TextField getNombre;

    @FXML
    private PasswordField getTextID;

    @FXML
    private ChoiceBox<String> ChoiceBoxTipo;

    @FXML
    void initialize() {
        cargarIdioma();
        actualizarOpcionesChoiceBox();
    }

    public void actualizarOpcionesChoiceBox() {
        ChoiceBoxTipo.getItems().clear();

        if (utilities.getIdioma().getLocale().getLanguage().equals("es")) {
            ChoiceBoxTipo.getItems().addAll("Administrador", "Miembro", "Entrenador");
        } else {
            ChoiceBoxTipo.getItems().addAll("Administrator", "Member", "Coach");
        }
    }


    @FXML
    void OnButtonUnirte(ActionEvent event) throws IOException{

        String selectedTipo = ChoiceBoxTipo.getValue();

        // Imprimir el tipo correspondiente
        if (selectedTipo != null) {
            if (selectedTipo.equals("Administrador")||selectedTipo.equals("Administrator")) {
                crearAdministrador();
            } else if (selectedTipo.equals("Miembro")||selectedTipo.equals("Member")) {
               crearMiembro();
            } else if (selectedTipo.equals("Entrenador")||selectedTipo.equals("Coach")) {
                crearEntrenador();
            }

        } else {
            Alerta.mostrarError(utilities.getIdioma().getString("noSeHaSeleccionadoTipo"));
        }

    }

    private void crearAdministrador() throws IOException{
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(utilities.getIdioma().getString("mensajeClaveDeSeguridad"));
        dialog.setHeaderText(null);
        dialog.setContentText(utilities.getIdioma().getString("mensajeIngreseClaveDeSeguridad"));

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String clave = result.get();
            if ("1234".equals(clave)) {
                club.aniadirAdministrador(getNombre.getText(), getTextID.getText());

                Alerta.mostrarInformacion(utilities.getIdioma().getString("mensajeTeHasUnidoAlClub"));
            }else{
                Alerta.mostrarError(utilities.getIdioma().getString("claveIncorrecta"));
            }
        } else {
            Alerta.mostrarError(utilities.getIdioma().getString("NoSeHaIngresadoClave"));
        }
    }

    private void crearMiembro() throws IOException{
        // Crear el primer diálogo para el correo electrónico
        TextInputDialog emailDialog = new TextInputDialog();
        emailDialog.setTitle(utilities.getIdioma().getString("mensajeCompletarDatos"));
        emailDialog.setHeaderText(null);
        emailDialog.setContentText(utilities.getIdioma().getString("mensajeIngreseSuCorreoElectronico"));

        Optional<String> emailResult = emailDialog.showAndWait();
        if (emailResult.isPresent()) {
            String email = emailResult.get();

            // Limpiar el campo de texto del diálogo y crear el segundo diálogo para la categoría
            TextInputDialog categoriaDialog = new TextInputDialog();
            categoriaDialog.setTitle(utilities.getIdioma().getString("mensajeCompletarDatos"));
            categoriaDialog.setHeaderText(null);
            categoriaDialog.setContentText(utilities.getIdioma().getString("mensajeIngreseCategoria"));

            Optional<String> categoriaResult = categoriaDialog.showAndWait();
            if (categoriaResult.isPresent()) {
                String categoria = categoriaResult.get();
                if (categoria.equalsIgnoreCase("Juvenil") || categoria.equalsIgnoreCase("Youth")) {
                    club.aniadirMiembro(getNombre.getText(), getTextID.getText(), email, Categoria.JUVENIL);
                    Alerta.mostrarInformacion(utilities.getIdioma().getString("mensajeTeHasUnidoAlClubConExitoComoJuvenil"));
                } else if (categoria.equalsIgnoreCase("Adulto") || categoria.equalsIgnoreCase("Adult")) {
                    club.aniadirMiembro(getNombre.getText(), getTextID.getText(), email, Categoria.ADULTO);
                    Alerta.mostrarInformacion(utilities.getIdioma().getString("mensajeTeHasUnidoAlClubConExitoComoAdulto"));
                } else {
                    Alerta.mostrarError(utilities.getIdioma().getString("categoriaInvalida"));
                }
            } else {
                Alerta.mostrarError(utilities.getIdioma().getString("noSeHaIngresadoCategoria"));
            }
        } else {
            Alerta.mostrarError(utilities.getIdioma().getString("noSeHaIngresadoCorreo"));
        }
    }


    private void crearEntrenador() throws IOException{
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(utilities.getIdioma().getString("mensajeCompletarDatos"));
        dialog.setHeaderText(utilities.getIdioma().getString("mensajeInformacion"));
        dialog.setContentText(utilities.getIdioma().getString("mensajeIngreseSuEspecialidad"));

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {

            club.aniadirEntrenador(getNombre.getText(), getTextID.getText(), String.valueOf(result));
            Alerta.mostrarInformacion(utilities.getIdioma().getString("mensajeTeHasUnidoAlClub"));
        } else {
            Alerta.mostrarError(utilities.getIdioma().getString("noSeHaIngresadoEspecialidad"));
        }
    }


    @FXML
    void OnNombre(MouseEvent event) {
        // Puedes agregar la lógica para manejar el evento aquí
    }

    @FXML
    void OnTextID(ActionEvent event) {
        // Puedes agregar la lógica para manejar el evento aquí
    }

    @FXML
    void OnButtonAtras() throws IOException {
        AppClub.setRoot("/com/programacion3/laboratorio1/INICIOCLUB.fxml");
    }

    public void cargarIdioma() {
        ButtonUnirte.setText(utilities.getIdioma().getString("botonUnirse"));
        TextFieldNombre.setText(utilities.getIdioma().getString("mensajeNombre"));
        TextFieldID.setText(utilities.getIdioma().getString("mensajeID"));
    }
}
