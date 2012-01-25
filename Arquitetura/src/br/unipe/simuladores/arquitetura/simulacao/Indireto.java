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
	
	private Text endereco;
	private Text opcode;
	private Text op1;
	private Text op2;
	private boolean primeiroOperando;
	
	private Path path;
	private Path path2;
	private Path path3;

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
					
					controlador.operar();
					animation = new Timeline();
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
		
		double yIr = controlador.getUcpInterna().getIr().getTxtValor().getY(); 
		Instrucao instrucaoAtual = controlador.getInstrucaoAtual();
		endereco = new Text(instrucaoAtual.enderecoProperty().getValue().toString());
		endereco.setX(controlador.getUcpInterna().getIr().getTxtValor().getX());
		endereco.setY(yIr);
		opcode = new Text(instrucaoAtual.opcodeProperty().getValue().toString());
		opcode.setX(endereco.getX() + endereco.getWrappingWidth() + 16);
		opcode.setY(yIr);
		op1 = new Text(instrucaoAtual.referenciaOp1Property().getValue().toString());
		op1.setX(opcode.getX() + opcode.getWrappingWidth() + 16);
		op1.setY(yIr);
		op2 = new Text(instrucaoAtual.referenciaOp2Property().getValue().toString());
		op2.setX(op1.getX() + op1.getWrappingWidth() + 16);
		op2.setY(yIr);
		
		if (primeiroOperando) {
			
			controlador.getUcpInterna().adicionar(op1);
			controlador.adicionarElemento(op1);
			animacaoTransferirOperandoMar(op1, op1.getX(), op1.getY());
			operandoMens = OperandoCicloIndireto.PRIMEIRO;
			
		} else {
	
			controlador.getUcpInterna().adicionar(op2);
			controlador.adicionarElemento(op2);
			animacaoTransferirOperandoMar(op2, op2.getX(), op2.getY());
			operandoMens = OperandoCicloIndireto.SEGUNDO;
		
		}
		
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
			public void handle(ActionEvent arg0) {
				
				controlador.getBarramentoInterno().remover(txtReferencia);
				MBR mbr = controlador.getUcpInterna().getMbr();
				mbr.atualizarValor(dado, 923, 478);
				controlador.getUcpInterna().atualizarValorUnidadeTela(mbr);
				
				controlador.operar();
				
			}
			
		});
		
		nextStep(EstadoCiclo.TRANSFERIR_DADO_REFERENCIA_INDIRETA_BARRAMENTO);
		
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
		
		try {
			Thread.sleep(2000);
			controlador.getBarramentoInterno().remover(read);
			controlador.getBarramentoInterno().remover(valorMar);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	protected TelaMensagemSimulacao construirTelaMensagem(EstadoCiclo estado) {
		
		if (estado ==  EstadoCiclo.TRANSFERIR_OPERANDO_MAR)
			return new TelaMensagemCicloIndireto(estado, operandoMens);
		else
			return new TelaMensagemCicloIndireto(estado);
		
	}

}
