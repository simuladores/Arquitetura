package br.unipe.simuladores.arquitetura.telas;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TelaErro extends Tela{
	
	private String mensagem;

	public TelaErro(String titulo, Color cor, String mensagem) {
		super(titulo, Color.rgb(245, 245, 245));
		this.mensagem = mensagem;
		criar();
	}
	
	@Override
	public void criar() {
		
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(5, 5, 5, 5));
		vBox.setSpacing(20);
		Text txtErro = new Text(mensagem);
		vBox.getChildren().add(txtErro);
		
		HBox hBox = new HBox();
		hBox.setAlignment(Pos.CENTER);
		Button btn = new Button("OK");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				stage.close();
				
			}
			
		});
		hBox.getChildren().add(btn);
		
		vBox.getChildren().add(hBox);
		
		root.getChildren().add(vBox);
		
	}

}
