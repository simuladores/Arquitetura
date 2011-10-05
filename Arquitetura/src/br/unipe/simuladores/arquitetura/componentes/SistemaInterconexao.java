package br.unipe.simuladores.arquitetura.componentes;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SistemaInterconexao extends Componente{

	private Group grupoBarramento;
	private Group grupoModuloES;
	
	public SistemaInterconexao() {
		
		super();
		expanded = false;
		
	}
	
	@Override
	public void expandir(double fromScale, double toScale, double time) {
		
		expanded = true;
		
	}

	@Override
	public void encolher(double fromScale, double toScale, double time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void adicionarTexto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void buildContent() {
		
		//remove todos os elementos do grupo desse componente para evitar a repetição deles
		group.getChildren().removeAll(grupoBarramento,grupoModuloES);
		
		Circle barramento = new Circle();
		Circle moduloES = new Circle();
		
		grupoBarramento = new Group();
		grupoBarramento.getChildren().add(barramento);
		grupoModuloES = new Group();
		grupoModuloES.getChildren().add(moduloES);
		
		barramento.setFill(Color.rgb(126, 126, 0, 0.5f));
		barramento.setCenterX(1100);
		barramento.setCenterY(320);
		barramento.setRadius(70);
		
		moduloES.setFill(Color.rgb(126, 126, 0, 0.5f));
		moduloES.setCenterX(450);
		moduloES.setCenterY(580);
		moduloES.setRadius(70);
		
		group.getChildren().addAll(grupoBarramento,grupoModuloES);
		
		//adicionarTexto();
		
		
	}

	@Override
	protected void definirAcoesEspecificas() {
		// TODO Auto-generated method stub
		
	}

}
