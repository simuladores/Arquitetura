package br.unipe.simuladores.arquitetura.telas;

import javafx.scene.paint.Color;
import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;

public class TelaMensagemCicloBusca extends TelaMensagemSimulacao{
	
	private static final String ATUALIZAR_PCTXT = "Ser� dado in�cio ao ciclo de busca, " +
			"para buscar a pr�xima instru��o na mem�ria.\n O valor de PC est� " +
			"atualizado com o endere�o da instru��o que ser� executada\n em seguida.";
	private static final String MOVER_MARTXT = "O valor de PC � ent�o transferido para" +
			" MAR, que cont�m os endere�os\n que s�o tranferidos do barramento para a " +
			"mem�ria e que chegam\n da mem�ria atrav�s do barramento." ;
	private static final String COPIAR_READ_BARRAMENTOTXT = "A unidade de controle � " +
			"respons�vel por coordenar as atividades da UCP.\n Neste caso, ela envia o " +
			"comando \"READ\" para o barramento, significando\n que a UCP est� requisi" +
			"tando uma leitura de dados da mem�ria.";
	private static final String COPIAR_VALOR_MAR_BARRAMENTOTXT = "Assim como o comando " +
			"\"READ\", o valor de MAR, que cont�m o endere�o\n da pr�xima instru��o, " +
			"tamb�m � transferido para o barramento.";
	private static final String MOVER_DADOS_BARRAMENTO_MEMORIATXT = "Esses dois valores " +
			"ser�o transferidos para a mem�ria, pelo barramento.\n O valor de MAR " +
			"no barramento de endere�os e o comando \"READ\"\n no barramento de instru��es.";
	private static final String TRANSFERIR_INSTRUCAOTXT = "A instru��o requisitada � " +
			"ent�o transferida para a UCP pelo barramento de dados.\n " +
			"Inicialmente, o registrador que conter� a instru��o ser� o MBR, j� que " +
			"� ele\n que armazena os dados ou instru��es logo que chegam da mem�ria.";
	private static final String ATUALIZAR_PC_PROX_INSTRUCAOTXT = "O valor de PC � atualizado " +
			"para conter o valor da pr�xima instru��o.";
	private static final String NAO_HA_PROX_INSTRUCAOTXT = "Como n�o h� pr�xima instru��o a ser executada, " +
			"o valor de PC n�o � alterado.";
	private static final String COPIAR_MBR_PARA_IRTXT = "A instru��o, que est� " +
			"em MBR, � copiada para IR, o registrador de instru��es.";
	
	public TelaMensagemCicloBusca(EstadoCiclo estado) {
		super("Mensagem", Color.rgb(245, 245, 245));
		
		super.estado = estado;
		
		modificarMensagem(obterTexto());
		
	}
	
	public String obterTexto() {
		
		switch(estado) {
		case INICIAL: return "Inicial";
		case ATUALIZAR_PC: return ATUALIZAR_PCTXT;
		case MOVER_MAR: return MOVER_MARTXT;
		case COPIAR_READ_BARRAMENTO: return COPIAR_READ_BARRAMENTOTXT;
		case COPIAR_VALOR_MAR_BARRAMENTO: return COPIAR_VALOR_MAR_BARRAMENTOTXT;
		case MOVER_DADOS_BARRAMENTO_MEMORIA: return MOVER_DADOS_BARRAMENTO_MEMORIATXT;
		case TRANSFERIR_INSTRUCAO: return TRANSFERIR_INSTRUCAOTXT;
		case ATUALIZAR_PC_PROX_INSTRUCAO: return ATUALIZAR_PC_PROX_INSTRUCAOTXT;
		case NAO_HA_PROX_INSTRUCAO: return NAO_HA_PROX_INSTRUCAOTXT;
		case COPIAR_MBR_PARA_IR: return COPIAR_MBR_PARA_IRTXT;
		}
		
		return null;
		
	}	

}
