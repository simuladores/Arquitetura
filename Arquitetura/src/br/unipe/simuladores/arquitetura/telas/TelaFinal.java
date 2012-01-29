package br.unipe.simuladores.arquitetura.telas;

import javafx.scene.paint.Color;

public class TelaFinal extends TelaMensagemSimulacao{
	
	private static final String MENSAGEM = "N�o h� mais instru��es a serem executadas. " +
			"Para executar mais instru��es, insira-as.";

	public TelaFinal() {
		
		super("Mensagem", Color.rgb(245, 245, 245));
		
		modificarMensagem(obterTexto());
		
	}

	@Override
	protected String obterTexto() {
		
		return MENSAGEM;
		
	}

}
