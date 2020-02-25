package Formularios;

import Clases.Acciones;
import ConexionBD.BD;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class DatosGenerales extends javax.swing.JFrame
{

    public DatosGenerales()
    {
        initComponents();
        acciones = new Acciones();
    }

    BD con = new BD();
    Statement st;
    Acciones acciones;
    
       
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txfID = new javax.swing.JTextField();
        txfNombre = new javax.swing.JTextField();
        txfDomicilio = new javax.swing.JTextField();
        txfRfc = new javax.swing.JTextField();
        cmbTipo = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnVerDatos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos Generales");
        setIconImage(getIconImage());
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel1.setText("ID:");

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel2.setText("NOMBRE:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel3.setText("DOMICILIO:");

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel4.setText("RFC:");

        jLabel5.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        jLabel5.setText("TIPO DE UNIDAD:");

        txfID.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        txfID.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txfID.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txfIDKeyTyped(evt);
            }
        });

        txfNombre.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N

        txfDomicilio.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N

        txfRfc.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        txfRfc.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyTyped(java.awt.event.KeyEvent evt)
            {
                txfRfcKeyTyped(evt);
            }
        });

        cmbTipo.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrativo", "Comercial" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txfRfc, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txfID, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txfNombre)
                    .addComponent(txfDomicilio))
                .addGap(45, 45, 45))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txfID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txfDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txfRfc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        btnGuardar.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnActualizarActionPerformed(evt);
            }
        });

        btnVerDatos.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        btnVerDatos.setText("VER DATOS");
        btnVerDatos.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnVerDatosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVerDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnVerDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnGuardarActionPerformed
    {//GEN-HEADEREND:event_btnGuardarActionPerformed
        try
        {
            int tipoUnidad=0;
            String cmb = cmbTipo.getSelectedItem().toString();
            if (cmb.equals("Administrativo"))
                tipoUnidad=1;
            else
                tipoUnidad=2;
            String registrar = "INSERT INTO datos_instancia "
                    + "VALUES ('" + Integer.parseInt(txfID.getText()) + "',"
                    + "'" + txfNombre.getText() + "',"
                    + "'" + txfDomicilio.getText() + "',"
                    + "'" + txfRfc.getText() + "',"
                    + "'" + tipoUnidad + "')";
            String numInstancia = txfID.getText();
            Connection c=con.conectar();
            st = c.prepareStatement(registrar);
            st.executeUpdate(registrar);
            JOptionPane.showConfirmDialog(this, "Datos Registrados con Éxito",
              "Registro de datos", JOptionPane.DEFAULT_OPTION);
            acciones.crearInstancia(numInstancia);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatosGenerales.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(DatosGenerales.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.desconectar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnActualizarActionPerformed
    {//GEN-HEADEREND:event_btnActualizarActionPerformed
        try
        {
            Connection c = con.conectar();
            int tipoUnidad=0;
            String cmb = cmbTipo.getSelectedItem().toString();
            if (cmb.equals("Administrativo"))
                tipoUnidad=1;
            else
                tipoUnidad=2;
            String modificar = modificar = "UPDATE datos_instancia SET "
                    + "nombre='" + txfNombre.getText() + "', "
                    + "domicilio='" + txfDomicilio.getText() + "', "
                    + "rfc='" + txfRfc.getText() + "', "
                    + "tipo_unidad_id=" + tipoUnidad + " "
                    + "WHERE id=" + Integer.parseInt(txfID.getText()) + "";
            
            st = c.prepareStatement(modificar);
            st.executeUpdate(modificar);
            JOptionPane.showConfirmDialog(this, "Datos Modificados con Éxito",
              "Edición de datos", JOptionPane.DEFAULT_OPTION);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatosGenerales.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.desconectar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void txfIDKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txfIDKeyTyped
    {//GEN-HEADEREND:event_txfIDKeyTyped
        char c = evt.getKeyChar();
        if(Character.isLetter(c)){
            getToolkit().beep();
            evt.consume();
        }
        
        if (txfID.getText().length()== 4) 

         evt.consume();
    }//GEN-LAST:event_txfIDKeyTyped

    private void btnVerDatosActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnVerDatosActionPerformed
    {//GEN-HEADEREND:event_btnVerDatosActionPerformed
        try
        {
            enviarDatos();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(DatosGenerales.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(DatosGenerales.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnVerDatosActionPerformed

    private void txfRfcKeyTyped(java.awt.event.KeyEvent evt)//GEN-FIRST:event_txfRfcKeyTyped
    {//GEN-HEADEREND:event_txfRfcKeyTyped
        if (txfRfc.getText().length()== 13) 

         evt.consume();
    }//GEN-LAST:event_txfRfcKeyTyped

    public void enviarDatos() throws SQLException, FileNotFoundException
    {
        String n="",d="",rfc="";
        int t=0, id=0;
        String numInstancia = acciones.leerInstancia();
        id = Integer.parseInt(numInstancia);
        Connection c=con.conectar();
        String sql = "SELECT * FROM datos_instancia where id="+id;
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while ( rs.next() )
        {
            n = rs.getString("nombre");
            d = rs.getString("domicilio");
            rfc = rs.getString("rfc");
            t = rs.getInt("tipo_unidad_id");
        }
        if (t==1)
            cmbTipo.setSelectedItem("Administrativo");
        else
            cmbTipo.setSelectedItem("Comercial");
        txfNombre.setText(n);
        txfDomicilio.setText(d);
        txfRfc.setText(rfc);
        txfID.setText(numInstancia);
    }
    
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/icono.png"));
        return retValue;
    }
    
    
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(DatosGenerales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(DatosGenerales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(DatosGenerales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(DatosGenerales.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new DatosGenerales().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnVerDatos;
    private javax.swing.JComboBox<String> cmbTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txfDomicilio;
    private javax.swing.JTextField txfID;
    private javax.swing.JTextField txfNombre;
    private javax.swing.JTextField txfRfc;
    // End of variables declaration//GEN-END:variables
}
