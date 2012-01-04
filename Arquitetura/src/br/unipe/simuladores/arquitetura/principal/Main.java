package br.unipe.simuladores.arquitetura.principal;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.unipe.simuladores.arquitetura.componentes.circulos.CaixaFormulario;
import br.unipe.simuladores.arquitetura.componentes.circulos.Computador;
import br.unipe.simuladores.arquitetura.componentes.internos.Variavel;
import br.unipe.simuladores.arquitetura.enums.TipoVariavel;
import br.unipe.simuladores.arquitetura.excecoes.DadosInvalidosException;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class Main extends Application {
	
	private static Group group;
	private final Computador computador = new Computador();
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
        
        stage.setTitle("SOAC - Simulador de Organiza��o e Arquitetura de Computadores");
        stage.setScene(scene);
        
		group = new Group();
        
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
		MenuItem instrucoes = new MenuItem("Instru��es");
		instrucoes.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        Stage formInserirInst = criarFormularioInserirInstrucao();
		        formInserirInst.show();
		    }
		});
		inserir.getItems().add(instrucoes);
		MenuItem variavel = new MenuItem("Vari�vel");
		variavel.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        Stage formInserirVar = criarFormularioInserirVariavel();
		        formInserirVar.show();
		    }
		});
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
		stage.setTitle("Outros Simuladores");
		Group root = new Group();
		Scene scene = new Scene(root, Color.WHITE);
		
		WebView view = new WebView();
		view.setTranslateX(550);
		final WebEngine eng = view.getEngine();
		eng.load(url);
		final TextField locationField = new TextField(url);
		locationField.setMaxHeight(Double.MAX_VALUE);
		locationField.setMinWidth(500);
		locationField.setTranslateX(200);
		Button goButton = new Button("Ir");
		goButton.setTranslateX(400);
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
	
	private Stage criarFormularioInserirInstrucao() {
		
		Stage stage = new Stage();
		stage.setTitle("Inserir Instru��es");
		Group root = new Group();
		Scene scene = new Scene(root, Color.rgb(245, 245, 245));
		
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10, 10, 10, 10));
		vBox.setSpacing(10);
		
		HBox hBox1 = new HBox();
		hBox1.setSpacing(10);
		hBox1.setAlignment(Pos.CENTER);
		Text tipo = new Text("Tipo:");
		hBox1.getChildren().add(tipo);
		ChoiceBox<String> cbTipo = new ChoiceBox<String>();
		cbTipo.getItems().addAll("MOV", "ADD", "SUB", "MUL", "DIV");
		cbTipo.getSelectionModel().selectFirst();
		hBox1.getChildren().add(cbTipo);
		vBox.getChildren().add(hBox1);
		
		HBox hBox2 = new HBox();
		hBox2.setSpacing(50);
		Text operando1 = new Text("Operando 1");
		operando1.setTranslateX(120);
		operando1.setTranslateY(5);
		operando1.setFill(Color.BLUE);
		hBox2.getChildren().add(operando1);
		Text operando2 = new Text("Operando 2");
		operando2.setTranslateX(375);
		operando2.setTranslateY(5);
		operando2.setFill(Color.BLUE);
		hBox2.getChildren().add(operando2);
		vBox.getChildren().add(hBox2);
		
		HBox hBox3 = new HBox();
		hBox3.setSpacing(60);
		hBox3.setPadding(new Insets(0, 0, 0, 20));
		
		GridPane grid1 = new GridPane();
		grid1.setTranslateY(10);
		grid1.setHgap(10);
		grid1.setVgap(10);
		
		Text modEnd1 = new Text("Modo de endere�amento:");
		grid1.add(modEnd1, 0, 0);
		ChoiceBox<String> cbModEnd1 = new ChoiceBox<String>();
		cbModEnd1.getItems().addAll("Imediato", "Direto", "Indireto", 
				"Registrador", "Indireto de Registrador");
		cbModEnd1.getSelectionModel().selectFirst();
		grid1.add(cbModEnd1, 1, 0);
		Text valor1 = new Text("Valor:");
		grid1.add(valor1, 0, 1);
		TextField txtValor1 = new TextField();
		txtValor1.setMaxWidth(50);
		grid1.add(txtValor1, 1, 1);
		hBox3.getChildren().add(grid1);
		
		GridPane grid2 = new GridPane();
		grid2.setTranslateY(10);
		grid2.setHgap(10);
		grid2.setVgap(10);
		
		Text modEnd2 = new Text("Modo de endere�amento:");
		grid2.add(modEnd2, 0, 0);
		ChoiceBox<String> cbModEnd2 = new ChoiceBox<String>();
		cbModEnd2.getItems().addAll("Imediato", "Direto", "Indireto", 
				"Registrador", "Indireto de Registrador");
		cbModEnd2.getSelectionModel().selectFirst();
		grid2.add(cbModEnd2, 1, 0);
		Text valor2 = new Text("Valor:");
		grid2.add(valor2, 0, 1);
		TextField txtValor2 = new TextField();
		txtValor2.setMaxWidth(50);
		grid2.add(txtValor2, 1, 1);
		hBox3.getChildren().add(grid2);
		
		vBox.getChildren().add(hBox3);
		
		HBox hBox4 = new HBox();
		hBox4.setAlignment(Pos.CENTER);
		Button btnInserir = new Button("Inserir>>");
		btnInserir.setTranslateY(50);
		hBox4.getChildren().add(btnInserir);
		vBox.getChildren().add(hBox4);
		
		HBox hBox5 = new HBox();
		hBox5.setAlignment(Pos.CENTER);
		hBox5.setSpacing(80);
		ListView<String> lstInstrucoes = new ListView<String>();
		lstInstrucoes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lstInstrucoes.setMaxWidth(200);
		lstInstrucoes.setMaxHeight(100);
		lstInstrucoes.setEditable(false);
		lstInstrucoes.setTranslateY(70);
		hBox5.getChildren().add(lstInstrucoes);
		VBox vBox2 = new VBox();
		vBox2.setAlignment(Pos.CENTER);
		Button btnRemover = new Button("Remover Selecionada");
		btnRemover.setTranslateY(70);
		vBox2.getChildren().add(btnRemover);
		hBox5.getChildren().add(vBox2);
		vBox.getChildren().add(hBox5);
		
		HBox hBox6 = new HBox();
		hBox6.setAlignment(Pos.CENTER);
		Button btnIniciar = new Button("Iniciar Simula��o");
		btnIniciar.setTranslateY(90);
	    hBox6.getChildren().add(btnIniciar);
	    vBox.getChildren().add(hBox6);
		
		root.getChildren().add(vBox);
		
		inserirCaixasFormulario(root);
		
		stage.setHeight(450);
		stage.setWidth(760);
		stage.setScene(scene);
		
		return stage;
		
	}
	
	private void inserirCaixasFormulario(Group r) {
		
		double pontos[][] = new double[6][2];
		pontos[0][0] = 15;
		pontos[0][1] = 55;
		pontos[1][0] = 120;
		pontos[1][1] = 55;
		pontos[2][0] = 200;
		pontos[2][1] = 55;
		pontos[3][0] = 355;
		pontos[3][1] = 55;
		pontos[4][0] = 355;
		pontos[4][1] = 150;
		pontos[5][0] = 15;
		pontos[5][1] = 150;
		
		CaixaFormulario caixa1 = new CaixaFormulario(pontos, Color.GREY);
		CaixaFormulario caixa2 = new CaixaFormulario(pontos, Color.GREY);
		caixa2.setTranslateX(370);
		
		r.getChildren().add(caixa1);
		r.getChildren().add(caixa2);
		
	}
	
	private Stage criarFormularioInserirVariavel() {
		
		final Stage stage = new Stage();
		stage.setTitle("Inserir Vari�vel");
		Group root = new Group();
		Scene scene = new Scene(root, Color.rgb(245, 245, 245));
		
		VBox vBox = new VBox();
		vBox.setSpacing(20);
		vBox.setPadding(new Insets(10, 10, 10, 10));
		
		HBox hBox1 = new HBox();
		hBox1.setSpacing(10);
	    Text txtIdentificador = new Text("Indentificador [A-Z]:");
		hBox1.getChildren().add(txtIdentificador);
		final TextField tfIdentificador = new TextField();
		tfIdentificador.setMaxWidth(50);
		hBox1.getChildren().add(tfIdentificador);
		Text txtTipo = new Text("Tipo:");
		hBox1.getChildren().add(txtTipo);
		final ChoiceBox<String> cbTipo = new ChoiceBox<String>();
		cbTipo.getItems().addAll("Inteiro", "String", "Ponto flutuante");
		cbTipo.getSelectionModel().selectFirst();
		hBox1.getChildren().add(cbTipo);
		vBox.getChildren().add(hBox1);
		
		HBox hBox2 = new HBox();
		hBox2.setSpacing(10);
		VBox vBox2 = new VBox();
		vBox2.setAlignment(Pos.CENTER);
		
		HBox hBox3 = new HBox();
		hBox3.setSpacing(10);
		final Text txtValor = new Text("Valor Inicial:");
		hBox3.getChildren().add(txtValor);
		final TextField tfValor = new TextField();
		tfValor.setMaxWidth(50);
		hBox3.getChildren().add(tfValor);
		vBox2.getChildren().add(hBox3);
		
		hBox2.getChildren().add(vBox2);
		
		VBox vBox3 = new VBox();
		vBox3.setSpacing(10);
		
		final ToggleGroup tg = new ToggleGroup();
		final RadioButton rbNormal = new RadioButton("Normal");
		rbNormal.setToggleGroup(tg);
		rbNormal.setSelected(true);
		rbNormal.setTranslateX(70);
		vBox3.getChildren().add(rbNormal);
		RadioButton rbPonteiro = new RadioButton("Ponteiro");
		rbPonteiro.setToggleGroup(tg);
		rbPonteiro.setSelected(false);
		rbPonteiro.setTranslateX(70);
		vBox3.getChildren().add(rbPonteiro);
		
		hBox2.getChildren().add(vBox3);
		
		vBox.getChildren().add(hBox2);
		
		HBox hBox4 = new HBox();
		hBox4.setAlignment(Pos.CENTER);
		Button btnInserirVar = new Button("Inserir");
		btnInserirVar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
		
				String identificador = tfIdentificador.getText();
				Integer tipoVariavel = cbTipo.getSelectionModel().
						selectedIndexProperty().intValue();
				String valor = tfValor.getText();
				Boolean normal;
				if (tg.getSelectedToggle() == rbNormal)
					normal = true;
				else 
					normal = false;
				
				try {
					inserirVariavelMemoria(identificador, tipoVariavel, valor, normal);
					stage.close();
				} catch (DadosInvalidosException die) {
					exibirJanelaMensagemErro(die.getMessage());
				}
				
			}
			
		});
		hBox4.getChildren().add(btnInserirVar);
		
		vBox.getChildren().add(hBox4);
		
		root.getChildren().add(vBox);
		
		stage.setScene(scene);
		
		return stage;
		
	}
	
	public void inserirVariavelMemoria(String id, Integer tipo, String val, Boolean normal) throws DadosInvalidosException{
		
		TipoVariavel tpVariavel = null;
		
		switch(tipo) {
		case 0: tpVariavel = TipoVariavel.INTEIRO; break;
		case 1: tpVariavel = TipoVariavel.STRING; break;
		case 2: tpVariavel = TipoVariavel.PONTO_FLUTUANTE; break;
		}
		
		validarDadosInserirVariavel(id, val, tpVariavel);
		
		Variavel variavel = new Variavel(val, tpVariavel, normal);
		computador.getMemoriaPrincipal().getMemoriaInterna().
			inserirDado(variavel, id);
		
	}
	
	private void validarDadosInserirVariavel(String id, String valor, TipoVariavel tp) throws DadosInvalidosException{
		
		if (id.isEmpty()) 
			throw new DadosInvalidosException("Por favor, informe um identificador");
		
		if (valor.isEmpty()) 
			throw new DadosInvalidosException("Por favor, informe um valor");
		
		Pattern padraoId = Pattern.compile("([a-zA-Z])\\w*");
		Matcher pesquisa = padraoId.matcher(id);
		
		if(!pesquisa.matches())
			throw new DadosInvalidosException(id+" n�o � um identificador v�lido");
		
		if (tp == TipoVariavel.INTEIRO) {
			
			try {
				Integer.parseInt(valor);
			}catch(NumberFormatException nfe) {
				throw new DadosInvalidosException("O valor informado n�o � um n�mero inteiro");
			}
			
			
		} else if (tp == TipoVariavel.PONTO_FLUTUANTE) {
			
			try {
				Float.parseFloat(valor);
			}catch(NumberFormatException nfe) {
				throw new DadosInvalidosException("O valor informado n�o � um n�mero de ponto flutuante");
			}
			
		}
		
		
	}
	
	private void exibirJanelaMensagemErro (String errMsg) {
		
		final Stage stage = new Stage();
		stage.setTitle("Erro");
		Group root = new Group();
		Scene scene = new Scene(root, Color.rgb(245, 245, 245));
		
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(5, 5, 5, 5));
		vBox.setSpacing(20);
		Text txtErro = new Text(errMsg);
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
		
		stage.setScene(scene);
		stage.show();
		
	}
	
	

}
