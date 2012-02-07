package br.unipe.simuladores.arquitetura.botoes;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class BotaoStop extends Botao{

	private BotaoPlay btnPlay;
	
	public BotaoStop() {
		
		super(STOP_IMAGE_DIR);
		definirAcoesEspecificas();
		
	}
	
	
	public void definirAcoesEspecificas() {
		
		este = this;
		setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent e) {
				
				if (!stopped) {
					
					esmaecer();
					animation.stop();
					controlador.limpar();
					btnPlay.parar();
					
				} 
				
			}
			
		});
		
	}
	
	public BotaoPlay getBtnPlay() {
		return btnPlay;
	}


	public void setBtnPlay(BotaoPlay btnPlay) {
		this.btnPlay = btnPlay;
	}

}
