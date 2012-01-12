package br.unipe.simuladores.arquitetura.componentes.internos;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.Instrucao;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.PC;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;

public class Movimentador {
	
	private MemoriaInterna memoriaInterna;
	private UCPInterna ucpInterna;
	private Instrucao instrucaoAtual;
	private Text valorTxt;
	
	public Movimentador() {
		
		memoriaInterna = TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna();
		ucpInterna = TelaPrincipal.getComputador().getUCP().getUCPInterna();
		
		valorTxt = new Text();
		
	}
	
	public void buscarInstrucao() {
		
		moverEnderecoPCParaMAR();
		
	}
	
	public void operar() {
		
		ObservableList<Instrucao> instrucoes = memoriaInterna.getInstrucoes();
		
		for (Instrucao instr : instrucoes) {
			
			PC pc = ucpInterna.getPc();
			
			pc.atualizarValor(instr.endereco.getValue(), 880, 438);
			ucpInterna.atualizarValorUnidadeTela(pc);
			instrucaoAtual = instr;
			
			buscarInstrucao();
			
		}
		
	}
	
	private void moverEnderecoPCParaMAR() {
		
		double xDe = ucpInterna.getPc().getTxtValor().getX();
		double yDe = ucpInterna.getPc().getTxtValor().getY();
		
		final double xPara = ucpInterna.getMar().getTxtValor().getX();
		final double yPara = ucpInterna.getMar().getTxtValor().getY();
		
		final Integer valor = (Integer)ucpInterna.getPc().getValor();
		
		valorTxt.setText(valor.toString());
		valorTxt.setX(xDe);
		valorTxt.setY(yDe);
		valorTxt.setFont(new Font(12));
		
		TelaPrincipal.removerDoPalco(valorTxt);
		TelaPrincipal.adicionarAoPalco(valorTxt);
		
		Timeline timeline = new Timeline();
			
		timeline.getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(valorTxt.xProperty(), xDe),
	                   new KeyValue(valorTxt.yProperty(), yDe)
	               ),
	               new KeyFrame(new Duration(3000), 
	                	new KeyValue(valorTxt.xProperty(), xPara),
		                new KeyValue(valorTxt.yProperty(), yPara)
	               )
	     );
		
		timeline.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				TelaPrincipal.removerDoPalco(valorTxt);
				ucpInterna.getMar().atualizarValor(valor, xPara, yPara);
				ucpInterna.atualizarValorUnidadeTela(ucpInterna.getMar());
				
			}
			
		});
			
		timeline.play();
				
	}

}
