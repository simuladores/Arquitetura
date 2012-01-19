package br.unipe.simuladores.arquitetura.simulacao;

import java.util.Enumeration;

import br.unipe.simuladores.arquitetura.enums.EstadoCicloBusca;
import br.unipe.simuladores.arquitetura.telas.TelaMensagemCicloBusca;
import br.unipe.simuladores.arquitetura.telas.TelaMensagemSimulacao;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;

public abstract class Ciclo {
	
	protected Controlador controlador;
	protected Timeline timeline;
	protected TelaMensagemSimulacao mensagemSimulacao;
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
