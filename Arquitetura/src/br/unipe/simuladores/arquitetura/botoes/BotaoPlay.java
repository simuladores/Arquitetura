package br.unipe.simuladores.arquitetura.botoes;

import br.unipe.simuladores.arquitetura.simulacao.Controlador;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class BotaoPlay extends Botao {
	
	private ImageView imgPause;
	private ImageView curImg;
	private boolean paused = true;
	private BotaoStop btnStop;
	
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
		
		este = this;
		setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				
				if (este.isStopped()) {
					
					controlador = new Controlador();
					controlador.iniciarSimulacao();
					btnStop.ativar();
					este.setStopped(false);
					
				} else {
					
					if (paused) {
						
						setPaused(false);
						timeline.play();
						
					} else {
						
						setPaused(true);
						timeline.pause();
						
					}
					
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
	
	public void parar() {
		
		stopped = true;
		getChildren().remove(curImg);
		curImg = image;
		getChildren().add(image);
		
	}

	public BotaoStop getBtnStop() {
		return btnStop;
	}

	public void setBtnStop(BotaoStop btnStop) {
		this.btnStop = btnStop;
	}


}
