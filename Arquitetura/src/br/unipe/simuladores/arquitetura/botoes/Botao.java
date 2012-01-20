package br.unipe.simuladores.arquitetura.botoes;

import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Botao extends Group {

	protected ImageView image;
	protected Timeline timeline;
		
	public Botao(String pathImg) {
		
		super();
		image = new ImageView(new Image(getClass().getResourceAsStream(pathImg)));
        getChildren().add(image);
        definirAcoes();
		
	}
	
	public Botao(String pathImg, Timeline timeline) {
		super();
		image = new ImageView(new Image(getClass().getResourceAsStream(pathImg)));
		this.timeline = timeline;
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

	public Timeline getTimeline() {
		return timeline;
	}

	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}
	

}
