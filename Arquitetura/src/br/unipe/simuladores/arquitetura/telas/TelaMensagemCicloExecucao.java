package br.unipe.simuladores.arquitetura.telas;

import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;
import javafx.scene.paint.Color;

public class TelaMensagemCicloExecucao extends TelaMensagemSimulacao{
	
	private static final String TRANSFERIR_IR_MBRTXT = "O valor contido no " +
			"segundo operando de IR � copiado para MBR,\n para em seguida, ser " +
			"transferido para a mem�ria.";
	private static final String TRANSFERIR_MBR_MEMORIATXT = "O dado, que � o valor " +
			"que est� contido em MBR, � transferido para a mem�ria\n pelo barramento " +
			"de dados.";

	public TelaMensagemCicloExecucao(EstadoCiclo estado) {
		
		super("Mensagem", Color.rgb(245, 245, 245));
		
		super.estado = estado;
		
		modificarMensagem(obterTexto());
		
	}

	@Override
	protected String obterTexto() {
		
		switch(estado) {
		case TRANSFERIR_IR_MBR: return TRANSFERIR_IR_MBRTXT;
		case TRANSFERIR_MBR_MEMORIA: return TRANSFERIR_MBR_MEMORIATXT;
		}
		
		return null;
	}

}
