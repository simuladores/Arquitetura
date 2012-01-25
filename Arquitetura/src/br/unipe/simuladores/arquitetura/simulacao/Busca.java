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
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.UC;
import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;
import br.unipe.simuladores.arquitetura.telas.TelaMensagemCicloBusca;
import br.unipe.simuladores.arquitetura.telas.TelaMensagemSimulacao;
import br.unipe.simuladores.arquitetura.telas.TelaSimplesMensagem;

public class Busca extends Ciclo{
	
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
			
			READParaBarramento();
		
			animation.setOnFinished(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent arg0) {
					
					copiarValorMARParaBarramentoBusca();
					
				}
				
			});
						
			nextStep(EstadoCiclo.COPIAR_READ_BARRAMENTO);
		
	}
	
	public void copiarValorMARParaBarramentoBusca() {
		
			copiarValorMARParaBarramento();
		
			animation.setOnFinished(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent e) {
				
					moverDadosBarramentoParaMemoriaBusca();
				
				}
			
			});

			nextStep(EstadoCiclo.COPIAR_VALOR_MAR_BARRAMENTO);
		
	}
	
	public void moverDadosBarramentoParaMemoriaBusca() {
				
		moverDadosLeituraBarramentoMemoria();
		
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
		Point2D p4 = new Point2D(950, 473);
		
		Instrucao instrucao = controlador.getInstrucaoAtual();
		String inst = instrucao.enderecoProperty().getValue().toString() + "   ";
		inst += instrucao.opcodeProperty().getValue().toString() + "   ";
		inst += instrucao.referenciaOp1Property().getValue().toString() + "   ";
		inst += instrucao.referenciaOp2Property().getValue().toString();
		
		txtInstrucao = new Text(inst);
		
		transferirDadoBarramento(p1, p2, p3, p4, txtInstrucao);
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				atualizarPCProximaInstrucao();
				
			}
			
		});
		
		nextStep(EstadoCiclo.TRANSFERIR_INSTRUCAO);
		
	}
	
	public void atualizarPCProximaInstrucao() {
		
		final Text endProxTxt;
		
		animation = new Timeline();
		
		if (controlador.haProximaInstrucao()) {
			
			final double x = controlador.getUcpInterna().getPc().getTxtValor().getX();
			final double y = controlador.getUcpInterna().getPc().getTxtValor().getY();
			
			final Integer endProx = controlador.obterEnderecoProximaInstrucao();
			endProxTxt = new Text(endProx.toString());
			endProxTxt.setX(x);
			endProxTxt.setY(y);
			
			UC uc = controlador.getUcpInterna().getUc();
			uc.atualizarValor("ATUAL_PC", 955, 593);
			controlador.getUcpInterna().atualizarUnidadeTela(uc);
			
			controlador.getUcpInterna().adicionar(endProxTxt);
			endProxTxt.setVisible(false);
			controlador.adicionarElemento(endProxTxt);
			
			((Timeline)animation).getKeyFrames().addAll(
		               new KeyFrame(Duration.ZERO, 
		                   new KeyValue(endProxTxt.opacityProperty(), 0.0f),
		                   new KeyValue(endProxTxt.visibleProperty(), true),
		                   new KeyValue(controlador.getUcpInterna().getPc()
		                		   .getTxtValor().opacityProperty(), 1.0f)
		               ),
		               new KeyFrame(new Duration(3000), 
		            		   new KeyValue(endProxTxt.opacityProperty(), 1.0f),
		            		   new KeyValue(endProxTxt.visibleProperty(), true),
			                   new KeyValue(controlador.getUcpInterna().getPc()
			                		   .getTxtValor().opacityProperty(), 0.0f)
		               )
		     );
			
			animation.setOnFinished(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent e) {
					
					controlador.getUcpInterna().remover(endProxTxt);
					controlador.getUcpInterna().getPc().atualizarValor(endProx, x, y);
					controlador.getUcpInterna().atualizarValorUnidadeTela(
							controlador.getUcpInterna().getPc());
					controlador.getUcpInterna().getPc().getTxtValor().opacityProperty().setValue(1.0f);
					controlador.getUcpInterna().getPc().getTxtValor().visibleProperty().setValue(true);

					copiarMBRParaIR();
					
				}
				
			});
			
			nextStep(EstadoCiclo.ATUALIZAR_PC_PROX_INSTRUCAO);
			
		} else {
			
			nextStep(EstadoCiclo.NAO_HA_PROX_INSTRUCAO);
			
			copiarMBRParaIR();
			
		}
		
		
		
	}
	
	public void copiarMBRParaIR() {
			
		double xMbr = controlador.getUcpInterna().getMbr().getTxtValor().getX();
		double yMbr = controlador.getUcpInterna().getMbr().getTxtValor().getY();
		final double xIr = controlador.getUcpInterna().getIr().getTxtValor().getX();
		final double yIr = controlador.getUcpInterna().getIr().getTxtValor().getY();
		
		String txtInst = txtInstrucao.getText();
		
		controlador.getUcpInterna().getMbr().atualizarValor(
				txtInst, xMbr, yMbr);
		controlador.getUcpInterna().atualizarValorUnidadeTela(
				controlador.getUcpInterna().getMbr());
		controlador.adicionarElemento(
				controlador.getUcpInterna().getMbr().getTxtValor());
		
		controlador.getBarramentoInterno().remover(txtInstrucao);
		txtInstrucao = new Text(txtInst);
		txtInstrucao.setX(xMbr);
		txtInstrucao.setY(yMbr);
		controlador.getUcpInterna().adicionar(txtInstrucao);
		controlador.adicionarElemento(txtInstrucao);
		
		controlador.getUcpInterna().getPc().getTxtValor().opacityProperty().setValue(1.0f);

		animation = new Timeline();
		
		((Timeline)animation).getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(txtInstrucao.xProperty(), xMbr),
	                   new KeyValue(txtInstrucao.yProperty(), yMbr)
	               ),
	               new KeyFrame(new Duration(3000), 
	            		   new KeyValue(txtInstrucao.xProperty(), xIr),
		                   new KeyValue(txtInstrucao.yProperty(), yIr)

	               )
	     );
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				controlador.getUcpInterna().getIr().atualizarValor(
						txtInstrucao.getText(), xIr, yIr);
				controlador.getUcpInterna().atualizarValorUnidadeTela(
						controlador.getUcpInterna().getIr());
				controlador.adicionarElemento(
						controlador.getUcpInterna().getIr().getTxtValor());
				controlador.getUcpInterna().remover(txtInstrucao);
				
				controlador.getUcpInterna().getPc().getTxtValor().opacityProperty().setValue(1.0f);
				
				limparElementosTela();
				
				Indireto indireto = new Indireto(controlador);
				indireto.mostrarAnimacoes();
				
			}
			
		});
		
		nextStep(EstadoCiclo.COPIAR_MBR_PARA_IR);
		
	}


	@Override
	protected void limparElementosTela() {
		
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
		
	}


	@Override
	protected TelaMensagemSimulacao construirTelaMensagem(EstadoCiclo estado) {
		
		return new TelaMensagemCicloBusca(estado);
		
	}
	
	
}
