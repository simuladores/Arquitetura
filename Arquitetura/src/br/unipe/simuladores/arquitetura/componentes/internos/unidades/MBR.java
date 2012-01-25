package br.unipe.simuladores.arquitetura.componentes.internos.unidades;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MBR extends UnidadeUCP{

	public MBR() {
		
		super();
		
	}
	
	@Override
	public void construirForma(double x, double y) {
		
		forma = new Rectangle();
		//((Rectangle)forma).setWidth(145);
		((Rectangle)forma).setWidth(145);
		((Rectangle)forma).setHeight(25);
		forma.setFill(Color.LIGHTSALMON);
		((Rectangle)forma).setX(x);
		((Rectangle)forma).setY(y);
		
	}

	@Override
	public void adicionarTexto(double x, double y) {
		
		txtNome = new Text("MBR");
		txtNome.setX(x);
		txtNome.setY(y);
		txtNome.setFont(new Font(12));
		
	}

	@Override
	public void atualizarValor(Object valor, double x, double y) {
		
		super.valor = valor;
		
		atualizarTexto(valor.toString(), x, y);
		
	}

}
