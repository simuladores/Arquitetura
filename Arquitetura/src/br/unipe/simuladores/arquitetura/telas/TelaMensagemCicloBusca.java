package br.unipe.simuladores.arquitetura.telas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import br.unipe.simuladores.arquitetura.enums.EstadoCicloBusca;
import br.unipe.simuladores.arquitetura.simulacao.Busca;

public class TelaMensagemCicloBusca extends TelaMensagemSimulacao{

	private EstadoCicloBusca estado;
	private Busca busca;
	
	private static final String ATUALIZAR_PCTXT = "Será dado início ao ciclo de busca, " +
			"para buscar a próxima instrução na memória.\n Primeiro, o valor de PC é " +
			"atualizado com o endereço da instrução que será\n executada em seguida";
	private static final String MOVER_MARTXT = "O valor de PC é então transferido para" +
			" MAR, que contém os endereços\n que são tranferidos do barramento para a " +
			"memória e que chegam\n da memória através do barramento" ;
	private static final String COPIAR_READ_BARRAMENTOTXT = "A unidade de controle é " +
			"responsável por coordenar as atividades da UCP.\n Neste caso, ela envia o " +
			"comando \"READ\" para o barramento, significando\n que a UCP está requisi" +
			"tando uma leitura de dados da memória";
	private static final String COPIAR_VALOR_MAR_BARRAMENTOTXT = "Assim como o comando " +
			"\"READ\", o valor de MAR, que contém o endereço\n da próxima instrução, " +
			"também é transferido para o barramento";
	private static final String MOVER_DADOS_BARRAMENTO_MEMORIATXT = "Esses dois valores " +
			"serão transferidos para a memória, pelo barramento.\n O valor de MAR " +
			"no barramento de endereços e o comando \"READ\"\n no barramento de controle";
	
	public TelaMensagemCicloBusca(EstadoCicloBusca estado) {
		super("Mensagem", Color.rgb(245, 245, 245));
		
		this.estado = estado;
		
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
