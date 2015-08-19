package est19;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {

    final static File DIR_PRINCIPAL = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Reportes Alumnos");
    final static File DIR_FOTOS = new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Reportes Alumnos"
            + System.getProperty("file.separator") + "fotos");
    final static File CONFIG_FILE = new File(DIR_PRINCIPAL + System.getProperty("file.separator") + "cfg.txt");

    //Default values
    private static String IP = "";
    private static String USER = "";
    private static String PASS = "";

    private static String userA = "--USERNAME--:\t";
    private static String passA = "--PASS--:\t\t";
    private static String ipA = "--IP--:\t\t\t";

    public Config() {
        if (!DIR_FOTOS.exists()) {
            if (DIR_PRINCIPAL.mkdirs()) {
                System.out.println("Directorio principal creado");
            }
            if (DIR_FOTOS.mkdirs()) {
                System.out.println("Directorio de fotos creado");
            }

        }
        if (!CONFIG_FILE.exists()) {
            crearConfig();
        }

        abrirConfiguracion();
    }

    public static void abrirConfiguracion() {
        try {

            String currentLine = "";
            BufferedReader bf = new BufferedReader(new FileReader(CONFIG_FILE));
            boolean user = false;
            boolean pass = false;
            boolean ip = false;
            Conexion c = new Conexion();
            while ((currentLine = bf.readLine()) != null) {

                //COnfigurar e usuario
                if (currentLine.startsWith(userA)) {
                    setUSER(currentLine.substring(userA.length(), currentLine.length()));
                    user = true;
                    //System.out.println(currentLine.substring(userA.length(), currentLine.length()));
                }

                //Configurar contrasena
                if (currentLine.startsWith(passA)) {
                    setPASS(currentLine.substring(passA.length(), currentLine.length()));
                    //System.out.println("Pass correcta");
                    pass = true;
                    //System.out.println(currentLine.substring(passA.length(), currentLine.length()));
                }

                if (currentLine.startsWith(ipA)) {
                    ip = true;
                    setIP(currentLine.substring(ipA.length(), currentLine.length()));
                    //System.out.println(currentLine.substring(ipA.length(), currentLine.length()));
                }

            }

            /*System.out.println(pass);
            System.out.println(ip);
            System.out.println(user);*/

            if (!(pass && ip && user)) {
                crearConfig();
            }
            if (!c.probarConexion()) {
                System.out.println("Informaion incorrecta o servidor MySQL apagado");
                crearConfig();
            } else {
                System.out.println("Todo Correcto y accediendo al programa");
            }

        } catch (IOException ex) {
            crearConfig();
        }

        //System.out.println("User: " + getUSER());
        //System.out.println("IP: " + getIP());
    }

    public static void crearConfig() {
        new Configuracion(null, true).setVisible(true);
        BufferedWriter bw;
        //System.out.println(getUSER());

        try {
            bw = new BufferedWriter(new FileWriter(CONFIG_FILE, false));
            bw.append("\nATENCION es muy importante que se repete la sintaxis o el programa no funcionara\n\n");
            bw.append(userA + getUSER() + "\n");
            bw.append(passA + getPASS() + "\n");
            bw.append(ipA + getIP());
            bw.close();

        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
    }

    public static String getIP() {
        return IP;
    }

    public static void setIP(String IP) {
        Config.IP = IP;
    }

    public static String getUSER() {
        return USER;
    }

    public static void setUSER(String USER) {
        Config.USER = USER;
    }

    public static String getPASS() {
        return PASS;
    }

    public static void setPASS(String PASS) {
        Config.PASS = PASS;
    }

}
