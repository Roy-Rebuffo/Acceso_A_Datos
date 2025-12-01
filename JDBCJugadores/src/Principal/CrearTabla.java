package Principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrearTabla {
	private static final String SQL_CREATE_EQUIPOS="CREATE TABLE IF NOT EXISTS EQUIPOS("+
			"id INT PRIMARY KEY AUTO_INCREMENT,"+ 
		    "nombre_club VARCHAR(50) UNIQUE"+
			")";
	private static final String SQL_CREATE_JUGADORES="CREATE TABLE IF NOT EXISTS JUGADORES("+
			 	"nombre_apellido VARCHAR(50),"+
			    "edad INT,"+
			    "cumpleaños DATE,"+
			    "liga VARCHAR(20),"+
			    "temporada VARCHAR(30),"+
			    "posicion VARCHAR(30),"+
			    "club_id INT,"+
			    "nacionalidad VARCHAR(50),"+
			    "goles_totales INT,"+
			    "goles_local INT,"+
			    "goles_visitante INT,"+
			    "goles_penalty INT,"+
			    "fallos_penalty INT,"+

			    "FOREIGN KEY (club_id) REFERENCES equipos(id)"+
			    ")";
	
	public static void main(String[] args) {
		try (
				Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3307/premier","root","root");
				PreparedStatement sentencia = conn.prepareStatement(SQL_CREATE_JUGADORES);) {
			sentencia.execute();
			System.out.println("Tabla creada.");
		} catch (SQLException ex) {
			System.err.format("%s %s", ex.getMessage(), ex.getSQLState());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
