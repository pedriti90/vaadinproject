package es.ayesa.mavenprojecgt;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.themes.ValoTheme;

@SuppressWarnings("serial")
public class LibroView extends VerticalLayout implements View {
	private LibroService libroService = LibroService.getInstancia();
	private Grid<Libro> grid = new Grid<>(Libro.class);
	final TextField filterText = new TextField();
	private LibroFormBack form = new LibroFormBack(this);
	private Navigator navigator;
	
	public static final String NAME = "LIBROS";
	
	public LibroView(Navigator navigator) {
		this.navigator = navigator;
		
		form.setVisible(false);

		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.setColumns("titulo", "autor", "cliente");
		grid.setSizeFull();

		actualizarTabla();

		Button borrarFiltro = new Button(FontAwesome.TIMES);
		borrarFiltro.setDescription("Borrar Filtro");
		borrarFiltro.addClickListener(e -> filterText.clear());

		filterText.setPlaceholder("Filtrar por titulo:");
		filterText.addValueChangeListener(e -> actualizarTabla());
		filterText.setValueChangeMode(ValueChangeMode.LAZY);

		CssLayout filtrado = new CssLayout();
		filtrado.addComponents(filterText, borrarFiltro);
		filtrado.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

		HorizontalLayout main = new HorizontalLayout();
		main.addComponents(grid, form);
		main.setSizeFull();
		grid.setSizeFull();
		form.setSizeFull();
		main.setExpandRatio(grid, 2);
		main.setExpandRatio(form, 1);
		
		grid.asSingleSelect().addValueChangeListener(evento -> {
			if( evento.getValue() == null ) {
				form.setVisible(false);
			} else {
				form.setLibro ( evento.getValue());
			}
		});
		
		HorizontalLayout botonera = new HorizontalLayout();
		Button nuevoLibro= new Button("Nuevo Libro");
		nuevoLibro.addClickListener( evento -> {grid.asSingleSelect().clear(); form.setLibro(new Libro());});
		
		Button navegar = new Button("Prueba");
		navegar.addClickListener( event -> this.navigator.navigateTo(PruebaView.NAME));
		
		botonera.addComponents(filtrado, nuevoLibro);
		
		this.addComponents(botonera, main);

	}
	
	public void actualizarTabla() {
		grid.setItems(libroService.findAll(filterText.getValue()));

	}

	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}

}
