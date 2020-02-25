package Clases;

import ConexionBD.BD;
import Formularios.DatosGenerales;
import Formularios.Login;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class Acciones
{

    public Acciones()
    {
        datosUsuario = new Usuario();
        numInstancia = new Instancia();
    }
    
    private Usuario datosUsuario;
    private Instancia numInstancia;
    BD con = new BD();
    Connection cn = con.conectar();
    
    
    public void cerrarSesion(Frame vntn) throws FileNotFoundException, SQLException, IOException {
        Frame ventana = vntn;
        if (JOptionPane.showConfirmDialog(null, "¿Está seguro de cerrar la sesión actual?", "Cerrar sesión", 1) == 0) {
            
            String email = "";
            email = leerArchivoUsuario();
            String sql = "UPDATE usuarios SET estatus_Login_id='" + 2 + "' WHERE email='"+ email+"'";
            Statement st = cn.createStatement();
            st.executeUpdate(sql);
            
            borrarArchivo();
            
            ventana.dispose();
            //se crea la ventana login
            Login vntLogin = new Login();
            //obtenemos dimensión de la pantalla
            Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
            //obtenemos el tamaño de la ventana  
            Dimension frame = vntLogin.getSize();
            //para centrar la ventana lo hacemos con el siguiente cálculo  
            vntLogin.setLocation((pantalla.width - frame.width) / 2, (pantalla.height - frame.height) / 2);
            vntLogin.setVisible(true);
            
        }
    }

    public void salir()
    {
        if (JOptionPane.showConfirmDialog(null,
                "¿Estás seguro de cerrar el Sistema?",
                "Cerrar Sistema", 1) == 0) {
            System.exit(0);
        }
    }
    
    public void vntDatosGenerales()
    {
        DatosGenerales dg = new DatosGenerales();
        dg.setVisible(true);
    }
    
    public void crearArchivoUsuario(String e, String est) throws IOException
    {
        File archivoUsuario;
        archivoUsuario = new File("datosUsuario.txt");
        archivoUsuario.createNewFile();            
        String email = e;
        String estatus = est;

        datosUsuario.setEmail(email);
        datosUsuario.setEstatusLogin(estatus);

        FileWriter escArchivo = new FileWriter(archivoUsuario);
        PrintWriter escLinea = new PrintWriter(escArchivo);

        escLinea.println(email);
        escLinea.print(estatus);
        escLinea.close();
        escArchivo.close();
    }
    
    public String leerArchivoUsuario() throws FileNotFoundException
    {
        File archivoUsuario;
        String email;
        Scanner leer = null;
        archivoUsuario = new File("datosUsuario.txt");
        leer = new Scanner(archivoUsuario);
        if (leer.hasNextLine())
            datosUsuario.setEmail(leer.nextLine());
        if (leer.hasNextLine())
            datosUsuario.setEstatusLogin(leer.nextLine());
        email = datosUsuario.getEmail();
        return email;
    }
    
    public String[] verificarDatosArchivo() throws FileNotFoundException
    {
        File archivoUsuario;
        Scanner leer = null;
        archivoUsuario = new File("datosUsuario.txt");
        leer = new Scanner(archivoUsuario);
        if (leer.hasNextLine())
            datosUsuario.setEmail(leer.nextLine());
        if (leer.hasNextLine())
            datosUsuario.setEstatusLogin(leer.nextLine());
        String email = datosUsuario.getEmail();
        String estatus = datosUsuario.getEstatusLogin();
        String[] datosArchivo = {email, estatus};
        return datosArchivo;
    }
    
    public int estatusUsuarioBD() throws SQLException, FileNotFoundException
    {
        String[] d = verificarDatosArchivo();
        String email = d[0];
        int estatus = 0;
        String sql = "SELECT * FROM usuarios WHERE email ='"+email+"'" ;
        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next())
            estatus = rs.getInt("estatus_login_id");
        System.out.println(estatus);
        
        return  estatus;
    }
    
    public void sobreescribirArchivoUsuario(String e, int est) throws IOException
    {
        File archivoUsuario;

        archivoUsuario = new File("datosUsuario.txt");

        archivoUsuario.createNewFile();            
        String email = e;
        String estatus = String.valueOf(est);

        datosUsuario.setEmail(email);
        datosUsuario.setEstatusLogin(estatus);

        FileWriter escArchivo = new FileWriter(archivoUsuario);
        PrintWriter escLinea = new PrintWriter(escArchivo);

        escLinea.println(email);
        escLinea.print(estatus);
        escLinea.close();
        escArchivo.close();
    }
    
    public void borrarArchivo() throws IOException
    {
        File archivo;
        archivo = new File("datosUsuario.txt");
        System.out.println(archivo.getAbsolutePath());
        FileWriter escArchivo = new FileWriter(archivo);
        PrintWriter escLinea = new PrintWriter(escArchivo);
        escLinea.println("f");
        escLinea.print("2");
        escLinea.close();
        escArchivo.close();
    }
    
    public void crearInstancia(String n) throws IOException
    {
        File archivo;
        archivo = new File("numinstancia.txt");
        archivo.createNewFile();            
        String num = n;
        numInstancia.setNumInstancia(num);

        FileWriter escArchivo = new FileWriter(archivo);
        PrintWriter escLinea = new PrintWriter(escArchivo);
        escLinea.print(num);
        escLinea.close();
        escArchivo.close();
    }
    
    public String leerInstancia() throws FileNotFoundException
    {
        File archivo;
        String num;
        Scanner leer = null;
        archivo = new File("numinstancia.txt");
        leer = new Scanner(archivo);
        if (leer.hasNextLine())
            numInstancia.setNumInstancia(leer.nextLine());
        num = numInstancia.getNumInstancia();
        return num;
    }
    
    public Usuario getDatos()
    {
        return datosUsuario;
    }
    
}
