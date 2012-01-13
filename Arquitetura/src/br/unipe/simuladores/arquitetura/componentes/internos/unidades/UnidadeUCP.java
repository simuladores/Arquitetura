package br.unipe.simuladores.arquitetura.componentes.internos.unidades;

import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public abstract class UnidadeUCP {
	
	protected Shape forma;
	protected Text txtNome;
	protected Text txtValor;
	protected Object valor;
	
	public UnidadeUCP() {
		
		txtValor = new Text();
		
	}
	
	public abstract void construirForma(double x, double y);
	
	public abstract void adicionarTexto(double x, double y);
	
	public abstract void atualizarValor(Object valor, double x, double y);
	
	protected void atualizarTexto(String valor, double x, double y) {
		
		txtValor.setText(valor);
		txtValor.setX(x);
		txtValor.setY(y);
		
	}
	
	public Shape getForma() {
		return forma;
	}
	public void setForma(Shape forma) {
		this.forma = forma;
	}
	public Text getTxtNome() {
		return txtNome;
	}
	public void setTxtNome(Text txtNome) {
		this.txtNome = txtNome;
	}
	public Text getTxtValor() {
		return txtValor;
	}
	public void setTxtValor(Text txtValor) {
		this.txtValor = txtValor;
	}
	public Object getValor() {
		return valor;
	}
	public void setValor(Object valor) {
		this.valor = valor;
	}

}
