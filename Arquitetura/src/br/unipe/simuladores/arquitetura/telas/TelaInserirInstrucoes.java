package br.unipe.simuladores.arquitetura.telas;

import br.unipe.simuladores.arquitetura.componentes.circulos.CaixaFormulario;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
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

public class TelaInserirInstrucoes extends Tela{

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
		
		Text modEnd1 = new Text("Modo de endereçamento:");
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
		
		Text modEnd2 = new Text("Modo de endereçamento:");
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
		Button btnIniciar = new Button("Iniciar Simulação");
		btnIniciar.setTranslateY(90);
	    hBox6.getChildren().add(btnIniciar);
	    vBox.getChildren().add(hBox6);
		
		root.getChildren().add(vBox);
		
		inserirCaixasFormulario(root);
		
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

}
