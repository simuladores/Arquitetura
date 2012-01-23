package br.unipe.simuladores.arquitetura.simulacao;

import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;
import br.unipe.simuladores.arquitetura.telas.TelaMensagemCicloBusca;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;
import br.unipe.simuladores.arquitetura.telas.TelaSimplesMensagem;
import javafx.animation.Animation;
import javafx.animation.Timeline;

public abstract class Ciclo {
	
	protected Controlador controlador;
	protected Animation animation;
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
			controlador.setAnimacaoAtual(animation);
			controlador.getBtnPlay().setPaused(true);
		}
		else {
			controlador.setAnimacaoAtual(animation);
			controlador.getBtnPlay().setPaused(false);
			animation.play();
		}
		
	}
	
	protected abstract void limparElementosTela();

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public boolean isContinuar() {
		return continuar;
	}

	public void setContinuar(boolean continuar) {
		this.continuar = continuar;
	}
	
	

}
