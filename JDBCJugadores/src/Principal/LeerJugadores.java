package Principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Clases.Equipo;
import Clases.Jugador;

public class LeerJugadores {
	public static void main(String[] args) throws IOException, ParseException {
		ArrayList<Jugador> listaJugadores = new ArrayList<Jugador>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		
		File fich = new File("jugadores.csv");
		File temp = new File("soloJugadores.csv");
		try (
				BufferedReader br = new BufferedReader(new FileReader(fich));
				BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
				){
			
			String linea = "";
            br.readLine(); // Ignorar cabecera
            linea = br.readLine();
			
            while(linea!=null) {
				 String[] partes = linea.split(",");//separador
				 
				    // 0. full_name
		            String nombre = partes[0].trim();
		            
		            // 1. age (INT)
		            int edad = Integer.valueOf(partes[1].trim()); 
		            
		            // 2. birthday (DATE) - ¡Corrección aquí!
		            String cumpleString = partes[2].trim();
		            long timestampSegundos = Long.parseLong(cumpleString); // Convertir a número
		            Date cumple = new Date(timestampSegundos * 1000); // Crear Date desde milisegundos

		            // 4. league
		            String liga = partes[4].trim(); 
		            
		            // 5. season
		            String temporada = partes[5].trim();
		            
		            // 6. position
		            String pos = partes[6].trim();
		            
		            // 7. Club (STRING) 
		            String club_nombre_del_csv = partes[7].trim();
		            
		            int club_id_temporal = 0; 
		            
		            // 11. nationality
		            String nacionalidad = partes[11].trim();
		            
		            // 15, 16, 17, 21, 22
		            int goles_totales = Integer.valueOf(partes[15].trim());
		            int goles_local = Integer.valueOf(partes[16].trim());
		            int goles_visitante = Integer.valueOf(partes[17].trim());
		            int goles_penalty = Integer.valueOf(partes[21].trim());
		            int goles_fallados = Integer.valueOf(partes[22].trim());
		            
		            // ... (Creación y adición de Jugador)
		            Jugador nuevoJugador = new Jugador(nombre, edad, cumple, liga, temporada, pos, 
		                    club_id_temporal, nacionalidad,
		                    goles_totales, goles_local, goles_visitante, goles_penalty, goles_fallados);
		            listaJugadores.add(nuevoJugador);
		 		
		            bw.write(nuevoJugador + "\n");
                   System.out.println(nuevoJugador);
				 
				 linea = br.readLine();
			 }
				
			}catch (FileNotFoundException ex) {
				System.err.println("Fichero no encontrado");
			}
		} 
	}
