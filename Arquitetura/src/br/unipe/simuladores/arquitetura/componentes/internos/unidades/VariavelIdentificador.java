package br.unipe.simuladores.arquitetura.componentes.internos.unidades;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import br.unipe.simuladores.arquitetura.enums.TipoVariavel;

public class VariavelIdentificador {
	
	private StringProperty id;
	private StringProperty data;

	public VariavelIdentificador(String id, String data) {
		
		this.id = new SimpleStringProperty(id);
		this.data = new SimpleStringProperty(data);
		
	}

	public StringProperty idProperty() {
		
		return id;
		
	}
	
	public StringProperty dataProperty() {
		
		return data;
		
	}

}
