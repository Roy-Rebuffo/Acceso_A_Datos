package Principal;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Clases.Emp;


public class SelectEmpleados {
	/*****************************************************************/
	public static String BBDD_URL = "jdbc:mysql://localhost:3307/";
	public static String DB_NAME = "bd_scott";
	public static String USER = "root";       
	public static String PASS = "root";
	/*****************************************************************/
	public static final String SQL_SELECT = "SELECT * FROM emp;";
	public static File fich = new File("Empleados.csv");
	/*****************************************************************/
	public static ArrayList<Emp> listaEmpleados = new ArrayList<Emp>();
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
			//Creacion de header
			String header = "empno:ename:job:mgr:sal:comm:deptno:hiredate";
            bw.write(header + "\n");
			while(rs.next()) {
				// Suponiendo que la tabla tiene columnas: EMPNO, ENAME, JOB, MGR, SAL,...
				int empno = rs.getInt("EMPNO");
				String ename = rs.getString("ENAME");
				String job = rs.getString("JOB");
				int mgr = rs.getInt("MGR");
				Float sal = rs.getFloat("SAL");
				Float comm = rs.getFloat("COMM");
				int deptno = rs.getInt("DEPTNO");
				Date hireDate = rs.getDate("HIREDATE");
				
				System.out.printf("empno: %d | ename: %s | job: %s | mgr: %d | sal: %.2f| comm: %.2f| deptno: %d| hireDate: %s%n" ,
                        empno, ename, job, mgr, sal, comm, deptno, hireDate);
				
				// ... (Creación y adición de Empleados)
	            Emp nuevoEmp = new Emp(empno,ename,job,mgr,sal,comm,deptno,hireDate);
	            listaEmpleados.add(nuevoEmp);
	            bw.write(nuevoEmp + "\n");
				
			}
			
		} catch (SQLException ex) {
            System.err.format("%s %s", ex.getMessage(), ex.getSQLState());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

	}

}
