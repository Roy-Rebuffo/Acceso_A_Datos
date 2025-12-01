package Principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet; // Importar HashSet

import Clases.Equipo;
import Clases.Jugador; // Import no usado, pero se mantiene

public class LeerEquipos {

    public static void main(String[] args) throws IOException {
        
        // 1. Lista para almacenar los objetos Equipo (final)
        ArrayList<Equipo> listaEquipos = new ArrayList<Equipo>();
        
        // 2. HashSet para registrar los nombres de club únicos (filtro)
        HashSet<String> nombresUnicos = new HashSet<>(); 
        
        File fich = new File("jugadores.csv");
        File temp = new File("equipos.csv");
        
        try (
                BufferedReader br = new BufferedReader(new FileReader(fich));
                BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
                ){
            
            String linea = "";
            br.readLine(); // Ignorar cabecera
            linea = br.readLine();
            
            while(linea!=null) {
                
                String[] partes = linea.split(",");//separador
            
                // Asegúrate de limpiar el nombre (trim)
                String nombre_club = partes[7].trim(); 
                
                // ************************************************************
                // 🚀 LÓGICA CLAVE: Usar HashSet para comprobar y añadir unicidad
                // ************************************************************
                // .add(nombre_club) devuelve TRUE si el nombre fue añadido (es decir, era único)
                if (nombresUnicos.add(nombre_club)) { 
                    
                    // Si es único, creamos el objeto Equipo y lo añadimos a la lista final
                    Equipo nuevoEquipo = new Equipo(nombre_club);
                    listaEquipos.add(nuevoEquipo);
                    
                    // Opcional: Escribir o mostrar inmediatamente.
                    // Nota: Si quieres escribirlos todos al final, mueve el 'for' fuera del while.
                    bw.write(nuevoEquipo + "\n");
                    System.out.println(nuevoEquipo);
                }
                // ************************************************************
                
                linea = br.readLine();
                
                // ** IMPORTANTE: **
                // Eliminé el bucle 'for' interno que escribía la lista completa en cada iteración.
                // Ese bucle era ineficiente y podía causar confusión si se leía el archivo 'temp' después.
            }
            
            // Si quieres ver toda la lista de equipos únicos al final:
            // System.out.println("\n--- Lista Final de Equipos Únicos ---");
            // for(Equipo e : listaEquipos) {
            //     System.out.println(e);
            // }
            
        } catch (FileNotFoundException ex) {
            System.err.println("Fichero no encontrado");
        }
    }
}