/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eval1;

import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.FileNotFoundException;

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
     * Serializa un objeto de la clase Grupo en un archivo XML 
     * @param grupo7J
     * @param filePath
     */
    public static void serializeGroup(Grupo grupo7J, String filePath) {
        //TODO Completa el código de esta función
    }

    /**
     * Deserializa un grupo que está en un archivo XML
     * @param filePath
     * @return
     */
    public static Grupo deserializeGroup(String filePath)  {
        //TODO Completa el código de esta función
    	return null;
    }

}
