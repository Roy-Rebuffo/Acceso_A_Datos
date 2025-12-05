package Principal;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Generales.Biblioteca;
import model.Cliente;

public class CRUD {
	
	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		menu();

	}
	public static void menu() {
		String basura = "";
		String opciones [] = {
				"1.-ALTA USUARIO", 
				"2.-MODIFICAR USUARIO", 
				"3.-BAJA USUARIO", 
				"4.-LISTAR ", 
				"5.-SALIR"};
		
		int opc;
		boolean seguir = true;
		
		while(seguir) {
			opc = Biblioteca.menu(sc, opciones);
			System.out.print("\033[2J");
			switch(opc) {
			case 1:
				darAlta();
				break;
			case 2:
				modificar();
				break;
			case 3:
				eliminar();
				break;
			case 4:
				listar();
				break;
			case 5:
				seguir = false;
				System.out.println("GRACIAS POR USAR EL SISTEMA. ");
				break;
			default:
				System.out.println("Opcion invalida.");
			}
			if(opc != 7) {
				System.out.println("Pulse una tecla para continuar");
				basura = sc.nextLine();
			}
		}
	}
	
	
	
	public static void darAlta() {
		
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPA_REP");
		EntityManager em = emp.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		System.out.print("Ingrese el id del empleado: ");
		int id = sc.nextInt();
		System.out.print("Ingrese el nombre: ");
		String ename = sc.next();
		System.out.print("Ingrese su ape1: ");
		String ape1 = sc.next();
		System.out.print("Ingrese su ape2: ");
		String ape2 = sc.next();
		System.out.print("Ingrese su ciudad: ");
		String ciudad = sc.next();
		System.out.print("Ingrese la categoria: ");
		int cat = sc.nextInt();
		
		
		transaccion.begin();
		Cliente empp = new Cliente(id, ename, ape1, ape2, ciudad, cat);
		em.persist(empp);
		transaccion.commit();
		em.close();
		
		System.out.println("Agregado correctamente. ");
		
	}
	
	public static void modificar() {
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPA_REP");
		EntityManager em = emp.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		System.out.print("Ingrese el id de empleado que desea modificar: ");
		int id = sc.nextInt();
		Cliente e = em.find(Cliente.class, id);
		System.out.println(e);
		
		System.out.print("Ingrese el nombre: ");
		String ename = sc.next();
		System.out.print("Ingrese su ape1: ");
		String ape1 = sc.next();
		System.out.print("Ingrese su ape2: ");
		String ape2 = sc.next();
		System.out.print("Ingrese su ciudad: ");
		String ciudad = sc.next();
		System.out.print("Ingrese la categoria: ");
		int cat = sc.nextInt();
		
		
		Cliente dept = em.find(Cliente.class, id);
		if(dept == null) {
			System.out.println("Cliente incorrecto.");
		}
		
		
		 transaccion.begin();
		    e.setNombre(ename);
		    e.setApellido1(ape1);
		    e.setApellido2(ape2);
		    e.setCiudad(ciudad);
		    e.setCategoría(cat);
		 transaccion.commit();

	}
	
	public static void eliminar() {
		
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPA_REP");
		EntityManager em = emp.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		transaccion.begin();
		
		//Se busaca por ID y luego se elimina
		System.out.print("Ingrese el ID a dar de BAJA: ");
		int id = sc.nextInt();
		Cliente e = em.find(Cliente.class, id);
		if(e!= null) {
			em.remove(e);
		}else {
			System.out.println("NO EXISTE");
		}
			
		transaccion.commit();
		em.close();
	}
	
	public static void listar() {
		EntityManagerFactory emp = Persistence.createEntityManagerFactory("JPA_REP");
	    EntityManager em = emp.createEntityManager();

	    List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c", Cliente.class)
	                               .getResultList();

	    for (Cliente c : clientes) {
	        System.out.println("ID: " + c.getId() +
	                           ", Nombre: " + c.getNombre() +
	                           ", Apellido1: " + c.getApellido1() +
	                           ", Apellido2: " + c.getApellido2() +
	                           ", Ciudad: " + c.getCiudad() +
	                           ", Categoría: " + c.getCategoría());
	    }

	    em.close();

	}

}
