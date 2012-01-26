package br.unipe.simuladores.arquitetura.telas;

import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;
import br.unipe.simuladores.arquitetura.enums.OperandoCicloIndireto;
import javafx.scene.paint.Color;

public class TelaMensagemCicloIndireto extends TelaMensagemSimulacao{

	private OperandoCicloIndireto operando;
	
	private static final String VERIFICAR_IRTXT = "A unidade de controle examina " +
			"o conte�do de IR para determinar\n se ele cont�m um especificador de " +
			"operando que use endere�amento\n indireto.";
	private static final String TRANSFERIR_OPERANDO_MARXT = "operando faz " +
			"uma refer�ncia indireta a um dado na mem�ria.\n Nesse caso, os bits em IR" +
			" que fazem essa refer�ncia s�o transferidos para MAR.";
	private static final String TRANSFERIR_OPERANDO_MARXT_NAO_HA = "N�o h� operandos " +
			"nessa instru��o que fazem refer�ncia indireta � mem�ria.";
	private static final String COPIAR_READ_BARRAMENTO_INDTXT = "O comando \"READ\" � " +
			"transferido para o barramento de endere�os para iniciar a leitura do " +
			"ciclo indireto.";
	private static final String COPIAR_VALOR_MAR_BARRAMENTO_INDTXT = "O valor de MAR, que " +
			"corresponde ao endere�o indireto, � transferido para o barramento de endere�os.";
	private static final String MOVER_DADOS_BARRAMENTO_MEMORIA_INDTXT = "Os dados contidos no " +
			"barramento s�o ent�o movidos para a mem�ria,\n requisitando o endere�o contido " +
			"no endere�o que est� no barramento.";
	private static final String TRANSFERIR_DADO_REFERENCIA_INDIRETA_BARRAMENTOTXT = "O endere�o que cont�m o dado " +
			"e que � referenciado indiretamente pela refer�ncia\n em MAR � transferido para a UCP " +
			"pelo barramento de dados.";
	private static final String TRANSFERIR_MBR_PARA_IRTXT = "O campo de endere�o referente " +
			"ao operando que faz a refer�ncia indireta\n � atualizado para conter o valor que " +
			"ele referencia, ou seja, o endere�o\n que cont�m o dado.";
	
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
