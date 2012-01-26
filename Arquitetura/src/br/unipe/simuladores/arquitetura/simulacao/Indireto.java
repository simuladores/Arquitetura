package br.unipe.simuladores.arquitetura.simulacao;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.text.Text;
import javafx.util.Duration;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.Instrucao;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.MAR;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.MBR;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.UC;
import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;
import br.unipe.simuladores.arquitetura.enums.OperandoCicloIndireto;
import br.unipe.simuladores.arquitetura.telas.TelaMensagemCicloIndireto;
import br.unipe.simuladores.arquitetura.telas.TelaMensagemSimulacao;

public class Indireto extends Ciclo{
	
	private OperandoCicloIndireto operando;
	private OperandoCicloIndireto operandoMens;
	private Execucao execucao;
	
	private boolean primeiroOperando;

	public Indireto(Controlador c) {
		
		super(c);
		
	}

	@Override
	public void mostrarAnimacoes() {
		
		verificarIr();
		
	}
	
	public void verificarIr() {
		
		Instrucao instrucaoAtual = controlador.getInstrucaoAtual();
		operando = operandoCicloIndireto(instrucaoAtual);
				
		Text txtValor = controlador.getUcpInterna().getIr().getTxtValor();
		
		UC uc = controlador.getUcpInterna().getUc();
		uc.atualizarValor("EXAM_IR", 959, 593);
		controlador.getUcpInterna().atualizarUnidadeTela(uc);
		
		animation = new Timeline();
		
		((Timeline)animation).getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(txtValor.opacityProperty(), 1.0f)
	               ),
	               new KeyFrame(Duration.millis(1500), 
		                   new KeyValue(txtValor.opacityProperty(), 0.0f)
		           ),
		           new KeyFrame(Duration.millis(3000), 
		                   new KeyValue(txtValor.opacityProperty(), 1.0f)
		           )
	     );
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				if (operando == OperandoCicloIndireto.NAO_HA) {
					
					alocarIr();
					
					animation = new Timeline();
					((Timeline)animation).setOnFinished(new EventHandler<ActionEvent>(){

						@Override
						public void handle(ActionEvent e) {
							
							execucao = new Execucao
									(endereco, opcode, op1, op2, controlador);
							execucao.mostrarAnimacoes();
							
						}
						
					});
					
					operandoMens = OperandoCicloIndireto.NAO_HA;
					nextStep(EstadoCiclo.TRANSFERIR_OPERANDO_MAR);
					
					
					
				} else if (operando == OperandoCicloIndireto.PRIMEIRO){
					primeiroOperando = true;
					transferirOperandoMar();
				}					
				else if (operando == OperandoCicloIndireto.SEGUNDO) {
					primeiroOperando = false;
					transferirOperandoMar();
				}
					
				else {
					primeiroOperando = true;
					transferirOperandoMar();
				}
				
			}
			
		});
		
		nextStep(EstadoCiclo.VERIFICAR_IR);
		
	}
	
	public void transferirOperandoMar() {
		
		
		if (!(!primeiroOperando && operando == OperandoCicloIndireto.OS_DOIS))
			alocarIr();
		
		Text trans = new Text();
		
		if (primeiroOperando) {
			
			trans.setText(op1.getText());
			trans.setX(op1.getX());
			trans.setY(op1.getY());
			controlador.getUcpInterna().adicionar(trans);
			controlador.adicionarElemento(trans);
			operandoMens = OperandoCicloIndireto.PRIMEIRO;
			
		} else {
	
			trans.setText(op2.getText());
			trans.setX(op2.getX());
			trans.setY(op2.getY());
			controlador.getUcpInterna().adicionar(trans);
			controlador.adicionarElemento(trans);
			operandoMens = OperandoCicloIndireto.SEGUNDO;
		
		}
		
		animacaoTransferirOperandoMar(trans, trans.getX(), trans.getY());
		
		nextStep(EstadoCiclo.TRANSFERIR_OPERANDO_MAR);
		
		
	}
	
	private void animacaoTransferirOperandoMar(final Text text, double xDe, double yDe) {
		
		final MAR mar = controlador.getUcpInterna().getMar();
		final double xPara = mar.getTxtValor().getX();
		final double yPara = mar.getTxtValor().getY();
		
		animation = new Timeline();
		
		((Timeline)animation).getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(text.xProperty(), xDe),
	                   new KeyValue(text.yProperty(), yDe)
	               ),
	               new KeyFrame(Duration.millis(3000), 
	            	   new KeyValue(text.xProperty(), xPara),
		               new KeyValue(text.yProperty(), yPara)
		           )
	     );
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				controlador.getUcpInterna().remover(text);
				mar.atualizarValor(new Integer(text.getText()), xPara, yPara);
				controlador.getUcpInterna().atualizarValorUnidadeTela(mar);
				
				/*if (operando == OperandoCicloIndireto.OS_DOIS && primeiroOperando){
					primeiroOperando = false;
					transferirOperandoMar();
					
				}*/
					
				transferirREADParaBarramento();
				
			}
			
		});
		
	}
	
	public void transferirREADParaBarramento() {
		
		
		READParaBarramento();
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				copiarValorMARParaBarramentoIndireto();
				
			}
			
		});
		
		nextStep(EstadoCiclo.COPIAR_READ_BARRAMENTO_IND);
		
		
	}
	
	public void copiarValorMARParaBarramentoIndireto() {
		
		copiarValorMARParaBarramento();
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				moverDadosBarramentoParaMemoriaIndireto();
				
			}
			
		});
		
		nextStep(EstadoCiclo.COPIAR_VALOR_MAR_BARRAMENTO_IND);
		
	}
	
	public void moverDadosBarramentoParaMemoriaIndireto() {
		
		moverDadosLeituraBarramentoMemoria();
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				transferirDadoReferenciaIndiretaPeloBarramento();
				
			}
			
		});
		
		nextStep(EstadoCiclo.MOVER_DADOS_BARRAMENTO_MEMORIA_IND);
		
	}
	
	public void transferirDadoReferenciaIndiretaPeloBarramento() {
		
		Point2D p1 = new Point2D(980, 60);
		Point2D p2 = new Point2D(1215, 60);
		Point2D p3 = new Point2D(1215, 473);
		Point2D p4 = new Point2D(926, 473);
		
		Integer endereco = new Integer(
				controlador.getUcpInterna().getMar().getTxtValor().getText());
		
		final Integer dado = controlador.getMemoriaInterna().obterDadoVariavel(endereco);
		
		final Text txtReferencia = new Text(dado.toString());
		
		transferirDadoBarramento(p1, p2, p3, p4, txtReferencia);
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				controlador.getBarramentoInterno().remover(txtReferencia);
				MBR mbr = controlador.getUcpInterna().getMbr();
				mbr.atualizarValor(dado, 923, 478);
				controlador.getUcpInterna().atualizarValorUnidadeTela(mbr);
				
				transferirMbrParaIr();
				
			}
			
		});
		
		nextStep(EstadoCiclo.TRANSFERIR_DADO_REFERENCIA_INDIRETA_BARRAMENTO);
		
	}
	
	public void transferirMbrParaIr() {
		
		MBR mbr =controlador.getUcpInterna().getMbr(); 
		double xDe = mbr.getTxtValor().getX();
		double yDe = mbr.getTxtValor().getY();
		double xPara, 
			yPara = op1.getY();
		
		if (primeiroOperando)
			xPara = op1.getX();
		else 
			xPara = op2.getX();
		
		final Text txtValor = new Text(mbr.getTxtValor().getText());
		txtValor.setX(xDe);
		txtValor.setY(yDe);
		controlador.getUcpInterna().adicionar(txtValor);
		controlador.adicionarElemento(txtValor);
		
		animation = new Timeline();
		
		((Timeline)animation).getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(txtValor.xProperty(), xDe),
	                   new KeyValue(txtValor.yProperty(), yDe)
	               ),
	               new KeyFrame(Duration.millis(3000), 
	            	   new KeyValue(txtValor.xProperty(), xPara),
		               new KeyValue(txtValor.yProperty(), yPara)
		           )
	     );
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				controlador.getUcpInterna().remover(txtValor);
				if (primeiroOperando)
					op1.setText(txtValor.getText());
				else
					op2.setText(txtValor.getText());
				
				if (primeiroOperando && operando == OperandoCicloIndireto.OS_DOIS){
					primeiroOperando = false;
					limparElementosTela();
					transferirOperandoMar();
				}
		
				
				if ((primeiroOperando && operando == OperandoCicloIndireto.PRIMEIRO)
						|| (!primeiroOperando && operando == 
						OperandoCicloIndireto.SEGUNDO) || 
						(!primeiroOperando && operando == 
						OperandoCicloIndireto.OS_DOIS)) {
					
					execucao = new Execucao(endereco, opcode, op1, op2, controlador);
						execucao.mostrarAnimacoes();
						
				}
				
			}
			
		});
		
		nextStep(EstadoCiclo.TRANSFERIR_MBR_PARA_IR);
		
	}	
	
	private OperandoCicloIndireto operandoCicloIndireto(Instrucao instrucao) {
		
		int opcode = instrucao.opcodeProperty().getValue().intValue();
		int codigo;
		
		if (opcode >= 1 && opcode <= 20)
			codigo = opcode;
		else if(opcode >= 21 && opcode <= 40)
			codigo = opcode - 20;
		else if(opcode >= 41 && opcode <= 60)
			codigo = opcode - 40;
		else if(opcode >= 61 && opcode <= 80)
			codigo = opcode - 60;
		else
			codigo = opcode - 80;
		
		switch(codigo){
		case 3: return OperandoCicloIndireto.SEGUNDO;
		case 6: return OperandoCicloIndireto.PRIMEIRO;
		case 7 : return OperandoCicloIndireto.PRIMEIRO;
		case 8 : return OperandoCicloIndireto.OS_DOIS;
		case 9 : return OperandoCicloIndireto.PRIMEIRO;
		case 10 : return OperandoCicloIndireto.PRIMEIRO;
		case 13: return OperandoCicloIndireto.SEGUNDO;
		case 18 : return OperandoCicloIndireto.SEGUNDO;
		default : return OperandoCicloIndireto.NAO_HA;
		}	
		
	}

	@Override
	protected void limparElementosTela() {
		
		controlador.getBarramentoInterno().remover(read);
		controlador.getBarramentoInterno().remover(valorMar);
		controlador.getMemoriaInterna().remover(path);
		controlador.getBarramentoInterno().remover(path2);
		controlador.getBarramentoInterno().remover(path3);
		
	}

	@Override
	protected TelaMensagemSimulacao construirTelaMensagem(EstadoCiclo estado) {
		
		if (estado ==  EstadoCiclo.TRANSFERIR_OPERANDO_MAR)
			return new TelaMensagemCicloIndireto(estado, operandoMens);
		else
			return new TelaMensagemCicloIndireto(estado);
		
	}
	
	

}
