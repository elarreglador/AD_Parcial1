/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eval1;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.thoughtworks.xstream.security.WildcardTypePermission;

public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //No modifiques el código de la función main
        String filePath = "archivo.xml";
        Grupo grupo7J = Grupo.getFakeGroup();
		
        System.out.println("Grupo de alumnos para serializar:");
        System.out.println(grupo7J.toString()+"\n");

        serializeGroup(grupo7J, filePath);

        Grupo new7J = deserializeGroup(filePath);
		
        System.out.println("Grupo de alumnos deserializado:");
        System.out.println(new7J.toString()+"\n");

}

    
    
    
     /**
     * Serializa un objeto de la clase Grupo en un archivo XML (1.5 PTOS)
     * @param grupo7J
     * @param filePath
     */
    public static void serializeGroup(Grupo grupo7J, String filePath) {
    	
    	try {	    	
	    	XStream flujox = new XStream();
			flujox.allowTypes(new Class[] {Alumno.class, Grupo.class});
			
			File archivo = new File(filePath);
		
			flujox.toXML(grupo7J, new FileOutputStream(archivo));
		} catch (FileNotFoundException e) {
			System.out.println("serializeGroup() excepcion: " + e);
		}
    }

    
    
    /**
     * Deserializa un grupo que está en un archivo XML (1.5 PTOS)
     * @param filePath
     * @return
     */
    public static Grupo deserializeGroup(String filePath)  {

    	// Si la lectura falla retornara null
    	Grupo grupo = null; 
    	
    	try {	
	    	// No pasar de String a File me ha vuelto loco!
	    	File archivo = new File(filePath);
	
	    	// Crea XStream
	    	XStream flujox = new XStream();
	
	    	flujox.alias("grupo", Grupo.class);
	    	
	        // Permitir deserializar la clase
	        flujox.addPermission(new WildcardTypePermission(new String[] { "**" }));
	    	
	        // deserializar archivo xml
	        grupo = (Grupo) flujox.fromXML(archivo);
    	} catch (Exception e) {
    		System.out.println("deserializeGroup() excepcion: " + e);
    	}
        
		return grupo;
    }

}
