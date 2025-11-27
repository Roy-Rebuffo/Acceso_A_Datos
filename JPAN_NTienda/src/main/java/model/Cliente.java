package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_CLIENTE")
	private int idCliente;

	@Column(name="NOMBRE_CLIENTE")
	private String nombreCliente;
	
	public Cliente() {
	}
	//bi-directional many-to-many association to Curso
	@ManyToMany(mappedBy="clientes", cascade = CascadeType.PERSIST)
	
	private List<Producto> productos = new ArrayList<Producto>();

	public Cliente(String nombreCliente) {
		super();
		this.nombreCliente = nombreCliente;
	}

	public int getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombreCliente() {
		return this.nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	
	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	public void addCurso(Producto producto) {
		productos.add(producto);
		producto.getClientes().add(this);
	}
	public void removeCurso(Producto producto) {
		productos.remove(producto);
		producto.getClientes().remove(this);
	}

}