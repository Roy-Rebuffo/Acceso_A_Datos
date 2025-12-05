package Main;

import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

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
    private static final String NOMBRE = "VentasExamen"; 
    
    private static final EntityManagerFactory emf = 
        Persistence.createEntityManagerFactory(NOMBRE);
    
	public static void main(String[] args) {
		menu();
        
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
        
        sc.close();
        System.out.println("Adiós.");
	}
    
    
	public static void crearCliente() {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaccion = em.getTransaction();
        
        System.out.println("\n--- CREAR NUEVO CLIENTE ---");
		System.out.print("Ingrese el nombre: ");
		String ename = sc.nextLine();
		System.out.print("Ingrese su primer apellido: ");
		String ape1 = sc.nextLine();
		System.out.print("Ingrese su segundo apellido: ");
		String ape2 = sc.nextLine();
		System.out.print("Ingrese su ciudad: ");
		String ciudad = sc.nextLine();
		System.out.print("Ingrese la categoría (número): ");
		int cat = -1;
        try {
            cat = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Categoría no válida. Creación cancelada.");
            em.close();
            return;
        }
		
		try {
		    transaccion.begin();
		    Cliente nuevoCliente = new Cliente(ename, ape1, ape2, ciudad, cat);
		    em.persist(nuevoCliente);
		    transaccion.commit();
		    System.out.println("Cliente agregado correctamente");
		} catch (Exception e) {
		    if (transaccion.isActive()) {
		        transaccion.rollback();
		    }
		    System.err.println("Error al crear el cliente: " + e.getMessage());
		} finally {
		    em.close();
		}
	}
    
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
            System.out.println("ID no válido. Inténtalo de nuevo.");
        } finally {
            em.close();
        }
    }
	
    
	public static void actualizarCliente() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
        
        System.out.println("\n--- MODIFICAR CLIENTE ---");
		System.out.print("Ingrese el ID del cliente que desea modificar: ");
		
        try {
            int id = Integer.parseInt(sc.nextLine());
            Cliente e = em.find(Cliente.class, id);
            
            if(e == null) {
                System.out.println("No existe un cliente con ID: " + id);
                em.close();
                return;
            }
            
            System.out.println("Cliente actual: " + formatoCliente(e));
            
            System.out.print("Ingrese el nuevo nombre: ");
            String ename = sc.nextLine();
            System.out.print("Ingrese su nuevo apellido 1: ");
            String ape1 = sc.nextLine();
            System.out.print("Ingrese su nuevo apellido 2: ");
            String ape2 = sc.nextLine();
            System.out.print("Ingrese su nueva ciudad: ");
            String ciudad = sc.nextLine();
            System.out.print("Ingrese la nueva categoría: ");
            int cat = Integer.parseInt(sc.nextLine()); 
            
            transaccion.begin();
            if (!ename.isEmpty()) e.setNombre(ename);
            if (!ape1.isEmpty()) e.setApellido1(ape1);
            if (!ape2.isEmpty()) e.setApellido2(ape2);
            if (!ciudad.isEmpty()) e.setCiudad(ciudad);
            e.setCategoría(cat);
            
            em.merge(e);
            transaccion.commit();
            System.out.println("Cliente con ID " + id + " modificado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("ID o Categoría no válida");
            if (transaccion.isActive()) transaccion.rollback();
        } catch (Exception e) {
            if (transaccion.isActive()) transaccion.rollback();
            System.err.println("Error al modificar el cliente: " + e.getMessage());
        } finally {
            em.close();
        }
	}
	
    public static void eliminarCliente() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction transaccion = em.getTransaction();
		
        System.out.println("\n--- ELIMINAR CLIENTE ---");
		System.out.print("Ingrese el ID del cliente a dar de BAJA: ");
        
        try {
            int id = Integer.parseInt(sc.nextLine());
            transaccion.begin();
            
            Cliente e = em.find(Cliente.class, id);
            if(e != null) {
                em.remove(e);
                transaccion.commit();
                System.out.println("Cliente eliminado correctamente.");
            } else {
                transaccion.rollback();
                System.out.println("NO EXISTE un cliente con ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("ID no válido.");
        } catch (Exception e) {
             if (transaccion.isActive()) transaccion.rollback();
             System.err.println("Error al eliminar el cliente: " + e.getMessage());
        } finally {
            em.close();
        }
	}
    
    private static String formatoCliente(Cliente c) {
        return "ID: " + c.getId() +
               " | Nombre: " + c.getNombre() +
               " " + c.getApellido1() +
               " " + c.getApellido2() +
               " | Ciudad: " + c.getCiudad() +
               " | Categoría: " + c.getCategoría();
    }
    
    public static void listarClientes() {
        System.out.println("\n--- LISTADO DE CLIENTES ---");
        EntityManager em = emf.createEntityManager();

        try {
            List<model.Cliente> clientes = em.createQuery("SELECT c FROM Cliente c", model.Cliente.class)
                                             .getResultList();

            if (clientes.isEmpty()) {
                System.out.println("No se encontraron clientes.");
                return;
            }

            for (model.Cliente c : clientes) {
                System.out.println(formatoCliente(c));
            }
            System.out.println("------------------------------------------");
            System.out.println("Total de clientes listados: " + clientes.size());

        } catch (Exception e) {
            System.err.println("Error al listar los clientes: " + e.getMessage());
        } finally {
            em.close();
        }
    }

private static String formatoPedido(Pedido p) {
    String fechaStr = p.getFecha() != null ? DATE_FORMAT.format(p.getFecha()) : "N/A";
    String clienteStr = p.getCliente() != null ? p.getCliente().getNombre() + " " + p.getCliente().getApellido1() + " (ID: " + p.getCliente().getId() + ")" : "N/A";
    String comercialStr = p.getComercial() != null ? p.getComercial().getNombre() + " " + p.getComercial().getApellido1() + " (ID: " + p.getComercial().getId() + ")" : "N/A";

    return "ID: " + p.getId() +
           " | Total: " + String.format("%.2f", p.getTotal()) + "€" +
           " | Fecha: " + fechaStr +
           " | Cliente: " + clienteStr +
           " | Comercial: " + comercialStr;
}

public static void crearPedido() {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaccion = em.getTransaction();
    
    System.out.println("\n--- CREAR NUEVO PEDIDO ---");
    
    // 1. Obtener Total
    System.out.print("Ingrese el total del pedido (ej: 1500.50): ");
    double total = 0.0;
    try {
        total = Double.parseDouble(sc.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("Total no válido.");
        em.close();
        return;
    }
    
    // 2. Obtener Fecha
    System.out.print("Ingrese la fecha del pedido (formato YYYY-MM-DD): ");
    Date fecha = null;
    try {
        fecha = DATE_FORMAT.parse(sc.nextLine());
    } catch (ParseException e) {
        System.out.println("Fecha no válida. Se usará la fecha actual.");
        fecha = new Date(); // Usar fecha actual por defecto
    }

    // 3. Obtener Cliente
    System.out.print("Ingrese el ID del Cliente: ");
    int idCliente = -1;
    try {
        idCliente = Integer.parseInt(sc.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("ID de Cliente no válido. Creación cancelada.");
        em.close();
        return;
    }
    Cliente cliente = em.find(Cliente.class, idCliente);
    if (cliente == null) {
        System.out.println("Cliente con ID " + idCliente + " no encontrado. Creación cancelada.");
        em.close();
        return;
    }

    // 4. Obtener Comercial
    System.out.print("Ingrese el ID del Comercial: ");
    int idComercial = -1;
    try {
        idComercial = Integer.parseInt(sc.nextLine());
    } catch (NumberFormatException e) {
        System.out.println("ID de Comercial no válido. Creación cancelada.");
        em.close();
        return;
    }
    Comercial comercial = em.find(Comercial.class, idComercial);
    if (comercial == null) {
        System.out.println("Comercial no encontrado.");
        em.close();
        return;
    }

    // 5. Persistir Pedido
    try {
        transaccion.begin();
        Pedido nuevoPedido = new Pedido(total, fecha, cliente, comercial);
        em.persist(nuevoPedido);
        transaccion.commit();
        System.out.println("Pedido con Totalagregado correctamente");
    } catch (Exception e) {
        if (transaccion.isActive()) {
            transaccion.rollback();
        }
        System.err.println("Error al crear el pedido: " + e.getMessage());
    } finally {
        em.close();
    }
}

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
        System.out.println("ID no válido. Inténtalo de nuevo.");
    } finally {
        em.close();
    }
}

public static void actualizarPedido() {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaccion = em.getTransaction();
    
    System.out.println("\n--- MODIFICAR PEDIDO ---");
    System.out.print("Ingrese el ID del pedido que desea modificar: ");
    
    try {
        int id = Integer.parseInt(sc.nextLine());
        Pedido p = em.find(Pedido.class, id);
        
        if(p == null) {
            System.out.println("No existe un pedido con ID: " + id);
            em.close();
            return;
        }
        
        System.out.println("Pedido actual: " + formatoPedido(p));
        
        
        System.out.print("Ingrese el nuevo total: ");
        String totalStr = sc.nextLine();
        double nuevoTotal = p.getTotal();
        if (!totalStr.isEmpty()) {
            nuevoTotal = Double.parseDouble(totalStr);
        }
        
      
        System.out.print("Ingrese la nueva fechaen formato YYYY-MM-DD): ");
        String fechaStr = sc.nextLine();
        Date nuevaFecha = p.getFecha();
        if (!fechaStr.isEmpty()) {
            nuevaFecha = DATE_FORMAT.parse(fechaStr);
        }

        
        System.out.print("Ingrese el nuevo ID de Cliente: ");
        String idClienteStr = sc.nextLine();
        Cliente nuevoCliente = p.getCliente();
        if (!idClienteStr.isEmpty()) {
            int idCliente = Integer.parseInt(idClienteStr);
            nuevoCliente = em.find(Cliente.class, idCliente);
            if (nuevoCliente == null) {
                throw new Exception("Cliente con ID " + idCliente + " no encontrado.");
            }
        }
        
       
        System.out.print("Ingrese el nuevo ID de Comercial: ");
        String idComercialStr = sc.nextLine();
        Comercial nuevoComercial = p.getComercial();
        if (!idComercialStr.isEmpty()) {
            int idComercial = Integer.parseInt(idComercialStr);
            nuevoComercial = em.find(Comercial.class, idComercial);
            if (nuevoComercial == null) {
                throw new Exception("Comercial con ID " + idComercial + " no encontrado.");
            }
        }
        
        
        transaccion.begin();
        p.setTotal(nuevoTotal);
        p.setFecha(nuevaFecha);
        p.setCliente(nuevoCliente);
        p.setComercial(nuevoComercial);
        
        em.merge(p);
        transaccion.commit();
        System.out.println("Pedido con ID " + id + " modificado correctamente.");
    } catch (NumberFormatException e) {
        System.out.println("ID o Total no válido. Actualización cancelada.");
        if (transaccion.isActive()) transaccion.rollback();
    } catch (ParseException e) {
        System.out.println("Formato de fecha no válido (debe ser YYYY-MM-DD).");
        if (transaccion.isActive()) transaccion.rollback();
    } catch (Exception e) {
        if (transaccion.isActive()) transaccion.rollback();
        System.err.println("Error al modificar el pedido: " + e.getMessage());
    } finally {
        em.close();
    }
}

/**
* Elimina un pedido por su ID.
*/
public static void eliminarPedido() {
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaccion = em.getTransaction();
    
    System.out.println("\n--- ELIMINAR PEDIDO ---");
    System.out.print("Ingrese el ID del pedido a dar de BAJA: ");
    
    try {
        int id = Integer.parseInt(sc.nextLine());
        transaccion.begin();
        
        Pedido p = em.find(Pedido.class, id);
        if(p != null) {
            em.remove(p);
            transaccion.commit();
            System.out.println("Pedido con ID " + id + " eliminado correctamente.");
        } else {
            transaccion.rollback();
            System.out.println("NO EXISTE un pedido con ID: " + id);
        }
    } catch (NumberFormatException e) {
        System.out.println("ID no válido. Eliminación cancelada.");
    } catch (Exception e) {
         if (transaccion.isActive()) transaccion.rollback();
         System.err.println("Error al eliminar el pedido: " + e.getMessage());
    } finally {
        em.close();
    }
}

/**
* Muestra todos los pedidos.
*/
public static void listarPedidos() {
    EntityManager em = emf.createEntityManager();
    System.out.println("\n--- LISTA DE PEDIDOS ---");

    try {
        List<Pedido> pedidos = em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente JOIN FETCH p.comercial", Pedido.class)
                                 .getResultList();
        
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos en la base de datos.");
            return;
        }

        for (Pedido p : pedidos) {
            System.out.println(formatoPedido(p));
        }
        System.out.println("------------------------------------------");
        System.out.println("Total de pedidos: " + pedidos.size());
    } catch (Exception e) {
         System.err.println("Error al listar pedidos: " + e.getMessage());
    } finally {
        em.close();
    }
}

