package programacion3.laboratorio1.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import programacion3.laboratorio1.Clases.AppClub;
import programacion3.laboratorio1.Clases.Club.Club;
import programacion3.laboratorio1.Clases.Herramientas.InicializarDatos;
import programacion3.laboratorio1.Clases.Herramientas.Utilities;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InicioClubController {

    static boolean iniciado = false;
    private final Utilities utilities  = Utilities.getInstance();
    private Club club = Club.getInstance();


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text TextFieldBienvenido;

    @FXML
    private Text TextFieldEligeQueCatego;

    @FXML
    private Text TextFieldNoHacesParte;

    @FXML
    private Button ButtonAdministrador;

    @FXML
    private Button ButtonDeportista;

    @FXML
    private Button ButtonEntrenador;

    @FXML
    private Button ButtonUnirte;

    @FXML
    private Button botonCambiarIdioma;

    @FXML
    void OnButtonAdministrador() throws IOException {
        AppClub.setRoot("/com/programacion3/laboratorio1/ADMININICIODESESION.fxml");
    }

    @FXML
    void OnButtonDeportista() throws IOException {
        AppClub.setRoot("/com/programacion3/laboratorio1/DEPORTISTAINICIOSESION.fxml");
    }

    @FXML
    void OnButtonEntrenador() throws IOException {
        AppClub.setRoot("/com/programacion3/laboratorio1/ENTRENADORINICIOSESION.fxml");
    }

    @FXML
    void OnButtonUnirte() throws IOException {
        AppClub.setRoot("/com/programacion3/laboratorio1/UNIRTEPAGINA.fxml");
    }

    @FXML
    void onCambiarIdioma() throws IOException {
        utilities.cambiarIdioma();
        cargarIdioma();
        AppClub.setRoot("/com/programacion3/laboratorio1/INICIOCLUB.fxml");
    }

    @FXML
    void initialize() throws IOException{
        cargarIdioma();
        if(!iniciado){
            InicializarDatos.inicializarDatos();
            iniciado = true;
        }
    }

    public void cargarIdioma(){
        TextFieldBienvenido.setText(utilities.getIdioma().getString("mensajeDeBienvenida"));
        TextFieldEligeQueCatego.setText(utilities.getIdioma().getString("mensajeDeEligeCategoria"));
        TextFieldNoHacesParte.setText(utilities.getIdioma().getString("mensajeUnirse"));
        ButtonAdministrador.setText(utilities.getIdioma().getString("botonAdministrador"));
        ButtonDeportista.setText(utilities.getIdioma().getString("botonDeportista"));
        ButtonEntrenador.setText(utilities.getIdioma().getString("botonEntrenador"));
        ButtonUnirte.setText(utilities.getIdioma().getString("botonUnirse"));
        botonCambiarIdioma.setText(utilities.getIdioma().getString("botonCambiarIdioma"));
    }
}