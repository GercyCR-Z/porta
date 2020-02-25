package ConexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class BD
{
    
//    public  String puerto="3306";
//    public  String nomservidor="localhost";
//    public  String db="demohuellas";
//    public  String user="root";
//    public  String pass="root";
    
    public  String puerto="3320";
    public  String nomservidor="189.204.31.157";
    public  String db="huellas";
    public  String user="root";
    public  String pass="software";
    
    Connection conn=null;
    Statement st;

    public Connection conectar()
    {
        try{
            String ruta="jdbc:mysql://" + nomservidor + ":3320/" +db;
            conn = DriverManager.getConnection(ruta,user,pass);
            st = conn.createStatement();

            if (conn!=null)
            {
                System.out.println("Conexión a base de datos");
            }
            else if (conn==null)
            {
                throw new SQLException();
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
//        } catch (ClassNotFoundException e) {
//            JOptionPane.showMessageDialog(null, "Se produjo el siguiente error: "+e.getMessage());
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Se produjo el siguiente error: "+e.getMessage());
        }finally{
            return conn;
        }
    }

    public void desconectar()
    {
        conn = null;
        System.out.println("Desconexión de base de datos");
    }
    
    
    
}
