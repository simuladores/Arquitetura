package br.unipe.simuladores.simulacao.execucao.instrucoes;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class Controladora extends Service<Void>{

	private Movimentador movimentador;
	
	public Movimentador getMovimentador() {
		return movimentador;
	}
	
	@Override
	public Task<Void> createTask() {
		
		return new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				
				movimentador.operar();
				
				return null;
			}
			
		};
	}
	
	public void setMovimentador(Movimentador movimentador) {
		this.movimentador = movimentador;
	}

	public Controladora(Movimentador m) {
		
		this.movimentador = m;
		
	}

}
