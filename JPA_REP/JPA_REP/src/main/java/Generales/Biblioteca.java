package Generales;

import java.util.Scanner;

public class Biblioteca {
	
	
	public static int menu(Scanner sc, String[] opciones ) {
		int op=0;
		do {
			for(String items: opciones) System.out.println(items);
			System.out.print("Dime la opcion a realizar: ");
			try {
			op=Integer.valueOf(sc.nextLine());
			}catch(NumberFormatException e) {
				continue;
			}
		}while(op<1 || op>opciones.length);
		return op;
	}
	
	public static double redondear(double numero, int decimales) {
		int exponente = (int)Math.pow(10, decimales);
		return (double) Math.round(numero*exponente)/exponente;
	}
	
	public static double truncar(double numero, int decimales) {
		double exponente = Math.pow(10, decimales);
		return (Math.round(numero*exponente))/exponente;
	}
	
}
