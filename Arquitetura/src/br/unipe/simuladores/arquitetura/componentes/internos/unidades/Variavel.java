package br.unipe.simuladores.arquitetura.componentes.internos.unidades;

import br.unipe.simuladores.arquitetura.componentes.internos.CelulaMemoria;
import br.unipe.simuladores.arquitetura.enums.TipoVariavel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Variavel extends CelulaMemoria {
	
	protected StringProperty data;
	
	protected TipoVariavel tipo;
	
	protected Boolean normal;
	
	public Variavel(Integer end, String dado, TipoVariavel tipo, Boolean normal) {
		
		super(end);
		data = new SimpleStringProperty(dado);
		setTipo(tipo);
		setNormal(normal);
		
	}
	
	public Variavel(String dado, TipoVariavel tipo, Boolean normal) {
		
		data = new SimpleStringProperty(dado);
		setTipo(tipo);
		setNormal(normal);
		
	}
	
	public StringProperty dataProperty() {
		
		return data;
		
	}

	public TipoVariavel getTipo() {
		return tipo;
	}

	public void setTipo(TipoVariavel tipo) {
		this.tipo = tipo;
	}

	public Boolean getNormal() {
		return normal;
	}

	public void setNormal(Boolean normal) {
		this.normal = normal;
	}

}
