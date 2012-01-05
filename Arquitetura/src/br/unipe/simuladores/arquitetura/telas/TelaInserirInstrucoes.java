package br.unipe.simuladores.arquitetura.telas;

import br.unipe.simuladores.arquitetura.componentes.circulos.CaixaFormulario;
import br.unipe.simuladores.arquitetura.enums.ModoEnderecamento;
import br.unipe.simuladores.arquitetura.excecoes.DadosInvalidosException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TelaInserirInstrucoes extends Tela implements Formulario{

	private ChoiceBox<String> cbTipo = new ChoiceBox<String>();
	private ChoiceBox<String> cbModEnd1 = new ChoiceBox<String>();
	private TextField tfValor1 = new TextField();
	private ChoiceBox<String> cbModEnd2 = new ChoiceBox<String>();
	private TextField tfValor2 = new TextField();
	private Button btnInserir = new Button("Inserir>>");
	private ListView<String> lstInstrucoes = new ListView<String>();
	private Button btnRemover = new Button("Remover Selecionada");
	private Button btnIniciar = new Button("Iniciar Simulação");
	private ModoEnderecamento modo1;
	private ModoEnderecamento modo2;
	
	public TelaInserirInstrucoes(String titulo, Color cor, double height, double width) {
		super(titulo, cor, height, width);
		criar();
	}
	
	@Override
	public void criar() {
		
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10, 10, 10, 10));
		vBox.setSpacing(10);
		
		HBox hBox1 = new HBox();
		hBox1.setSpacing(10);
		hBox1.setAlignment(Pos.CENTER);
		Text tipo = new Text("Tipo:");
		hBox1.getChildren().add(tipo);
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
		
		Text modEnd1 = new Text("Modo de endereçamento:");
		grid1.add(modEnd1, 0, 0);
		cbModEnd1.getItems().addAll("Imediato", "Direto", "Indireto", 
				"Registrador", "Indireto de Registrador");
		cbModEnd1.getSelectionModel().selectFirst();
		grid1.add(cbModEnd1, 1, 0);
		Text valor1 = new Text("Valor:");
		grid1.add(valor1, 0, 1);
		tfValor1.setMaxWidth(50);
		grid1.add(tfValor1, 1, 1);
		hBox3.getChildren().add(grid1);
		
		GridPane grid2 = new GridPane();
		grid2.setTranslateY(10);
		grid2.setHgap(10);
		grid2.setVgap(10);
		
		Text modEnd2 = new Text("Modo de endereçamento:");
		grid2.add(modEnd2, 0, 0);
		cbModEnd2.getItems().addAll("Imediato", "Direto", "Indireto", 
				"Registrador", "Indireto de Registrador");
		cbModEnd2.getSelectionModel().selectFirst();
		grid2.add(cbModEnd2, 1, 0);
		Text valor2 = new Text("Valor:");
		grid2.add(valor2, 0, 1);
		tfValor2.setMaxWidth(50);
		grid2.add(tfValor2, 1, 1);
		hBox3.getChildren().add(grid2);
		
		vBox.getChildren().add(hBox3);
		
		HBox hBox4 = new HBox();
		hBox4.setAlignment(Pos.CENTER);
		btnInserir.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				
				
			}
			
		});
		btnInserir.setTranslateY(50);
		hBox4.getChildren().add(btnInserir);
		vBox.getChildren().add(hBox4);
		
		HBox hBox5 = new HBox();
		hBox5.setAlignment(Pos.CENTER);
		hBox5.setSpacing(80);
		lstInstrucoes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		lstInstrucoes.setMaxWidth(200);
		lstInstrucoes.setMaxHeight(100);
		lstInstrucoes.setEditable(false);
		lstInstrucoes.setTranslateY(70);
		hBox5.getChildren().add(lstInstrucoes);
		VBox vBox2 = new VBox();
		vBox2.setAlignment(Pos.CENTER);
		btnRemover.setTranslateY(70);
		vBox2.getChildren().add(btnRemover);
		hBox5.getChildren().add(vBox2);
		vBox.getChildren().add(hBox5);
		
		HBox hBox6 = new HBox();
		hBox6.setAlignment(Pos.CENTER);
		btnIniciar.setTranslateY(90);
	    hBox6.getChildren().add(btnIniciar);
	    vBox.getChildren().add(hBox6);
		
		root.getChildren().add(vBox);
		
		inserirCaixasFormulario();
		
	}
	
	private void inserirCaixasFormulario() {
		
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
		
		root.getChildren().add(caixa1);
		root.getChildren().add(caixa2);
		
	}

	@Override
	public void validarDados() throws DadosInvalidosException {
		
		String valor1 = tfValor1.getText();
		String valor2 = tfValor2.getText();
		
		if (valor1.isEmpty()) 
			throw new DadosInvalidosException("Por favor, informe um valor para o operando 1");
		
		if (valor2.isEmpty()) 
			throw new DadosInvalidosException("Por favor, informe um valor para o operando 2");
		
		modo1 = obterModoEnderecamento(cbModEnd1);
		modo2 = obterModoEnderecamento(cbModEnd2);
		
		validarEnderecamentoOperando1(valor1);
		validarEnderecamentoOperando2(valor2);			
		
	}
	
	private void validarEnderecamentoOperando1 (String valor) throws DadosInvalidosException {
		
		switch(modo1) {
		case IMEDIATO: throw new DadosInvalidosException
			("O modo de enderçamento do operando 1 não pode ser imediato");
		case DIRETO:{ 
			if (!TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna().contemVar(valor, false))
				throw new DadosInvalidosException("A variável com o valor informado para o operando 1 não existe");
		};break;
		case INDIRETO: {
			if (!TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna().contemVar(valor, false))
				throw new DadosInvalidosException("A variável com o valor informado para o operando 1 não existe");
			else {
				if (!TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna().ehPonteiro(valor, false))
					throw new DadosInvalidosException("A variável com o valor informado para o operando 1 não é um ponteiro");
			};
		}break;
		case REGISTRADOR: {
			if(!TelaPrincipal.getComputador().getUCP().getUCPInterna().contemRegistrador(valor))
				throw new DadosInvalidosException("O registrador com o valor informado para o operando 1 não existe");
		}break;
		case INDIRETO_REGISTRADOR: {
			if(!TelaPrincipal.getComputador().getUCP().getUCPInterna().contemRegistrador(valor))
				throw new DadosInvalidosException("O registrador com o valor informado para o operando 1 não existe");
			else {
				Integer endereco = TelaPrincipal.getComputador().getUCP().getUCPInterna().obterEnderecoRegistrador(valor);
				if (!TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna().contemVar(endereco, true))
					throw new DadosInvalidosException("O registrador informado não contém um endereço de memória");
			}
		}break;
		}
		
	}
	
	private void validarEnderecamentoOperando2 (String valor) throws DadosInvalidosException {
		
		switch(modo2) {
		case IMEDIATO: {
			try {
				Integer.parseInt(valor);
			} catch(NumberFormatException nfe) {
				try {
					Float.parseFloat(valor);
				}catch(NumberFormatException nfe2) {
					throw new DadosInvalidosException("O valor informado para o operando 1 precisa ser um inteiro ou ponto flutuante");
				}
				throw new DadosInvalidosException("O valor informado para o operando 1 precisa ser um inteiro ou ponto flutuante");
			}
		}break;
		case INDIRETO: {
			if (!TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna().contemVar(valor, false))
				throw new DadosInvalidosException("A variável com o valor informado para o operando 2 não existe");
			else {
				if (!TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna().ehPonteiro(valor, false))
					throw new DadosInvalidosException("A variável com o valor informado para o operando 2 não é um ponteiro");
			};
		}break;
		case REGISTRADOR: {
			if(!TelaPrincipal.getComputador().getUCP().getUCPInterna().contemRegistrador(valor))
				throw new DadosInvalidosException("O registrador com o valor informado para o operando 2 não existe");
		}break;
		case INDIRETO_REGISTRADOR: {
			if(!TelaPrincipal.getComputador().getUCP().getUCPInterna().contemRegistrador(valor))
				throw new DadosInvalidosException("O registrador com o valor informado para o operando 2 não existe");
			else {
				Integer endereco = TelaPrincipal.getComputador().getUCP().getUCPInterna().obterEnderecoRegistrador(valor);
				if (!TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna().contemVar(endereco, true))
					throw new DadosInvalidosException("O registrador informado não contém um endereço de memória");
			}
		}break;
		}
		
	}
	
	private ModoEnderecamento obterModoEnderecamento (ChoiceBox<String> cb) {
				
		switch(cb.getSelectionModel().selectedIndexProperty().intValue()) {
		case 0: return ModoEnderecamento.IMEDIATO;
		case 1: return ModoEnderecamento.DIRETO;
		case 2: return ModoEnderecamento.INDIRETO;
		case 3: return ModoEnderecamento.REGISTRADOR;
		case 4: return ModoEnderecamento.INDIRETO_REGISTRADOR;
		}
		
		return null;
		
		
	}

}
