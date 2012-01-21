package br.unipe.simuladores.arquitetura.telas;

import br.unipe.simuladores.arquitetura.simulacao.Ciclo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public abstract class TelaMensagemSimulacao extends TelaSimplesMensagem{

	protected Button continuar;
	protected Button cancelar;
	
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
	
	protected void modificarMensagem(String msg) {
		
		txtMensagem.setText(msg);
		
	}
	
	public abstract void definirAcoesBotoes();

}
