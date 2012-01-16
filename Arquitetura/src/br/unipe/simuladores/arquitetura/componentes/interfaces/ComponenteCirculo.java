package br.unipe.simuladores.arquitetura.componentes.interfaces;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public abstract class ComponenteCirculo extends Componente{
	
	protected Circle circulo;
	
	protected boolean expanded;
	
	protected Text textoExplicativo;
	
	public ComponenteCirculo() {
		
		super();
		circulo = new Circle();
		definirAcoesGerais();
		buildContent();
		setTextoExplicativo(new Text("Este é um computador"));

	}
	
    public abstract void expandir(double fromScale, double toScale, double time);
	
	public abstract void encolher(double fromScale, double toScale, double time);
	
	public void definirAcoesGerais() {
		
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
	
	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public Text getTextoExplicativo() {
		return textoExplicativo;
	}

	public void setTextoExplicativo(Text textoExplicativo) {
		this.textoExplicativo = textoExplicativo;
	}

}
