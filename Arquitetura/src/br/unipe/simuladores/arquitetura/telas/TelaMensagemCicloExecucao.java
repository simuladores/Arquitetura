package br.unipe.simuladores.arquitetura.telas;

import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;
import javafx.scene.paint.Color;

public class TelaMensagemCicloExecucao extends TelaMensagemSimulacao{
	
	private static final String TRANSFERIR_IR_MBRTXT = "O valor contido no " +
			"segundo operando de IR é copiado para MBR,\n para em seguida, ser " +
			"transferido para a memória.";
	private static final String TRANSFERIR_MAR_MBR_MEMORIATXT = "O dado, que é o valor " +
			"que está contido em MBR, e o endereço onde esses dado será inserido na " +
			"memória, que está em MAR, são transferidos para a memória, pelos barramentos " +
			"de dados e endereços, respectivamente.";
	private static final String TRANSFERIR_IR_MARTXT = "A referência do endereço para " +
			"onde será transferido o dado é copiado para MAR";

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
