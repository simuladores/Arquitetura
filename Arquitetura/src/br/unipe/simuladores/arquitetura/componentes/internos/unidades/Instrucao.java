package br.unipe.simuladores.arquitetura.componentes.internos.unidades;

import br.unipe.simuladores.arquitetura.componentes.internos.CelulaMemoria;
import br.unipe.simuladores.arquitetura.enums.ModoEnderecamento;
import br.unipe.simuladores.arquitetura.enums.Operacao;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Instrucao extends CelulaMemoria {
	
	private IntegerProperty opcode;
	private IntegerProperty referenciaOperando1;
	private IntegerProperty referenciaOperando2;
	
	public Instrucao(Integer end, Integer op, Integer refOp1, Integer refOp2) {
		
		super(end);
		opcode = new SimpleIntegerProperty(op);
		referenciaOperando1 = new SimpleIntegerProperty(refOp1);
		referenciaOperando2 = new SimpleIntegerProperty(refOp2);
		
	}
	
	public Instrucao(Integer op, Integer refOp1, Integer refOp2) {
		
		super(0);
		opcode = new SimpleIntegerProperty(op);
		referenciaOperando1 = new SimpleIntegerProperty(refOp1);
		referenciaOperando2 = new SimpleIntegerProperty(refOp2);
		
	}
	
	public Instrucao(Integer refOp1, Integer refOp2, Operacao operacao, 
			ModoEnderecamento modEndOp1, ModoEnderecamento modEndOp2){
		
		super(0);
		opcode = new SimpleIntegerProperty(0);
		this.gerarOpcode(operacao, modEndOp1, modEndOp2);
		referenciaOperando1 = new SimpleIntegerProperty(refOp1);
		referenciaOperando2 = new SimpleIntegerProperty(refOp2);
				
	}
	
	public IntegerProperty opcodeProperty() {
		
		return opcode;
		
	}
	
	public IntegerProperty referenciaOp1Property() {
		
		return referenciaOperando1;
		
	}
	
	public IntegerProperty referenciaOp2Property() {
		
		return referenciaOperando2;
		
	}
	
	public void gerarOpcode(Operacao operacao, ModoEnderecamento modEndOp1, ModoEnderecamento modEndOp2) {
		
		int fatorOperacao = 0;
		
		switch(operacao) {
		case MOV: fatorOperacao = 0;break;
		case ADD: fatorOperacao = 1;break;
		case SUB: fatorOperacao = 2;break;
		case MUL: fatorOperacao = 3;break;
		case DIV: fatorOperacao = 4;break;
		}
		
		int fatorEnderecamentoOp1 = 0;
		
		switch(modEndOp1) {
		case DIRETO: fatorEnderecamentoOp1 = 0; break;
		case INDIRETO: fatorEnderecamentoOp1 = 1; break;
		case REGISTRADOR: fatorEnderecamentoOp1 = 2; break;
		case INDIRETO_REGISTRADOR: fatorEnderecamentoOp1 = 3; break;
		}
		
		int codigoRelativoOpcode = obterCodigoRelativoOpcode(fatorEnderecamentoOp1, modEndOp2);
		
		opcode.setValue(codigoRelativoOpcode + 20 * fatorOperacao);
		
	}
	
	private int obterCodigoRelativoOpcode(int fatorEndOp1, ModoEnderecamento modEndOp2) {
		
		int fatorEnderecamentoOp2 = 0;
		
		switch(modEndOp2) {
		case IMEDIATO: fatorEnderecamentoOp2 = 1; break;
		case DIRETO: fatorEnderecamentoOp2 = 2; break;
		case INDIRETO: fatorEnderecamentoOp2 = 3; break;
		case REGISTRADOR: fatorEnderecamentoOp2 = 4; break;
		case INDIRETO_REGISTRADOR: fatorEnderecamentoOp2 = 5; break;
		}
		
		return fatorEnderecamentoOp2 + 5 * fatorEndOp1; 
		
	}

}
