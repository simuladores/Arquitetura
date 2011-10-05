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
		// TODO Auto-generated method stub
		
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
		group.getChildren().removeAll(circulo, grupoBarramento,grupoModuloES);
		
		Circle barramento = new Circle();
		Circle moduloES = new Circle();
		
		grupoBarramento = new Group();
		grupoBarramento.getChildren().add(barramento);
		grupoModuloES = new Group();
		grupoModuloES.getChildren().add(moduloES);
		
		barramento.setFill(Color.rgb(255, 0, 0, 0.5f));
		barramento.setCenterX(1000);
		barramento.setCenterY(1000);
		barramento.setRadius(32);
		
		/*moduloES.setFill(Color.rgb(255, 0, 0, 0.5f));
		moduloES.setCenterX(650);
		moduloES.setCenterY(295);
		moduloES.setRadius(32);*/
		
		group.getChildren().addAll(circulo, grupoBarramento,grupoModuloES);
		
		//adicionarTexto();
		
		
	}

	@Override
	protected void definirAcoesEspecificas() {
		// TODO Auto-generated method stub
		
	}

}
