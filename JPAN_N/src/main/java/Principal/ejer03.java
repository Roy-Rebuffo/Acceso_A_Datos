package Principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Alumno;
import model.Curso;

public class ejer03 {

	public static void main(String[] args) {
		//Eliminar net3 de maria y pedro
		
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("JPAN_N"); //nombre de la unidad de persistencia
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		//Encuentra a los alumnos
		Alumno a = em.find(Alumno.class, "3");
		Alumno b = em.find(Alumno.class, "4");
		//Encuentra al curso
		Curso c = em.find(Curso.class, "NET3");
		System.out.printf("Alumno a%s, Alumno b%s, Curso:%s",a,b,c);
		
		//remove quita un objeto
		c.removeAlumno(a);
		c.removeAlumno(b);
		
		em.getTransaction().commit();
		em.close();
		System.out.println("Terminada");

	}

}
