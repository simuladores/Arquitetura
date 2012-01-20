package br.unipe.simuladores.arquitetura.botoes;

import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class BotaoPlay extends Botao {
	
	private ImageView imgPause;
	private ImageView curImg;
	private boolean paused = true;
	
	public BotaoPlay() {
		
		super("play.png");
		imgPause = new ImageView(new Image(getClass().getResourceAsStream("pause.png")));
		curImg = image;
		definirAcoesEspecificas();
		
	}
	
	public BotaoPlay(Timeline timeline) {
		
		super("play.png", timeline);
		definirAcoesEspecificas();
		
	}
	
	private void definirAcoesEspecificas() {
		
		setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				
				if (paused) {
					
					setPaused(false);
					timeline.play();
					
				} else {
					
					setPaused(true);
					timeline.pause();
					
				}
				
			}
			
		});
		
	}

	public ImageView getImgPause() {
		return imgPause;
	}

	public void setImgPause(ImageView imgPause) {
		this.imgPause = imgPause;
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		
		this.paused = paused;
		
		if (paused) {
			
			getChildren().remove(curImg);
			curImg = image;
			getChildren().add(image);
			
		} else {
			
			getChildren().remove(curImg);
			curImg = imgPause;
			getChildren().add(imgPause);
			
		}
	}
	
	
	

}
