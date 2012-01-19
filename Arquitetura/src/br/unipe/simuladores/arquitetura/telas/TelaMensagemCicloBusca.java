package br.unipe.simuladores.arquitetura.telas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import br.unipe.simuladores.arquitetura.enums.EstadoCicloBusca;
import br.unipe.simuladores.arquitetura.simulacao.Busca;

public class TelaMensagemCicloBusca extends TelaMensagemSimulacao{

	private EstadoCicloBusca estado;
	private Busca busca;
	
	public TelaMensagemCicloBusca(String titulo, Color cor, String mensagem,
			Busca busca, EstadoCicloBusca estado) {
		super(titulo, cor, mensagem, busca);
		
		this.busca = busca;
		this.estado = estado;
		
	}

	@Override
	public void definirAcoesBotoes() {
		
		continuar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				switch(estado) {
				case INICIAL: busca.atualizarPC(); break;
				case ATUALIZAR_PC: busca.moverEnderecoPCParaMAR();break;
				case MOVER_MAR: busca.copiarREADParaBarramento(); break;
				case COPIAR_READ_BARRAMENTO: busca.copiarValorMARParaBarramento(); break;
				case COPIAR_VALOR_MAR_BARRAMENTO: busca.moverDadosBarramentoParaMemoria(); break;
				}
				
				stage.close();
				
				
			}
			
		});
		
		cancelar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				TelaPrincipal.setExibirMensagensDeSimulacao(false);
				
				stage.close();
				
			}
			
		});
		
	}

}
