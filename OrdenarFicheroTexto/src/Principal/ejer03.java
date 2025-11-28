//Fichero de la premier
//ordenar a los jugadores por equipo
//a igualdad de equipo por fecha de cumpleaños

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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import Clases.Jugador;

public class ejer03 {

	public static void main(String[] args) throws IOException, ParseException {
		ArrayList<Jugador> lista = new ArrayList<Jugador>();
		File fich = new File("jugadores.csv");
		File temp = new File("jugadores_ordenados.csv");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		
		try (
				BufferedReader br = new BufferedReader(new FileReader(fich));
				BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
				){
			String linea = "";
			linea = br.readLine();
			while(linea!=null) {
				String[] partes = linea.split(",");//separador
				
				LocalDate cumple = LocalDate.parse(partes[3],formatter);
				String eq = String.valueOf(partes[7]);
				
				lista.add(new Jugador(cumple,eq));
				
				linea = br.readLine();
			}
	    	
			lista.sort(new Comparator<Jugador>() {
			    @Override
			    public int compare(Jugador p1, Jugador p2) {
			        int cmp = p1.getCumple().compareTo(p2.getCumple());
			        if (cmp != 0) return cmp;
			        return p2.getClub().compareTo(p1.getClub());
			    }
			});

			for (Jugador p : lista) {
				bw.write(p.getCumple()+ ":" + p.getClub() + "\n");
				System.out.println(p);
			}
			
		} catch (FileNotFoundException ex) {
			System.err.println("Fichero no encontrado");
			//ex.printStackTrace();
		}
	
	}

}
