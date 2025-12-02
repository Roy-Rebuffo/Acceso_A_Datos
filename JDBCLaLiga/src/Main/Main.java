package Main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Scanner;


public class Main {
	
	/*****************************************************************/
	public static String BBDD_URL = "jdbc:mysql://localhost:3307/";
	public static String DB_NAME = "laliga";
	public static String USER = "root";       
	public static String PASS = "root";
	/*****************************************************************/
	public static final String SQL_SELECT_EQUIPOS = "SELECT * FROM equipos";
	public static final String SQL_SELECT_JORNADA = "SELECT * FROM partidos where IDJORNADA=?";
	/*****************************************************************/
	public static File fich = new File("Jornada.csv");
	/*****************************************************************/
	public static HashMap<Integer,String> listaEquipos = new HashMap<>();
	public static HashMap<Integer,Integer[]> listaJornadas = new HashMap<>();
	/*****************************************************************/
	public static final Scanner sc = new Scanner(System.in);
	/*****************************************************************/
	public static void main(String[] args) {
		try (
				Connection conn = DriverManager.getConnection(
					    BBDD_URL + DB_NAME, 
					    USER, 
					    PASS
					);
	            PreparedStatement selectEquipos = conn.prepareStatement(SQL_SELECT_EQUIPOS);
	            ResultSet rse = selectEquipos.executeQuery();
	            PreparedStatement selectJornada = conn.prepareStatement(SQL_SELECT_JORNADA);
				ResultSet rsj = selectJornada.executeQuery();
				BufferedWriter bw = new BufferedWriter(new FileWriter(fich));
				){
			//Escribir un header
			String header = "PUESTO | NOMBRE | PUNTOS";
            bw.write(header + "\n");
            
            
            System.out.print("Escriba el número de la jornada que desea ver: ");
            int nJornada = sc.nextInt();
            selectJornada.setInt(1,nJornada);
            /*
            while(rse.next()) {
            	// Suponiendo que la tabla tiene columnas: id | NOMBRE 
            	int id = rse.getInt("IDEQUIPO");
            	String nombre = rse.getString("NOMBRE");
            	System.out.printf("%d | %s%n", id ,nombre);
            	listaEquipos.put(id, nombre);
            }
            */
 
            while(rsj.next()) {
            	//Obtener la info de las jornadas
            	int idJornada = rsj.getInt("IDJORNADA");
            	int idLocal = rsj.getInt("IDLOCAL");
            	int idVisitante = rsj.getInt("IDVISITANTE");
            	System.out.printf("%d |%d | %d%n", idJornada ,idLocal, idVisitante);
            	//listaEquipos.put(idJornada, idLocal,idVisitante);
            }
            
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
