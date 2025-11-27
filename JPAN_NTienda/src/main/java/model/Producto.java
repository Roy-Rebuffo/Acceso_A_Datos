package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@NamedQuery(name="Producto.findAll", query="SELECT p FROM Producto p")
public class Producto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_PRODUCTO")
	private int idProducto;

	@Column(name="NOMBRE_PRODUCTO")
	private String nombreProducto;

	public Producto() {
	}
	//bi-directional many-to-many association to Alumno
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
		name="clienteproducto"
		, joinColumns={
			@JoinColumn(name="ID_CLIENTE")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ID_PRODUCTO")
			}
		)
	private List<Cliente> clientes = new ArrayList<Cliente>();
	public Producto(String nombreProducto) {
		super();
		this.nombreProducto = nombreProducto;
	}

	public int getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return this.nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public void addCurso(Cliente cliente) {
		clientes.add(cliente);
		cliente.getProductos().add(this);
	}
	public void removeCurso(Cliente cliente) {
		clientes.remove(cliente);
		cliente.getProductos().remove(this);
	}
	

}