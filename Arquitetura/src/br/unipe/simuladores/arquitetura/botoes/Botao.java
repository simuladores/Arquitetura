package br.unipe.simuladores.arquitetura.botoes;

import br.unipe.simuladores.arquitetura.simulacao.Controlador;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Botao extends Group {

	protected ImageView image;
	protected Animation animation;
	protected Controlador controlador;
	protected boolean stopped = false;
	protected Botao este;
		
	public Botao(String pathImg) {
		
		super();
		image = new ImageView(new Image(getClass().getResourceAsStream(pathImg)));
        getChildren().add(image);
        definirAcoes();
		
	}
	
	public Botao(String pathImg, Animation animation) {
		super();
		image = new ImageView(new Image(getClass().getResourceAsStream(pathImg)));
		this.animation = animation;
        getChildren().add(image);
        definirAcoes();
	}
	
	protected void definirAcoes() {
		
		setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent arg0) {
				
				setCursor(Cursor.HAND);
				
			}
			
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent arg0) {
				
				setCursor(Cursor.DEFAULT);
				
			}
			
		});
		
	}


	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public boolean isStopped() {
		return stopped;
	}

	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}
	

}
