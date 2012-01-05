package br.unipe.simuladores.arquitetura.componentes.internos;

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
	
	public IntegerProperty opcodeProperty() {
		
		return opcode;
		
	}
	
	public IntegerProperty referenciaOp1Property() {
		
		return referenciaOperando1;
		
	}
	
	public IntegerProperty referenciaOp2Property() {
		
		return referenciaOperando2;
		
	}

}
