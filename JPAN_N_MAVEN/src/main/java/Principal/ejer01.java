package Principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import model.Alumno;
import model.Curso;

public class ejer01 {

	public static void main(String[] args) {
		Alumno pedro = new Alumno("1","Pedro","Gomez",30);
		Alumno maria = new Alumno("2","Maria","Perez",22);
		Curso cursoJava = new Curso("JAVA2",300,"Introducción a Java");
		Curso cursoNET = new Curso("NET2",250,"Introducción a .NET");
		
		pedro.getCursos().add(cursoJava);
		cursoJava.getAlumnos().add(pedro);
		pedro.getCursos().add(cursoNET);
		cursoNET.getAlumnos().add(pedro);
		
		maria.getCursos().add(cursoNET);
		cursoNET.getAlumnos().add(maria);
		
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("JPAN_N"); //nombre de la unidad de persistencia
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(pedro);
		em.persist(maria);
		em.persist(cursoJava);
		em.persist(cursoNET);
		
		em.getTransaction().commit();
		em.close();
		System.out.println("Terminada");
		
		
	}

}
