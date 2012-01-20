package br.unipe.simuladores.arquitetura.simulacao;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.PC;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;

public class Busca extends Ciclo{
	
	private Text read;
	private Text valorMar;
	private TranslateTransition translate;
	private Button button;

	public Busca(Controlador c) {
		
		super(c);
		
	}
	
	
	@Override
	public void mostrarAnimacoes() {
		
	    atualizarPC();
		moverEnderecoPCParaMAR();
		
		
	}
	
	public void atualizarPC() {
		
		
			final PC pc = controlador.getUcpInterna().getPc();
		
			pc.atualizarValor(controlador.getInstrucaoAtual().enderecoProperty().getValue(), 880, 438);
				
			controlador.getUcpInterna().atualizarValorUnidadeTela(pc);		
			
		
	}
	
	public void moverEnderecoPCParaMAR() {
		
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
				
			controlador.getUcpInterna().remover(valorTxt);
			controlador.getUcpInterna().adicionar(valorTxt);
			valorTxt.toFront();
					
			timeline = new Timeline();
			
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
				
					controlador.getUcpInterna().remover(valorTxt);
					controlador.getUcpInterna().getMar().atualizarValor(valor, xPara, yPara);
					controlador.getUcpInterna().atualizarValorUnidadeTela(controlador.getUcpInterna().getMar());	
				
					copiarREADParaBarramento();
				
				}
			
			});
		
			timeline.play();
			
			/* translate = 
					new TranslateTransition(Duration.seconds(3), valorTxt);
			translate.setFromX(xDe);
			translate.setFromY(yDe);
			translate.setToX(xPara);
			translate.setToY(yPara);
			
			translate.play();*/
			
			
	}
	
	public void copiarREADParaBarramento() {
		
		
			double xDe = 975, yDe = 593, xPara = 1100;
		
			controlador.getUcpInterna().getUc().atualizarValor("READ", xDe, yDe);
			controlador.getUcpInterna()
				.atualizarUnidadeTela(controlador.getUcpInterna().getUc());
		
			read = new Text("READ");
			read.setX(xDe);
			read.setY(yDe);
			read.setFont(new Font(12));
		
			controlador.getBarramentoInterno().adicionar(read);
			read.toFront();
		
			timeline = new Timeline();
			
				timeline.getKeyFrames().addAll(
		               new KeyFrame(Duration.ZERO, 
		                   new KeyValue(read.xProperty(), xDe),
		                   new KeyValue(read.yProperty(), yDe)
		               ),
		               new KeyFrame(new Duration(3000), 
		                	new KeyValue(read.xProperty(), xPara),
			                new KeyValue(read.yProperty(), yDe)
		               )
				);
			
				timeline.setOnFinished(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent arg0) {
					
					copiarValorMARParaBarramento();
					
				}
				
			});
						
			timeline.play();
		
	}
	
	public void copiarValorMARParaBarramento() {
		
			Integer valor = (Integer)controlador.getUcpInterna().getMar().getValor();
		
			double xDe = controlador.getUcpInterna().getMar().getTxtValor().getX();
			double yDe = controlador.getUcpInterna().getMar().getTxtValor().getY();
			double xPara = 1160;
		
			valorMar = new Text(valor.toString());
			valorMar.setX(xDe);
			valorMar.setY(yDe);
			valorMar.setFont(new Font(12));
		
			controlador.getBarramentoInterno().adicionar(valorMar);
			valorMar.toFront();
		
			timeline = new Timeline();
		
			timeline.getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(valorMar.xProperty(), xDe),
	                   new KeyValue(valorMar.yProperty(), yDe)
	               ),
	               new KeyFrame(new Duration(3000), 
	                	new KeyValue(valorMar.xProperty(), xPara),
		                new KeyValue(valorMar.yProperty(), yDe)
	               )
			);
		
			timeline.setOnFinished(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent e) {
				
					moverDadosBarramentoParaMemoria();
				
				}
			
			});

			timeline.play();
		
	}
	
	public void moverDadosBarramentoParaMemoria() {
				
		double xDeRead = read.getX();
		double yDeRead = read.getY();
		double yPara = 40;
		
		double xDeMar = valorMar.getX();
		double yDeMar = valorMar.getY();
		
		timeline = new Timeline();
		
		timeline.getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(read.xProperty(), xDeRead),
	                   new KeyValue(read.yProperty(), yDeRead),
	               	   new KeyValue(valorMar.xProperty(), xDeMar),
                       new KeyValue(valorMar.yProperty(), yDeMar)
	               ),
	               new KeyFrame(new Duration(3000), 
	                	new KeyValue(read.xProperty(), xDeRead),
		                new KeyValue(read.yProperty(), yPara),
		                new KeyValue(valorMar.xProperty(), xDeMar),
		                new KeyValue(valorMar.yProperty(), yPara)
	               )
	     );
		
		timeline.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				try {
					Thread.sleep(2000);
					controlador.getBarramentoInterno().remover(read);
					controlador.getBarramentoInterno().remover(valorMar);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				controlador.operar();
				
			}
			
		});
		
		timeline.play();
		
	}
	
}
