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
			"uma referência indireta a um dado na memória.\n Nesse caso, os bits em MBR" +
			" que fazem essa referência são transferidos para MAR.";
	private static final String TRANSFERIR_OPERANDO_MARXT_NAO_HA = "Não há operandos " +
			"nessa instrução que fazem referência indireta à memória.";
	
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
		}
		
		return null;
	}

	
}
