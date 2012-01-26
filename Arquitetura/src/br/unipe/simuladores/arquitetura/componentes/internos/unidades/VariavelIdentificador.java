package br.unipe.simuladores.arquitetura.componentes.internos.unidades;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class VariavelIdentificador {
	
	private StringProperty id;
	private StringProperty endereco;
	private StringProperty data;

	public VariavelIdentificador(String id, String end, String data) {
		
		this.id = new SimpleStringProperty(id);
		this.endereco = new SimpleStringProperty(end);
		this.data = new SimpleStringProperty(data);
		
	}

	public StringProperty idProperty() {
		
		return id;
		
	}
	
	public StringProperty enderecoProperty() {
		
		return endereco;
		
	}
	
	public StringProperty dataProperty() {
		
		return data;
		
	}

}
