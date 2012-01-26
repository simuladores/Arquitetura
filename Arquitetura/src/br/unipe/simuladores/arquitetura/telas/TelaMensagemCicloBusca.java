package br.unipe.simuladores.arquitetura.telas;

import javafx.scene.paint.Color;
import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;

public class TelaMensagemCicloBusca extends TelaMensagemSimulacao{
	
	private static final String ATUALIZAR_PCTXT = "Será dado início ao ciclo de busca, " +
			"para buscar a próxima instrução na memória.\n O valor de PC está " +
			"atualizado com o endereço da instrução que será executada\n em seguida.";
	private static final String MOVER_MARTXT = "O valor de PC é então transferido para" +
			" MAR, que contém os endereços\n que são tranferidos do barramento para a " +
			"memória e que chegam\n da memória através do barramento." ;
	private static final String COPIAR_READ_BARRAMENTOTXT = "A unidade de controle é " +
			"responsável por coordenar as atividades da UCP.\n Neste caso, ela envia o " +
			"comando \"READ\" para o barramento, significando\n que a UCP está requisi" +
			"tando uma leitura de dados da memória.";
	private static final String COPIAR_VALOR_MAR_BARRAMENTOTXT = "Assim como o comando " +
			"\"READ\", o valor de MAR, que contém o endereço\n da próxima instrução, " +
			"também é transferido para o barramento.";
	private static final String MOVER_DADOS_BARRAMENTO_MEMORIATXT = "Esses dois valores " +
			"serão transferidos para a memória, pelo barramento.\n O valor de MAR " +
			"no barramento de endereços e o comando \"READ\"\n no barramento de instruções.";
	private static final String TRANSFERIR_INSTRUCAOTXT = "A instrução requisitada é " +
			"então transferida para a UCP pelo barramento de dados.\n " +
			"Inicialmente, o registrador que conterá a instrução será o MBR, já que " +
			"é ele\n que armazena os dados ou instruções logo que chegam da memória.";
	private static final String ATUALIZAR_PC_PROX_INSTRUCAOTXT = "O valor de PC é atualizado " +
			"para conter o valor da próxima instrução.";
	private static final String NAO_HA_PROX_INSTRUCAOTXT = "Como não há próxima instrução a ser executada, " +
			"o valor de PC não é alterado.";
	private static final String COPIAR_MBR_PARA_IRTXT = "O endereço da instrução, que está " +
			"contido em MBR, é copiado para IR, o registrador de instruções.";
	
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
