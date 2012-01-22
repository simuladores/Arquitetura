package br.unipe.simuladores.arquitetura.simulacao;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.Instrucao;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.PC;
import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;

public class Busca extends Ciclo{
	
	private Text read;
	private Text valorMar;
	private Text endInst;
	private Text opcodeInst;
	private Text op1;
	private Text op2;

	public Busca(Controlador c) {
		
		super(c);
		
	}
	
	
	@Override
	public void mostrarAnimacoes() {
		
	    atualizarPC();
		
	}
	
	public void atualizarPC() {

			final PC pc = controlador.getUcpInterna().getPc();
		
			pc.atualizarValor(controlador.getInstrucaoAtual().enderecoProperty().getValue(), 880, 438);
			controlador.getUcpInterna().atualizarValorUnidadeTela(controlador.getUcpInterna().getPc());
			
			controlador.getUcpInterna().getPc().getTxtValor().setVisible(false);
			
			timeline = new Timeline();
			
			timeline.getKeyFrames().addAll(
					new KeyFrame(Duration.ZERO,
							new KeyValue(controlador.getUcpInterna().getPc()
									.getTxtValor().visibleProperty(), true),
							new KeyValue(controlador.getUcpInterna().getPc()
									.getTxtValor().opacityProperty(), 0.0f)
				    ),
	                new KeyFrame(new Duration(3000),
	                		new KeyValue(controlador.getUcpInterna().getPc()
									.getTxtValor().visibleProperty(), true),
	                		new KeyValue(controlador.getUcpInterna().getPc()
									.getTxtValor().opacityProperty(), 1.0f)
	                )
			);
			
			timeline.setOnFinished(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent arg0) {
				
					moverEnderecoPCParaMAR();
				
				}
			
			});
		
			nextStep(EstadoCiclo.ATUALIZAR_PC);

		
	}
	
	public void moverEnderecoPCParaMAR() {
				
			double xDe = controlador.getUcpInterna().getPc().getTxtValor().getX();
			double yDe = controlador.getUcpInterna().getPc().getTxtValor().getY();
		
			final Integer valor = (Integer)controlador.getUcpInterna().getPc().getValor();
		
			final Text valorTxt = new Text();
			valorTxt.setText(valor.toString());
			valorTxt.setX(xDe);
			valorTxt.setY(yDe);
			valorTxt.setFont(new Font(12));
				
			controlador.getUcpInterna().remover(valorTxt);
			controlador.getUcpInterna().adicionar(valorTxt);
			controlador.adicionarElemento(valorTxt);
			valorTxt.toFront();
					
			timeline = new Timeline();
			
			timeline.getKeyFrames().addAll(
					new KeyFrame(Duration.ZERO, 
							new KeyValue(valorTxt.xProperty(), xDe),
							new KeyValue(valorTxt.yProperty(), yDe)
				    ),
	                new KeyFrame(new Duration(3000), 
	                		new KeyValue(valorTxt.xProperty(), 975),
	                		new KeyValue(valorTxt.yProperty(), 438)
	                )
			);
		
			timeline.setOnFinished(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent arg0) {
				
					controlador.getUcpInterna().remover(valorTxt);
					controlador.getUcpInterna().getMar().atualizarValor(valor, 975, 438);
					controlador.getUcpInterna().atualizarValorUnidadeTela(controlador.getUcpInterna().getMar());	
				
					copiarREADParaBarramento();
				
				}
			
			});
		
			nextStep(EstadoCiclo.MOVER_MAR);
			
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
			controlador.getUcpInterna().getUc().getTxtValor().setVisible(false);
			read.setVisible(false);
		
			controlador.getBarramentoInterno().adicionar(read);
			controlador.adicionarElemento(read);
			read.toFront();
		
			timeline = new Timeline();
			
				timeline.getKeyFrames().addAll(
		               new KeyFrame(Duration.ZERO, 
		                   new KeyValue(read.xProperty(), xDe),
		                   new KeyValue(read.visibleProperty(), true),
		                   new KeyValue(controlador.getUcpInterna().getUc()
		                		   .getTxtValor().visibleProperty(), true),
		                   new KeyValue(read.yProperty(), yDe)
		               ),
		               new KeyFrame(new Duration(3000), 
		                	new KeyValue(read.xProperty(), xPara),
		                	new KeyValue(read.visibleProperty(), true),
		                	new KeyValue(controlador.getUcpInterna().getUc()
		                			.getTxtValor().visibleProperty(), true),
			                new KeyValue(read.yProperty(), yDe)
		               )
				);
			
				timeline.setOnFinished(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent arg0) {
					
					copiarValorMARParaBarramento();
					
				}
				
			});
						
			nextStep(EstadoCiclo.COPIAR_READ_BARRAMENTO);
		
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
			controlador.adicionarElemento(valorMar);
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

			nextStep(EstadoCiclo.COPIAR_VALOR_MAR_BARRAMENTO);
		
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
				
				fornecerInstrucao();
				
			}
			
		});
		
		nextStep(EstadoCiclo.MOVER_DADOS_BARRAMENTO_MEMORIA);
		
	}
	
	public void fornecerInstrucao() {
		
		Instrucao instrucao = controlador.getInstrucaoAtual();
		double xEnd = 1015, y = 70, xOpcode = 995, xOp1 = 975, xOp2 = 955;
		
		endInst = new Text(instrucao.enderecoProperty().getValue().toString());
		endInst.setX(xEnd);
		endInst.setY(y);
		controlador.getMemoriaInterna().adicionar(endInst);
		controlador.adicionarElemento(endInst);
		endInst.toBack();
		
		opcodeInst = new Text(instrucao.opcodeProperty().getValue().toString());
		opcodeInst.setX(xOpcode);
		opcodeInst.setY(y);
		controlador.getMemoriaInterna().adicionar(opcodeInst);
		controlador.adicionarElemento(opcodeInst);
		opcodeInst.toBack();
		
		op1 = new Text(instrucao.referenciaOp1Property().getValue().toString());
		op1.setX(xOp1);
		op1.setY(y);
		controlador.getMemoriaInterna().adicionar(op1);
		controlador.adicionarElemento(op1);
		op1.toBack();
		
		op2 = new Text(instrucao.referenciaOp2Property().getValue().toString());
		op2.setX(xOp2);
		op2.setY(y);
		controlador.getMemoriaInterna().adicionar(op2);
		controlador.adicionarElemento(op2);
		op2.toBack();
		
		timeline = new Timeline();
		
		timeline.getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(endInst.xProperty(), xEnd),
	                   new KeyValue(endInst.yProperty(), y),
	               	   new KeyValue(opcodeInst.xProperty(), xOpcode),
	               	   new KeyValue(opcodeInst.yProperty(), y),
	               	   new KeyValue(op1.xProperty(), xOp1),
	                   new KeyValue(op1.yProperty(), y),
	               	   new KeyValue(op2.xProperty(), xOp2),
	               	   new KeyValue(op2.yProperty(), y)
	               	   
	               ),
	               new KeyFrame(new Duration(3000), 
	            	   new KeyValue(endInst.xProperty(), 1210),
		               new KeyValue(endInst.yProperty(), y),
		               new KeyValue(opcodeInst.xProperty(), 1190),
		               new KeyValue(opcodeInst.yProperty(), y),
		               new KeyValue(op1.xProperty(), 1170),
		               new KeyValue(op1.yProperty(), y),
		               new KeyValue(op2.xProperty(), 1150),
		               new KeyValue(op2.yProperty(), y)
	               )
	     );
		
		timeline.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				try {
					Thread.sleep(2000);
					controlador.getBarramentoInterno().remover(read);
					controlador.getBarramentoInterno().remover(valorMar);
					controlador.getMemoriaInterna().remover(endInst);
					controlador.getMemoriaInterna().remover(opcodeInst);
					controlador.getMemoriaInterna().remover(op1);
					controlador.getMemoriaInterna().remover(op2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				controlador.operar();
				
			}
			
		});
		
		nextStep(EstadoCiclo.FORNECER_INSTRUCAO);
		
	}
	
	
}
