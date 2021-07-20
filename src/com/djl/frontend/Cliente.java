
package com.djl.frontend;
import com.djl.backend.Archivo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class Cliente extends javax.swing.JFrame {
    
    Controlador control;
    ArrayList<Archivo> archivos=new ArrayList<Archivo>();
    
    public Cliente() throws IOException {
        initComponents();
        control = new Controlador();
        actualizarTabla();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        BorrarArchivo = new javax.swing.JMenuItem();
        DescargarArchivo = new javax.swing.JMenuItem();
        jFrame1 = new javax.swing.JFrame();
        tabOptions = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        btnCreate = new javax.swing.JButton();
        btnChooseCreate = new javax.swing.JButton();
        lblFileStatusC = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsr = new javax.swing.JTable();
        lblUsr = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDoc = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        BorrarArchivo.setText("Eliminar archivo");
        BorrarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BorrarArchivoActionPerformed(evt);
            }
        });
        jPopupMenu1.add(BorrarArchivo);

        DescargarArchivo.setText("Descargar");
        DescargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DescargarArchivoActionPerformed(evt);
            }
        });
        jPopupMenu1.add(DescargarArchivo);

        jFrame1.setBackground(new java.awt.Color(153, 255, 153));

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 847, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 596, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DJL Cloud Manager");
        setForeground(new java.awt.Color(51, 255, 51));

        btnCreate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCreate.setText("Subir");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });

        btnChooseCreate.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnChooseCreate.setText("Escoger Archivo");
        btnChooseCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChooseCreateActionPerformed(evt);
            }
        });

        lblFileStatusC.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblFileStatusC.setText("Escoga un Archivo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(btnCreate))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFileStatusC)
                            .addComponent(btnChooseCreate))))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(btnChooseCreate)
                .addGap(28, 28, 28)
                .addComponent(btnCreate)
                .addGap(44, 44, 44)
                .addComponent(lblFileStatusC)
                .addContainerGap(199, Short.MAX_VALUE))
        );

        tabOptions.addTab("Subir", jPanel1);

        tblUsr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Usuario", "Permiso", "Últ. sesión"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblUsr);

        lblUsr.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblUsr.setText("USUARIOS DEL SISTEMA");

        tblDoc = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tblDoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Tamaño", "Formato", "Creado por", "Últ. modificación"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDoc.setComponentPopupMenu(jPopupMenu1);
        jScrollPane2.setViewportView(tblDoc);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("DOCUMENTOS ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(586, 586, 586)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblUsr)
                                .addGap(230, 230, 230))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tabOptions, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(587, 587, 587))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 722, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lblUsr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tabOptions))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BorrarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BorrarArchivoActionPerformed
        try {
            // TODO add your handling code here:
            int index = tblDoc.getSelectedRow();
            control.eliminar(tblDoc.getValueAt(index, 0).toString());
            control.update(tblDoc);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BorrarArchivoActionPerformed

    private void btnChooseCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChooseCreateActionPerformed
        control.escogerArchivo(lblFileStatusC);
    }//GEN-LAST:event_btnChooseCreateActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
        try {
            // TODO add your handling code here:
            control.subirArchivo(lblFileStatusC);
            actualizarTabla();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCreateActionPerformed

    private void DescargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DescargarArchivoActionPerformed
        // TODO add your handling code here:
        int index = tblDoc.getSelectedRow();
        try {
            if(index>=0)
                System.out.println(tblDoc.getValueAt(index, 0).toString());
                control.descargarArchivo(tblDoc.getValueAt(index, 0).toString());
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_DescargarArchivoActionPerformed
    private void actualizarTabla(){
        try {
            DefaultTableModel tabla = control.update(tblDoc);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new Cliente().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem BorrarArchivo;
    private javax.swing.JMenuItem DescargarArchivo;
    private javax.swing.JButton btnChooseCreate;
    private javax.swing.JButton btnCreate;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblFileStatusC;
    private javax.swing.JLabel lblUsr;
    private javax.swing.JTabbedPane tabOptions;
    private javax.swing.JTable tblDoc;
    private javax.swing.JTable tblUsr;
    // End of variables declaration//GEN-END:variables
}
