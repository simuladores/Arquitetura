package br.unipe.simuladores.arquitetura.telas;

import br.unipe.simuladores.arquitetura.simulacao.Ciclo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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
