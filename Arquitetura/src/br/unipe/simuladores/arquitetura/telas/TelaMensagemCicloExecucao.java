package br.unipe.simuladores.arquitetura.telas;

import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;
import javafx.scene.paint.Color;

public class TelaMensagemCicloExecucao extends TelaMensagemSimulacao{
	
	private static final String TRANSFERIR_IR_MBRTXT = "O valor contido no " +
			"segundo operando de IR é copiado para MBR,\n para em seguida, ser " +
			"transferido para a memória.";
	private static final String COPIAR_VALOR_MAR_BARRAMENTO_EXECUCAOTXT = "O valor de MAR, que é " +
			"o endereço para onde o dado será inserido na memória,\n é transferido para o barramento " +
			"de endereços.";
	private static final String TRANSFERIR_IR_MARTXT = "A referência do endereço para " +
			"onde será transferido o dado é copiado para MAR.";
	private static final String COPIAR_VALOR_MBR_BARRAMENTO_EXECUCAOTXT = "O valor de MBR, " +
			"que corresponde ao dado a ser escrito na memória, é transferido para o barramento " +
			"de dados.";
	private static final String COPIAR_WRITE_BARRAMENTOTXT = "A instrução \"WRITE\" é copiada " +
			"da UC para o barramento de instruções";
	private static final String MOVER_DADOS_BARRAMENTO_MEMORIA_ESCRITATXT = "Os dados " +
			"contidos no barramento são então transferidos para a memória.";
	private static final String MOVER_DADO_MEMORIATXT = "O dado é então escrito na " +
			"memória no endereço requisitado pela UCP.";
	private static final String MOVER_DADO_REGISTRADORTXT = "O dado, referente ao segundo operando " +
			"da instrução em IR, é copiado para o registrador especificado no primeiro operando.";
	private static final String MOVER_REFERENCIA_INDIRETA_REGISTRADOR_IRTXT = "O valor do registrador " +
			"referenciado no primeiro operando de IR é transferido para esse operando. ";

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
		case COPIAR_VALOR_MAR_BARRAMENTO_EXECUCAO: return COPIAR_VALOR_MAR_BARRAMENTO_EXECUCAOTXT;
		case COPIAR_VALOR_MBR_BARRAMENTO_EXECUCAO: return COPIAR_VALOR_MBR_BARRAMENTO_EXECUCAOTXT;
		case COPIAR_WRITE_BARRAMENTO: return COPIAR_WRITE_BARRAMENTOTXT;
		case MOVER_DADOS_BARRAMENTO_MEMORIA_ESCRITA: return MOVER_DADOS_BARRAMENTO_MEMORIA_ESCRITATXT;
		case MOVER_DADO_MEMORIA: return MOVER_DADO_MEMORIATXT;
		case MOVER_DADO_REGISTRADOR: return MOVER_DADO_REGISTRADORTXT;
		case MOVER_REFERENCIA_INDIRETA_REGISTRADOR_IR: return MOVER_REFERENCIA_INDIRETA_REGISTRADOR_IRTXT;
		}
		
		return null;
	}

}
