package br.unipe.simuladores.arquitetura.telas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import br.unipe.simuladores.arquitetura.enums.EstadoCicloBusca;
import br.unipe.simuladores.arquitetura.simulacao.Busca;

public class TelaMensagemCicloBusca extends TelaMensagemSimulacao{

	private EstadoCicloBusca estado;
	private Busca busca;
	
	public TelaMensagemCicloBusca(EstadoCicloBusca estado) {
		super("Mensagem", Color.rgb(245, 245, 245));
		
		this.estado = estado;
		
		modificarMensagem(obterTexto());
		
	}
	
	public String obterTexto() {
		
		switch(estado) {
		case INICIAL: return "Inicial";
		case ATUALIZAR_PC: return "Atualizar PC";
		case MOVER_MAR: return "Mover Mar";
		case COPIAR_READ_BARRAMENTO: return "Copiar read para barramento";
		case COPIAR_VALOR_MAR_BARRAMENTO: return "Copiar valor para barramento";
		case MOVER_DADOS_BARRAMENTO_MEMORIA: return "Mover dados do barramento para a memória";
		}
		
		return null;
		
	}

	@Override
	public void definirAcoesBotoes() {
		
		continuar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
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
	
	private void continuar() {
		
		busca.setContinuar(true);
		stage.close();
		
	}

}
