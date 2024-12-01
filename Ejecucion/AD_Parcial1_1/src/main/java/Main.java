// Acceso a Datos (AD)
// Parcial 1, 21/11/2024
// Profesor: Pedro Santarrufina
// Alumno: David Moreno Bolivar

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		
		// Usamos un unico scanner para toda la app
		Scanner teclado = new Scanner(System.in);
		// Array con enunciado y opciones
		String[] opciones = {
			"Con que SGBD quiere conectar?",
			"SQLite",
			"HSQLDB"
		};
		
		int seleccion = Menu.menu(opciones, teclado);
		
		Connection con = null;
		String tipoBD = null;
		String directorio;
		File archivo = null;
		if (seleccion == 1) {
			System.out.println("Conecta a la BD SQLite");
    		tipoBD = "sqlite";
    		directorio = "sqlite/";
    		archivo = new File(directorio + "database");
		} else if (seleccion == 2) {
			System.out.println("Conecta a la BD HSQLDB");
			tipoBD = "hsqldb";
			directorio = "hsqldb/";
			archivo = new File(directorio + "database");
		}
		
		// MOSTRAMOS EL NOMBRE DEL DRIVER (1.5PTO)
		con = conectaBD(tipoBD, archivo);
		System.out.println("Driver: " + infoDriver(con));
		System.out.println();
		
		// Array con enunciado y opciones
				String[] opciones2 = {
					"Que operacion desea realizar?",
					"Mostrar un listado de alumnos",
					"Mostrar un listado de municipios",
					"Introducir un nuevo alumno (PreparedStatement)",
					"Introducir un nuevo municipio (Procedimiento)",
					"Salir",
				};
				
		Boolean salir = false;
		while (salir == false) {
			
			switch (Menu.menu(opciones2, teclado)) {
	        case 1:
	        	System.out.println("opcion uno: Mostrar un listado de alumnos");
	        	mostrar_alumnos(con); // (1 PTO)
	        	System.out.println();
	        	break;
	        case 2:
	        	System.out.println("opcion dos: Mostrar un listado de municipios");
	        	mostrar_municipios(con); // (1 PTO)
	        	System.out.println();
	        	break;
	        case 3:
	        	System.out.println("opcion tres: Introducir un nuevo alumno (PreparedStatement)");
	        	introduce_alumno(con,teclado); // (1.5 PTO)
	        	System.out.println();
	        	break;
	        case 4:
	        	System.out.println("opcion cuatro: Introducir un nuevo municipio (Procedimiento)");
	        	introduce_municipio(con,teclado); // (1 PTO)
	        	System.out.println();
	        	break;
	        case 5:
	        	System.out.println("opcion cinco: Salir");
	        	salir = !salir;
	        	System.out.println();
	        	break;
			}
		}
		
		
		System.out.println("Cerramos conexion");
		con.close();

	}

	
	
	
	
	
	
	
	





	// FUNCIONES PUNTUABLES

	public static void mostrar_alumnos(Connection con) throws SQLException {
	    try (Statement stmt = con.createStatement()) {
	    	String nombre = null;
	    	String apellido = null;
	    	String codigoMunicipio = null;
	    	
        	System.out.println("| NOMBRE | APELLIDOS | ID_MUNICIPIO |");
	    	String query = "SELECT * FROM alumnos;";
	        ResultSet rs1 =  stmt.executeQuery(query);
	        while (rs1.next()) {
	        	nombre = rs1.getString("nombre");
	        	apellido = rs1.getString("apellidos");
	        	codigoMunicipio = rs1.getString("id_municipio");
	        	System.out.println("| " + nombre + " | " + apellido + " | " + codigoMunicipio + " | ");
	        }
	    }

	}
	


	public static void mostrar_municipios(Connection con) throws SQLException {
	    try (Statement stmt = con.createStatement()) {
	    	String id_municipio = null;
	    	String nombre = null;
	    	Integer n_habitantes = null;
	    	
        	System.out.println("| ID_MUNICIPIO | NOMBRE | N_HABITANTES |");
	    	String query = "SELECT * FROM municipios;";
	        ResultSet rs1 =  stmt.executeQuery(query);
	        while (rs1.next()) {
	        	id_municipio = rs1.getString("id_municipio");
	        	nombre = rs1.getString("nombre");
	        	n_habitantes = rs1.getInt("n_habitantes");
	        	System.out.println("| " + id_municipio + " | " + nombre + " | " + Integer.toString(n_habitantes) + " | ");
	        }
	    }

	}
	
	
	
	public static void introduce_alumno(Connection con, Scanner teclado) throws SQLException{
		// TODO: Falla en sqlite si el CP no existe
		
		Integer nia = null;
		String nombre = null;
    	String apellido = null;
    	String codigoMunicipio = null;
		
    	System.out.print("NIA: ");
    	nia = teclado.nextInt();
    	System.out.print("Nombre: ");
    	nombre = teclado.next();
    	System.out.print("Apellido: ");
    	apellido = teclado.next();
    	System.out.print("CodigoMunicipio: ");
    	codigoMunicipio = teclado.next();
    	
    	
		String query = "INSERT INTO alumnos (nia, nombre, apellidos, id_municipio) VALUES (?,?,?,?);";
		try (PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setInt(1, nia);
			pstmt.setString(2, nombre);
			pstmt.setString(3, apellido);
			pstmt.setString(4, codigoMunicipio);
			
			pstmt.executeUpdate();
		}
	}
		
		
		
	public static int introduce_municipio(Connection con, Scanner teclado) throws SQLException{

		String BdRequerida = "HSQL Database Engine Driver";
		String BdActual = infoDriver(con);
		
		if (!BdActual.equals(BdRequerida)) {
			System.out.println("Opcion disponible solo en HSQLDB");
			return -1;
		}
		
		Integer id_municipio = null;
		String nombre = null;
    	Integer n_habitantes = null;
		
    	System.out.print("id_municipio: ");
    	id_municipio = teclado.nextInt();
    	System.out.print("Nombre: ");
    	nombre = teclado.next();
    	System.out.print("n_habitantes: ");
    	n_habitantes = teclado.nextInt();
    	
    	String query = "{CALL nuevo_municipio (?,?,?)}";

    	try (CallableStatement cstmt = con.prepareCall(query)) {
    		cstmt.setInt(1, id_municipio);
    		cstmt.setString(2, nombre);
    		cstmt.setInt(3, n_habitantes);
			
    		cstmt.executeUpdate();
		}
			
			return 0;
		}
	
	
	
	// FUNCIONES ADICIONALES
	
	public static String infoDriver(Connection con) throws SQLException {
		DatabaseMetaData metaData = con.getMetaData();
		return metaData.getDriverName();
	}
	
	

	public static Connection conectaBD(String tipoBD, File archivo) throws SQLException {
	    if (!tipoBD.equals("sqlite") && !tipoBD.equals("hsqldb")) {
	        return null;
	    }

	    if ("sqlite".equals(tipoBD)) {
	        // Si la carpeta no existe sqlite da un error y no crea la BD
	        File parentDir = archivo.getParentFile();
	        if (parentDir != null && !parentDir.exists()) {
	            parentDir.mkdirs();	            
	        }
	    }

	    String StrJDBC = "jdbc:" + tipoBD + ":" + archivo;
	    return DriverManager.getConnection(StrJDBC);
	}
	
	

	
	
	
	
	
}











