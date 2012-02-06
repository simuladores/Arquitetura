package br.unipe.simuladores.arquitetura.componentes.interfaces;

import javafx.scene.text.Text;
import br.unipe.simuladores.arquitetura.enums.OpcaoJanelaMensagem;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;

public abstract class Componente {
	
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
	
	public Text getTextoExplicativo() {
		return new Text(obterTextoExplicativo());
	}

	public abstract String obterTextoExplicativo();
	

}
