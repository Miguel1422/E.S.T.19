
package est19;

import java.util.Date;

public class Reporte {
    private int idReporte;
    private String maestro;
    private String causa;
    private Date fecha;
    private String Observaciones;
    private int idAlumnos;

    public int getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(int idReporte) {
        this.idReporte = idReporte;
    }

    public String getMaestro() {
        return maestro;
    }

    public void setMaestro(String maestro) {
        this.maestro = maestro;
    }

    public String getCausa() {
        return causa;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

    public int getIdAlumnos() {
        return idAlumnos;
    }

    public void setIdAlumnos(int idAlumnos) {
        this.idAlumnos = idAlumnos;
    }
}
