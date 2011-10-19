package br.unipe.simuladores.arquitetura.componentes.internos;

import javafx.scene.paint.Color;
import br.unipe.simuladores.arquitetura.componentes.interfaces.ComponenteInterno;

public class UCPInterna extends ComponenteInterno{
	
	public UCPInterna() {
		
		super();
		
	}

	@Override
	protected void adicionarTexto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void buildContent() {
		
		group.getChildren().removeAll(retangulo);
		
		retangulo.setWidth(280);
		retangulo.setHeight(280);
		retangulo.setFill(Color.BLUEVIOLET);
		retangulo.setX(750);
		retangulo.setY(370);
		
		group.getChildren().addAll(retangulo);
		
	}

	@Override
	protected void definirAcoesGerais() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void definirAcoesEspecificas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surgir() {
		// TODO Auto-generated method stub
		
	}

	

}
