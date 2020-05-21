package Formularios;

import Clases.Acciones;
import ConexionBD.BD;
import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.capture.event.DPFPErrorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPErrorEvent;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPReaderStatusEvent;
import com.digitalpersona.onetouch.capture.event.DPFPSensorAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPSensorEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

/**
 *
 * @author diego
 */
public class Empleado extends javax.swing.JFrame
{

    public Empleado()
    {
        TrustManager[] trustAllCerts = new TrustManager[]{
        new X509TrustManager() {

            public java.security.cert.X509Certificate[] getAcceptedIssuers()
            {
                return null;
            }
            public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
            {
                //No need to implement.
            }
            public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
            {
                //No need to implement.
            }
        }
};

// Install the all-trusting trust manager
try 
{
    SSLContext sc = SSLContext.getInstance("SSL");
    sc.init(null, trustAllCerts, new java.security.SecureRandom());
    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
} 
catch (Exception e) 
{
    System.out.println(e);
}
        initComponents();
        acciones = new Acciones();
        this.imgValidate = new ImageIcon(imgCheck.getImage().getScaledInstance(lblValidacion.getWidth(), lblValidacion.getHeight(), Image.SCALE_DEFAULT));
        this.imgError = new ImageIcon(imgTache.getImage().getScaledInstance(lblValidacion.getWidth(), lblValidacion.getHeight(), Image.SCALE_DEFAULT));
    }
    
    private Acciones acciones;
    
    //Varible que permite iniciar el dispositivo de lector de huella conectado
    // con sus distintos metodos.
    private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();

    //Varible que permite establecer las capturas de la huellas, para determina sus caracteristicas
    // y poder estimar la creacion de un template de la huella para luego poder guardarla
    private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();

    //Esta variable tambien captura una huella del lector y crea sus caracteristcas para auntetificarla
    // o verificarla con alguna guardada en la BD
    private DPFPVerification Verificador = DPFPGlobal.getVerificationFactory().createVerification();

    //Variable que para crear el template de la huella luego de que se hallan creado las caracteriticas
    // necesarias de la huella si no ha ocurrido ningun problema
    private DPFPTemplate template;
    public static String TEMPLATE_PROPERTY = "template";

