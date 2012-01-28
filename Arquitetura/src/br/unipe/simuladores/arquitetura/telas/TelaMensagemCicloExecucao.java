package br.unipe.simuladores.arquitetura.telas;

import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;
import javafx.scene.paint.Color;

public class TelaMensagemCicloExecucao extends TelaMensagemSimulacao{
	
	private static final String TRANSFERIR_IR_MBRTXT = "O valor contido no " +
			"segundo operando de IR é copiado para MBR,\n para em seguida, ser " +
			"transferido para a memória.";
	private static final String COPIAR_VALOR_MAR_BARRAMENTO_EXECUCAOTXT = "O valor de MAR é " +
			"transferido para o barramento de endereços.";
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
	private static final String TRANSFERIR_IR_MAR_2TXT = "O segundo operando de IR, que contém a referência " +
			"do dado a ser buscado na memória, é transferido para MAR.";
	private static final String COPIAR_READ_BARRAMENTO_EXECUCAOTXT = "O valor \"READ\" é copiado " +
			"da UC para o barramento de instruções.";
	private static final String MOVER_DADOS_BARRAMENTO_MEMORIA_LEITURATXT = "Os dados " +
			"da requisição da leitura do dado contido no endereço especificado são movidos\n " +
			"para a memória pelo barramento.";
	private static final String TRANSFERIR_DADO_LEITURA_BARRAMENTOTXT = "O dado lido da " +
			"memória é então transferido para a UCP pelo barramento de dados.";
	private static final String TRANSFERIR_MBR_PARA_IR_EXECUCAOTXT = "O valor contido em MBR, " +
			"que corresponde ao dado buscado da memória, é transferido para o segundo operando " +
			"de IR.";
	private static final String MOVER_DADO_REGISTRADOR_IRTXT = "O valor contido no registrador " +
			"referenciado no segundo operando de IR é transferido para esse operando.";

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
		case TRANSFERIR_IR_MAR_2: return TRANSFERIR_IR_MAR_2TXT;
		case COPIAR_READ_BARRAMENTO_EXECUCAO: return COPIAR_READ_BARRAMENTO_EXECUCAOTXT;
		case MOVER_DADOS_BARRAMENTO_MEMORIA_LEITURA: return MOVER_DADOS_BARRAMENTO_MEMORIA_LEITURATXT;
		case TRANSFERIR_DADO_LEITURA_BARRAMENTO: return TRANSFERIR_DADO_LEITURA_BARRAMENTOTXT;
		case TRANSFERIR_MBR_PARA_IR_EXECUCAO: return TRANSFERIR_MBR_PARA_IR_EXECUCAOTXT;
		case MOVER_DADO_REGISTRADOR_IR: return MOVER_DADO_REGISTRADOR_IRTXT;
		}
		
		return null;
	}

}
