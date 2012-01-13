package br.unipe.simuladores.simulacao.execucao.instrucoes;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.concurrent.Service;
import javafx.concurrent.ServiceBuilder;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import br.unipe.simuladores.arquitetura.componentes.internos.UCPInterna;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;

public class Animadora extends Service<Void>{
	
	private Movimentador movimentador;
	
	public Animadora(Movimentador m) {
		this.movimentador = m;
	}

	@Override
	protected Task<Void> createTask() {
		
		return new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				
				movimentador.buscarInstrucao();
				
				return null;
				
			}
			
		};
		

	}

}
