//En el fichero poner x:y ej. ana:40 maria:40...
//ordenar por edades
//a igualdad de edades ordenar alfabeticamente

package Principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import Clases.Persona;

public class ejer02 {

	public static void main(String[] args) throws IOException {
		ArrayList<Persona> lista = new ArrayList<Persona>();
		File fich = new File("palabras_random.txt");
		File temp = new File("temp");
		
		try (
				BufferedReader br = new BufferedReader(new FileReader(fich));
				BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
				){
			String linea = "";
			linea = br.readLine();
			while(linea!=null) {
				
				String[] partes = linea.split(":");//separador
				
				String nombre = String.valueOf(partes[0]);
				int edad = Integer.valueOf(partes[1]);
				
				lista.add(new Persona(nombre,edad));
				
				linea = br.readLine();
			}
	    	
			Collections.sort(lista, new Comparator<Persona>() {

				@Override
				public int compare(Persona o1, Persona o2) {
					int n = o2.getEdad() - o1.getEdad(); //mayor a menor
					// int n =-1*( o2.getEdad() - o1.getEdad()); //menor a mayo
					if(n!=0) return n;
					return o2.getNombre().compareTo(o1.getNombre());
				}
			});
			for (Persona p : lista) {
				bw.write(p.getNombre() + ":" + p.getEdad() + "\n");
				System.out.println(p);
			}
			
		} catch (FileNotFoundException ex) {
			System.err.println("Fichero no encontrado");
			//ex.printStackTrace();
		}
		
		//Una vez obtenido todo borramos el fichero
		fich.delete();
    	if(!temp.renameTo(fich)) throw new IOException("no se renombró el fichero");
	}

}
