package br.unipe.simuladores.arquitetura.simulacao;

import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;
import br.unipe.simuladores.arquitetura.telas.TelaMensagemCicloBusca;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;
import br.unipe.simuladores.arquitetura.telas.TelaSimplesMensagem;
import javafx.animation.Timeline;

public abstract class Ciclo {
	
	protected Controlador controlador;
	protected Timeline timeline;
	protected boolean continuar = false;
	protected TelaSimplesMensagem telaMensagem;
	
	public Ciclo(Controlador c) {
		
		this.controlador = c;
		
	}
	
	public abstract void mostrarAnimacoes();
	
	protected void nextStep(EstadoCiclo estado) {
		
		if (TelaPrincipal.isExibirMensagensDeSimulacao()) {
			telaMensagem = new TelaMensagemCicloBusca
					(estado);
			telaMensagem.exibir();
			controlador.setTimelineAtual(timeline);
			controlador.getBtnPlay().setPaused(true);
		}
		else {
			controlador.setTimelineAtual(timeline);
			controlador.getBtnPlay().setPaused(false);
			timeline.play();
		}
		
	}

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
