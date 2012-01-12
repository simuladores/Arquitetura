package br.unipe.simuladores.simulacao.execucao.instrucoes;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class Espera extends Service{

	@Override
	protected Task createTask() {
		return new Task() {

			@Override
			protected Object call() throws Exception {
				
				Thread.sleep(3010);
				
				return null;
			}
			
		};
	}

}
