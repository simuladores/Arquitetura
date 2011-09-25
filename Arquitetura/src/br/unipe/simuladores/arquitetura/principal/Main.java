package br.unipe.simuladores.arquitetura.principal;

import br.unipe.simuladores.arquitetura.componentes.Computador;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Group group;
	private boolean started = false;

	public static void main (String args[]) {
		
		Application.launch(args);
		
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		//Cria a cena
		Group root = new Group();
        Scene scene = new Scene(root, Color.WHITE);
        
        //Ajusta a cena para ocupar toda a tela do monitor
        Rectangle2D tela = Screen.getPrimary().getVisualBounds();
        stage.setWidth(tela.getWidth());
        stage.setHeight(tela.getHeight());
        
        stage.setTitle("SOAC - Simulador de Organização e Arquitetura de Computadores");
        stage.setScene(scene);
        
		group = new Group();
				
		final Computador computador = new Computador();
        
		group.getChildren().add(computador.getContent());
		
	    /*Tooltip tooltip = new Tooltip();
		tooltip.setText("Clique para expandir");
		Tooltip.install(group, tooltip);
		tooltip.show(stage);*/
		
		final Text textClique = new Text("Clique para expandir");
		textClique.setX(550);
		textClique.setY(300);
				
		root.getChildren().add(group);
		
		root.getChildren().add(textClique);
		
        stage.setVisible(true);
        
        
        group.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				
				//expande o computador
				if (!started) {
					textClique.setVisible(false);
					computador.expandir(0.2, 1, 3000);
					started = true;
				}
				
			}
			
		});
		
		
	}
	
	public static void adicionarAoPalco(Node node) {
		
		group.getChildren().add(node);
		
	}
	
	public static void removerDoPalco(Node node) {
		
		group.getChildren().remove(node);
		
	}
	
	

}
