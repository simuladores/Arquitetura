package br.unipe.simuladores.arquitetura.componentes.internos;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Dado extends CelulaMemoria {
	
	private IntegerProperty data;
	
	public Dado(Integer end, Integer d) {
		
		super(end);
		data = new SimpleIntegerProperty(d);
		
	}
	
	public IntegerProperty dataProperty() {
		
		return data;
		
	}

}
