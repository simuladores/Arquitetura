package br.unipe.simuladores.arquitetura.componentes.circulos;

import br.unipe.simuladores.arquitetura.enums.OperacaoAritmetica;

public class ULA {
	
	private Integer operando1;
	private Integer operando2;
	private Integer resultado;
	private OperacaoAritmetica operacao;
	
	public ULA() {
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

}