    private boolean registroAsistencia = true;
    private boolean iniciado = false;
    private Date date = null;
    public DPFPFeatureSet featuresinscripcion;
    public DPFPFeatureSet featuresverificacion;
    //inicarlizar sonidos
    public AudioClip beepValidate = Applet.newAudioClip(getClass().getResource("/Sonidos/validate.wav"));
    public AudioClip beepError = Applet.newAudioClip(getClass().getResource("/Sonidos/error.wav"));
    //inicializar imágenes
    public ImageIcon imgCheck = new ImageIcon(getClass().getResource("/Imagenes/correcto.png"));
    public ImageIcon imgTache = new ImageIcon(getClass().getResource("/Imagenes/incorrecto.png"));
    public Icon imgValidate;
    public Icon imgError;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        pnlImagen = new javax.swing.JPanel();
        lblValidacion = new javax.swing.JLabel();
        pnlBotones = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuArrchivo = new javax.swing.JMenu();
        mniDatosGenerales = new javax.swing.JMenuItem();
        mniCerrarSesion = new javax.swing.JMenuItem();
        mniSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Asistencia");
        setIconImage(getIconImage());
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowOpened(java.awt.event.WindowEvent evt)
            {
                formWindowOpened(evt);
            }
        });

        pnlImagen.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "VALIDACIÓN", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Light", 0, 12))); // NOI18N
        pnlImagen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblValidacion.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        lblValidacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout pnlImagenLayout = new javax.swing.GroupLayout(pnlImagen);
        pnlImagen.setLayout(pnlImagenLayout);
        pnlImagenLayout.setHorizontalGroup(
            pnlImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlImagenLayout.createSequentialGroup()
                .addComponent(lblValidacion, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlImagenLayout.setVerticalGroup(
            pnlImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblValidacion, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
        );

        pnlBotones.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED), "ACCIONES", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        btnSalir.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13)); // NOI18N
        btnSalir.setText("SALIR");
        btnSalir.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(47, 47, 47))
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnSalir)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        mnuArrchivo.setText("Archivo");

        mniDatosGenerales.setText("Datos generales");
        mniDatosGenerales.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mniDatosGeneralesActionPerformed(evt);
            }
        });
        mnuArrchivo.add(mniDatosGenerales);

        mniCerrarSesion.setText("Cerrar sesión");
        mniCerrarSesion.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mniCerrarSesionActionPerformed(evt);
            }
        });
        mnuArrchivo.add(mniCerrarSesion);

        mniSalir.setText("Salir");
        mniSalir.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                mniSalirActionPerformed(evt);
            }
        });
        mnuArrchivo.add(mniSalir);

        jMenuBar1.add(mnuArrchivo);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnSalirActionPerformed
    {//GEN-HEADEREND:event_btnSalirActionPerformed
        acciones.salir();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void mniDatosGeneralesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mniDatosGeneralesActionPerformed
    {//GEN-HEADEREND:event_mniDatosGeneralesActionPerformed
        acciones.vntDatosGenerales();
    }//GEN-LAST:event_mniDatosGeneralesActionPerformed

    private void mniCerrarSesionActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mniCerrarSesionActionPerformed
    {//GEN-HEADEREND:event_mniCerrarSesionActionPerformed
        try
        {
            acciones.cerrarSesion(this);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex)
        {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        stop();
    }//GEN-LAST:event_mniCerrarSesionActionPerformed

    private void mniSalirActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_mniSalirActionPerformed
    {//GEN-HEADEREND:event_mniSalirActionPerformed
        acciones.salir();
    }//GEN-LAST:event_mniSalirActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt)//GEN-FIRST:event_formWindowOpened
    {//GEN-HEADEREND:event_formWindowOpened
        Iniciar();
        start();
    }//GEN-LAST:event_formWindowOpened

    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().
                getImage(ClassLoader.getSystemResource("Imagenes/icono.png"));
        return retValue;
    }
        
    protected void Iniciar(){
        Lector.addDataListener(new DPFPDataAdapter() {
            @Override public void dataAcquired(final DPFPDataEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
//                        EnviarTexto("Verificando...");
//                        EnviarTexto("La Asistencia ha sido Capturada");
                        date = new Date();
                        ProcesarCaptura(e.getSample());
                    }
                });
            }
        });

        Lector.addReaderStatusListener(new DPFPReaderStatusAdapter(){
            @Override public void readerConnected(final DPFPReaderStatusEvent e) {
            SwingUtilities.invokeLater(new Runnable() {	public void run() {
                if (! iniciado) {
//                    EnviarTexto("Sensor de huella digital conectado");
                    iniciado = true;
                }

                if (registroAsistencia) {
//                    EnviarTexto("****************************************");
//                    EnviarTexto("Registro de asistencia activado");
//                    EnviarTexto("****************************************");
                }

         }});}
         
            @Override public void readerDisconnected(final DPFPReaderStatusEvent e) {
            SwingUtilities.invokeLater(new Runnable() {	
                public void run() {
//                EnviarTexto("Sensor de huella digital no conectado");
            }});}
        });

        Lector.addSensorListener(new DPFPSensorAdapter() {
            @Override public void fingerTouched(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                    //EnviarTexto("El dedo ha sido colocado sobre el Lector de Huella");
                    }
                });
            }
            @Override public void fingerGone(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                    //EnviarTexto("El dedo ha sido quitado del Lector de Huella");
                    }
                });
            }
        });

        Lector.addErrorListener(new DPFPErrorAdapter(){
            public void errorReader(final DPFPErrorEvent e){
                SwingUtilities.invokeLater(new Runnable() { 
                    public void run() {
//                        EnviarTexto("Error: "+e.getError());
                    }
                });
            }
        });
    }
    
    public  void ProcesarCaptura(DPFPSample sample)
    {
        // Procesar la muestra de la huella y crear un conjunto de características con el propósito de inscripción.
        featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);

        // Procesar la muestra de la huella y crear un conjunto de características con el propósito de verificacion.
        featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

        // Comprobar la calidad de la muestra de la huella y lo añade a su reclutador si es bueno
        if (featuresinscripcion != null)
        {
            try{
                System.out.println("Las caracteristicas de la huella han sido creada");
                Reclutador.addFeatures(featuresinscripcion);// Agregar las caracteristicas de la huella a la plantilla a crear

                // Dibuja la huella dactilar capturada.
                Image image=CrearImagenHuella(sample);
//                DibujarHuella(image);

    //            btnVerificar.setEnabled(true);
    //            btnIdentificar.setEnabled(true);

                if (this.registroAsistencia) {
                    identificarHuella(false);
                    Reclutador.clear();
//                    lblHuella.setIcon(null);
                } else {
//                    btnCancelar.setEnabled(true);
                }
            }catch (DPFPImageQualityException ex) {
                System.err.println("Error: "+ex.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (! this.registroAsistencia) {
                    if (Reclutador.getFeaturesNeeded() == 3) {
                        int huellaRepetida = 0;
                        try {
                            huellaRepetida = identificarHuella(true);
                        } catch (IOException ex) {
                            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        if (huellaRepetida != 0) {
                            JOptionPane.showMessageDialog(null, "Ya existe un registro con esta huella. ID empleado: " + huellaRepetida);
                            Reclutador.clear();
//                            lblHuella.setIcon(null);
//                            btnGuardar.setEnabled(false);
//                            btnCancelar.setEnabled(false);
//                            EnviarTexto("****************************************");
                            EstadoHuellas();
                            return;
                        }
                    }

                   EstadoHuellas();
                   System.out.println("Estado plantilla: " + Reclutador.getTemplateStatus());
                   // Comprueba si la plantilla se ha creado.
                   switch(Reclutador.getTemplateStatus()) {
                        case TEMPLATE_STATUS_READY:	// informe de éxito y detiene  la captura de huellas
                            stop();
                            setTemplate(Reclutador.getTemplate());
                            //EnviarTexto("La plantilla de la huella ha sido creada");
    //                        btnIdentificar.setEnabled(false);
    //                        btnVerificar.setEnabled(false);
//                            btnGuardar.setEnabled(true);
//                            btnGuardar.grabFocus();
                            //jButton1.setEnabled(true);
                            break;

                        case TEMPLATE_STATUS_FAILED: // informe de fallas y reiniciar la captura de huellas
                            Reclutador.clear();
                            stop();
                            EstadoHuellas();
                            setTemplate(null);
                            JOptionPane.showMessageDialog(Empleado.this, "La Plantilla de la Huella no pudo ser creada, Repita el Proceso", "Inscripcion de Huellas Dactilares", JOptionPane.ERROR_MESSAGE);
                            start();
                            break;
                    }
                }
           }
        }
    }
    
    public  DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose){
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try {
            return extractor.createFeatureSet(sample, purpose);
        } catch (DPFPImageQualityException e) {
            return null;
        }
    }
    
    public  Image CrearImagenHuella(DPFPSample sample) {
        return DPFPGlobal.getSampleConversionFactory().createImage(sample);
    }

