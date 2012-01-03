package br.unipe.simuladores.arquitetura.componentes.internos;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class CelulaMemoria {
	
	protected IntegerProperty endereco;
	
	public CelulaMemoria() {
		
		endereco = new SimpleIntegerProperty(0);
		
	}
	
	public CelulaMemoria(Integer end) {
		
		endereco = new SimpleIntegerProperty(end);
		
	}
	
	public IntegerProperty enderecoProperty() {
		
		return endereco;
		
	}

}
