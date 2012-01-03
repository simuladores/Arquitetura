package br.unipe.simuladores.arquitetura.componentes.internos;

import br.unipe.simuladores.arquitetura.enums.TipoVariavel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Variavel extends CelulaMemoria {
	
	private IntegerProperty data;
	
	private TipoVariavel tipo;
	
	public Variavel(Integer end, Integer d, TipoVariavel t) {
		
		super(end);
		data = new SimpleIntegerProperty(d);
		setTipo(t);
	}
	
	public IntegerProperty dataProperty() {
		
		return data;
		
	}

	public TipoVariavel getTipo() {
		return tipo;
	}

	public void setTipo(TipoVariavel tipo) {
		this.tipo = tipo;
	}

}
