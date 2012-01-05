package br.unipe.simuladores.arquitetura.telas;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.unipe.simuladores.arquitetura.componentes.internos.Variavel;
import br.unipe.simuladores.arquitetura.enums.TipoVariavel;
import br.unipe.simuladores.arquitetura.excecoes.DadosInvalidosException;
import br.unipe.simuladores.arquitetura.excecoes.VariavelExistenteException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class TelaInserirVariavel extends Tela implements Formulario{

	private final TextField tfIdentificador = new TextField();
	private final ChoiceBox<String> cbTipo = new ChoiceBox<String>();
	private final Text txtValor = new Text("Valor Inicial:");
	private final TextField tfValor = new TextField();
	private final ToggleGroup tg = new ToggleGroup();
	private Button btnInserirVar = new Button("Inserir");
	private TipoVariavel tipoVariavel;
	private final RadioButton rbNormal = new RadioButton("Normal");
	private RadioButton rbPonteiro = new RadioButton("Ponteiro");
	private Boolean normal;
	
	public TelaInserirVariavel(String titulo, Color cor) {		
		super(titulo, cor);
		criar();		
	}
	
	@Override
	public void criar() {
		
		VBox vBox = new VBox();
		vBox.setSpacing(20);
		vBox.setPadding(new Insets(10, 10, 10, 10));
		
		HBox hBox1 = new HBox();
		hBox1.setSpacing(10);
	    Text txtIdentificador = new Text("Indentificador [A-Z]:");
		hBox1.getChildren().add(txtIdentificador);
		tfIdentificador.setMaxWidth(50);
		hBox1.getChildren().add(tfIdentificador);
		Text txtTipo = new Text("Tipo:");
		hBox1.getChildren().add(txtTipo);
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
		hBox3.getChildren().add(txtValor);
		tfValor.setMaxWidth(50);
		hBox3.getChildren().add(tfValor);
		vBox2.getChildren().add(hBox3);
		
		hBox2.getChildren().add(vBox2);
		
		VBox vBox3 = new VBox();
		vBox3.setSpacing(10);
		
		rbNormal.setToggleGroup(tg);
		rbNormal.setSelected(true);
		rbNormal.setTranslateX(70);
		vBox3.getChildren().add(rbNormal);
		rbPonteiro.setToggleGroup(tg);
		rbPonteiro.setSelected(false);
		rbPonteiro.setTranslateX(70);
		vBox3.getChildren().add(rbPonteiro);
		
		hBox2.getChildren().add(vBox3);
		
		vBox.getChildren().add(hBox2);
		
		HBox hBox4 = new HBox();
		hBox4.setAlignment(Pos.CENTER);
		btnInserirVar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				
				try {
					inserirVariavelMemoria();
					stage.close();
				} catch (DadosInvalidosException die) {
					TelaErro erro = new TelaErro("Erro", Color.rgb(245, 245, 245),
							die.getMessage());
					erro.exibir();
				} catch (VariavelExistenteException vee) {
					TelaErro erro = new TelaErro("Erro", Color.rgb(245, 245, 245),
							vee.getMessage());
					erro.exibir();
				}
				
			}
			
		});
		hBox4.getChildren().add(btnInserirVar);
		
		vBox.getChildren().add(hBox4);
		
		root.getChildren().add(vBox);
		
	}
	
	public void inserirVariavelMemoria() throws DadosInvalidosException, VariavelExistenteException{
		
		tipoVariavel = obterTipoVariavel();
		
		validarDados();
		
		Variavel variavel = new Variavel(tfValor.getText(), tipoVariavel, normal);
		TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna().
			inserirDado(variavel, tfIdentificador.getText());
		
	}
	
	public void validarDados() throws DadosInvalidosException{
		
		String identificador = tfIdentificador.getText();
		String valor = tfValor.getText();
		if (tg.getSelectedToggle() == rbNormal)
			normal = true;
		else 
			normal = false;
		
		if (identificador.isEmpty()) 
			throw new DadosInvalidosException("Por favor, informe um identificador");
		
		if (valor.isEmpty()) 
			throw new DadosInvalidosException("Por favor, informe um valor");
		
		Pattern padraoId = Pattern.compile("([a-zA-Z])\\w*");
		Matcher pesquisa = padraoId.matcher(identificador);
		
		if(!pesquisa.matches())
			throw new DadosInvalidosException(identificador+" não é um identificador válido");
		
		if (tipoVariavel == TipoVariavel.INTEIRO) {
			
			try {
				Integer.parseInt(valor);
			}catch(NumberFormatException nfe) {
				throw new DadosInvalidosException("O valor informado não é um número inteiro");
			}
			
			
		} else if (tipoVariavel == TipoVariavel.PONTO_FLUTUANTE) {
			
			try {
				Float.parseFloat(valor);
			}catch(NumberFormatException nfe) {
				throw new DadosInvalidosException("O valor informado não é um número de ponto flutuante");
			}
			
		}
		
		
	}
	
	private TipoVariavel obterTipoVariavel() {
		
		TipoVariavel tpVariavel = null;
		
		switch( cbTipo.getSelectionModel().
				selectedIndexProperty().intValue()) {
		case 0: tpVariavel = TipoVariavel.INTEIRO; break;
		case 1: tpVariavel = TipoVariavel.STRING; break;
		case 2: tpVariavel = TipoVariavel.PONTO_FLUTUANTE; break;
		}
		
		return tpVariavel;
		
	}

}
