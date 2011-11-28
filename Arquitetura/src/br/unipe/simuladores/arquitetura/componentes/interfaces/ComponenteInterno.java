package br.unipe.simuladores.arquitetura.componentes.interfaces;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public abstract class ComponenteInterno extends Componente{
	
	protected Rectangle retangulo;
	
	public ComponenteInterno() {
		
		super();
		retangulo = new Rectangle();
		definirAcoesGerais();
		buildContent();
		
	}
	
	public void surgir(double time) {
		
		group.setVisible(true);
		
		  timeline = new Timeline();
			
		  timeline.getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(group.opacityProperty(), 0.0f)
	               ),
	               new KeyFrame(new Duration(time), 
	                	new KeyValue(group.opacityProperty(), 1.0f)
	               )
	       );
			
	      timeline.play();
		
	}

}
