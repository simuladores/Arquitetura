package br.unipe.simuladores.arquitetura.simulacao;

import javafx.animation.Timeline;

public abstract class Ciclo {
	
	protected Controlador controlador;
	protected Timeline timeline;
	protected boolean continuar = false;
	
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

	public Timeline getTimeline() {
		return timeline;
	}

	public void setTimeline(Timeline timeline) {
		this.timeline = timeline;
	}
	
	protected void exibirMensagem() {
		
		//if (TelaPrincipal.isExibirMensagensDeSimulacao())
		
	}

	public boolean isContinuar() {
		return continuar;
	}

	public void setContinuar(boolean continuar) {
		this.continuar = continuar;
	}
	
	

}
