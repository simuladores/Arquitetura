package br.unipe.simuladores.arquitetura.telas;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public abstract class Tela {
	
	protected Stage stage;
	protected static Group root;
	protected Scene scene;
	protected Color cor;
	
	public Tela() {
		stage = new Stage();
	}
	
	public Tela(String titulo, Color cor, double height, double width) {
		stage = new Stage();
		iniciarCriacaoTela(titulo, cor);
		stage.setHeight(height);
		stage.setWidth(width);
	}
	
	public Tela(Stage stage, String titulo, Color cor, double height, double width) {
		this.stage = stage;
		iniciarCriacaoTela(titulo, cor);
		stage.setHeight(height);
		stage.setWidth(width);
	}
	
	public Tela(Stage stage, String titulo, Color cor) {
		this.stage = stage;
		iniciarCriacaoTela(titulo, cor);
	}
	
	public Tela(String titulo, Color cor) {
		stage = new Stage();
		iniciarCriacaoTela(titulo, cor);
	}
	
		
	public void iniciarCriacaoTela(String titulo, Color cor) {
		stage.setTitle(titulo);
		root = new Group();
		scene = new Scene(root, cor);
		stage.setScene(scene);
	}
	
	protected void fechar() {
		
		stage.close();
		System.gc();
		
	}

	public abstract void criar() ;
	
	public void exibir() {
		
		stage.show();
		
	}
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
