package br.unipe.simuladores.arquitetura.simulacao;

import java.util.LinkedList;
import java.util.Queue;

import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import br.unipe.simuladores.arquitetura.botoes.BotaoPlay;
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
	
	protected ObservableList<Instrucao> instrucoes;
	protected Queue<Instrucao> instrucoesQueue = new LinkedList<Instrucao>();
	
	private Timeline timelineAtual;
		
	public Controlador() {
		
		memoriaInterna = TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna();
		ucpInterna = TelaPrincipal.getComputador().getUCP().getUCPInterna();
		barramentoInterno = TelaPrincipal.getComputador().getSistemaInterconexao().getBarramentoInterno();
		btnPlay = TelaPrincipal.getBotaoPlay();

		instrucoes = memoriaInterna.getInstrucoes();
		instrucoesQueue = new LinkedList<Instrucao>();
		
		for (Instrucao instr : instrucoes)
			instrucoesQueue.add(instr);
		
		
	}
	
	public void iniciarSimulacao() {
		
		btnPlay.setVisible(true);

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

	public Timeline getTimelineAtual() {
		return timelineAtual;
	}

	public void setTimelineAtual(Timeline timelineAtual) {
		this.timelineAtual = timelineAtual;
		btnPlay.setTimeline(this.timelineAtual);
	}

	public BotaoPlay getBtnPlay() {
		return btnPlay;
	}

	public void setBtnPlay(BotaoPlay btnPlay) {
		this.btnPlay = btnPlay;
	}

}
