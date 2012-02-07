package br.unipe.simuladores.arquitetura.componentes.internos.unidades;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class UC extends UnidadeUCP{
	
	private static final String UC_TXT = "A UC (Unidade de Controle) é quem " +
			"coordena as atividades\n da UCP. Ela emite as instruções de leitura " +
			"de dados ou\n instruções e de escrita de dados para o barramento de\n " +
			"instruções. Além disso, ela é responsável por emitir\n instruções " +
			"que vão realizar operações internas na UCP,\ncomo atualizar o PC, " +
			"realizar operações aritméticas e\ntransferir dados entre componentes " +
			"da UCP pelo\n barramento interno dessa."; 
	
	public UC() {
		
		super();
		
		construirForma(950, 575);
		adicionarTexto(975, 618);
		//atualizarValor("WRITE", 965, 593);
		
		group.getChildren().addAll(forma, txtNome, txtValor);
		
		definirAcoes();
		
	}
	
	@Override
	public void construirForma(double x, double y) {
		
		forma = new Rectangle();
		((Rectangle)forma).setWidth(65);
		((Rectangle)forma).setHeight(25);
		forma.setFill(Color.CORAL);
		((Rectangle)forma).setX(x);
		((Rectangle)forma).setY(y);
		
	}

	@Override
	public void adicionarTexto(double x, double y) {
		
		txtNome = new Text("UC");
		txtNome.setX(x);
		txtNome.setY(y);
		txtNome.setFont(new Font(12));
		
	}

	@Override
	public void atualizarValor(Object valor, double x, double y) {
		
		super.valor = valor;
		
		atualizarTexto((String)valor, x, y);
		
	}

	@Override
	public String obterTextoExplicativo() {

		return UC_TXT;
		
	}

}
