package Principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import Clases.Dept;


public class SelectDept {
	/*****************************************************************/
	public static String BBDD_URL = "jdbc:mysql://localhost:3307/";
	public static String DB_NAME = "bd_scott";
	public static String USER = "root";       
	public static String PASS = "root";
	/*****************************************************************/
	public static final String SQL_SELECT = "SELECT * FROM dept;";
	public static File fich = new File("Departamentos.csv");
	/*****************************************************************/
	public static ArrayList<Dept> listaDepartamentos = new ArrayList<Dept>();
	/*****************************************************************/
	public static void main(String[] args) {
		try (
				Connection conn = DriverManager.getConnection(
					    BBDD_URL + DB_NAME, 
					    USER, 
					    PASS
					);
	            PreparedStatement sentencia = conn.prepareStatement(SQL_SELECT);
	            ResultSet rs = sentencia.executeQuery();
				BufferedWriter bw = new BufferedWriter(new FileWriter(fich));
				){
			//Escribir un header
			String header = "deptno:dname:loc";
            bw.write(header + "\n");
			while(rs.next()) {
				// Suponiendo que la tabla tiene columnas: EMPNO, ENAME, JOB, MGR, SAL,...	
				int deptno = rs.getInt("DEPTNO");
				String dname = rs.getString("DNAME");
				String loc = rs.getString("LOC");
				
				System.out.printf("DEPTNO: %d | DNAME: %s | LOC: %s%n" ,
						deptno, dname, loc);
				
				// ... (Creación y adición de Departamentos)
	            Dept nuevoDept = new Dept(deptno,dname,loc);
	            listaDepartamentos.add(nuevoDept);
	            bw.write(nuevoDept + "\n");	
			}
			
		} catch (SQLException ex) {
            System.err.format("%s %s", ex.getMessage(), ex.getSQLState());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

	}

}
