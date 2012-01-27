package br.unipe.simuladores.arquitetura.simulacao;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javafx.animation.Animation;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TableView.TableViewSelectionModel;
import br.unipe.simuladores.arquitetura.botoes.BotaoPlay;
import br.unipe.simuladores.arquitetura.botoes.BotaoStop;
import br.unipe.simuladores.arquitetura.componentes.internos.BarramentoInterno;
import br.unipe.simuladores.arquitetura.componentes.internos.MemoriaInterna;
import br.unipe.simuladores.arquitetura.componentes.internos.UCPInterna;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.Instrucao;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;

public class Controlador{
	
	private MemoriaInterna memoriaInterna;
	private UCPInterna ucpInterna;
	private Instrucao instrucaoAtual;
	private BarramentoInterno barramentoInterno;
	private BotaoPlay btnPlay;
	private BotaoStop btnStop;
	private List<Node> elementosAdicionados;
	
	protected ObservableList<Instrucao> instrucoes;
	protected Queue<Instrucao> instrucoesQueue = new LinkedList<Instrucao>();
	
	private Animation animacaoAtual;
		
	public Controlador() {
		
		memoriaInterna = TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna();
		ucpInterna = TelaPrincipal.getComputador().getUCP().getUCPInterna();
		barramentoInterno = TelaPrincipal.getComputador().getSistemaInterconexao().getBarramentoInterno();
		btnPlay = TelaPrincipal.getBotaoPlay();
		btnStop = TelaPrincipal.getBotaoStop();

		instrucoes = memoriaInterna.getInstrucoes();
		instrucoesQueue = new LinkedList<Instrucao>();
		
		for (Instrucao instr : instrucoes)
			instrucoesQueue.add(instr);
		
		elementosAdicionados = new ArrayList<Node>();
		
		
	}
	
	public void iniciarSimulacao() {
		
		btnPlay.setVisible(true);
		btnPlay.setControlador(this);
		
		btnStop.setVisible(true);
		btnStop.setBtnPlay(btnPlay);
		btnStop.setControlador(this);

		btnPlay.setBtnStop(btnStop);
		
		operar();
		
	}
	
    void operar() {
		
				
		if (instrucoesQueue.size() > 0) {
					
			instrucaoAtual = instrucoesQueue.poll();
					
			TableViewSelectionModel <Instrucao> selectionModel =  
			memoriaInterna.getTabelaInstrucoes().getSelectionModel();
			selectionModel.select(instrucaoAtual);
			memoriaInterna.getTabelaInstrucoes().selectionModelProperty().setValue(selectionModel);
					
			Busca busca = new Busca(this);
			//acaoIniciarSimulacao(busca.timeline);
			busca.mostrarAnimacoes();
			
										
		}
				
	}
    
    public void adicionarElemento(Node n) {
    	
    	elementosAdicionados.add(n);
    	
    }
    
    public void limpar() {
    	
    	for (Node elemento : elementosAdicionados) {
    		
    		ucpInterna.remover(elemento);
    		barramentoInterno.remover(elemento);
    		
    	}
    	
    	ucpInterna.remover(ucpInterna.getPc().getTxtValor());
    	ucpInterna.remover(ucpInterna.getMar().getTxtValor());
    	ucpInterna.remover(ucpInterna.getMbr().getTxtValor());
    	ucpInterna.remover(ucpInterna.getUc().getTxtValor());
    	
    	elementosAdicionados.clear();
    	
    }
    
    public boolean haProximaInstrucao() {
    	
    	if (instrucoesQueue.isEmpty())
    		return false;
    	
    	return true;
    	
    }
    
    public Integer obterEnderecoProximaInstrucao() {
    	
    	Instrucao inst = instrucoesQueue.peek();
    	if (inst == null)
    		return -1;
    	
    	return inst.enderecoProperty().getValue();
    	
    }

	public MemoriaInterna getMemoriaInterna() {
		return memoriaInterna;
	}

	public void setMemoriaInterna(MemoriaInterna memoriaInterna) {
		this.memoriaInterna = memoriaInterna;
	}

	public UCPInterna getUcpInterna() {
		return ucpInterna;
	}

	public void setUcpInterna(UCPInterna ucpInterna) {
		this.ucpInterna = ucpInterna;
	}

	public Instrucao getInstrucaoAtual() {
		return instrucaoAtual;
	}

	public void setInstrucaoAtual(Instrucao instrucaoAtual) {
		this.instrucaoAtual = instrucaoAtual;
	}

	public BarramentoInterno getBarramentoInterno() {
		return barramentoInterno;
	}

	public void setBarramentoInterno(BarramentoInterno barramentoInterno) {
		this.barramentoInterno = barramentoInterno;
	}
	
	

	public Animation getAnimacaoAtual() {
		return animacaoAtual;
	}

	public void setAnimacaoAtual(Animation animacaoAtual) {
		this.animacaoAtual = animacaoAtual;
		btnPlay.setAnimation(this.animacaoAtual);
		btnStop.setAnimation(this.animacaoAtual);
	}

	public BotaoPlay getBtnPlay() {
		return btnPlay;
	}

	public void setBtnPlay(BotaoPlay btnPlay) {
		this.btnPlay = btnPlay;
	}

	public List<Node> getElementosAdicionados() {
		return elementosAdicionados;
	}

	public void setElementosAdicionados(List<Node> elementosAdicionados) {
		this.elementosAdicionados = elementosAdicionados;
	}


}
