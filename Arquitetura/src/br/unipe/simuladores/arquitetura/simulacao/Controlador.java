package br.unipe.simuladores.arquitetura.simulacao;

import java.util.LinkedList;
import java.util.Queue;

import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.text.Text;
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
	
	protected ObservableList<Instrucao> instrucoes;
	protected Queue<Instrucao> instrucoesQueue = new LinkedList<Instrucao>();
	
	private Timeline timelineAtual;
	private Button btnIniciar;
	

	
	public Controlador() {
		
		memoriaInterna = TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna();
		ucpInterna = TelaPrincipal.getComputador().getUCP().getUCPInterna();
		barramentoInterno = TelaPrincipal.getComputador().getSistemaInterconexao().getBarramentoInterno();

		instrucoes = memoriaInterna.getInstrucoes();
		instrucoesQueue = new LinkedList<Instrucao>();
		
		for (Instrucao instr : instrucoes)
			instrucoesQueue.add(instr);
		
		
	}
	
	public void iniciarSimulacao() {
		
		btnIniciar = new Button("Iniciar");
		btnIniciar.setTranslateX(50);
		btnIniciar.setTranslateY(60);
		acoesUsuario();
		ucpInterna.adicionar(btnIniciar);

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
	
	public void acoesUsuario() {
		
		btnIniciar.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				timelineAtual.play();
				
			}
			
		});
		
	}

	public Timeline getTimelineAtual() {
		return timelineAtual;
	}

	public void setTimelineAtual(Timeline timelineAtual) {
		this.timelineAtual = timelineAtual;
	}

}
