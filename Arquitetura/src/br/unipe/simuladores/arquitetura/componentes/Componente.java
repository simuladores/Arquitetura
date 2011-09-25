package br.unipe.simuladores.arquitetura.componentes;

import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public abstract class Componente {

	protected Circle circulo;
	
	protected Label nome;
	
	protected Group group;
	
	protected Timeline timeline;
	
	protected boolean expanded;
	
	public Componente() {
		
		group = new Group();
		definirAcoesGerais();
		
	}
	
	public abstract void expandir(double fromScale, double toScale, double time);
	
	public abstract void encolher(double fromScale, double toScale, double time);
	
	public abstract Group getContent();
	
	protected abstract void adicionarTexto();
	
	protected abstract void buildContent();
	
	protected void definirAcoesGerais() {
		
			
		/*Define as ações que ocorrem com os componentes*/
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
	
	protected abstract void definirAcoesEspecificas();
	
	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	
	

}
