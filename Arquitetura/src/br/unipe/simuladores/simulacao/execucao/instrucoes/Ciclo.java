package br.unipe.simuladores.simulacao.execucao.instrucoes;

public abstract class Ciclo {
	
	protected Controlador controlador;
	
	public Ciclo(Controlador c) {
		
		this.controlador = c;
		
	}
	
	public abstract void mostrarAnimacoes();

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

}
