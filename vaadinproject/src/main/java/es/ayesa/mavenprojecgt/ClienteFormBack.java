package es.ayesa.mavenprojecgt;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction.KeyCode;

@SuppressWarnings("serial")
public class ClienteFormBack extends ClienteForm {
	private ClienteService clienteService = ClienteService.getInstancia();
	private Binder<Cliente> binder = new Binder<>(Cliente.class);
	private Cliente cliente;
	private ClienteView myUI;
	
	public ClienteFormBack( ClienteView myui ) {
		this.myUI = myui;
		
		guardar.setClickShortcut( KeyCode.ENTER );
		estado.setItems (ClienteEstado.values() );
		
		binder.bindInstanceFields( this );
		
		guardar.addClickListener( e -> this.guardar() );
		borrar.addClickListener( e -> this.borrar() );
	}

	private void guardar() {
		clienteService.guardar( cliente );
		myUI.actualizarTabla();
		setVisible( false );
	}
	
	private void borrar() {
		clienteService.borrar( cliente );
		myUI.actualizarTabla();
		setVisible( false );
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
		binder.setBean( cliente );
		
		borrar.setVisible( cliente.getId() != null );
		setVisible( true );
		nombre.selectAll();
	}
	
	

}
