package Main;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    
	/*****************************************************************/
	public static String BBDD_URL = "jdbc:mysql://localhost:3306/"; //CAMBIAR PUERTO
	public static String SYS = "sys";
	public static String DB_NAME = "ventas";
	public static String USER = "root";       
	public static String PASS = "1234";//CAMBIAR PASS
	/*****************************************************************/
	public static final Scanner sc = new Scanner(System.in);
	/*****************************************************************/
	
	public static void main(String[] args) {
		boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ JDBC SENCILLO ---");
            System.out.println("1. Crear Schema");
            System.out.println("2. Crear Tabla");
            System.out.println("3. Añadir empleado");
            System.out.println("4. Borrar empleado");
            System.out.println("5. Modificar empleado");
            System.out.println("6. Listar Empleados/Deptos");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");
            
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                	crearSchema();
                    break;
                case "2":
                	CrearTabla();
                    break;
                case "3":
                	insertarDatos();
                	break;
                case "4":
                	borrar();
                	break;
                case "5":
                	modificar();
                	break;
                case "6":
                	lista();
                	break;
                case "7":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida, prueba de nuevo.");
            }
        } 
        System.out.println("Adiós.");
    }
	
	/**************************************************************************************************************/
	public static void crearSchema() {
		
		String consulta = "CREATE DATABASE IF NOT EXISTS VENTAS";
		try (
				Connection conn = DriverManager.getConnection(
					    BBDD_URL + SYS, 
					    USER, 
					    PASS
					);
				PreparedStatement s = conn.prepareStatement(consulta);
				){
			
			s.execute();
			System.out.println("Base de datos creada correctamente");
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	/**************************************************************************************************************/
	
	public static void CrearTabla(){

		final String SQL_CLIENTE = "CREATE TABLE CLIENTE("
				+ "ID INT PRIMARY KEY,"
				+ " NOMBRE VARCHAR(20),"
				+ " APE VARCHAR(20),"
				+ " APE2 VARCHAR(20),"
				+ " CIUDAD VARCHAR(20),"
				+ " CATEGORIA INT)";
		final String SQL_COMERCIAL = "CREATE TABLE COMERCIAL("
				+ "ID INT PRIMARY KEY,"
				+ " NOMBRE VARCHAR(20),"
				+ " APE1 VARCHAR(20),"
				+ " APE2 VARCHAR(20),"
				+ " CIUDAD VARCHAR(20),"
				+ " COMISION FLOAT)";
		final String SQL_PEDIDO = "CREATE TABLE PEDIDO("
				+ "ID INT PRIMARY KEY,"
				+ " CANTIDAD DOUBLE,"
				+ " FECHA DATE,"
				+ " ID_CLIENTE INT,"
				+ " ID_COMERCIAL INT,"
				+ " FOREIGN KEY(ID_CLIENTE) REFERENCES CLIENTE(ID),"
				+ " FOREIGN KEY(ID_COMERCIAL) REFERENCES COMERCIAL(ID))";
		try(
				Connection conn = DriverManager.getConnection(
					    BBDD_URL + DB_NAME, 
					    USER, 
					    PASS
					);
			PreparedStatement sentencia = conn.prepareStatement(SQL_CLIENTE);
			PreparedStatement sentencia1 = conn.prepareStatement(SQL_COMERCIAL);
			PreparedStatement sentencia2 = conn.prepareStatement(SQL_PEDIDO);
				) {
			
			sentencia.execute();
			sentencia1.execute();
			sentencia2.execute();
			System.out.println("Tablas Creada con Exito");
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}catch(Exception e) {}
		
	}
	/**************************************************************************************************************/
	public static void insertarDatos() {
		
		String SQL_INSERT="INSERT INTO CLIENTE VALUES(?,?,?,?,?,?)";
		
		try(
				Connection conn = DriverManager.getConnection(
					    BBDD_URL + DB_NAME, 
					    USER, 
					    PASS
					);
				PreparedStatement sentencia = conn.prepareStatement(SQL_INSERT);

					) {
				System.out.print("Introduce el id: ");
				int id = sc.nextInt();
				System.out.print("Introduce el nombre: ");
				String nombre = sc.next();
				System.out.print("Ape1: ");
				String ape1 = sc.next();
				System.out.print("Ape2: ");
				String ape2 = sc.next();
				System.out.print("Ciudad: ");
				String ciu = sc.next();
				System.out.print("categoria: ");
				int cat = sc.nextInt();
				
				sentencia.setInt(1, id);
				sentencia.setString(2, nombre);
				sentencia.setString(3, ape1);
				sentencia.setString(4, ape2);
				sentencia.setString(5, ciu);
				sentencia.setInt(6, cat);
			
				sentencia.execute();
				
				System.out.println("Datos insertados con Exito");
				
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}catch(Exception e) {}
			
	}

	/**************************************************************************************************************/
	public static void lista() {
		String ca = "SELECT * FROM CLIENTE";
		String cadena="";
		int n = 5, c= 0;
		try(
				Connection conn = DriverManager.getConnection(
					    BBDD_URL + DB_NAME, 
					    USER, 
					    PASS
					);
			PreparedStatement sentencia = conn.prepareStatement(ca);
			) {
				ResultSet r = sentencia.executeQuery();
				while(r.next()) {
					c++;
					cadena += String.format("%5d %-10s %-10s %-10s %-15s %5d",
					        r.getInt("ID"),
					        r.getString("NOMBRE"),
					        r.getString("APE"),
					        r.getString("APE2"),
					        r.getString("CIUDAD"),
					        r.getInt("CATEGORIA"));
					
					if(c%n==0) {
						System.out.println(cadena);
						cadena = "";
					}
				}
				System.out.println(cadena);
			
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}catch(Exception e) {}
	}
	/**************************************************************************************************************/
	public static void modificar() {
		
		System.out.print("ID del cliente a actualizar: ");
	    int id = sc.nextInt();
	    sc.nextLine();

	    System.out.print("Nuevo nombre: ");
	    String nombre = sc.nextLine();

	    System.out.print("Nuevo apellido 1: ");
	    String ape = sc.nextLine();

	    System.out.print("Nuevo apellido 2: ");
	    String ape2 = sc.nextLine();

	    System.out.print("Nueva ciudad: ");
	    String ciudad = sc.nextLine();

	    System.out.print("Nueva categoria: ");
	    int categoria = sc.nextInt();
		 String sql = "UPDATE CLIENTE SET NOMBRE=?, APE=?, APE2=?, CIUDAD=?, CATEGORIA=? WHERE ID=?";
		
		try(
				Connection conn = DriverManager.getConnection(
					    BBDD_URL + DB_NAME, 
					    USER, 
					    PASS
					);
			PreparedStatement ps = conn.prepareStatement(sql);
			) {
			
			ps.setString(1, nombre);
	        ps.setString(2, ape);
	        ps.setString(3, ape2);
	        ps.setString(4, ciudad);
	        ps.setInt(5, categoria);
	        ps.setInt(6, id);

	        ps.executeUpdate();
	        System.out.println("Cliente actualizado correctamente.");
				
			
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}catch(Exception e) {}
	}
	
	
	/**************************************************************************************************************/
public static void borrar() {
		
		System.out.print("ID del cliente a BORRAR: ");
	    int id = sc.nextInt();
	    sc.nextLine();

	    String sql="DELETE FROM CLIENTE WHERE ID = ? ";
		
		try(
				Connection conn = DriverManager.getConnection(
					    BBDD_URL + DB_NAME, 
					    USER, 
					    PASS
					);
			PreparedStatement ps = conn.prepareStatement(sql);
			) {
			
			ps.setInt(1, id);

	        int filas = ps.executeUpdate();

	        if (filas > 0) {
	            System.out.println("Cliente borrado correctamente.");
	        } else {
	            System.out.println("No existe un cliente con ese ID.");
	        }
			
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}catch(Exception e) {}
	}
	
	/**************************************************************************************************************/
    
}
