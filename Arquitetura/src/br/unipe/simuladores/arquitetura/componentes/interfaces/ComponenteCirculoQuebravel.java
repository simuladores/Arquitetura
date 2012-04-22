package br.unipe.simuladores.arquitetura.componentes.interfaces;

import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;

public abstract class ComponenteCirculoQuebravel extends ComponenteCirculo{

	protected void exibirMenus() {
		
		if (TelaPrincipal.getComputador().todosComponentesInternosExpandidos()){
			
			TelaPrincipal.getMenuSuperior().getMenus().get(0).getItems().get(0)
				.setDisable(false);
	
			TelaPrincipal.getMenuSuperior().getMenus().get(0).getItems().get(1)
				.setDisable(false);
			
		}
			
		
	}
	
	public abstract void quebrar(double time);
	
}
