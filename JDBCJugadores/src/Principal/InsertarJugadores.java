package Principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import Clases.Jugador;

public class InsertarJugadores {

    // Consulta para obtener el ID del club (la clave foránea) a partir de su nombre.
    private static final String SQL_SELECT_CLUB_ID = "SELECT id FROM EQUIPOS WHERE nombre_club = ?";

    // Consulta para insertar el Jugador.
    private static final String SQL_INSERT_JUGADOR = 
            "INSERT INTO JUGADORES (nombre_apellido, edad, cumpleaños, liga, temporada, posicion, club_id, " +
            "nacionalidad, goles_totales, goles_local, goles_visitante, goles_penalty, fallos_penalty) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    // Configuración de la DB
    private static final String DB_URL = "jdbc:mysql://localhost:3307/premier";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static void main(String[] args) throws IOException {
        
        File fich = new File("jugadores.csv");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        int filasInsertadas = 0;

        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // Sentencia para buscar el club_id
            PreparedStatement psSelectClub = conn.prepareStatement(SQL_SELECT_CLUB_ID);
            // Sentencia para insertar el jugador
            PreparedStatement psInsertJugador = conn.prepareStatement(SQL_INSERT_JUGADOR);
            BufferedReader br = new BufferedReader(new FileReader(fich));
        ) {
            
            // Ignorar la cabecera del CSV
            br.readLine(); 
            String linea;

            while ((linea = br.readLine()) != null) {
                
                String[] partes = linea.split(",");
                
                if (partes.length < 23) { 
                    System.err.println("Línea omitida por datos insuficientes: " + linea);
                    continue;
                }

                try {
                    // 1. EXTRAER DATOS DEL JUGADOR DEL CSV
                    String nombre = partes[0].trim();
                    int edad = Integer.valueOf(partes[1].trim()); 
                    
                    // La fecha es un timestamp en segundos, requiere conversión
                    long timestampSegundos = Long.parseLong(partes[2].trim()); 
                    java.sql.Date cumpleSQL = new java.sql.Date(timestampSegundos * 1000); 

                    String liga = partes[4].trim(); 
                    String temporada = partes[5].trim();
                    String pos = partes[6].trim();
                    String club_nombre_del_csv = partes[7].trim(); // <-- Nombre del club
                    String nacionalidad = partes[11].trim();
                    
                    int goles_totales = Integer.valueOf(partes[15].trim());
                    int goles_local = Integer.valueOf(partes[16].trim());
                    int goles_visitante = Integer.valueOf(partes[17].trim());
                    int goles_penalty = Integer.valueOf(partes[21].trim());
                    int fallos_penalty = Integer.valueOf(partes[22].trim());
                    
                    // 2. OBTENER CLUB_ID (FK) DESDE LA BASE DE DATOS
                    int club_id;
                    
                    // a) Establecer el parámetro del club_nombre
                    psSelectClub.setString(1, club_nombre_del_csv);
                    
                    try (ResultSet rs = psSelectClub.executeQuery()) {
                        if (rs.next()) {
                            club_id = rs.getInt("id"); // Obtener el ID numérico
                        } else {
                            System.err.println("ERROR: Club no encontrado en la DB: " + club_nombre_del_csv);
                            continue; // Saltar a la siguiente línea del CSV
                        }
                    }

                    // 3. INSERTAR EL JUGADOR
                    psInsertJugador.setString(1, nombre);
                    psInsertJugador.setInt(2, edad);
                    psInsertJugador.setDate(3, cumpleSQL); // Usar java.sql.Date para el PreparedStatement
                    psInsertJugador.setString(4, liga);
                    psInsertJugador.setString(5, temporada);
                    psInsertJugador.setString(6, pos);
                    psInsertJugador.setInt(7, club_id); // ¡Usar la FK obtenida!
                    psInsertJugador.setString(8, nacionalidad);
                    psInsertJugador.setInt(9, goles_totales);
                    psInsertJugador.setInt(10, goles_local);
                    psInsertJugador.setInt(11, goles_visitante);
                    psInsertJugador.setInt(12, goles_penalty);
                    psInsertJugador.setInt(13, fallos_penalty);

                    filasInsertadas += psInsertJugador.executeUpdate();
                    
                } catch (NumberFormatException e) {
                    System.err.println("Error de parseo de datos en línea: " + linea + ". Error: " + e.getMessage());
                }
            }
            
            System.out.println("✅ Inserción de jugadores completa.");
            System.out.println("Total de jugadores insertados: " + filasInsertadas);
            
        } catch (SQLException ex) {
            System.err.println("Error de SQL: " + ex.getMessage());
        }
    }
}