package br.unipe.simuladores.arquitetura.componentes.internos;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import br.unipe.simuladores.arquitetura.componentes.interfaces.ComponenteInterno;

public class UCPInterna extends ComponenteInterno{
	
	private List<Rectangle> registradores;
	private Rectangle uc;
	
	private Group grupoRegistradores; 
	
	
	public UCPInterna() {
		
		super();
		
	}

	@Override
	protected void adicionarTexto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void buildContent() {
		
		group.getChildren().removeAll(retangulo, grupoRegistradores, uc);
		
		registradores = new ArrayList<Rectangle>();
		grupoRegistradores = new Group();
		
		retangulo.setWidth(280);
		retangulo.setHeight(280);
		retangulo.setFill(Color.WHEAT);
		retangulo.setX(750);
		retangulo.setY(370);
		
		int i, y = 420;
		Rectangle registrador;
		for (i = 0; i < 4; i++, y += 25) {
			
			registrador = new Rectangle();
			registrador.setWidth(50);
			registrador.setHeight(25);
			registrador.setFill(Color.CHARTREUSE);
			registrador.setX(780);
			registrador.setY(y);
			registrador.setStroke(Color.BLACK);
			registrador.setStrokeWidth(0.5);
			
			registradores.add(registrador);
			
		}
		
		for (Rectangle reg : registradores) 
			grupoRegistradores.getChildren().add(reg);
		
		uc = new Rectangle();
		uc.setWidth(50);
		uc.setHeight(25);
		uc.setFill(Color.CORAL);
		uc.setX(780);
		uc.setY(600);
		//uc.setStroke(Color.BLACK);
		//uc.setStrokeWidth(0.5);
		
		group.getChildren().addAll(retangulo, grupoRegistradores, uc);
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
