package br.unipe.simuladores.arquitetura.componentes.interfaces;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public abstract class ComponenteCirculo extends Componente{
	
	protected Circle circulo;
	
	protected boolean expanded;
	
	public ComponenteCirculo() {
		
		super();
		circulo = new Circle();
		definirAcoesGerais();
		buildContent();

	}
	
    public abstract void expandir(double fromScale, double toScale, double time);
	
	public abstract void encolher(double fromScale, double toScale, double time);
	
	public void definirAcoesGerais() {
		
		/*Define as a��es que ocorrem com os componentes*/
		group.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
					
				if (expanded)
					group.setCursor(Cursor.DEFAULT);
				else
					group.setCursor(Cursor.HAND);
					
			}
				
		});
			
		group.setOnMouseExited(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
					
				group.setCursor(Cursor.DEFAULT);
					
			}
				
		});
		
	}
	
	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

}
