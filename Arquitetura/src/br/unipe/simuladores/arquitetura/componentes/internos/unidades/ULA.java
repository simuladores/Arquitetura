package br.unipe.simuladores.arquitetura.componentes.internos.unidades;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import br.unipe.simuladores.arquitetura.enums.OperacaoAritmetica;

public class ULA extends UnidadeUCP{
	
	private Integer operando1;
	private Integer operando2;
	private Integer resultado;
	private OperacaoAritmetica operacao;
	
	public ULA() {
		
		super();
		
		operando1 = 0;
		operando2 = 0;
		resultado = 0;
		operacao = OperacaoAritmetica.SOMA;
		
	}
	
	public void operar() {
		
		switch(operacao) {
		case SOMA: somar(); break;
		case SUBTRACAO: subtrair(); break;
		case MULTIPLICACAO: multiplicar(); break;
		case DIVISAO: dividir(); break;
		}
		
	}
	
	public void somar() {
		
		resultado = operando1 + operando2;
		
	}
	
	public void subtrair() {
		
		resultado = operando1 - operando2;
		
	}
	
	public void multiplicar() {
		
		resultado = operando1 * operando2;
		
	}
	
	public void dividir() {
		
		resultado = operando1 / operando2;
		
	}

	public Integer getOperando1() {
		return operando1;
	}

	public void setOperando1(Integer operando1) {
		this.operando1 = operando1;
	}

	public Integer getOperando2() {
		return operando2;
	}

	public void setOperando2(Integer operando2) {
		this.operando2 = operando2;
	}

	public OperacaoAritmetica getOperacao() {
		return operacao;
	}

	public void setOperacao(OperacaoAritmetica operacao) {
		this.operacao = operacao;
	}

	public Integer getResultado() {
		return resultado;
	}

	public void setResultado(Integer resultado) {
		this.resultado = resultado;
	}

	@Override
	public void construirForma(double x, double y) {
		
		forma = new Polyline(new double[]{
				/*x, y,
				x + 30, y,
				x + 35, y + 10,
				x + 40, y,
				x + 70, y,
				x + 60, y + 40,
				x + 10, y + 40,
				x, y*/
				
				x, y,
				x + 45, y,
				x + 52.5, y + 15,
				x + 60, y,
				x + 105, y,
				x + 90, y + 50,
				x + 15, y + 50,
				x, y
				
		});
		
		/*forma = new Polyline(new double[]{
					850, 550,
					880, 550,
					885, 560,
					890, 550,
					920, 550,
					910, 590,
					860, 590,
					850, 550
			});*/
			
		forma.setStrokeWidth(0.5);
			
		forma.setFill(Color.YELLOW);
		forma.setTranslateY(20);
		forma.setTranslateX(10);
		
	}

	@Override
	public void adicionarTexto(double x, double y) {
		
		txtNome = new Text("ULA");
		txtNome.setX(x);
		txtNome.setY(y);
		txtNome.setFont(new Font(12));
		
	}

	@Override
	public void atualizarValor(Object valor, double x, double y) {
		
		super.valor = valor;
		
		atualizarTexto((String)valor, x, y);
		
	}

}
