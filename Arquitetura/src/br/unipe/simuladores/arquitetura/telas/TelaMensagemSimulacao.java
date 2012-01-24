package br.unipe.simuladores.arquitetura.telas;

import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public abstract class TelaMensagemSimulacao extends TelaSimplesMensagem{

	protected Button continuar;
	protected Button cancelar;
	protected EstadoCiclo estado;
	
	public TelaMensagemSimulacao(String titulo, Color cor) {
		
		super(titulo, cor, "");
		
	}

	@Override
	public void criar() {
		
		adicionarComponentesComuns();
		
		HBox hBox2 = new HBox();
		hBox2.setAlignment(Pos.CENTER);
		hBox2.getChildren().add(txtMensagem);
		vBox.getChildren().add(hBox2);
		
		hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		hBox.setSpacing(10);
		vBox.getChildren().add(hBox);
		
		root.getChildren().add(vBox);
		
		continuar = new Button("Continuar");
	
		hBox.getChildren().add(continuar);
		
		cancelar = new Button("Não Mostrar Mensagens");
		
		hBox.getChildren().add(cancelar);
		
		definirAcoesBotoes();
		
	}
	
	protected abstract String obterTexto();
	
	protected void modificarMensagem(String msg) {
		
		txtMensagem.setText(msg);
		
	}
	
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

}
