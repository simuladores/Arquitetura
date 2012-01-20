package br.unipe.simuladores.arquitetura.componentes.internos.unidades;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import br.unipe.simuladores.arquitetura.enums.TipoVariavel;

public class VariavelIdentificador extends Variavel{
	
	private StringProperty id;

	public VariavelIdentificador(String id, Integer end, String dado, TipoVariavel tipo,
			Boolean normal) {
		super(end, dado, tipo, normal);
		this.id = new SimpleStringProperty(id);
	}

	public StringProperty idProperty() {
		
		return id;
		
	}

}
