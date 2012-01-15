package br.unipe.simuladores.simulacao.execucao.instrucoes;

import java.util.LinkedList;
import java.util.Queue;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView.TableViewSelectionModel;
import br.unipe.simuladores.arquitetura.componentes.internos.MemoriaInterna;
import br.unipe.simuladores.arquitetura.componentes.internos.UCPInterna;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.Instrucao;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.PC;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;

public class Controlador{
	
	protected MemoriaInterna memoriaInterna;
	protected UCPInterna ucpInterna;
	protected Instrucao instrucaoAtual;
	
	protected ObservableList<Instrucao> instrucoes;
	protected Queue<Instrucao> instrucoesQueue = new LinkedList<Instrucao>();

	
	public Controlador() {
		
		memoriaInterna = TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna();
		ucpInterna = TelaPrincipal.getComputador().getUCP().getUCPInterna();

		instrucoes = memoriaInterna.getInstrucoes();
		instrucoesQueue = new LinkedList<Instrucao>();
		
		for (Instrucao instr : instrucoes)
			instrucoesQueue.add(instr);
		
		
	}
	
	public void iniciarSimulacao() {
		
		operar();
		
	}
	
    void operar() {
		
				
		if (instrucoesQueue.size() > 0) {
					
			Instrucao instr = instrucoesQueue.poll();
					
			final PC pc = ucpInterna.getPc();
			
			Integer x = instr.enderecoProperty().getValue();
			pc.atualizarValor(x, 880, 438);
					
			ucpInterna.atualizarValorUnidadeTela(pc);

			instrucaoAtual = instr;
					
			TableViewSelectionModel <Instrucao> selectionModel =  
			memoriaInterna.getTabelaInstrucoes().getSelectionModel();
			selectionModel.select(instrucaoAtual);
			memoriaInterna.getTabelaInstrucoes().selectionModelProperty().setValue(selectionModel);
					
			Busca busca = new Busca(this);
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
	

}
