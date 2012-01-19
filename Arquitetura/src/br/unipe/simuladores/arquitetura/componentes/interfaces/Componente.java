package br.unipe.simuladores.arquitetura.componentes.interfaces;

import br.unipe.simuladores.arquitetura.enums.OpcaoJanelaMensagem;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public abstract class Componente {
	
	protected Label nome;
	
	protected Group group;
	
	protected Timeline timeline;
	
	public Componente() {
		
		group = new Group();
		
	}
	
	public Group getContent() {
		
		return group;
		
	}
	
	protected abstract void adicionarTexto();
	
	protected abstract void buildContent();
	
	protected abstract void definirAcoesGerais() ;
	
	protected abstract void definirAcoesEspecificas();
	
	public Text getTextoExplicativo() {
		return new Text(obterTextoExplicativo());
	}
	
	public abstract String obterTextoExplicativo();
	
	protected void exibirMensagemExplicativa() {
		
		if (TelaPrincipal.getOpcaoJanelaMensagem() 
				== OpcaoJanelaMensagem.EXIBIR 
				|| TelaPrincipal.getOpcaoJanelaMensagem() 
				== OpcaoJanelaMensagem.ESCONDER) {
		
			TelaPrincipal.getMensagem().setExpanded(true);
			TelaPrincipal.getMensagem().setContent(getTextoExplicativo());
		}
		
		if (TelaPrincipal.getOpcaoJanelaMensagem() == OpcaoJanelaMensagem.ESCONDER)
			TelaPrincipal.getMensagem().setVisible(true);
		
		if (TelaPrincipal.getOpcaoJanelaMensagem() == OpcaoJanelaMensagem.NAO_EXIBIR)
			TelaPrincipal.getMensagem().setVisible(false);
		
	}
	
	protected void esconderMensagemExplicativa() {
		
		if (!(TelaPrincipal.getOpcaoJanelaMensagem() 
				== OpcaoJanelaMensagem.NAO_EXIBIR)){
			TelaPrincipal.getMensagem().setExpanded(false);
			TelaPrincipal.colocarTextoPadraoMensagem();
		
			if (TelaPrincipal.getOpcaoJanelaMensagem() 
					== OpcaoJanelaMensagem.ESCONDER)
				TelaPrincipal.getMensagem().setVisible(false);
		}
		
	}
	
	

}
