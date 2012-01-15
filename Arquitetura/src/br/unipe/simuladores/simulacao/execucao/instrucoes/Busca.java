package br.unipe.simuladores.simulacao.execucao.instrucoes;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;

public class Busca extends Ciclo{

	public Busca(Controlador c) {
		
		super(c);
		
	}
	
	@Override
	public void mostrarAnimacoes() {
		
		moverEnderecoPCParaMAR();
		
	}
	
	private void moverEnderecoPCParaMAR() {
		
		double xDe = controlador.getUcpInterna().getPc().getTxtValor().getX();
		double yDe = controlador.getUcpInterna().getPc().getTxtValor().getY();
		
		final double xPara = controlador.getUcpInterna().getMar().getTxtValor().getX();
		final double yPara = controlador.getUcpInterna().getMar().getTxtValor().getY();
		
		final Integer valor = (Integer)controlador.getUcpInterna().getPc().getValor();
		
		final Text valorTxt = new Text();
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
				controlador.getUcpInterna().getMar().atualizarValor(valor, xPara, yPara);
				controlador.getUcpInterna().atualizarValorUnidadeTela(controlador.getUcpInterna().getMar());	
				
				controlador.operar();
				
			}
			
		});
		
		timeline.play();
			
	}

}