//    public void DibujarHuella(Image image) {
//        lblHuella.setIcon(new ImageIcon(
//        image.getScaledInstance(lblHuella.getWidth(), lblHuella.getHeight(), Image.SCALE_DEFAULT)));
//        repaint();
//    }

    public  void EstadoHuellas(){
//        EnviarTexto("Muestras de huellas faltantes para guardar: " + Reclutador.getFeaturesNeeded());
    }

//    public void EnviarTexto(String string) {
//        txtArea.append(string + "\n");
//    }

    public  void start(){
        Lector.startCapture();
        //EnviarTexto("Utilizando el Lector de Huella Dactilar ");
    }

    public  void stop(){
        Lector.stopCapture();
        //EnviarTexto("No se está usando el Lector de Huella Dactilar ");
    }

    public DPFPTemplate getTemplate() {
        return template;
    }

    public void setTemplate(DPFPTemplate template) {
        DPFPTemplate old = this.template;
        this.template = template;
        firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }
    
    BD con = new BD();
    /*
     * Guarda los datos de la huella digital actual en la base de datos
     */
    public void guardarHuella() throws  IOException {
        //Obtiene los datos del template de la huella actual
        ByteArrayInputStream datosHuella = new ByteArrayInputStream(template.serialize());
        Integer tamanioHuella=template.serialize().length;
        String instancia = acciones.leerInstancia();
        int idEmpleado = 0;
        int idInstancia = Integer.parseInt(instancia);
        while(true) {
            String strEmpleado = JOptionPane.showInputDialog("ID Empleado:");
//            String strInstancia = JOptionPane.showInputDialog("ID de instancia:");
            
            if (strEmpleado == null) {
                Reclutador.clear();
//                lblHuella.setIcon(null);
//                btnGuardar.setEnabled(false);
//                btnCancelar.setEnabled(false);
//                EnviarTexto("****************************************");
                EstadoHuellas();
                return; 
            }
            
            try {
                Connection c=con.conectar();
                idEmpleado = Integer.parseInt(strEmpleado);
//                idInstancia = Integer.parseInt(strInstancia);
                PreparedStatement verificarStmt = c.prepareStatement("SELECT id FROM empleados WHERE id_empleado=?");
                verificarStmt.setInt(1, idEmpleado);
                ResultSet rs = verificarStmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Ya existe una huella registrada para este empleado.");
                    continue;
                }
                
                c.close();
                break;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Debes ingresar un número, por favor revisa los detalles del empleado en sinomi.dscorp.com.mx");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Ocurrió un error. Por favor, vuelve a intentar.");
                Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                con.desconectar();
            }
        }
        
        try {
            //Establece los valores para la sentencia SQL
            Connection c=con.conectar(); //establece la conexion con la BD
            PreparedStatement guardarStmt = c.prepareStatement("INSERT INTO empleados (id_empleado, huella, instancia_id) values (?,?,?)");

            guardarStmt.setInt(1, idEmpleado);
            guardarStmt.setBinaryStream(2, datosHuella,tamanioHuella);
            guardarStmt.setInt(3, idInstancia);
            //Ejecuta la sentencia
            guardarStmt.execute();
            guardarStmt.close();
            JOptionPane.showMessageDialog(null,"Huella Guardada Correctamente");
            con.desconectar();
//            btnGuardar.setEnabled(false);
//            btnCancelar.setEnabled(false);
//            btnVerificar.grabFocus();
            
//            HttpResponse<JsonNode> jsonResponse = Unirest.post("https://sinomi.dscorp.com.mx/huella?")
//                                .header("accept", "application/json")
//                                .field("empleado", idEmpleado)
//                                .field("instancia_id", idInstancia)
//                                .asJson();
            
            String url = "https://sinomi.dscorp.com.mx/huella?empleado=" + idEmpleado + "&instancia_id=" + idInstancia;
            System.out.println(url);

            URL objURL = new URL(url);
            HttpURLConnection con = (HttpURLConnection) objURL.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            System.out.println(responseCode);
            
            BufferedReader in = new BufferedReader(
                                new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());
            
            if (responseCode == 200){
                lblValidacion.setIcon(imgValidate);
                eliminarImagen();
                beepValidate.play();
//                btnVerificar.grabFocus();
            } else {
                lblValidacion.setIcon(imgError);
                eliminarImagen();
                beepError.play();
                JOptionPane.showMessageDialog(null,"Ocurrió un error al guardar. Por favor, vuelve a intentar.");
            }
            System.out.println(response);
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null,"Ocurrió un error al guardar. Por favor, vuelve a intentar.");
//        } catch (UnirestException ex) {
//            JOptionPane.showMessageDialog(null,"Ocurrió un error al guardar. Por favor, vuelve a intentar.");
//            Logger.getLogger(CapturaHuella.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
    }
    
    /**
    * Verifica la huella digital actual contra otra en la base de datos
    */
    public void verificarHuella(String nom) {
        try {
            //Establece los valores para la sentencia SQL
            Connection c=con.conectar();
            //Obtiene la plantilla correspondiente a la persona indicada
            PreparedStatement verificarStmt = c.prepareStatement("SELECT huehuella FROM somhue WHERE huenombre=?");
            verificarStmt.setString(1,nom);
            ResultSet rs = verificarStmt.executeQuery();

            //Si se encuentra el nombre en la base de datos
            if (rs.next()){
                //Lee la plantilla de la base de datos
                byte templateBuffer[] = rs.getBytes("huehuella");
                //Crea una nueva plantilla a partir de la guardada en la base de datos
                DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
                //Envia la plantilla creada al objeto contendor de Template del componente de huella digital
                setTemplate(referenceTemplate);

                // Compara las caracteriticas de la huella recientemente capturda con la
                // plantilla guardada al usuario especifico en la base de datos
                DPFPVerificationResult result = Verificador.verify(featuresverificacion, getTemplate());

                //compara las plantilas (actual vs bd)
                if (result.isVerified()) {
                    JOptionPane.showMessageDialog(null, "Las huella capturada coinciden con la de "+nom,"Verificacion de Huella", JOptionPane.INFORMATION_MESSAGE);
                    lblValidacion.setIcon(imgError);
                    eliminarImagen();
                    beepError.play();
                }
                else {
                    JOptionPane.showMessageDialog(null, "No corresponde la huella con "+nom, "Verificacion de Huella", JOptionPane.ERROR_MESSAGE);
                    lblValidacion.setIcon(imgError);
                    eliminarImagen();
                    beepError.play();
                }

            //Si no encuentra alguna huella correspondiente al nombre lo indica con un mensaje
            } else {
                lblValidacion.setIcon(imgError);
                eliminarImagen();
                beepError.play();
                JOptionPane.showMessageDialog(null, "No existe un registro de huella para "+nom, "Verificacion de Huella", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            //Si ocurre un error lo indica en la consola
            lblValidacion.setIcon(imgError);
            eliminarImagen();
            beepError.play();
            System.err.println("Error al verificar los datos de la huella.");
        }finally{
            con.desconectar();
        }
    }
    
    /**
    * Identifica a una persona registrada por medio de su huella digital
    */
    public int identificarHuella(boolean verificarHuellaRepetida) throws IOException{
        try {
            //Establece los valores para la sentencia SQL
            Connection c=con.conectar();

            //Obtiene todas las huellas de la bd
            PreparedStatement identificarStmt = c.prepareStatement("SELECT id_empleado,huella, instancia_id FROM empleados");
            ResultSet rs = identificarStmt.executeQuery();

            while(rs.next()){
                //Lee la plantilla de la base de datos
                byte templateBuffer[] = rs.getBytes("huella");
                int idEmpleado = rs.getInt("id_empleado");
                int idInstancia = rs.getInt("instancia_id");
                //Crea una nueva plantilla a partir de la guardada en la base de datos
                DPFPTemplate referenceTemplate = DPFPGlobal.getTemplateFactory().createTemplate(templateBuffer);
                //Envia la plantilla creada al objeto contendor de Template del componente de huella digital
                setTemplate(referenceTemplate);

                // Compara las caracteriticas de la huella recientemente capturda con la
                // alguna plantilla guardada en la base de datos que coincide con ese tipo
                DPFPVerificationResult result = Verificador.verify(featuresverificacion, getTemplate());
                
                
                //compara las plantilas (actual vs bd)
                //Si encuentra correspondencia dibuja el mapa
                //e indica el nombre de la persona que coincidió.
                if (result.isVerified()){
                    if (! verificarHuellaRepetida) {
                        System.out.println("Empleado: " + idEmpleado);
                        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
                        System.out.println("Hora: " + hourFormat.format(this.date));
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        System.out.println("Fecha: " + dateFormat.format(this.date));
                        System.out.println("Instancia: " + idInstancia);

                        //HttpResponse<JsonNode> jsonResponse;
                        //System.setProperty("javax.net.ssl.trustStore", 'C:/Program Files (x86)/Java/jre8/lib/security/cacerts');

                        String url = "https://sinomi.dscorp.com.mx/asistencia?empleado=" + idEmpleado
                                + "&hora=" + hourFormat.format(this.date) + "&fecha=" + dateFormat.format(this.date)+ "&instancia_id=" + idInstancia;
                        System.out.println(url);

                        URL obj = new URL(url);
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                        // optional default is GET
                        con.setRequestMethod("GET");

                        int responseCode = con.getResponseCode();
                        //System.out.println(responseCode);
                        System.out.println("Sending 'GET' request to URL : " + url);
                        System.out.println("Response Code : " + responseCode);

                        BufferedReader in = new BufferedReader(
                                new InputStreamReader(con.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();

                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();
                        
                        if (responseCode == 200){
                            lblValidacion.setIcon(imgValidate);
                            eliminarImagen();
                            beepValidate.play();
                            //inicia el contador para quitar los textos
                        } else {
                            lblValidacion.setIcon(imgValidate);
                            eliminarImagen();
                            beepError.play();
                        }

                        //print result
                        System.out.println(response.toString()+"\n");
                    }
                    return idEmpleado;
                }
            }
            if(! verificarHuellaRepetida) {
                //Si no encuentra alguna huella correspondiente al nombre lo indica con un mensaje
                //JOptionPane.showMessageDialog(null, "No existe ningún registro que coincida con la huella", "Verificacion de Huella", JOptionPane.ERROR_MESSAGE);
                System.out.println("No existe ningún registro que coincida con la huella");
//                EnviarTexto("No existe ningún registro que coincida con la huella");
                lblValidacion.setIcon(imgError);
                eliminarImagen();
                beepError.play();
                setTemplate(null);
                //inicia el contador para quitar los textos
//                timer.start();
            }
        } catch (SQLException e) {
            //Si ocurre un error lo indica en la consola
            System.err.println("Error al identificar huella dactilar."+e.getMessage());
//            EnviarTexto("Error al identificar huella dactilar.");
            lblValidacion.setIcon(imgError);
            eliminarImagen();
            beepError.play();
        } finally{
            con.desconectar();
        }
        return 0;
    }
    
    public void eliminarImagen(){
        int delay = 2500; //millisegundos
        ActionListener taskPerformer = new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent evt) {
                 lblValidacion.setIcon(null);
           }
        };
        Timer timer = new Timer(delay, taskPerformer);
        timer.setRepeats(false);
        timer.start();
    }
    
    
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new Empleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btnSalir;
    javax.swing.JMenuBar jMenuBar1;
    javax.swing.JLabel lblValidacion;
    javax.swing.JMenuItem mniCerrarSesion;
    javax.swing.JMenuItem mniDatosGenerales;
    javax.swing.JMenuItem mniSalir;
    javax.swing.JMenu mnuArrchivo;
    javax.swing.JPanel pnlBotones;
    javax.swing.JPanel pnlImagen;
    // End of variables declaration//GEN-END:variables
}
