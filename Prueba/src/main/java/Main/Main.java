package Main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Cliente;
import model.Comercial;
import model.Pedido;

public class Main {
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	private static final Scanner sc = new Scanner(System.in);
    private static final EntityManagerFactory emf = 
        Persistence.createEntityManagerFactory("Prueba");
	
	public static void main(String[] args) {
		menu();
		sc.close();
		emf.close(); 
	}
	
	/************************************************************/
	/*********************Métodos Clientes************************/
	/************************************************************/
	private static String formatoCliente(Cliente c) {
        return "ID: " + c.getId() +
               " | Nombre: " + c.getNombre() +
               " " + c.getApellido1() +
               " " + c.getApellido2() +
               " | Ciudad: " + c.getCiudad() +
               " | Categoría: " + c.getCategoría();
    }
	
	/************************************************************/
	public static void insertarCliente() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
	    
		System.out.println("\n--- CREAR NUEVO CLIENTE ---");
		try {
			System.out.print("Ingrese el nombre: ");
			String ename = sc.nextLine();
			System.out.print("Ingrese su primer apellido: ");
			String ape1 = sc.nextLine();
			System.out.print("Ingrese su segundo apellido: ");
			String ape2 = sc.nextLine();
			System.out.print("Ingrese su ciudad: ");
			String ciudad = sc.nextLine();
			System.out.print("Ingrese la categoría (número): ");
			int cat = Integer.parseInt(sc.nextLine());
			
			transaccion.begin();
			Cliente nuevoCliente = new Cliente(ename, ape1, ape2, ciudad, cat);
			em.persist(nuevoCliente);
			transaccion.commit();
			System.out.println("Cliente agregado correctamente!");
	    } catch (NumberFormatException e) {
	    	System.out.println("Error: La categoría debe ser un número entero.");
	    	if (transaccion.isActive()) transaccion.rollback();
		} catch (Exception e) {
			System.out.println("Error al insertar cliente: " + e.getMessage());
			if (transaccion.isActive()) transaccion.rollback();
		} finally {
			em.close();
		}
	}
	
	/************************************************************/
	public static void obtenerClientePorId() {
        EntityManager em = emf.createEntityManager();
        System.out.println("\n--- BUSCAR CLIENTE POR ID ---");
        System.out.print("Ingrese el ID del cliente a buscar: ");
        
        try {
            int id = Integer.parseInt(sc.nextLine());
            
            Cliente cliente = em.find(Cliente.class, id);
            
            if (cliente != null) {
                System.out.println("\n--- Cliente encontrado ---");
                System.out.println(formatoCliente(cliente));
            } else {
                System.out.println("No existe un cliente con ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: ID no válido. Inténtalo de nuevo.");
        } finally {
            em.close();
        }
    }
	
	/************************************************************/
	public static void actualizarCliente() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		
		System.out.println("\n--- MODIFICAR CLIENTE ---");
		
		try {
			System.out.print("Ingrese el ID del cliente que desea modificar: ");
	    	int id = Integer.parseInt(sc.nextLine());
	    	
	    	Cliente cliente = em.find(Cliente.class, id);
	    	if (cliente == null) {
	    		System.out.println("Error: No existe un cliente con ID: " + id);
	    		return;
	    	}
	    	
	    	// Pedir nuevos datos
	    	System.out.print("Ingrese el nuevo nombre: ");
			String ename = sc.nextLine();
			System.out.print("Ingrese su nuevo primer apellido: ");
			String ape1 = sc.nextLine();
			System.out.print("Ingrese su nuevo segundo apellido: ");
			String ape2 = sc.nextLine();
			System.out.print("Ingrese su nueva ciudad: ");
			String ciudad = sc.nextLine();
			System.out.print("Ingrese la nueva categoría: ");
			int cat = Integer.parseInt(sc.nextLine());
			
			transaccion.begin();
			
			cliente.setNombre(ename);
			cliente.setApellido1(ape1);
			cliente.setApellido2(ape2);
			cliente.setCiudad(ciudad);
			cliente.setCategoría(cat);
			
			transaccion.commit();
			System.out.println("Cliente ID " + id + " modificado correctamente.");
			
		} catch (NumberFormatException e) {
	    	System.out.println("Error: El ID o la categoría deben ser números válidos.");
	    	if (transaccion.isActive()) transaccion.rollback();
		} catch (Exception e) {
			System.out.println("Error al actualizar cliente: " + e.getMessage());
			if (transaccion.isActive()) transaccion.rollback();
		} finally {
			em.close();
		}
	}
	
	/************************************************************/
	public static void eliminarCliente() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		
        System.out.println("\n--- ELIMINAR CLIENTE ---");
        
		try {
			System.out.print("Ingrese el ID del cliente a dar de BAJA: ");
			int id = Integer.parseInt(sc.nextLine());
			
			transaccion.begin();
			
			Cliente cliente = em.find(Cliente.class, id);
			if (cliente != null) {
				em.remove(cliente);
				System.out.println("Cliente ID " + id + " eliminado correctamente.");
			} else {
				System.out.println("No existe un cliente con ID: " + id);
			}
			
			transaccion.commit();
			
			
		} catch (NumberFormatException e) {
	    	System.out.println("Error: El ID debe ser un número válido.");
	    	if (transaccion.isActive()) transaccion.rollback();
		} catch (Exception e) {
			System.out.println("Error al eliminar cliente: " + e.getMessage());
			if (transaccion.isActive()) transaccion.rollback();
		} finally {
			em.close();
		}
	}
	
	/************************************************************/
	/*********************Métodos Pedidos************************/
	/************************************************************/
	private static String formatoPedido(Pedido p) {
	    String fechaStr = DATE_FORMAT.format(p.getFecha());
	    String clienteStr = p.getCliente() != null ? p.getCliente().getNombre() + " " + p.getCliente().getApellido1() + " (ID: " + p.getCliente().getId() + ")" : "N/A";
	    String comercialStr = p.getComercial() != null ? p.getComercial().getNombre() + " " + p.getComercial().getApellido1() + " (ID: " + p.getComercial().getId() + ")" : "N/A";
	    
	    return "ID: " + p.getId() +
	           " | Total: " + String.format("%.2f", p.getTotal()) + "€" +
	           " | Fecha: " + fechaStr +
	           " | Cliente: " + clienteStr +
	           " | Comercial: " + comercialStr;
	}
	
	/************************************************************/
	public static void crearPedido() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		
		System.out.println("\n--- CREAR NUEVO PEDIDO ---");
		
		try {
			System.out.print("Ingrese el total del pedido (ej: 1500.50): ");
			double total = Double.parseDouble(sc.nextLine());
			
			System.out.print("Ingrese la fecha del pedido (formato YYYY-MM-DD): ");
		    Date fecha = DATE_FORMAT.parse(sc.nextLine());
		    
		    System.out.print("Ingrese el ID del Cliente: ");
		    int idCliente = Integer.parseInt(sc.nextLine());
		    Cliente cliente = em.find(Cliente.class, idCliente);
		    
		    if (cliente == null) {
		    	System.out.println("Error: No se encontró un cliente con ID: " + idCliente);
		    	return;
		    }
		    
		    System.out.print("Ingrese el ID del Comercial: ");
		    int idComercial = Integer.parseInt(sc.nextLine());
		    Comercial comercial = em.find(Comercial.class, idComercial);
		    
		    if (comercial == null) {
		    	System.out.println("Error: No se encontró un comercial con ID: " + idComercial);
		    	return;
		    }
		    
		    transaccion.begin();
	        Pedido nuevoPedido = new Pedido(total, fecha, cliente, comercial);
	        em.persist(nuevoPedido);
	        transaccion.commit();
	        System.out.println("Pedido con agregado correctamente");
	        
		} catch (ParseException e) {
			System.out.println("Error: Formato de fecha inválido. Use YYYY-MM-DD.");
			if (transaccion.isActive()) transaccion.rollback();
		} catch (NumberFormatException e) {
			System.out.println("Error: El Total o los IDs deben ser números válidos.");
			if (transaccion.isActive()) transaccion.rollback();
		} catch (Exception e) {
			System.out.println("Error al crear pedido: " + e.getMessage());
			if (transaccion.isActive()) transaccion.rollback();
		} finally {
			em.close();
		}
	}
	
	/************************************************************/
	public static void obtenerPedidoPorId() {
		EntityManager em = emf.createEntityManager();
		
		System.out.println("\n--- BUSCAR PEDIDO POR ID ---");
	    System.out.print("Ingrese el ID del pedido a buscar: ");
	    
		try {
			int id = Integer.parseInt(sc.nextLine());
	        
	        Pedido pedido = em.find(Pedido.class, id);
	        
	        if (pedido != null) {
	            System.out.println("\n--- Pedido encontrado ---");
	            System.out.println(formatoPedido(pedido));
	        } else {
	            System.out.println("No existe un pedido con ID: " + id);
	        }
		} catch (NumberFormatException e) {
			System.out.println("Error: ID no válido. Inténtalo de nuevo.");
		} finally {
			em.close();
		}
	}
	
	/************************************************************/
	public static void eliminarPedido() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		
		 System.out.println("\n--- ELIMINAR PEDIDO ---");
		 
		 try {
			 System.out.print("Ingrese el ID del pedido a dar de BAJA: ");
			 int id = Integer.parseInt(sc.nextLine());
		   
			 transaccion.begin();
				
			 Pedido pedido = em.find(Pedido.class, id);
			 if (pedido != null) {
				em.remove(pedido); 
				System.out.println("Pedido ID " + id + " eliminado correctamente.");
			 } else {
				System.out.println("No existe un pedido con ID: " + id);
			 }
				
			 transaccion.commit();
			
		} catch (NumberFormatException e) {
			System.out.println("Error: El ID debe ser un número válido.");
			if (transaccion.isActive()) transaccion.rollback();
		} catch (Exception e) {
			System.out.println("Error al eliminar pedido: " + e.getMessage());
			if (transaccion.isActive()) transaccion.rollback();
		} finally {
			em.close();
		}
	}
	
	/************************************************************/
	public static void actualizarPedido() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		
		System.out.println("\n--- MODIFICAR PEDIDO ---");
		
		try {
			System.out.print("Ingrese el ID del pedido que desea modificar: ");
			int id = Integer.parseInt(sc.nextLine());
	        
			Pedido p = em.find(Pedido.class, id);
	        
			if (p == null) {
	        	System.out.println("Error: No existe un pedido con ID: " + id);
	        	return;
	        }
	        
			System.out.print("Ingrese el nuevo Total €");
			double nuevoTotal = Double.parseDouble(sc.nextLine());
			
	        System.out.print("Ingrese la nueva fecha en formato YYYY-MM-DD): ");
	        Date nuevaFecha = DATE_FORMAT.parse(sc.nextLine());
	        
	        System.out.print("Ingrese el nuevo ID de Cliente: ");
	        int idCliente = Integer.parseInt(sc.nextLine());
	        Cliente nuevoCliente = em.find(Cliente.class, idCliente);
	        
	        if (nuevoCliente == null) {
	        	System.out.println("Error: No se encontró un nuevo cliente con ID: " + idCliente);
		    	return;
		    }
	        
	        System.out.print("Ingrese el nuevo ID de Comercial: ");
	        int idComercial = Integer.parseInt(sc.nextLine());
	        Comercial nuevoComercial = em.find(Comercial.class, idComercial);
	        
	        if (nuevoComercial == null) {
	        	System.out.println("Error: No se encontró un nuevo comercial con ID: " + idComercial);
		    	return;
		    }
			
			transaccion.begin();

	        p.setTotal(nuevoTotal);
	        p.setFecha(nuevaFecha);
	        p.setCliente(nuevoCliente);
	        p.setComercial(nuevoComercial);
			
			transaccion.commit();
			System.out.println("Pedido ID " + id + " modificado correctamente.");
			
		} catch (ParseException e) {
			System.out.println("Error: Formato de fecha inválido. Use YYYY-MM-DD.");
			if (transaccion.isActive()) transaccion.rollback();
		} catch (NumberFormatException e) {
			System.out.println("Error: El Total o los IDs deben ser números válidos.");
			if (transaccion.isActive()) transaccion.rollback();
		} catch (Exception e) {
			System.out.println("Error al actualizar pedido: " + e.getMessage());
			if (transaccion.isActive()) transaccion.rollback();
		} finally {
			em.close();
		}
	}
	
	/************************************************************/
	/************************ MENÚS *****************************/
	/************************************************************/
	
	public static void menuClientes() {
		boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ CRUD CLIENTES ---");
            System.out.println("1. Insertar Cliente");
            System.out.println("2. Actualizar Cliente");
            System.out.println("3. Borrar Cliente");
            System.out.println("4. Buscar Cliente por ID");
            System.out.println("5. Salir al Menú Principal");
            System.out.print("Elige una opción: ");
            
            String opcion = sc.nextLine();
            switch (opcion) {
                case "1":
                    insertarCliente();
                    break;
                case "2":
                	actualizarCliente();
                    break;
                case "3":
                	eliminarCliente();
                	break;
                case "4":
                	obtenerClientePorId();
                	break;
                case "5":
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida, prueba de nuevo.");
            }
        }
	}
	
	public static void menuPedidos() {
		boolean salir = false;

        while (!salir) {
            System.out.println("\n--- MENÚ CRUD PEDIDOS ---");
            System.out.println("1. Insertar Pedido");
            System.out.println("2. Actualizar Pedido");
            System.out.println("3. Borrar Pedido");
            System.out.println("4. Buscar Pedido por ID");
            System.out.println("5. Salir al Menú Principal");
            System.out.print("Elige una opción: ");
            
            String opcion = sc.nextLine();
            try {
	            switch (opcion) {
	                case "1":
	                    crearPedido();
	                    break;
	                case "2":
	                    actualizarPedido();
	                    break;
	                case "3":
	                	eliminarPedido();
	                	break;
	                case "4":
	                	obtenerPedidoPorId();
	                	break;
	                case "5":
	                    salir = true;
	                    break;
	                default:
	                    System.out.println("Opción no válida, prueba de nuevo.");
	            }
            } catch (Exception e) {
            	System.out.println("Ocurrió un error inesperado en la operación: " + e.getMessage());
            }
        }
	}
	
	public static void menu() {
		 boolean salir = false;

		 while (!salir) {
		     System.out.println("\n--- MENÚ PRINCIPAL JPA SENCILLO ---");
		     System.out.println("1. CRUD de Clientes");
		     System.out.println("2. CRUD de Pedidos");
		     System.out.println("3. Salir del Programa");
		     System.out.print("Elige una opción: ");
		     
		     String opcion = sc.nextLine();
		     switch (opcion) {
		         case "1":
		             menuClientes();
		             break;
		         case "2":
		             menuPedidos();
		             break;
		         case "3":
		             salir = true;
		             break;
		         default:
		             System.out.println("Opción no válida, prueba de nuevo.");
		     }
		 }
		}
}