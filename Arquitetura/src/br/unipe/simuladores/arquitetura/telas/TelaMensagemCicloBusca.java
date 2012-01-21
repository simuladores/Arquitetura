package br.unipe.simuladores.arquitetura.telas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import br.unipe.simuladores.arquitetura.enums.EstadoCicloBusca;
import br.unipe.simuladores.arquitetura.simulacao.Busca;

public class TelaMensagemCicloBusca extends TelaMensagemSimulacao{

	private EstadoCicloBusca estado;
	private Busca busca;
	
	private static final String ATUALIZAR_PCTXT = "Ser� dado in�cio ao ciclo de busca, " +
			"para buscar a pr�xima instru��o na mem�ria.\n Primeiro, o valor de PC � " +
			"atualizado com o endere�o da instru��o que ser�\n executada em seguida";
	private static final String MOVER_MARTXT = "O valor de PC � ent�o transferido para" +
			" MAR, que cont�m os endere�os\n que s�o tranferidos do barramento para a " +
			"mem�ria e que chegam\n da mem�ria atrav�s do barramento" ;
	private static final String COPIAR_READ_BARRAMENTOTXT = "A unidade de controle � " +
			"respons�vel por coordenar as atividades da UCP.\n Neste caso, ela envia o " +
			"comando \"READ\" para o barramento, significando\n que a UCP est� requisi" +
			"tando uma leitura de dados da mem�ria";
	private static final String COPIAR_VALOR_MAR_BARRAMENTOTXT = "Assim como o comando " +
			"\"READ\", o valor de MAR, que cont�m o endere�o\n da pr�xima instru��o, " +
			"tamb�m � transferido para o barramento";
	private static final String MOVER_DADOS_BARRAMENTO_MEMORIATXT = "Esses dois valores " +
			"ser�o transferidos para a mem�ria, pelo barramento.\n O valor de MAR " +
			"no barramento de endere�os e o comando \"READ\"\n no barramento de controle";
	
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
