package es.ayesa.mavenprojecgt;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Libro implements Serializable, Cloneable{
	private String titulo;
	private String autor;
	private Long id;
	private Cliente cliente;
	
	public Libro clone() throws CloneNotSupportedException {
		return (Libro) super.clone();
	}
	
	public int hashCode() {
		int hash = 5;
		
		hash = 42 * hash + (id == null ? 0: id.hashCode());
		
		return hash;
	}
	
	public boolean equals(Object obj) {
		if( this == obj ) {
			return true;
		}
		
		if(this.id == null) {
			return false;
		}
		
		if ( obj instanceof Libro && obj.getClass().equals(getClass())) {
			return this.id.equals(((Libro) obj).id);
		}
		return false;
	}
	
	public String toString() {
		return titulo + ", " + autor;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

}
