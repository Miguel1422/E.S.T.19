package est19;

import java.awt.Image;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Principal extends javax.swing.JFrame {

    private ArrayList<Alumno> listaAlumnos = new ArrayList<>();
    private Alumno alumnoActual;
    Conexion c = new Conexion();
    ImageIcon icono = new ImageIcon(getClass().getResource("/image/palomita.png"));

    public Principal() {
        initComponents();
        setLocationRelativeTo(null);
        mostrarAlumnos();
        tfFecha.setDate(new Date());
    }

    private void actualizarAlumnoActual() {
        int a = cbNombre.getSelectedIndex();
        alumnoActual = listaAlumnos.get(a);
        cambiarImagen();
    }

    private void agregarReporte() {
        if (c.agregarReporte(tfMaestro.getText(), taCausa.getText(), obtenerFecha(),
                taObservaciones.getText(), alumnoActual.getId())) {
            JOptionPane.showMessageDialog(null, "Registro Exitoso", "", JOptionPane.INFORMATION_MESSAGE, icono);
        } else {
            JOptionPane.showMessageDialog(this, "No se ha podido registrar ", "Error", JOptionPane.ERROR_MESSAGE, null);
        }
    }

    public void cambiarImagen() {
        String nombre = cbNombre.getSelectedItem().toString().replace(" ", "");
        File dir = Config.DIR_FOTOS;
        ImageIcon ico = new ImageIcon((dir.getAbsolutePath()) + System.getProperty("file.separator") + nombre + ".jpg");
        Image img = ico.getImage();
        Image newImg = img.getScaledInstance(170, 185, java.awt.Image.SCALE_SMOOTH);

        //System.out.println("cambiando imagen: " + nombre);
        lImagen.setText(null);
        lImagen.setIcon(new ImageIcon(newImg));
    }

    public boolean datosCompletos() {
        if (taCausa.getText().equals("")) {
            return false;
        } else if (tfMaestro.getText().equals("")) {
            return false;
        } else if (tfFecha.getDate() == null) {
            return false;
        }

        return true;
    }

    public void mostrarAlumnos() {

        int grado = cbGrado.getSelectedIndex() + 1;
        char grupo = cbGrupo.getSelectedItem().toString().charAt(0);
        //System.out.println(grado + " Grupo:" + grupo);
        listaAlumnos = c.obtenerAlumno(grado, grupo);

        if (listaAlumnos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay alumnos de " + grado + " " + grupo, "", JOptionPane.ERROR_MESSAGE, null);
            bGuardar.setEnabled(false);
            return;
        } else {
            bGuardar.setEnabled(true);
        }

        String[] nombres = new String[listaAlumnos.size()];
        for (int i = 0; i < nombres.length; i++) {
            nombres[i] = listaAlumnos.get(i).getNombreCompleto();
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(nombres);

        cbNombre.setModel(model);
        cambiarImagen();
        actualizarAlumnoActual();
    }

    public String obtenerFecha() {
        Date d = tfFecha.getDate();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        return f.format(d);
    }
    
    private void vaciarCampos(){
        taCausa.setText(null);
        tfMaestro.setText(null);
        taObservaciones.setText(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        panelContenedor = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbGrado = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cbGrupo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cbNombre = new javax.swing.JComboBox();
        lImagen = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taCausa = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        tfMaestro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfFecha = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taObservaciones = new javax.swing.JTextArea();
        bGuardar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Agregar Reporte");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Grado:");

        cbGrado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbGrado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1er Grado", "2do Grado", "3er Grado" }));
        cbGrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGradoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Grupo:");

        cbGrupo.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbGrupo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "A", "B" }));
        cbGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGrupoActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Nombre:");

        cbNombre.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbNombre.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Maria del Rocio Morales Godoy", "Eduardo Rafael Olivares Sanchez" }));
        cbNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNombreActionPerformed(evt);
            }
        });

        lImagen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lImagen.setText("Imagen");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Causa:");

        taCausa.setColumns(20);
        taCausa.setWrapStyleWord(true);
        taCausa.setLineWrap(true);
        taCausa.setRows(5);
        jScrollPane1.setViewportView(taCausa);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Maestro:");

        tfMaestro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tfMaestro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfMaestroActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Fecha:");

        tfFecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Observaciones:");

        taObservaciones.setColumns(20);
        taObservaciones.setLineWrap(true);
        taObservaciones.setRows(5);
        taObservaciones.setWrapStyleWord(true);
        jScrollPane2.setViewportView(taObservaciones);

        bGuardar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        bGuardar.setText("Guardar");
        bGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelContenedorLayout = new javax.swing.GroupLayout(panelContenedor);
        panelContenedor.setLayout(panelContenedorLayout);
        panelContenedorLayout.setHorizontalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContenedorLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContenedorLayout.createSequentialGroup()
                        .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(panelContenedorLayout.createSequentialGroup()
                                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(204, 204, 204)
                                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(bGuardar))
                    .addGroup(panelContenedorLayout.createSequentialGroup()
                        .addComponent(lImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelContenedorLayout.createSequentialGroup()
                                .addGap(57, 57, 57)
                                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(cbGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContenedorLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(cbGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))
                            .addGroup(panelContenedorLayout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(jLabel5)))))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        panelContenedorLayout.setVerticalGroup(
            panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContenedorLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelContenedorLayout.createSequentialGroup()
                        .addComponent(bGuardar)
                        .addGap(35, 35, 35))
                    .addGroup(panelContenedorLayout.createSequentialGroup()
                        .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelContenedorLayout.createSequentialGroup()
                                .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContenedorLayout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cbGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(panelContenedorLayout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContenedorLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(29, 29, 29)))
                                .addGap(58, 58, 58)
                                .addComponent(jLabel5))
                            .addGroup(panelContenedorLayout.createSequentialGroup()
                                .addComponent(lImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(11, 11, 11)
                        .addGroup(panelContenedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))))
        );

        jScrollPane4.setViewportView(panelContenedor);

        jMenu1.setText("Archivo");

        jMenuItem1.setText("Nuevo Alumno");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edicion");

        jMenuItem2.setText("Ver o editar reportes");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new NuevoAlumno(this, true).setVisible(true);
        mostrarAlumnos();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new Reportes(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void bGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGuardarActionPerformed
        if (datosCompletos()) {
            agregarReporte();
            vaciarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Tienes que rellenar todos los campos", "", JOptionPane.ERROR_MESSAGE, null);
        }

    }//GEN-LAST:event_bGuardarActionPerformed

    private void tfMaestroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfMaestroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfMaestroActionPerformed

    private void cbNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNombreActionPerformed
        actualizarAlumnoActual();
    }//GEN-LAST:event_cbNombreActionPerformed

    private void cbGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGrupoActionPerformed
        mostrarAlumnos();
    }//GEN-LAST:event_cbGrupoActionPerformed

    private void cbGradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGradoActionPerformed
        mostrarAlumnos();
    }//GEN-LAST:event_cbGradoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
        //</editor-fold>

        new Config();

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bGuardar;
    private javax.swing.JComboBox cbGrado;
    private javax.swing.JComboBox cbGrupo;
    private javax.swing.JComboBox cbNombre;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lImagen;
    private javax.swing.JPanel panelContenedor;
    private javax.swing.JTextArea taCausa;
    private javax.swing.JTextArea taObservaciones;
    private com.toedter.calendar.JDateChooser tfFecha;
    private javax.swing.JTextField tfMaestro;
    // End of variables declaration//GEN-END:variables
}
