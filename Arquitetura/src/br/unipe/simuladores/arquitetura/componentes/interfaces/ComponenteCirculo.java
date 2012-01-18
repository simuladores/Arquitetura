package br.unipe.simuladores.arquitetura.componentes.interfaces;

import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public abstract class ComponenteCirculo extends Componente{
	
	protected Circle circulo;
	
	protected boolean expanded;
	
	protected boolean broken = false;
	
	public ComponenteCirculo() {
		
		super();
		circulo = new Circle();
		definirAcoesGerais();
		buildContent();

	}
	
    public abstract void expandir(double fromScale, double toScale, double time);
	
	public abstract void encolher(double fromScale, double toScale, double time);
	
	public void definirAcoesGerais() {
				
		group.setOnMouseExited(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
					
				group.setCursor(Cursor.DEFAULT);
									
				TelaPrincipal.getMensagem().setExpanded(false);
				TelaPrincipal.colocarTextoPadraoMensagem();
					
					
			}
				
		});
		
	}
	
	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public boolean isBroken() {
		return broken;
	}

	public void setBroken(boolean broken) {
		this.broken = broken;
	}

}
