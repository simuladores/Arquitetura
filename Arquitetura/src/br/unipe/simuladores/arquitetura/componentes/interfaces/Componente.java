package br.unipe.simuladores.arquitetura.componentes.interfaces;

import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public abstract class Componente {
	
	protected Label nome;
	
	protected Group group;
	
	protected Timeline timeline;
	
	public Componente() {
		
		group = new Group();
		
	}
	
	public Group getContent() {
		
		return group;
		
	}
	
	protected abstract void adicionarTexto();
	
	protected abstract void buildContent();
	
	protected abstract void definirAcoesGerais() ;
	
	protected abstract void definirAcoesEspecificas();
	
	public Text getTextoExplicativo() {
		return new Text(obterTextoExplicativo());
	}
	
	public abstract String obterTextoExplicativo();
	
	

}
