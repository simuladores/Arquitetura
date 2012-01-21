package br.unipe.simuladores.arquitetura.botoes;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class BotaoStop extends Botao{

	private BotaoPlay btnPlay;
	
	public BotaoStop() {
		
		super("stop.png");
		definirAcoesEspecificas();
		
	}
	
	
	public void definirAcoesEspecificas() {
		
		este = this;
		setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				
				if (!stopped) {
					
					esmaecer();
					timeline.stop();
					controlador.limpar();
					btnPlay.parar();
					
				} 
				
			}
			
		});
		
	}


	public void esmaecer() {
		
		setOpacity(0.5f);
		stopped = true;
		
	}
	
	public void ativar() {
		
		setOpacity(1.0f);
		stopped = false;
		
	}
	
	public BotaoPlay getBtnPlay() {
		return btnPlay;
	}


	public void setBtnPlay(BotaoPlay btnPlay) {
		this.btnPlay = btnPlay;
	}

}
