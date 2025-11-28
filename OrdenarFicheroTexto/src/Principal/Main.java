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

public class Main {

	public static void main(String[] args) throws IOException {
		ArrayList<String> a = new ArrayList<String>();
		File fich = new File("palabras_random.txt");
		File temp = new File("temp");
		
		try (
				BufferedReader br = new BufferedReader(new FileReader(fich));
				BufferedWriter bw = new BufferedWriter(new FileWriter(temp));
				){
			String linea = "";
			linea = br.readLine();
			while(linea!=null) {
				a.add(linea);
				linea = br.readLine();
			}
	    	
			Collections.sort(a);
			for(String p : a) {
				bw.write(p + "\n");
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
