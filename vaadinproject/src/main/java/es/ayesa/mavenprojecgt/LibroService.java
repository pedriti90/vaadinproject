package es.ayesa.mavenprojecgt;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class LibroService {
	private static LibroService instancia;
	private final Map<Long, Libro> biblioteca = new HashMap<>();
	private long siguienteId = 0;
	
	private LibroService() {}
	
	public static LibroService getInstancia() {
		if(instancia == null) {
			instancia = new LibroService();
			instancia.generarDatos();
		}
		
		return instancia;
	}
	
	public List<Libro> findAll(){
		return findAll(null);
	}
	
	public List<Libro> findAll(String cadena){
		return biblioteca.values().stream().filter( libro -> {
			return (libro == null || cadena.isEmpty()) || ( (libro.getTitulo().toLowerCase().contains(cadena) || libro.getAutor().toLowerCase().contains(cadena))); 
		}).collect(Collectors.toList());
	}
	
	private void generarDatos() {
		if(findAll().isEmpty()) {
			Libro libro1 = new Libro();
			libro1.setAutor("Tolkien");
			libro1.setTitulo("El hobbit");
			guardar(libro1);
			
			Libro libro2 = new Libro();
			libro2.setAutor("Tolkien");
			libro2.setTitulo("La comunidad del anillo");
			guardar(libro2);
			
			Libro libro3 = new Libro();
			libro3.setAutor("Tolkien");
			libro3.setTitulo("Las dos torres");
			guardar(libro3);
			
			
			Libro libro4 = new Libro();
			libro4.setAutor("Tolkien");
			libro4.setTitulo("El retorno del rey");
			guardar(libro4);
			
			Libro libro5 = new Libro();
			libro5.setAutor("Tolkien");
			libro5.setTitulo("El silamrillion");
			guardar(libro5);
		
			}
		}
	
	public void guardar(Libro libro) {
		if ( libro == null ) {
			return;
		} else {
			if( libro.getId() == null) {
				libro.setId(siguienteId++);
			}
		}
		
		try {
			libro = libro.clone();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		biblioteca.put(libro.getId(), libro);
	}
	
	public long count() {
		return biblioteca.size();
	}
	
	public void borrar(Libro libro) {
		biblioteca.remove(libro.getId());
	}
}
