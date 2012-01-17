package br.unipe.simuladores.arquitetura.telas;

import br.unipe.simuladores.arquitetura.componentes.circulos.Computador;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class TelaPrincipal extends Tela{

	private static Group group;
	private static final Computador computador = new Computador();
	private static MenuBar menuSuperior;
	private boolean started = false;
	//private static Accordion accordion;
	private static TitledPane mensagem;
	private static TitledPane variaveis;
	
	private static final Text defaultContentMensagem = new Text("Não há mensagens");
	private static final Text defaultContentVariaveis = new Text("Não há variáveis");
	
	
	public TelaPrincipal(Stage stage, String titulo, Color cor, double height, double width) {
		super(stage, titulo, cor, height, width);
		criar();
	}
	
	
	@Override
	public void criar() {
		
		//group = new Group();
        
		//group.getChildren().add(computador.getContent());
		
		/*final Text textClique = new Text("Clique para expandir");
		textClique.setX(550);
		textClique.setY(300);*/
				
		root.getChildren().add(computador.getContent());
		
		//root.getChildren().add(textClique);
		
		menuSuperior = criarMenu(scene);
		root.getChildren().add(menuSuperior);
		
		/*accordion = criarAccordion();
		root.getChildren().add(accordion);*/
		
		mensagem = criarTitledPaneMensagem();
		root.getChildren().add(mensagem);
		
		variaveis = criarTitledPaneVariaveis();
		root.getChildren().add(variaveis);
		
		/*group.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				
				//expande o computador
				if (!started) {
					textClique.setVisible(false);
					computador.expandir(0.2, 1, 3000);
					started = true;
				}
				
			}
			
		});*/
		
	}
	
	private MenuBar criarMenu(Scene cena) {
		
		MenuBar menuBar = new MenuBar();
		
		Menu inserir = new Menu("Inserir");
		MenuItem instrucoes = new MenuItem("Instruções");
		instrucoes.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	TelaInserirInstrucoes inserirInstrucoes = 
		    			new TelaInserirInstrucoes("Inserir Instruções", 
		    					Color.rgb(245, 245, 245), 450, 760);
		    	inserirInstrucoes.exibir();
		    }
		});
		instrucoes.setDisable(true);
		inserir.getItems().add(instrucoes);
		MenuItem variavel = new MenuItem("Variável");
		variavel.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	TelaInserirVariavel inserirVariavel = 
		    			new TelaInserirVariavel("Inserir Variável", 
		    					Color.rgb(245, 245, 245));
		    	inserirVariavel.exibir();
		    }
		});
		variavel.setDisable(true);
		inserir.getItems().add(variavel);
		
		Menu outros = new Menu("Outros");
		MenuItem sied = new MenuItem("SIED - Simuladores Integrados de Estrutura de Dados");
		sied.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	TelaWebView telaSied = new TelaWebView("Outros Simuladores", Color.WHITE, 
		    			Screen.getPrimary().getVisualBounds().getHeight(), 
		    			Screen.getPrimary().getVisualBounds().getWidth(), 
		    			"http://www.hilariotomaz.com.br/SI-ED/");
		        telaSied.exibir();
		    }
		});
		outros.getItems().add(sied);
		
		MenuItem sin = new MenuItem("SIN - Simuladores Integrados de Sistemas Operacionais");
		sin.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	TelaWebView telaSin = new TelaWebView("Outros Simuladores", Color.WHITE, 
		    			Screen.getPrimary().getVisualBounds().getHeight(), 
		    			Screen.getPrimary().getVisualBounds().getWidth(), 
		    			"http://wrco.ccsa.ufpb.br:8080/SimuladorSO/");
		    	telaSin.exibir();
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
	
	private TitledPane criarTitledPaneMensagem() {
		
		TitledPane mensagem = new TitledPane();
		mensagem.setText("Mensagem");
		mensagem.setContent(defaultContentMensagem);
		mensagem.setTranslateY(500);
		mensagem.setTranslateX(90);
		
		return mensagem;
		
	}
	
	private TitledPane criarTitledPaneVariaveis() {
		
		TitledPane variaveis = new TitledPane();
		variaveis.setText("Variáveis");
		variaveis.setContent(defaultContentVariaveis);
		variaveis.setTranslateY(500);
		
		return variaveis;
		
	}
	
	public static void colocarTextoPadraoMensagem() {
		
		mensagem.setContent(defaultContentMensagem);
		
	}
	
	public static void colocarTextoPadraoVariaveis() {
		
		variaveis.setContent(defaultContentVariaveis);
		
	}

	public static void adicionarAoPalco(Node node) {
		
		root.getChildren().add(node);
		
	}
	
	public static void removerDoPalco(Node node) {
		
		root.getChildren().remove(node);
		
	}
	
	public static Computador getComputador() {
		return computador;
	}
	
	public static MenuBar getMenuSuperior() {
		return menuSuperior;
	}

	/*public static Accordion getAccordion() {
		return accordion;
	}


	public static void setAccordion(Accordion accordion) {
		TelaPrincipal.accordion = accordion;
	}*/


	public static TitledPane getMensagem() {
		return mensagem;
	}


	public static void setMensagem(TitledPane mensagem) {
		TelaPrincipal.mensagem = mensagem;
	}


	public static TitledPane getVariaveis() {
		return variaveis;
	}


	public static void setVariaveis(TitledPane variaveis) {
		TelaPrincipal.variaveis = variaveis;
	}

}
