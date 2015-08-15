/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package est19;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Reportes extends javax.swing.JDialog {

    private ArrayList<Alumno> listaAlumnos = new ArrayList<>();
    ArrayList<Reporte> reportes;
    private Alumno alumnoActual;
    Conexion c = new Conexion();
    ImageIcon icono = new ImageIcon(getClass().getResource("/image/palomita.png"));
    Reporte r = new Reporte();

    public Reportes(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        mostrarAlumnos();
    }

    private void actualizarAlumnoActual() {
        int a = cbNombre.getSelectedIndex();
        alumnoActual = listaAlumnos.get(a);
        cambiarImagen();

        tieneReportes();
        //System.out.println(alumnoActual.getId());
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

    public void mostrarAlumnos() {
        int grado = cbGrado.getSelectedIndex() + 1;
        char grupo = cbGrupo.getSelectedItem().toString().charAt(0);
        //System.out.println(grado + " Grupo:" + grupo);
        listaAlumnos = c.obtenerAlumno(grado, grupo);

        if (listaAlumnos.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay alumnos de " + grado + " " + grupo, "", JOptionPane.ERROR_MESSAGE, null);
            return;
        }

        String[] nombres = new String[listaAlumnos.size()];
        for (int i = 0; i < nombres.length; i++) {
            nombres[i] = listaAlumnos.get(i).getNombreCompleto();
        }
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(nombres);

        cbNombre.setModel(model);
        cambiarImagen();
        actualizarAlumnoActual();
        tieneReportes();
    }

    private boolean tieneReportes() {
        reportes = c.getReportes(alumnoActual);
        int numReportes = reportes.size();
        if (numReportes > 0) {
            String[] p = new String[numReportes];
            for (int i = 0; i < p.length; i++) {
                p[i] = "" + (i + 1);
            }
            //System.out.println("Si tiene reportes: " + numReportes);
            cbReporte.setModel(new DefaultComboBoxModel(p));
            cbReporte.setEnabled(true);
            actualizarReportes();
            jCheckBox2.setEnabled(true);
            jButton2.setEnabled(true);
            return true;
        } else {
            cbReporte.setModel(new DefaultComboBoxModel());
            taCausa.setText(null);
            taObservaciones.setText(null);
            tfMaestro.setText(null);
            cbReporte.setEnabled(false);
            tfMaestro.setEditable(false);
            taCausa.setEditable(false);
            tfReportes.setText("" + 0);
            tfSus.setText(""+0);
            taObservaciones.setEditable(false);
            jCheckBox2.setSelected(false);
            jCheckBox2.setEnabled(false);
            jButton2.setEnabled(false);
            jButton1.setEnabled(false);
            jDateChooser1.setDate(null);
            jDateChooser1.setEnabled(false);
            return false;
        }

    }
    
    private boolean tieneReportes (Alumno a){
        return !c.getReportes(a).isEmpty();
    }

    private void actualizarReportes() {
        int itemSelected = cbReporte.getSelectedIndex();
        taCausa.setText(reportes.get(itemSelected).getCausa());
        taObservaciones.setText(reportes.get(itemSelected).getObservaciones());
        tfReportes.setText("" + reportes.size());
        tfSus.setText(reportes.size()/3+"");
        tfMaestro.setText(reportes.get(itemSelected).getMaestro());
        jDateChooser1.setDate(reportes.get(itemSelected).getFecha());
        // System.out.println("Tiene reportes");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        lImagen = new javax.swing.JLabel();
        cbGrado = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbGrupo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cbNombre = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfReportes = new javax.swing.JTextField();
        tfSus = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cbReporte = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taCausa = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        taObservaciones = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        tfMaestro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jCheckBox2 = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ver o editar reportes");

        lImagen.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lImagen.setText("Imagen");

        cbGrado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cbGrado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1er Grado", "2do Grado", "3er Grado" }));
        cbGrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGradoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Grado:");

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

        jLabel1.setText("No. de Reportes:");

        jLabel5.setText("No. de Suspensiones");

        tfReportes.setEditable(false);
        tfReportes.setBackground(new java.awt.Color(255, 255, 255));

        tfSus.setEditable(false);
        tfSus.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("Ver reporte");

        cbReporte.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbReporteActionPerformed(evt);
            }
        });

        jLabel7.setText("Causa:");

        taCausa.setEditable(false);
        taCausa.setColumns(20);
        taCausa.setRows(5);
        jScrollPane2.setViewportView(taCausa);

        jLabel8.setText("Observaciones:");

        taObservaciones.setEditable(false);
        taObservaciones.setColumns(20);
        taObservaciones.setRows(5);
        jScrollPane3.setViewportView(taObservaciones);

        jLabel9.setText("Maestro:");

        tfMaestro.setEditable(false);
        tfMaestro.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setText("Fecha:");

        jDateChooser1.setBackground(new java.awt.Color(255, 255, 255));
        jDateChooser1.setEnabled(false);

        jCheckBox2.setText("Editar");
        jCheckBox2.setEnabled(false);
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jButton1.setText("Guardar");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Borrar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(tfReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(cbReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(26, 26, 26)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(tfSus, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(cbGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(cbGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)))))
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jButton1)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(47, 47, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbGrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(29, 29, 29)))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfReportes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(cbReporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfSus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(tfMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addGap(40, 40, 40)
                                .addComponent(jLabel7))
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox2)
                            .addComponent(jButton1))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGrupoActionPerformed
        mostrarAlumnos();
    }//GEN-LAST:event_cbGrupoActionPerformed

    private void cbNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNombreActionPerformed
        actualizarAlumnoActual();
        tieneReportes();
    }//GEN-LAST:event_cbNombreActionPerformed

    private void cbGradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGradoActionPerformed
        mostrarAlumnos();
    }//GEN-LAST:event_cbGradoActionPerformed

    private void cbReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbReporteActionPerformed
        actualizarReportes();
    }//GEN-LAST:event_cbReporteActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        if (jCheckBox2.isSelected()) {
            tfMaestro.setEditable(true);
            taCausa.setEditable(true);
            taObservaciones.setEditable(true);
            jButton1.setEnabled(true);
            jDateChooser1.setEnabled(true);

        } else {
            tfMaestro.setEditable(false);
            taCausa.setEditable(false);
            taObservaciones.setEditable(false);
            jButton1.setEnabled(false);
            jDateChooser1.setEnabled(false);
            if (tieneReportes()) {
                actualizarReportes();
            }
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Reporte re = reportes.get(cbReporte.getSelectedIndex());
        re.setCausa(taCausa.getText());
        re.setFecha(jDateChooser1.getDate());
        re.setMaestro(tfMaestro.getText());
        re.setObservaciones(taObservaciones.getText());
        //System.out.println("ID: " + re.getIdReporte());
        if (c.editarReporte(re)) {
            JOptionPane.showMessageDialog(null, "Edicion correcta", "", JOptionPane.INFORMATION_MESSAGE, icono);
            if (tieneReportes()) {
                actualizarReportes();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int i = JOptionPane.showConfirmDialog(this, "Esta seguro de que desea eliminar este reporte al alumno");
        switch (i) {
            case JOptionPane.OK_OPTION:
                Reporte re = reportes.get(cbReporte.getSelectedIndex());
                if (c.borrarReporte(re)) {
                    JOptionPane.showMessageDialog(null, "Se borro correcatmente el reporte", "", JOptionPane.INFORMATION_MESSAGE, icono);
                    if (tieneReportes()) {
                        actualizarReportes();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error no se ha podido borrar el reporte", "", JOptionPane.ERROR_MESSAGE, null);
                }
                break;
            case JOptionPane.NO_OPTION:
            case JOptionPane.CANCEL_OPTION:
                break;
        }

    }//GEN-LAST:event_jButton2ActionPerformed

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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Reportes dialog = new Reportes(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cbGrado;
    private javax.swing.JComboBox cbGrupo;
    private javax.swing.JComboBox cbNombre;
    private javax.swing.JComboBox cbReporte;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lImagen;
    private javax.swing.JTextArea taCausa;
    private javax.swing.JTextArea taObservaciones;
    private javax.swing.JTextField tfMaestro;
    private javax.swing.JTextField tfReportes;
    private javax.swing.JTextField tfSus;
    // End of variables declaration//GEN-END:variables
}
