package br.unipe.simuladores.arquitetura.telas;

import br.unipe.simuladores.arquitetura.componentes.circulos.Computador;
import br.unipe.simuladores.arquitetura.enums.OpcaoJanelaMensagem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class TelaPrincipal extends Tela{

	private static final Computador computador = new Computador();
	private static MenuBar menuSuperior;
	private static TitledPane mensagem;
	private static TitledPane variaveis;
	
	private static final Text defaultContentMensagem = new Text("Não há mensagens");
	private static final Text defaultContentVariaveis = new Text("Não há variáveis");
	private static OpcaoJanelaMensagem opcaoJanelaMensagem;
	private static boolean exibirMensagensDeSimulacao = true;
	
	public TelaPrincipal(Stage stage, String titulo, Color cor, double height, double width) {
		super(stage, titulo, cor, height, width);
		criar();
	}
	
	
	@Override
	public void criar() {
						
		root.getChildren().add(computador.getContent());
		
		menuSuperior = criarMenu(scene);
		root.getChildren().add(menuSuperior);
		
		mensagem = criarTitledPaneMensagem();
		root.getChildren().add(mensagem);
		
		variaveis = criarTitledPaneVariaveis();
		root.getChildren().add(variaveis);
		
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
		
		ToggleGroup tgGroupMensagem = new ToggleGroup();
		Menu janela = new Menu("Janela");
		Menu mensagens = new Menu("Mensagens");
		RadioMenuItem exibir = new RadioMenuItem("Exibir");
		exibir.setToggleGroup(tgGroupMensagem);
		mensagens.getItems().add(exibir);
		
		exibir.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				setOpcaoJanelaMensagem(OpcaoJanelaMensagem.EXIBIR);
				mensagem.setVisible(true);
				
			}
			
		});
		
		RadioMenuItem esconder = new RadioMenuItem("Esconder");
		esconder.setToggleGroup(tgGroupMensagem);
		mensagens.getItems().add(esconder);
		
		esconder.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				setOpcaoJanelaMensagem(OpcaoJanelaMensagem.ESCONDER);
				mensagem.setVisible(false);
				
			}
			
		});
		
		RadioMenuItem naoExibir = new RadioMenuItem("Não Exibir");
		naoExibir.setToggleGroup(tgGroupMensagem);
		mensagens.getItems().add(naoExibir);
		
		naoExibir.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				setOpcaoJanelaMensagem(OpcaoJanelaMensagem.NAO_EXIBIR);
				mensagem.setVisible(false);
				
			}
			
		});
		
		tgGroupMensagem.selectToggle(esconder);
		
		final RadioMenuItem opExibirMensSimulacao = new RadioMenuItem("Exibir Mensagens da Simulacão");
		opExibirMensSimulacao.setSelected(true);
		
		exibirMensagensDeSimulacao = true;
		
		opExibirMensSimulacao.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				exibirMensagensDeSimulacao = opExibirMensSimulacao.isSelected();
				
			}
			
		});
		
		mensagens.getItems().addAll(new SeparatorMenuItem(), opExibirMensSimulacao);
		
			
		janela.getItems().add(mensagens);
		
		ToggleGroup tgGroupVariaveis = new ToggleGroup();
	    Menu var = new Menu("Variáveis");
		RadioMenuItem exibirVar = new RadioMenuItem("Exibir");
		exibirVar.setToggleGroup(tgGroupVariaveis);
		var.getItems().add(exibirVar);
		
		exibirVar.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				variaveis.setVisible(true);
				
			}
			
		});
		
		RadioMenuItem naoExibirVar = new RadioMenuItem("Não Exibir");
		naoExibirVar.setToggleGroup(tgGroupVariaveis);
		var.getItems().add(naoExibirVar);
		
		naoExibirVar.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				variaveis.setVisible(false);
				
			}
			
		});
		
		tgGroupVariaveis.selectToggle(exibirVar);
		
		janela.getItems().add(var);
		
		Menu ajuda = new Menu("Ajuda");
		MenuItem sobre = new MenuItem("Sobre");
		ajuda.getItems().add(sobre);
		
		menuBar.getMenus().add(inserir);
		menuBar.getMenus().add(janela);
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
		mensagem.setVisible(false);
		setOpcaoJanelaMensagem(OpcaoJanelaMensagem.ESCONDER);
		
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


	public static OpcaoJanelaMensagem getOpcaoJanelaMensagem() {
		return opcaoJanelaMensagem;
	}


	public static void setOpcaoJanelaMensagem(
			OpcaoJanelaMensagem opcaoJanelaMensagem) {
		TelaPrincipal.opcaoJanelaMensagem = opcaoJanelaMensagem;
	}


	public static boolean isExibirMensagensDeSimulacao() {
		return exibirMensagensDeSimulacao;
	}


	public static void setExibirMensagensDeSimulacao(
			boolean exibirMensagensDeSimulacao) {
		TelaPrincipal.exibirMensagensDeSimulacao = exibirMensagensDeSimulacao;
	}
	

}