//=======================================================
//=== MENÚ PRINCIPAL Y SUBMENÚS ===
//=======================================================

public static void menu() {
 boolean salir = false;

 while (!salir) {
     System.out.println("\n--- MENÚ PRINCIPAL JPA Sencillo ---");
     System.out.println("1. CRUD de Clientes");
     System.out.println("2. CRUD de Pedidos");
     System.out.println("3. Salir");
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

public static void menuClientes() {
 boolean volver = false;

 while (!volver) {
     System.out.println("\n--- MENÚ CRUD CLIENTES ---");
     System.out.println("1. Crear nuevo Cliente");
     System.out.println("2. Buscar Cliente por ID");
     System.out.println("3. Actualizar datos de Cliente");
     System.out.println("4. Eliminar Cliente");
     System.out.println("5. Listar todos los Clientes");
     System.out.println("6. Volver al menú principal");
     System.out.print("Elige una opción: ");
     
     String opcion = sc.nextLine();
     switch (opcion) {
         case "1":
             crearCliente();
             break;
         case "2":
             obtenerClientePorId();
             break;
         case "3":
             actualizarCliente();
             break;
         case "4":
             eliminarCliente();
             break;
         case "5":
             listarClientes();
             break;
         case "6":
             volver = true;
             break;
         default:
             System.out.println("Opción no válida, prueba de nuevo.");
     }
 }
}

public static void menuPedidos() {
 boolean volver = false;

 while (!volver) {
     System.out.println("\n--- MENÚ CRUD PEDIDOS ---");
     System.out.println("1. Crear nuevo Pedido");
     System.out.println("2. Buscar Pedido por ID");
     System.out.println("3. Actualizar datos de Pedido");
     System.out.println("4. Eliminar Pedido");
     System.out.println("5. Listar todos los Pedidos");
     System.out.println("6. Volver al menú principal");
     System.out.print("Elige una opción: ");
     
     String opcion = sc.nextLine();
     switch (opcion) {
         case "1":
             crearPedido();
             break;
         case "2":
             obtenerPedidoPorId();
             break;
         case "3":
             actualizarPedido();
             break;
         case "4":
             eliminarPedido();
             break;
         case "5":
             listarPedidos();
             break;
         case "6":
             volver = true;
             break;
         default:
             System.out.println("Opción no válida, prueba de nuevo.");
     }
 }
}

}