package br.unipe.simuladores.arquitetura.componentes.interfaces;

import javafx.scene.shape.Rectangle;

public abstract class ComponenteInterno extends Componente{
	
	private Rectangle retangulo;
	
	public ComponenteInterno() {
		
		super();
		retangulo = new Rectangle();
		definirAcoesGerais();
		buildContent();
		
	}
	
	public abstract void surgir();

}
