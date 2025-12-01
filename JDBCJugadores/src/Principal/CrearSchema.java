package Principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrearSchema {

	public static final String SQL_DELETE = "DROP DATABASE IF EXISTS PREMIER";
	public static final String SQL_CREATE = "CREATE DATABASE IF NOT EXISTS PREMIER";
	public static void main(String[] args) {
		try (
				Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3307/sys","root","root");
				PreparedStatement sentencia0 = conn.prepareStatement(SQL_DELETE);
				PreparedStatement sentencia = conn.prepareStatement(SQL_CREATE);) {
			sentencia0.execute();
			System.out.println("SCHEMA borrado.");
			sentencia.execute();
			System.out.println("SCHEMA creado.");
		} catch (SQLException ex) {
			System.err.format("%s %s", ex.getMessage(), ex.getSQLState());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
