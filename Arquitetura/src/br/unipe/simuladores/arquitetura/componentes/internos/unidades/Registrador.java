package br.unipe.simuladores.arquitetura.componentes.internos.unidades;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Registrador extends UnidadeUCP{

	private String nome;

	public Registrador(String nome) {
		
		this.nome = nome;
		
	}
	
	@Override
	public void construirForma(double x, double y) {
		
		forma = new Rectangle();
		((Rectangle)forma).setWidth(50);
		((Rectangle)forma).setHeight(25);
		forma.setFill(Color.CHARTREUSE);
		((Rectangle)forma).setX(x);
		((Rectangle)forma).setY(y);
		forma.setStroke(Color.BLACK);
		forma.setStrokeWidth(0.5);
		
	}

	@Override
	public void adicionarTexto(double x, double y) {
		
		txtNome = new Text(nome);
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
