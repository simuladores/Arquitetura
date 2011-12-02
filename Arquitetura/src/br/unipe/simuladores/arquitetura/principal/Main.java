package br.unipe.simuladores.arquitetura.principal;

import br.unipe.simuladores.arquitetura.componentes.circulos.Computador;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Group group;
	private boolean started = false;
	
	private MenuBar menu;

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
		
		menu = criarMenu(scene);
		root.getChildren().add(menu);
		
        stage.show();
        
        
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
	
	private MenuBar criarMenu(Scene cena) {
		
		MenuBar menuBar = new MenuBar();
		
		Menu inserir = new Menu("Inserir");
		MenuItem instrucoes = new MenuItem("Instruções");
		inserir.getItems().add(instrucoes);
		MenuItem variavel = new MenuItem("Variável");
		inserir.getItems().add(variavel);
		
		Menu outros = new Menu("Outros");
		MenuItem sied = new MenuItem("SIED - Simuladores Integrados de Estrutura de Dados");
		sied.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        Stage siedPage = criarWebView("http://www.hilariotomaz.com.br/SI-ED/");
		        siedPage.show();
		    }
		});
		outros.getItems().add(sied);
		
		MenuItem sin = new MenuItem("SIN - Simuladores Integrados de Sistemas Operacionais");
		sin.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        Stage sinPage = criarWebView("http://wrco.ccsa.ufpb.br:8080/SimuladorSO/");
		        sinPage.show();
		    }
		});
		outros.getItems().add(sin);
		
		Menu ajuda = new Menu("Ajuda");
		MenuItem sobre = new MenuItem("Sobre");
		ajuda.getItems().add(sobre);
		
		menuBar.getMenus().add(inserir);
		menuBar.getMenus().add(outros);
		menuBar.getMenus().add(ajuda);
		
		menuBar.setMinWidth(Screen.getPrimary().getVisualBounds().getWidth());
		
		return menuBar;
		
		
		
	}
	
	private Stage criarWebView(String url) {
		
		Stage stage = new Stage();
		Group root = new Group();
		Scene scene = new Scene(root, Color.WHITE);
		
		WebView view = new WebView();
		//view.setMinSize(1000, 400);
	    //view.setPrefSize(1000, 400);
		view.setTranslateX(550);
		final WebEngine eng = view.getEngine();
		eng.load(url);
		final TextField locationField = new TextField(url);
		locationField.setMaxHeight(Double.MAX_VALUE);
		Button goButton = new Button("Ir");
	    goButton.setDefaultButton(true);
		EventHandler<ActionEvent> goAction = new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				eng.load(locationField.getText().startsWith("http://") ? locationField.getText() :
						"http://" + locationField.getText());
			}
		};
		goButton.setOnAction(goAction);
		locationField.setOnAction(goAction);
		eng.locationProperty().addListener(new ChangeListener<String>() {
			@Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				locationField.setText(newValue);
			}
		});
		GridPane grid = new GridPane();
		grid.setVgap(5);
		grid.setHgap(5);
		GridPane.setConstraints(locationField, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.SOMETIMES);
		GridPane.setConstraints(goButton,1,0);
		GridPane.setConstraints(view, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS);
		grid.getColumnConstraints().addAll(
				new ColumnConstraints(100, 100, Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, true),
				new ColumnConstraints(40, 40, 40, Priority.NEVER, HPos.CENTER, true)
		);
		grid.getChildren().addAll(locationField, goButton, view);
		root.getChildren().add(grid);
		
		stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
		stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
		
		view.setMinWidth(stage.getWidth());
		view.setMinHeight(stage.getHeight());
		
		stage.setScene(scene);
		
		return stage;
		
	}
	
	

}
