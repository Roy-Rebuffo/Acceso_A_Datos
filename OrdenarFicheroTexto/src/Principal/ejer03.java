//Fichero de la premier
//ordenar a los jugadores por equipo
//a igualdad de equipo por fecha de cumpleaños

package Principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import Clases.Jugador;
import Clases.Persona;

public class ejer03 {

	public static void main(String[] args) throws ParseException {
// Campos 2 y 7 (Cumpleaños y equipo) //
		String futbol = "jugadores.csv";
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		try {
			FileReader fr = new FileReader(futbol);
			BufferedReader br = new BufferedReader(fr);
			Date cumple;
			String nombre;
			String club;
			ArrayList<Jugador> jugador = new ArrayList<Jugador>();
			String linea;
			br.readLine();
			while ((linea = br.readLine()) != null) {
				String partes[] = linea.split(",");

				nombre = partes[0];
				cumple = formatter.parse(partes[3]);
				club = partes[7];
				Jugador j = new Jugador(nombre, cumple, club);
				jugador.add(j);
			}
			Collections.sort(jugador, new Comparator<Jugador>() {
				@Override
				public int compare(Jugador o1, Jugador o2) {
					int n = o1.getCumple().compareTo(o2.getCumple());
					if (n != 0)
						return n;
					return o2.getEquipo().compareTo(o1.getEquipo());
				}
			});
			File jugadores_limpio = new File("Jugadores_limpio.txt");
			FileWriter fw = new FileWriter(jugadores_limpio);
			BufferedWriter bw = new BufferedWriter(fw);
			for (Jugador player : jugador) {
				bw.write(player.getNombre() + "," + new SimpleDateFormat("dd/MM/yyyy").format(player.getCumple()) + ","
						+ player.getEquipo());
				bw.newLine();
			}
			bw.close();
			BufferedReader br2 = new BufferedReader(new FileReader(jugadores_limpio));
			String linea2;
			System.out.println("\nContenido de Jugadores_limpio.txt:\n");
			while ((linea2 = br2.readLine()) != null) {
				System.out.println(linea2);
			}
			br2.close();
		} catch (IOException ex) {

		}

	}

}
