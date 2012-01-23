package br.unipe.simuladores.arquitetura.simulacao;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.Instrucao;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.PC;
import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;

public class Busca extends Ciclo{
	
	private Text read;
	private Text valorMar;
	private Text txtInstrucao;

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
			
			animation = new Timeline();
			
			((Timeline)animation).getKeyFrames().addAll(
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
			
			animation.setOnFinished(new EventHandler<ActionEvent>(){

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
					
			animation = new Timeline();
			
			((Timeline)animation).getKeyFrames().addAll(
					new KeyFrame(Duration.ZERO, 
							new KeyValue(valorTxt.xProperty(), xDe),
							new KeyValue(valorTxt.yProperty(), yDe)
				    ),
	                new KeyFrame(new Duration(3000), 
	                		new KeyValue(valorTxt.xProperty(), 975),
	                		new KeyValue(valorTxt.yProperty(), 438)
	                )
			);
		
			animation.setOnFinished(new EventHandler<ActionEvent>(){

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
		
			animation = new Timeline();
			
			((Timeline)animation).getKeyFrames().addAll(
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
			
			animation.setOnFinished(new EventHandler<ActionEvent>(){

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
		
			animation = new Timeline();
		
			((Timeline)animation).getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(valorMar.xProperty(), xDe),
	                   new KeyValue(valorMar.yProperty(), yDe)
	               ),
	               new KeyFrame(new Duration(3000), 
	                	new KeyValue(valorMar.xProperty(), xPara),
		                new KeyValue(valorMar.yProperty(), yDe)
	               )
			);
		
			animation.setOnFinished(new EventHandler<ActionEvent>(){

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
		
		animation = new Timeline();
		
		((Timeline)animation).getKeyFrames().addAll(
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
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				transferirInstrucao();
				
			}
			
		});
		
		nextStep(EstadoCiclo.MOVER_DADOS_BARRAMENTO_MEMORIA);
		
	}
	
	public void transferirInstrucao() {
		
		Point2D p1 = new Point2D(980, 60);
		Point2D p2 = new Point2D(1215, 60);
		Point2D p3 = new Point2D(1215, 473);
		Point2D p4 = new Point2D(967, 473);
		
		Instrucao instrucao = controlador.getInstrucaoAtual();
		String inst = instrucao.enderecoProperty().getValue().toString() + "   ";
		inst += instrucao.opcodeProperty().getValue().toString() + "   ";
		inst += instrucao.referenciaOp1Property().getValue().toString() + "   ";
		inst += instrucao.referenciaOp2Property().getValue().toString();
		
		txtInstrucao = new Text(inst);
		
		controlador.getMemoriaInterna().adicionar(txtInstrucao);
		txtInstrucao.toBack();
		controlador.adicionarElemento(txtInstrucao);
		
		final Path path = new Path();
		path.getElements().add(new MoveTo(p1.getX(), p1.getY()));
		path.getElements().add(new LineTo(p2.getX(), p2.getY()));
		path.setStroke(Color.TRANSPARENT);
		controlador.getMemoriaInterna().adicionar(path);
		controlador.adicionarElemento(path);
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(3000));
		pathTransition.setPath(path);
		pathTransition.setNode(txtInstrucao);
		pathTransition.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				controlador.getMemoriaInterna().remover(txtInstrucao);
				controlador.getBarramentoInterno().adicionar(txtInstrucao);
				
			}
			
		});
		
		RotateTransition rotateTransition = 
				new RotateTransition(Duration.seconds(0.1));
		rotateTransition.setFromAngle(0);
		rotateTransition.setToAngle(-90);
		rotateTransition.setNode(txtInstrucao);
		
		final Path path2 = new Path();
		path2.getElements().add(new MoveTo(p2.getX(), p2.getY()));
		path2.getElements().add(new LineTo(p3.getX(), p3.getY()));
		path2.setStroke(Color.TRANSPARENT);
		controlador.getBarramentoInterno().adicionar(path2);
		controlador.adicionarElemento(path2);
		PathTransition pathTransition2 = new PathTransition();
		pathTransition2.setDuration(Duration.millis(3000));
		pathTransition2.setPath(path2);
		pathTransition2.setNode(txtInstrucao);
		
		RotateTransition rotateTransition2 = 
				new RotateTransition(Duration.seconds(0.1));
		rotateTransition2.setFromAngle(0);
		rotateTransition2.setToAngle(0);
		rotateTransition2.setNode(txtInstrucao);
		
		final Path path3 = new Path();
		path3.getElements().add(new MoveTo(p3.getX(), p3.getY()));
		path3.getElements().add(new LineTo(p4.getX(), p4.getY()));
		path3.setStroke(Color.TRANSPARENT);
		controlador.getUcpInterna().adicionar(path3);
		controlador.adicionarElemento(path3);
		PathTransition pathTransition3 = new PathTransition();
		pathTransition3.setDuration(Duration.millis(3000));
		pathTransition3.setPath(path3);
		pathTransition3.setNode(txtInstrucao);
		
		animation = new SequentialTransition();
		((SequentialTransition)animation).getChildren().addAll(
				pathTransition,
				rotateTransition,
				pathTransition2,
				rotateTransition2,
				pathTransition3
				);
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				try {
					Thread.sleep(2000);
					controlador.getBarramentoInterno().remover(read);
					controlador.getBarramentoInterno().remover(valorMar);
					controlador.getBarramentoInterno().remover(txtInstrucao);
					controlador.getMemoriaInterna().remover(path);
					controlador.getBarramentoInterno().remover(path2);
					controlador.getBarramentoInterno().remover(path3);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				controlador.operar();
				
			}
			
		});
		
		nextStep(EstadoCiclo.TRANSFERIR_INSTRUCAO);
		
	}
	
	
}
