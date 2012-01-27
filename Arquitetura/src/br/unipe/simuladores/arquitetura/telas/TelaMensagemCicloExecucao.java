package br.unipe.simuladores.arquitetura.telas;

import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;
import javafx.scene.paint.Color;

public class TelaMensagemCicloExecucao extends TelaMensagemSimulacao{
	
	private static final String TRANSFERIR_IR_MBRTXT = "O valor contido no " +
			"segundo operando de IR � copiado para MBR,\n para em seguida, ser " +
			"transferido para a mem�ria.";
	/*private static final String TRANSFERIR_MAR_MBR_MEMORIATXT = "O dado, que � o valor " +
			"que est� contido em MBR, e o endere�o onde esses dado ser� inserido na " +
			"mem�ria, que est� em MAR, s�o transferidos para a mem�ria, pelos barramentos " +
			"de dados e endere�os, respectivamente.";*/
	private static final String COPIAR_VALOR_MAR_BARRAMENTO_EXECUCAOTXT = "O valor de MAR, que � " +
			"o endere�o para onde o dado ser� inserido na mem�ria,\n � transferido para o barramento " +
			"de endere�os.";
	private static final String TRANSFERIR_IR_MARTXT = "A refer�ncia do endere�o para " +
			"onde ser� transferido o dado � copiado para MAR.";
	private static final String COPIAR_VALOR_MBR_BARRAMENTO_EXECUCAOTXT = "O valor de MBR, " +
			"que corresponde ao dado a ser escrito na mem�ria, � transferido para o barramento " +
			"de dados.";
	private static final String COPIAR_WRITE_BARRAMENTOTXT = "A instru��o \"WRITE\" � copiada " +
			"da UC para o barramento de instru��es";
	private static final String MOVER_DADOS_BARRAMENTO_MEMORIA_ESCRITATXT = "Os dados " +
			"contidos no barramento s�o ent�o transferidos para a mem�ria.";

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
		//case TRANSFERIR_MAR_MBR_MEMORIA: return TRANSFERIR_MAR_MBR_MEMORIATXT;
		}
		
		return null;
	}

}
