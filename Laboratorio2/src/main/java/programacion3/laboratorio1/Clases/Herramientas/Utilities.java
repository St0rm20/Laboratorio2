package programacion3.laboratorio1.Clases.Herramientas;



import javax.swing.*;
import java.beans.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Utilities {
    public static Utilities instance;
    private static final Logger LOGGER = Logger.getLogger(Utilities.class.getName());
    static FileHandler archivo;
    static ResourceBundle idioma = ResourceBundle.getBundle("MiRecurso/MiRecurso", new Locale("en", "UK"));
    static String directorioPath = "C://Reportes_Java";

    static {
        try {
            archivo = new FileHandler("MyLog.txt", true);
            archivo.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(archivo);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static Utilities getInstance() {
        if (instance == null) {
            instance = new Utilities();
        }
        return instance;
    }


    public void escribirLogInfo(String mensaje) throws IOException {
        LOGGER.log(Level.INFO, mensaje);
        LOGGER.addHandler(archivo);
    }

    public void escribirLogWarning(String mensaje) throws IOException {
        LOGGER.log(Level.WARNING, mensaje);
        LOGGER.addHandler(archivo);
    }

    public void escribirLogError(String mensaje) throws IOException {

        LOGGER.log(Level.SEVERE, mensaje);
        LOGGER.addHandler(archivo);
    }

    public ResourceBundle getIdioma() {
        return idioma;
    }

    public void cambiarIdioma() {
        if (idioma.getLocale().getLanguage().equals("es")) {
            idioma = ResourceBundle.getBundle("MiRecurso/MiRecurso", new Locale("en", "UK"));
        } else {
            idioma = ResourceBundle.getBundle("MiRecurso/MiRecurso", new Locale("es", "CO"));
        }
    }


    public static void serializarObjetoBinario(String nombreArchivo, Object objeto) throws IOException{
        ObjectOutputStream salida;

        salida = new  ObjectOutputStream(new FileOutputStream(nombreArchivo));
        salida.writeObject(objeto);
        salida.close();
    }

    public static Object deserializarObjetoBinario( String nombreArchivo) throws IOException, ClassNotFoundException {
        Object objeto;
        ObjectInputStream entrada;

        entrada = new ObjectInputStream( new FileInputStream(nombreArchivo));
        objeto = entrada.readObject();
        entrada.close();

        return objeto;
    }

    public static void serializarObjetoXML(String filename, Object obj) throws IOException {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)))) {
            encoder.setPersistenceDelegate(LocalDate.class, new DefaultPersistenceDelegate() {
                @Override
                protected Expression instantiate(Object obj, Encoder enc) {
                    LocalDate localDate = (LocalDate) obj;
                    return new Expression(localDate, LocalDate.class, "of",
                            new Object[] { localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth() });
                }
            });
            encoder.writeObject(obj);
        }
    }


    public static Object deserializarObjetoXML (String nombre) throws IOException{
        XMLDecoder decodificador;
        Object objeto;

        decodificador = new XMLDecoder( new FileInputStream(nombre));
        objeto = decodificador.readObject();
        decodificador.close();

        return objeto;
    }

    public static void escribriArchivo(String nombreArchivo, ArrayList<String> texto) throws IOException{


        File directorio = new File(directorioPath);
        if (!directorio.exists()) {
            directorio.mkdir();
        }

        FileWriter archivoSalida;
        BufferedWriter bufferSalida;
        archivoSalida = new FileWriter( directorioPath+ "/" + nombreArchivo, true);
        bufferSalida= new BufferedWriter(archivoSalida);

        int i = 0;
        for( String linea : texto ){
            if (i<10){
            bufferSalida.write(linea);
            bufferSalida.newLine();
            i++;
            }else{
                break;
            }
            bufferSalida.flush();
        }


        bufferSalida.close();
        archivoSalida.close();

    }

    public static ArrayList<String> leerArchivo (String nombreArchivo) throws IOException{
        FileReader archivoEntrada;
        BufferedReader bufferEntrada;
        ArrayList<String> contenidoDeTexto;
        String linea;

        archivoEntrada = null;
        contenidoDeTexto = new ArrayList<>();

        archivoEntrada = new FileReader(directorioPath+ "/" + nombreArchivo);
        bufferEntrada =  new BufferedReader(archivoEntrada);

        while((linea = bufferEntrada.readLine())!= null){

            contenidoDeTexto.add(linea);
        }

        bufferEntrada.close();
        archivoEntrada.close();


        return  contenidoDeTexto;



    }



}
