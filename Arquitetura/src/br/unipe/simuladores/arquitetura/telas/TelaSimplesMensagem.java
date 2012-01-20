package br.unipe.simuladores.arquitetura.telas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public abstract class TelaSimplesMensagem extends Tela{

	private String mensagem;
	protected VBox vBox;
	protected Text txtMensagem;
	protected HBox hBox;
	
	public TelaSimplesMensagem(String titulo, Color cor, String mensagem) {
		
		super(titulo, Color.rgb(245, 245, 245));
		this.mensagem = mensagem;
		criar();
		
	}
	
	@Override
	public abstract void criar();
	
	protected void adicionarComponentesComuns() {
		
		vBox = new VBox();
		vBox.setPadding(new Insets(5, 5, 5, 5));
		vBox.setSpacing(20);
		txtMensagem = new Text(mensagem);
		vBox.getChildren().add(txtMensagem);
		
		hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		
		vBox.getChildren().add(hBox);
		
		root.getChildren().add(vBox);
		
	}

}
