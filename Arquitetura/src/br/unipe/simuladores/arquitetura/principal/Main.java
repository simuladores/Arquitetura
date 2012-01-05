package br.unipe.simuladores.arquitetura.principal;

import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main (String args[]) {

		Application.launch(args);
		
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		 //Ajusta a cena para ocupar toda a tela do monitor
        Rectangle2D tela = Screen.getPrimary().getVisualBounds();
		
		TelaPrincipal telaPrincipal = new TelaPrincipal(stage, 
				"SOAC - Simulador de Organização e Arquitetura de Computadores", 
				Color.WHITE, tela.getHeight(), tela.getWidth());
		telaPrincipal.exibir();
		
	}
	

}