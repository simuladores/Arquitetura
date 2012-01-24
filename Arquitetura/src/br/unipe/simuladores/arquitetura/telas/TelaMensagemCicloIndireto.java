package br.unipe.simuladores.arquitetura.telas;

import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;
import javafx.scene.paint.Color;

public class TelaMensagemCicloIndireto extends TelaMensagemSimulacao{

	private static final String VERIFICAR_IRTXT = "A unidade de controle examina " +
			"o conteúdo de IR para determinar se ele contém um especificador de " +
			"operando que use endereçamento indireto";
	
	public TelaMensagemCicloIndireto(EstadoCiclo estado) {
		super("Mensagem", Color.rgb(245, 245, 245));
		
		super.estado = estado;
		
		modificarMensagem(obterTexto());
	}

	@Override
	protected String obterTexto() {
		
		switch(estado) {
		case VERIFICAR_IR: return VERIFICAR_IRTXT;
		}
		
		return null;
	}

	
}
