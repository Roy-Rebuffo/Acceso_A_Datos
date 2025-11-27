package Principal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Cliente;
import model.Producto;

public class ejer01 {

	public static void main(String[] args) {
		Cliente juan = new Cliente("Juan");
		Cliente pedro = new Cliente("Pedro");
		Producto peras = new Producto("Peras");
		Producto manzanas = new Producto("Manzanas");
		
		juan.getProductos().add(peras);
		peras.getClientes().add(juan);
		
		pedro.getProductos().add(manzanas);
		manzanas.getClientes().add(pedro);
			
		EntityManagerFactory emf = 
				Persistence.createEntityManagerFactory("JPAN_NTienda"); //nombre de la unidad de persistencia
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		em.persist(pedro);
		em.persist(juan);
		em.persist(peras);
		em.persist(manzanas);
		
		em.getTransaction().commit();
		em.close();
	}

}
