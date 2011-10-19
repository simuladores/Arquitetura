package br.unipe.simuladores.arquitetura.componentes.internos;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import br.unipe.simuladores.arquitetura.componentes.interfaces.ComponenteInterno;

public class UCPInterna extends ComponenteInterno{
	
	public UCPInterna() {
		
		super();
		
	}

	@Override
	protected void adicionarTexto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void buildContent() {
		
		group.getChildren().removeAll(retangulo);
		
		retangulo.setWidth(280);
		retangulo.setHeight(280);
		retangulo.setFill(Color.WHEAT);
		retangulo.setX(750);
		retangulo.setY(370);
		
		group.getChildren().addAll(retangulo);
		group.setVisible(false);
		
	}

	@Override
	protected void definirAcoesGerais() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void definirAcoesEspecificas() {
		// TODO Auto-generated method stub
		
	}

	@Override
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
