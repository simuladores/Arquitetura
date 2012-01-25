package br.unipe.simuladores.arquitetura.telas;

import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;
import br.unipe.simuladores.arquitetura.enums.OperandoCicloIndireto;
import javafx.scene.paint.Color;

public class TelaMensagemCicloIndireto extends TelaMensagemSimulacao{

	private OperandoCicloIndireto operando;
	
	private static final String VERIFICAR_IRTXT = "A unidade de controle examina " +
			"o conteúdo de IR para determinar\n se ele contém um especificador de " +
			"operando que use endereçamento\n indireto.";
	private static final String TRANSFERIR_OPERANDO_MARXT = "operando faz " +
			"uma referência indireta a um dado na memória.\n Nesse caso, os bits em IR" +
			" que fazem essa referência são transferidos para MAR.";
	private static final String TRANSFERIR_OPERANDO_MARXT_NAO_HA = "Não há operandos " +
			"nessa instrução que fazem referência indireta à memória.";
	private static final String COPIAR_READ_BARRAMENTO_INDTXT = "O comando \"READ\" é " +
			"transferido para o barramento de endereços para iniciar a leitura do " +
			"ciclo indireto.";
	private static final String COPIAR_VALOR_MAR_BARRAMENTO_INDTXT = "O valor de MAR, que " +
			"corresponde ao endereço indireto, é transferido para o barramento de endereços.";
	private static final String MOVER_DADOS_BARRAMENTO_MEMORIA_INDTXT = "Os dados contidos no " +
			"barramento são então movidos para a memória,\n requisitando o endereço contido " +
			"no endereço que está no barramento.";
	private static final String TRANSFERIR_DADO_REFERENCIA_INDIRETA_BARRAMENTOTXT = "O endereço que contém o dado " +
			"e que é referenciado indiretamente pela referência\n em MAR é transferido para a UCP " +
			"pelo barramento de dados.";
	private static final String TRANSFERIR_MBR_PARA_IRTXT = "O campo de endereço referente " +
			"ao operando que faz a referência indireta\n é atualizado para conter o valor que " +
			"ele referencia, ou seja, o endereço\n que contém o dado";
	
	public TelaMensagemCicloIndireto(EstadoCiclo estado) {
		super("Mensagem", Color.rgb(245, 245, 245));
		
		super.estado = estado;
		
		modificarMensagem(obterTexto());
	}
	
	public TelaMensagemCicloIndireto(EstadoCiclo estado, OperandoCicloIndireto operando) {
		super("Mensagem", Color.rgb(245, 245, 245));
		
		super.estado = estado;
		
		this.operando = operando;
		
		modificarMensagem(obterTexto());
	}

	@Override
	protected String obterTexto() {
		
		switch(estado) {
		case VERIFICAR_IR: return VERIFICAR_IRTXT;
		case TRANSFERIR_OPERANDO_MAR: {
			if (operando == OperandoCicloIndireto.PRIMEIRO)
				return "O primeiro " + TRANSFERIR_OPERANDO_MARXT;
			else if (operando == OperandoCicloIndireto.SEGUNDO)
				return "O segundo " + TRANSFERIR_OPERANDO_MARXT;
			else
				return TRANSFERIR_OPERANDO_MARXT_NAO_HA;
		}
		case COPIAR_READ_BARRAMENTO_IND: return COPIAR_READ_BARRAMENTO_INDTXT;
		case COPIAR_VALOR_MAR_BARRAMENTO_IND: return COPIAR_VALOR_MAR_BARRAMENTO_INDTXT;
		case MOVER_DADOS_BARRAMENTO_MEMORIA_IND: return MOVER_DADOS_BARRAMENTO_MEMORIA_INDTXT;
		case TRANSFERIR_DADO_REFERENCIA_INDIRETA_BARRAMENTO: return TRANSFERIR_DADO_REFERENCIA_INDIRETA_BARRAMENTOTXT;
		case TRANSFERIR_MBR_PARA_IR: return TRANSFERIR_MBR_PARA_IRTXT;
		}
		
		return null;
	}

	
}
