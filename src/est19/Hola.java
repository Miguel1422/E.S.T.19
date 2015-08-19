
package est19;

import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JOptionPane;


public class Hola {
    public static void main(String [] args){
        Conexion c=new Conexion();
        c.cargarDriver();
        //c.registrarReporte(3, "miguel", "mal portado", "2015-05-18", "estas bien feo", 7);
        //c.registrarAlumno(6, "juan", "papel", "higienico", 2, "b", "juares3", "5584896", "soila", "mato", "911", "diarrea", "polvo");
        //JOptionPane.showMessageDialog(null, "Hola", "Adios", JOptionPane.INFORMATION_MESSAGE, null);
        Config.abrirConfiguracion();
        //c.subirGrado();
    }
}
