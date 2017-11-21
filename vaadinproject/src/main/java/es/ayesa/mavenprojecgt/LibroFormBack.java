package es.ayesa.mavenprojecgt;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;

public class LibroFormBack extends LibroForm {
	private LibroService libroService = LibroService.getInstancia();
	private Binder<Libro> binder = new Binder<>(Libro.class);
	private Libro libro;
	private LibroView myUI;
	
	public LibroFormBack( LibroView myui ) {
		this.myUI = myui;
		
		guardar.setClickShortcut( KeyCode.ENTER );
		cliente.setItems ( ClienteService.getInstancia().findAll() );
		
		binder.bindInstanceFields( this );
		
		guardar.addClickListener( e -> this.guardar() );
		borrar.addClickListener( e -> this.borrar() );
	}

	private void guardar() {
		libroService.guardar( libro );
		myUI.actualizarTabla();
		setVisible( false );
	}
	
	private void borrar() {
		libroService.borrar( libro );
		myUI.actualizarTabla();
		setVisible( false );
	}
	
	public void setLibro(Libro libro) {
		this.libro = libro;
		binder.setBean( libro );
		
		borrar.setVisible( libro.getId() != null );
		setVisible( true );
		titulo.selectAll();
	}
	
}
