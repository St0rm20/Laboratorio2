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
import programacion3.laboratorio1.Clases.Personas.Entrenador;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class InicioSesionEntrenadorController {

    Club club = Club.getInstance();
    Utilities utilities = Utilities.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text TextFieldEntrenador;

    @FXML
    private Text TextFieldID;

    @FXML
    private Text TextFieldIngresacontusDatos;

    @FXML
    private Text TextFieldNombre;

    @FXML
    private PasswordField getIDPassword;

    @FXML
    private TextField getNombre;

    @FXML
    private Button ButtonAtras;

    @FXML
    private Button ButtonIngresar;

    @FXML
    void OnButtonAtras() throws IOException {
        AppClub.setRoot("/com/programacion3/laboratorio1/INICIOCLUB.fxml");
    }

    @FXML
    void OnButtonIngresar() throws IOException {
        Entrenador usuarioEncontrado = confirmarUsuario();
        if(usuarioEncontrado != null){
            UsuarioActual.setUsuarioActual(usuarioEncontrado);
            AppClub.setRoot("/com/programacion3/laboratorio1/ENTRENADORPAGINA.fxml");
        }
    }

    private Entrenador confirmarUsuario()throws IOException {
        String nombre = getNombre.getText();
        String id = getIDPassword.getText();

        for (Entrenador entrenador : club.getEntrenadores()) {
            if (Objects.equals(entrenador.getNombre(), nombre) && Objects.equals(entrenador.getID(), id)) {
                return entrenador;
            }
        }

        // Ningún usuario coincide.
        Alerta.mostrarError("Nombre de usuario o ID incorrectos.");
        return null;
    }


    public void cargarIdioma() {
        TextFieldEntrenador.setText(utilities.getIdioma().getString("opcionEntrenador"));
        TextFieldID.setText(utilities.getIdioma().getString("mensajeID"));
        TextFieldIngresacontusDatos.setText(utilities.getIdioma().getString("mensajeIngresaConTusDatos"));
        TextFieldNombre.setText(utilities.getIdioma().getString("mensajeNombre"));
        ButtonIngresar.setText(utilities.getIdioma().getString("botonIngresar"));

    }

    @FXML
    void initialize() throws IOException {
        cargarIdioma();
    }

}
