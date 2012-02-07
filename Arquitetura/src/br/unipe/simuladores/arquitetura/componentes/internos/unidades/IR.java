package br.unipe.simuladores.arquitetura.componentes.internos.unidades;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class IR extends UnidadeUCP{
	
	private static final String IR_TXT = "O IR (Instruction Register) é " +
			"responsável por\n armazenar as instruções que chegam da memória.";

	public IR() {
		
		super();
		
		construirForma(870, 510);
		adicionarTexto(848, 528);
		atualizarValor("", 878, 528);
		
		group.getChildren().addAll(forma, txtNome, txtValor);
		
		definirAcoes();
		
	}
	
	@Override
	public void construirForma(double x, double y) {
		
		forma = new Rectangle();
		((Rectangle)forma).setWidth(103);
		((Rectangle)forma).setHeight(25);
		forma.setFill(Color.TURQUOISE);
		((Rectangle)forma).setX(x);
		((Rectangle)forma).setY(y);
		
	}

	@Override
	public void adicionarTexto(double x, double y) {
		
		txtNome = new Text("IR");
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

		return IR_TXT;
		
	}

}
