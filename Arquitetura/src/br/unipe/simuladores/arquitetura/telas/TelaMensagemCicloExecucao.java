package br.unipe.simuladores.arquitetura.telas;

import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;
import javafx.scene.paint.Color;

public class TelaMensagemCicloExecucao extends TelaMensagemSimulacao{
	
	private static final String TRANSFERIR_IR_MBRTXT = "O valor contido no " +
			"segundo operando de IR � copiado para MBR,\n para em seguida, ser " +
			"transferido para a mem�ria.";
	private static final String TRANSFERIR_MAR_MBR_MEMORIATXT = "O dado, que � o valor " +
			"que est� contido em MBR, e o endere�o onde esses dado ser� inserido na " +
			"mem�ria, que est� em MAR, s�o transferidos para a mem�ria, pelos barramentos " +
			"de dados e endere�os, respectivamente.";
	private static final String TRANSFERIR_IR_MARTXT = "A refer�ncia do endere�o para " +
			"onde ser� transferido o dado � copiado para MAR";

	public TelaMensagemCicloExecucao(EstadoCiclo estado) {
		
		super("Mensagem", Color.rgb(245, 245, 245));
		
		super.estado = estado;
		
		modificarMensagem(obterTexto());
		
	}

	@Override
	protected String obterTexto() {
		
		switch(estado) {
		case TRANSFERIR_IR_MBR: return TRANSFERIR_IR_MBRTXT;
		case TRANSFERIR_IR_MAR: return TRANSFERIR_IR_MARTXT;
		case TRANSFERIR_MAR_MBR_MEMORIA: return TRANSFERIR_MAR_MBR_MEMORIATXT;
		}
		
		return null;
	}

}
