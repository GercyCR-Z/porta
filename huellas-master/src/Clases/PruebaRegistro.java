package Clases;

import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author diego
 */
public class PruebaRegistro
{
    
    public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
    {
        //Leer la distribución de Windows
        String value = WinRegistry.readString (
                WinRegistry.HKEY_LOCAL_MACHINE,                             //HKEY
                "SOFTWARE\\WOW6432Node\\Microsoft\\Windows NT\\CurrentVersion",           //Key
                "ProductName");                                              //ValueName
        System.out.println("Windows Distribution = " + value);  
        
        //Escribir en el Registro de Windows
        //para que la aplicación inicie como un servicio
        //y funcione en segundo plano
        WinRegistry.writeStringValue(
                WinRegistry.HKEY_LOCAL_MACHINE,                             //HKEY
                "SOFTWARE\\WOW6432Node\\Microsoft\\Windows\\CurrentVersion\\Run",           //Key
                "Huellas",
                "\"C:\\Program Files (x86)\\HuellaDigital\\FingerPrintRemoto.exe\""); //ValueName
//        System.out.println("Escribir en el registro = " + escribir);  

    }
    
}
