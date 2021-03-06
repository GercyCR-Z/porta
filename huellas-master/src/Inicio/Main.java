package Inicio;

import ConexionBD.BD;
import Formularios.Login;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class Main
{
    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException, NoSuchAlgorithmException, KeyManagementException
    {
        try
        {
            // Create a trust manager that does not validate certificate chains
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            BD cnxn = new BD();
            cnxn.conectar();
            Login login = new Login();
            login.verificarLogin();
        }
        catch (InstantiationException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (UnsupportedLookAndFeelException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
