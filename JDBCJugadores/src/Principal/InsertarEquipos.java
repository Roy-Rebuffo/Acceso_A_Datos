package Principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet; // Importar HashSet

public class InsertarEquipos {

    public static final String SQL_INSERT = "INSERT INTO EQUIPOS(NOMBRE_CLUB) VALUES (?);";

    public static void main(String[] args) throws IOException {
        
        // 1. Configuración de Archivo: ¡Cambio a equipos.csv!
        File fich = new File("equipos.csv");
        int filasInsertadas = 0;
        
        // ** Filtro para garantizar unicidad en Java **
        HashSet<String> clubesProcesados = new HashSet<>(); 

        // 2. Configuración de Base de Datos
        String DB_URL = "jdbc:mysql://localhost:3307/premier";
        String USER = "root";
        String PASS = "root";
        
        // 3. Abrir Conexión DB y Lector de Archivo en un bloque try-with-resources
        try (
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement sentencia = conn.prepareStatement(SQL_INSERT);
            BufferedReader br = new BufferedReader(new FileReader(fich));
        ) {
            
            String linea;

            while ((linea = br.readLine()) != null) {
                
                // Si el archivo solo contiene el nombre del club por línea:
                String nombre_club = linea.trim(); 
                
                // Si el archivo usa un separador (ej. una coma) y el nombre es el primer campo:
                // String[] partes = linea.split(",");
                // String nombre_club = partes[0].trim();
                
                // 🚀 Lógica de filtrado de unicidad
                if (clubesProcesados.add(nombre_club)) {
                    
                    // Solo insertamos si el nombre es nuevo en el HashSet
                    sentencia.setString(1, nombre_club);
                    
                    try {
                        int row = sentencia.executeUpdate();
                        filasInsertadas += row;
                        System.out.println("Fila insertada: " + nombre_club);
                    } catch (SQLException ex) {
                        // Este catch captura errores de unicidad si el club ya existía previamente en la DB.
                        System.err.println("Error al insertar " + nombre_club + ": Ya existe o error de DB.");
                        // Opcional: mostrar error: ex.printStackTrace();
                    }
                    
                } else {
                    // Opcional: System.out.println("Saltando duplicado: " + nombre_club);
                }
            }
            
            System.out.println("✅ Inserción completa.");
            System.out.println("Total de filas insertadas: " + filasInsertadas);
            
        } catch (SQLException ex) {
            // Manejo de errores de conexión/sentencia
            System.err.println("Error de SQL (Conexión/Sentencia): " + ex.getMessage());
            System.err.println("SQL State: " + ex.getSQLState());
        } catch (FileNotFoundException ex) {
            System.err.println("Error: Fichero no encontrado: " + fich.getAbsolutePath());
        } catch (IOException ex) {
            System.err.println("Error de lectura/escritura del archivo.");
            ex.printStackTrace();
        } 
    }
}