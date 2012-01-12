package br.unipe.simuladores.arquitetura.componentes.internos;

import javafx.collections.ObservableList;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.Instrucao;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.PC;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;

public class Movimentador {
	
	private MemoriaInterna memoriaInterna;
	private UCPInterna ucpInterna;
	private Instrucao instrucaoAtual;
	
	public Movimentador() {
		
		memoriaInterna = TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna();
		ucpInterna = TelaPrincipal.getComputador().getUCP().getUCPInterna();
		
	}
	
	public void buscarInstrucao() {
		
		moverEnderecoPCParaMAR();
		
	}
	
	public void operar() {
		
		ObservableList<Instrucao> instrucoes = memoriaInterna.getInstrucoes();
		
		for (Instrucao instr : instrucoes) {
			
			PC pc = ucpInterna.getPc();
			
			pc.atualizarValor(instr.endereco.getValue(), 892, 438);
			ucpInterna.atualizarValorUnidadeTela(pc);
			instrucaoAtual = instr;
			
			buscarInstrucao();
			
		}
		
	}
	
	private void moverEnderecoPCParaMAR() {
		
		//double xDe = ;
		
	}

}
