package est19;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Conexion {

    private static String IP = Config.getIP();
    private static String DB = "est19";
    private static String URL = "jdbc:mysql://" + IP + "/" + DB;
    private static String USER = Config.getUSER();
    private static String PASS = Config.getPASS();

    private Connection conexion;
    private Statement comando;

    public Conexion() {
        cargarDriver();
    }

    public void cargarDriver() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception ex) {

        }
    }

    public void crearConexion() {
        try {
            actualizar();
            conexion = DriverManager.getConnection(URL, USER, PASS);
            comando = conexion.createStatement();

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void actualizar() {
        setIP(Config.getIP());
        setPASS(Config.getPASS());
        setUSER(Config.getUSER());
        //System.out.println(" asd " + Config.getUSER());
        setURL("jdbc:mysql://" + getIP() + "/" + DB);
    }

    public boolean agregarReporte(String maestro, String causa, String fecha, String observaciones, int idAlumnos) {
        try {
            crearConexion();
            //comando.executeUpdate("INSERT INTO reportes(idReportes,Maestro,Causa,Fecha,Observaciones,idAlumnos) VALUES("+idReportes+Maestro+Causa+Fecha+Observaciones+idAlumnos+")");
            comando.executeUpdate("INSERT INTO `" + DB + "`.`reportes` (`Maestro`, `Causa`, `Fecha`, `Observaciones`, `idAlumnos`) VALUES ('" + maestro + "', '" + causa + "', '" + fecha + "', '" + observaciones + "', '" + idAlumnos + "')");
            conexion.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("error " + ex.toString());
            return false;
        }

    }

    public boolean agregarReporte(Reporte reporte) {
        try {
            crearConexion();
            //comando.executeUpdate("INSERT INTO reportes(idReportes,Maestro,Causa,Fecha,Observaciones,idAlumnos) VALUES("+idReportes+Maestro+Causa+Fecha+Observaciones+idAlumnos+")");
            comando.executeUpdate("INSERT INTO `" + DB + "`.`reportes` (`Maestro`, `Causa`, `Fecha`, `Observaciones`, `idAlumnos`) VALUES ('"
                    + reporte.getMaestro() + "', '" + reporte.getCausa() + "', '"
                    + reporte.getFecha() + "', '" + reporte.getObservaciones() + "', '" + reporte.getIdAlumnos() + "')");
            conexion.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("error " + ex.toString());
            return false;
        }

    }

    public boolean borrarReporte(Reporte r) {
        // "DELETE FROM `"+DB+"`.`reportes` WHERE `reportes`.`idReportes` = 3"

        try {
            crearConexion();
            int result = comando.executeUpdate("DELETE FROM `" + DB + "`.`reportes` WHERE `reportes`.`idReportes` = "
                    + r.getIdReporte());
            if (result == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            return false;
        }
    }

    public boolean borrarReporte(int r) {
        // "DELETE FROM `"+DB+"`.`reportes` WHERE `reportes`.`idReportes` = 3"

        try {
            crearConexion();
            int result = comando.executeUpdate("DELETE FROM `" + DB + "`.`reportes` WHERE `reportes`.`idReportes` = "
                    + r);

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return false;
    }

    public boolean editarReporte(Reporte r) {
        //UPDATE `"+DB+"`.`reportes` SET `Maestro` = 'Primera prueba (edicion)', `Causa` = 'Reporte a 1a asd asd asd (edicion)', `Fecha` = '2015-08-04', `Observaciones` = 'Primera prueba (edicion)' WHERE `reportes`.`idReportes` = 1
        try {
            crearConexion();
            int i = comando.executeUpdate("UPDATE `" + DB + "`.`reportes` SET `Maestro` = '"
                    + r.getMaestro() + "', `Causa` = '"
                    + r.getCausa() + "', `Fecha` = '"
                    + obtenerFecha(r.getFecha()) + "', `Observaciones` = '"
                    + r.getObservaciones() + "' WHERE `reportes`.`idReportes` = "
                    + r.getIdReporte());
            if (i == 1) {
                //System.out.println("Correcto");
                return true;
            } else {
                System.out.println("error");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al editar reporte");
            return false;
        }

    }

    public boolean editarReporte(String maestro, String causa, Date fecha, String obs, int id) {
        //UPDATE `"+DB+"`.`reportes` SET `Maestro` = 'Primera prueba (edicion)', `Causa` = 'Reporte a 1a asd asd asd (edicion)', `Fecha` = '2015-08-04', `Observaciones` = 'Primera prueba (edicion)' WHERE `reportes`.`idReportes` = 1
        try {
            crearConexion();
            int i = comando.executeUpdate("UPDATE `" + DB + "`.`reportes` SET `Maestro` = '"
                    + maestro + "', `Causa` = '" + causa + "', `Fecha` = '"
                    + fecha + "', `Observaciones` = '" + obs
                    + "' WHERE `reportes`.`idReportes` = " + id);
            if (i == 1) {
                System.out.println("Correcto");
                return true;
            } else {
                System.out.println("Error al");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error al editar reporte");
            return false;
        }

    }

    public int getSizeAlumnos() {
        int count = 0;

        try {
            crearConexion();
            ResultSet registro = comando.executeQuery("SELECT * FROM `alumnos`");

            while (registro.next()) {
                count++;

            }

            conexion.close();
        } catch (SQLException ex) {
            System.out.println("error " + ex.toString());
        }

        return count;
    }

    public ArrayList<Reporte> getReportes(Alumno a) {
        ArrayList<Reporte> r = new ArrayList<>();

        try {
            crearConexion();
            ResultSet rs = comando.executeQuery("SELECT * FROM `reportes` WHERE `idAlumnos` = " + a.getId());
            while (rs.next()) {
                Reporte reporte = new Reporte();
                reporte.setMaestro(rs.getString("Maestro"));
                reporte.setCausa(rs.getString("Causa"));
                reporte.setFecha(rs.getDate("Fecha"));
                reporte.setObservaciones(rs.getString("Observaciones"));
                reporte.setIdAlumnos(rs.getInt("idAlumnos"));
                reporte.setIdReporte(rs.getInt("idReportes"));
                r.add(reporte);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        //SELECT * FROM `"+DB+"`.`alumnos` WHERE `idAlumnos` = 23 ORDER BY `Grado` ASC
        return r;
    }

    public ArrayList<Alumno> obtenerAlumno(int grado, char grupo) {
        ArrayList<Alumno> alumnos = new ArrayList<>();

        try {
            crearConexion();
            ResultSet registro = comando.executeQuery("SELECT * FROM `alumnos` WHERE `Grado` = " + grado + " AND `Grupo` LIKE '" + grupo + "' ORDER BY `Nombre` ASC");

            while (registro.next()) {
                Alumno a = new Alumno();
                a.setNombre(registro.getString("nombre"));
                a.setApellidoPaterno(registro.getString("ApellidoPaterno"));
                a.setApellidoMaterno(registro.getString("ApellidoMaterno"));
                a.setGrado(grado);
                a.setGrupo("" + grupo);
                a.setDomicilio(registro.getString("Domicilio"));
                a.setTelefono(registro.getString("Telefono"));
                a.setNombreMama(registro.getString("NombreMama"));
                a.setNombrePapa(registro.getString("NombrePapa"));
                a.setTelefonoEmergencia(registro.getString("TelefonoEmergencia"));
                a.setEnfermedades(registro.getString("Enfermedades"));
                a.setAlergias(registro.getString("Alergias"));
                try {
                    a.setId(Integer.parseInt(registro.getString("idAlumnos")));
                } catch (Exception e) {
                    System.out.println("Error " + e.getMessage());
                }

                alumnos.add(a);

            }

            conexion.close();
        } catch (SQLException ex) {
            System.out.println("error " + ex.toString());
        }
        return alumnos;
    }

    //Todavia no funciona 
    public void obtenerAlumnos() {
        try {
            ArrayList<Alumno> alumnos = new ArrayList<>();
            crearConexion();
            ResultSet registro = comando.executeQuery("SELECT * FROM `alumnos` ORDER BY `alumnos`.`Nombre` ASC");

            while (registro.next()) {

                String a = registro.getString("nombre");
                //System.out.println(getSizeAlumnos());

            }

            conexion.close();
        } catch (SQLException ex) {
            System.out.println("error " + ex.toString());
        }
    }

    public boolean probarConexion() {
        try {
            actualizar();
            conexion = DriverManager.getConnection(URL, USER, PASS);
            comando = conexion.createStatement();
            return true;
        } catch (SQLException ex) {
            //Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error: " + ex.getMessage());
            return false;
        }
    }

    public boolean registrarAlumno(int idAlumnos, String Nombre, String ApellidoPaterno, String ApellidoMaterno, int Grado, String Grupo, String Domicilio, String Telefono, String NombreMama, String NombrePapa, String TelefonoEmergencia, String Enfermedades, String Alergias) {
        try {
            crearConexion();
            //comando.executeUpdate("INSERT INTO reportes(idReportes,Maestro,Causa,Fecha,Observaciones,idAlumnos) VALUES("+idReportes+Maestro+Causa+Fecha+Observaciones+idAlumnos+")");
            comando.executeUpdate("INSERT INTO alumnos (`Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Grado`, `Grupo`, `Domicilio`, `Telefono`, `NombreMama`, `NombrePapa`, `TelefonoEmergencia`, `Enfermedades`, `Alergias`) VALUES ('"
                    + Nombre + "','" + ApellidoPaterno + "','" + ApellidoMaterno + "','" + Grado + "','" + Grupo + "','" + Domicilio + "','" + Telefono + "','" + NombreMama + "','" + NombrePapa + "','" + TelefonoEmergencia + "','" + Enfermedades + "','" + Alergias + "'" + ")");

            conexion.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("error " + ex.toString());
            return false;
        }
    }

    public boolean registrarAlumno(Alumno alumno) {
        try {
            crearConexion();
            //comando.executeUpdate("INSERT INTO reportes(idReportes,Maestro,Causa,Fecha,Observaciones,idAlumnos) VALUES("+idReportes+Maestro+Causa+Fecha+Observaciones+idAlumnos+")");
            comando.executeUpdate("INSERT INTO alumnos (`Nombre`, `ApellidoPaterno`, `ApellidoMaterno`, `Grado`, `Grupo`, `Domicilio`, `Telefono`, `NombreMama`, `NombrePapa`, `TelefonoEmergencia`, `Enfermedades`, `Alergias`) VALUES ('"
                    + alumno.getNombre() + "','" + alumno.getApellidoPaterno() + "','" + alumno.getApellidoMaterno() + "','" + alumno.getGrado() + "','" + alumno.getGrupo() + "','" + alumno.getDomicilio() + "','" + alumno.getTelefono() + "','" + alumno.getNombreMama() + "','" + alumno.getNombrePapa() + "','" + alumno.getTelefonoEmergencia() + "','" + alumno.getEnfermedades() + "','" + alumno.getAlergias() + "'" + ")");

            conexion.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("error " + ex.toString());
            return false;
        }
    }

    public void registrarReporte(int idReportes, String Maestro, String Causa, String Fecha, String Observaciones, int idAlumnos) {
        try {
            crearConexion();
            comando.executeUpdate("INSERT INTO reportes (Maestro, Causa, Fecha, Observaciones) VALUES ('" + Maestro + "','" + Causa + "','" + Fecha + "','" + Observaciones + "'" + ")");
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("error " + ex.toString());
        }
    }

    public boolean subirGrado() {
        try {
            crearConexion();
            ResultSet registro = comando.executeQuery("SELECT * FROM `alumnos`");
            ArrayList<Integer> ids = new ArrayList<>();
            ArrayList<Integer> grados = new ArrayList<>();

            while (registro.next()) {
                int i = registro.getInt("idAlumnos");
                int g = registro.getInt("Grado");
                ids.add(i);
                grados.add(g);
            }

            for (int i = 0; i < ids.size(); i++) {
                if (grados.get(i) >= 3 ||grados.get(i) == 0 ) {
                    int r = comando.executeUpdate("UPDATE `" + DB + "`.`alumnos` SET `Grado` = '" + (0)
                            + "' WHERE `alumnos`.`idAlumnos` = " + ids.get(i));
                } else {
                    int r = comando.executeUpdate("UPDATE `" + DB + "`.`alumnos` SET `Grado` = '" + (grados.get(i) + 1)
                            + "' WHERE `alumnos`.`idAlumnos` = " + ids.get(i));
                }
//                if (r == 1) {
//                    ResultSet re = comando.executeQuery("SELECT Nombre FROM `"+DB+"`.`alumnos` WHERE `alumnos`.`idAlumnos`=" + ids.get(i));
//                    re.next();
//                    String nombre = re.getString("Nombre");
//                    System.out.println("Correcto para " + nombre);
//                    //System.out.println(ids.get(i));
//                }
            }
            conexion.close();
            return true;
        } catch (Exception e) {
            System.out.println("ERROR");
            return false;
        }
    }

    private String obtenerFecha(Date date) {
        Date d = date;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(d);
    }

    public static String getIP() {
        return IP;
    }

    public static void setIP(String IP) {
        Conexion.IP = IP;
    }

    public static String getURL() {
        return URL;
    }

    public static void setURL(String URL) {
        Conexion.URL = URL;
    }

    public static String getUSER() {
        return USER;
    }

    public static void setUSER(String USER) {
        Conexion.USER = USER;
    }

    public static String getPASS() {
        return PASS;
    }

    public static void setPASS(String PASS) {
        Conexion.PASS = PASS;
    }

}
