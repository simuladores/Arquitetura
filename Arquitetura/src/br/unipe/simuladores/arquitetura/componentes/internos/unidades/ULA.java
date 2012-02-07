package br.unipe.simuladores.arquitetura.componentes.internos.unidades;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import br.unipe.simuladores.arquitetura.enums.OperacaoAritmetica;
import br.unipe.simuladores.arquitetura.enums.TipoVariavel;

public class ULA extends UnidadeUCP{
	
	private static final String ULA_TXT = "A ULA (Unidade Lógica e Aritmética) é " +
			"a reponsável\n por executar operações aritméticas, como somar,\nsubtrair, " +
			"multiplicar e dividir.";
	
	private String operando1;
	private String operando2;
	private Number op1;
	private Number op2;
	private Number resultado;
	private OperacaoAritmetica operacao;
	private TipoVariavel tpOp1;
	private TipoVariavel tpOp2;
	
	public ULA() {
		
		super();
		
		operando1 = new String("0");
		operando2 = new String("0");
		operacao = OperacaoAritmetica.SOMA;
		
		//construirForma(850, 550);
		construirForma(830, 550);
		//adicionarTexto(885, 630);
		adicionarTexto(885, 638);
		//atualizarValor("536 + 270", 872, 600);
	    //1 dígito
		//mar.atualizarValor("123456", 846, 587);
		//mbr.atualizarValor("123456", 898, 587);
		//ula.atualizarValor("+", 865, 600);
		//ula.atualizarValor("=", 915, 600);
		//ula.atualizarValor("5", 890, 610);
		
		group.getChildren().addAll(forma, txtNome, txtValor);
		
		definirAcoes();
		
	}
	
	public void operar() {
		
		obterTipoOperandos();
		
		switch(operacao) {
		case SOMA: somar(); break;
		case SUBTRACAO: subtrair(); break;
		case MULTIPLICACAO: multiplicar(); break;
		case DIVISAO: dividir(); break;
		}
		
	}
	
	public void somar() {
		
		if (tpOp1 == TipoVariavel.INTEIRO) {
			
			if (tpOp2 == TipoVariavel.INTEIRO)
				
				resultado = op1.intValue() + op2.intValue();
			
			else
				
				resultado = op1.intValue() + op2.floatValue();
			
		} else {
			
			if (tpOp2 == TipoVariavel.INTEIRO)
				
				resultado = op1.floatValue() + op2.intValue();
			
			else
				
				resultado = op1.floatValue() + op2.floatValue();
			
		}
		
		
	}
	
	public void subtrair() {
		
		if (tpOp1 == TipoVariavel.INTEIRO) {
			
			if (tpOp2 == TipoVariavel.INTEIRO)
				
				resultado = op1.intValue() - op2.intValue();
			
			else
				
				resultado = op1.intValue() - op2.floatValue();
			
		} else {
			
			if (tpOp2 == TipoVariavel.INTEIRO)
				
				resultado = op1.floatValue() - op2.intValue();
			
			else
				
				resultado = op1.floatValue() - op2.floatValue();
			
		}

		
	}
	
	public void multiplicar() {
		
		
		if (tpOp1 == TipoVariavel.INTEIRO) {
			
			if (tpOp2 == TipoVariavel.INTEIRO)
				
				resultado = op1.intValue() * op2.intValue();
			
			else
				
				resultado = op1.intValue() * op2.floatValue();
			
		} else {
			
			if (tpOp2 == TipoVariavel.INTEIRO)
				
				resultado = op1.floatValue() * op2.intValue();
			
			else
				
				resultado = op1.floatValue() * op2.floatValue();
			
		}
		
		
	}
	
	public void dividir() {
		
		
		if (tpOp1 == TipoVariavel.INTEIRO) {
			
			if (tpOp2 == TipoVariavel.INTEIRO)
				
				resultado = op1.intValue() / op2.intValue();
			
			else
				
				resultado = op1.intValue() / op2.floatValue();
			
		} else {
			
			if (tpOp2 == TipoVariavel.INTEIRO)
				
				resultado = op1.floatValue() / op2.intValue();
			
			else
				
				resultado = op1.floatValue() / op2.floatValue();
			
		}
		
		
	}

	public String getOperando1() {
		return operando1;
	}

	public void setOperando1(String operando1) {
		this.operando1 = operando1;
	}

	public String getOperando2() {
		return operando2;
	}

	public void setOperando2(String operando2) {
		this.operando2 = operando2;
	}

	public OperacaoAritmetica getOperacao() {
		return operacao;
	}

	public void setOperacao(OperacaoAritmetica operacao) {
		this.operacao = operacao;
	}

	public Number getResultado() {
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
	
	public void obterTipoOperandos() {
		
		try {
			
			Integer.parseInt(operando1);
			tpOp1 = TipoVariavel.INTEIRO;
			op1 = new Integer(operando1);
			
		} catch(NumberFormatException nfe) {
			
			tpOp1 = TipoVariavel.PONTO_FLUTUANTE;
			op1 = new Float(operando1);
			
		}
		
		try {
			
			Integer.parseInt(operando2);
			tpOp2 = TipoVariavel.INTEIRO;
			op2 = new Integer(operando2);
			
		} catch(NumberFormatException nfe) {
			
			tpOp2 = TipoVariavel.PONTO_FLUTUANTE;
			op2 = new Float(operando2);
			
		}
		
		
	}

	@Override
	public String obterTextoExplicativo() {

		return ULA_TXT;
		
	}

}
