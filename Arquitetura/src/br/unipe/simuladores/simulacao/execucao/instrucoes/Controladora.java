package br.unipe.simuladores.simulacao.execucao.instrucoes;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class Controladora extends Service<Void>{

	private Movimentador movimentador;
	
	private Task<Void> task;
	
	public Controladora(Movimentador m) {
		
		this.movimentador = m;
		
		//setExecutor(m);
		
		setTask(createTask());
		
	}
	
	@Override
	public Task<Void> createTask() {
		
		final Controladora este = this;
		
		return new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				
				movimentador.operar();
				
				return null;
			}
			
		};
	}
	
	public Movimentador getMovimentador() {
		return movimentador;
	}
	
	public void setMovimentador(Movimentador movimentador) {
		this.movimentador = movimentador;
	}


	public Task<Void> getTask() {
		return task;
	}

	public void setTask(Task<Void> task) {
		this.task = task;
	}

}
