package programacion3.laboratorio1.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import programacion3.laboratorio1.Clases.AppClub;
import programacion3.laboratorio1.Clases.Club.Club;
import programacion3.laboratorio1.Clases.Herramientas.Alerta;
import programacion3.laboratorio1.Clases.Herramientas.UsuarioActual;
import programacion3.laboratorio1.Clases.Herramientas.Utilities;
import programacion3.laboratorio1.Clases.Personas.Miembro;

import java.io.IOException;
import java.util.Objects;

public class InicioSesionDeportistaController {

        Club club = Club.getInstance();
        Utilities utilities = Utilities.getInstance();

        @FXML
        private Text TextFieldDeportista;

        @FXML
        private Text TextFieldId;

        @FXML
        private Text TextFieldIngresarDatos;

        @FXML
        private Text TextFieldNombre;

        @FXML
        private PasswordField getIDPassword;

        @FXML
        private TextField getNombre;

        @FXML
        private Button ButtonAtras;

        @FXML
        private Button onButtonRegistrar;


    @FXML
    void OnButtonAtras() throws IOException {
        AppClub.setRoot("/com/programacion3/laboratorio1/INICIOCLUB.fxml");
    }

    @FXML
    void OnButtonRegistrar() throws IOException {
        Miembro usuarioEncontrado = confirmarUsuario();
        if(usuarioEncontrado != null){
            UsuarioActual.setUsuarioActual(usuarioEncontrado);
            AppClub.setRoot("/com/programacion3/laboratorio1/MIEMBROPAGINA.fxml");
        }

    }

    private Miembro confirmarUsuario() throws IOException {
        String nombre = getNombre.getText();
        String id = getIDPassword.getText();

        for (Miembro miembro : club.getMiembros()) {
            if (Objects.equals(miembro.getNombre(), nombre) && Objects.equals(miembro.getID(), id)) {
                return miembro;
            }
        }

        // Ningún usuario coincide.
        Alerta.mostrarError("Nombre de usuario o ID incorrectos.");
        return null;
    }

    public void cargarIdioma(){
            TextFieldDeportista.setText(utilities.getIdioma().getString("mensajeDeportista"));
            TextFieldId.setText(utilities.getIdioma().getString("mensajeID"));
            TextFieldIngresarDatos.setText(utilities.getIdioma().getString("mensajeIngresaConTusDatos"));
            TextFieldNombre.setText(utilities.getIdioma().getString("mensajeNombre"));
        onButtonRegistrar.setText(utilities.getIdioma().getString("botonIngresar"));
    }

    @FXML
    void initialize() {
        cargarIdioma();
    }
    }

