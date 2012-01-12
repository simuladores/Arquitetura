package br.unipe.simuladores.simulacao.execucao.instrucoes;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import br.unipe.simuladores.arquitetura.componentes.internos.UCPInterna;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;

public class Animador extends Service{
	
	private UCPInterna ucpInterna;
	private Text valorTxt;
	
	public Animador(UCPInterna ucp, Text valor) {
		this.ucpInterna = ucp;
		this.valorTxt = valor;
	}

	@Override
	protected Task createTask() {
		
		return new Task() {

			@Override
			protected Object call() throws Exception {
				
				buscarInstrucao();
				
				return null;
			}
			
		};
		

	}
	
	
	public void buscarInstrucao() {
		
		moverEnderecoPCParaMAR();
		
	}
	
	private void moverEnderecoPCParaMAR() {
		
		System.out.println("b");
		
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
		
		try {
			Thread.sleep(3010);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

}
