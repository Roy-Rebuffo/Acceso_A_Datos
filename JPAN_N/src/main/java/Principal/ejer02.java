package Principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Alumno;
import model.Curso;

public class ejer02 {

	public static void main(String[] args) {
		Alumno pedro = new Alumno("3","Pedro","Gomez",30);
		Alumno maria = new Alumno("4","Maria","Perez",22);
		Curso cursoJava = new Curso("JAVA3",300,"Introducción a Java");
		Curso cursoNET = new Curso("NET3",250,"Introducción a .NET");
		
		pedro.addCurso(cursoJava);
		pedro.addCurso(cursoNET);
		maria.addCurso(cursoNET);
		
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("JPAN_N"); //nombre de la unidad de persistencia
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(pedro);
		em.persist(maria);
		
		em.getTransaction().commit();
		em.close();
		System.out.println("Terminada");

	}

}
