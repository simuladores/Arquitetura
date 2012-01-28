package br.unipe.simuladores.arquitetura.simulacao;

import br.unipe.simuladores.arquitetura.componentes.internos.unidades.IR;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.Instrucao;
import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;
import br.unipe.simuladores.arquitetura.telas.TelaMensagemSimulacao;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;
import javafx.animation.Animation;
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

public abstract class Ciclo {
	
	protected Controlador controlador;
	protected Animation animation;
	protected boolean continuar = false;
	protected TelaMensagemSimulacao telaMensagem;
	protected Text valorUc;
	protected Text valorMar;
	protected Text valorMbr;
	
	protected Path path;
	protected Path path2;
	protected Path path3;
	
	protected Text endereco;
	protected Text opcode;
	protected Text op1;
	protected Text op2;
	
	public Ciclo(Controlador c) {
		
		this.controlador = c;
		
	}
	
	public abstract void mostrarAnimacoes();
	
	protected void nextStep(EstadoCiclo estado) {
		
		if (TelaPrincipal.isExibirMensagensDeSimulacao()) {
			telaMensagem = construirTelaMensagem(estado);
			telaMensagem.exibir();
			controlador.setAnimacaoAtual(animation);
			controlador.getBtnPlay().setPaused(true);
		}
		else {
			controlador.setAnimacaoAtual(animation);
			controlador.getBtnPlay().setPaused(false);
			animation.play();
		}
		
	}
	
	protected void ValorUCParaBarramento(
			double xDe, double yDe, double xPara, String valor) {
		
		controlador.getUcpInterna().getUc().atualizarValor(valor, xDe, yDe);
		controlador.getUcpInterna()
			.atualizarUnidadeTela(controlador.getUcpInterna().getUc());
	
		valorUc = new Text(valor);
		valorUc.setX(xDe);
		valorUc.setY(yDe);
		valorUc.setFont(new Font(12));
		controlador.getUcpInterna().getUc().getTxtValor().setVisible(false);
		valorUc.setVisible(false);
	
		controlador.getBarramentoInterno().adicionar(valorUc);
		controlador.adicionarElemento(valorUc);
		valorUc.toFront();
	
		animation = new Timeline();
		
		((Timeline)animation).getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(valorUc.xProperty(), xDe),
	                   new KeyValue(valorUc.visibleProperty(), true),
	                   new KeyValue(controlador.getUcpInterna().getUc()
	                		   .getTxtValor().visibleProperty(), true),
	                   new KeyValue(valorUc.yProperty(), yDe)
	               ),
	               new KeyFrame(new Duration(3000), 
	                	new KeyValue(valorUc.xProperty(), xPara),
	                	new KeyValue(valorUc.visibleProperty(), true),
	                	new KeyValue(controlador.getUcpInterna().getUc()
	                			.getTxtValor().visibleProperty(), true),
		                new KeyValue(valorUc.yProperty(), yDe)
	               )
			);
		
	}
	
	protected void copiarValorMARParaBarramento() {
		
		Integer valor = new Integer(controlador.getUcpInterna().getMar().getTxtValor().getText());
		
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
		
	}
	
	protected void moverDadosLeituraBarramentoMemoria() {
		
		double xDeRead = valorUc.getX();
		double yDeRead = valorUc.getY();
		double yPara = 40;
		
		double xDeMar = valorMar.getX();
		double yDeMar = valorMar.getY();
		
		animation = new Timeline();
		
		((Timeline)animation).getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(valorUc.xProperty(), xDeRead),
	                   new KeyValue(valorUc.yProperty(), yDeRead),
	               	   new KeyValue(valorMar.xProperty(), xDeMar),
                       new KeyValue(valorMar.yProperty(), yDeMar)
	               ),
	               new KeyFrame(new Duration(3000), 
	                	new KeyValue(valorUc.xProperty(), xDeRead),
		                new KeyValue(valorUc.yProperty(), yPara),
		                new KeyValue(valorMar.xProperty(), xDeMar),
		                new KeyValue(valorMar.yProperty(), yPara)
	               )
	     );
		
	}
	
	protected void transferirDadoBarramento(
			Point2D p1, Point2D p2, Point2D p3, Point2D p4, final Text txt) {
		
		
		controlador.getMemoriaInterna().adicionar(txt);
		txt.toBack();
		controlador.adicionarElemento(txt);
		
		path = new Path();
		path.getElements().add(new MoveTo(p1.getX(), p1.getY()));
		path.getElements().add(new LineTo(p2.getX(), p2.getY()));
		path.setStroke(Color.TRANSPARENT);
		controlador.getMemoriaInterna().adicionar(path);
		controlador.adicionarElemento(path);
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(3000));
		pathTransition.setPath(path);
		pathTransition.setNode(txt);
		pathTransition.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				controlador.getMemoriaInterna().remover(txt);
				controlador.getBarramentoInterno().adicionar(txt);
				
			}
			
		});
		
		RotateTransition rotateTransition = 
				new RotateTransition(Duration.seconds(0.1));
		rotateTransition.setFromAngle(0);
		rotateTransition.setToAngle(-90);
		rotateTransition.setNode(txt);
		
		path2 = new Path();
		path2.getElements().add(new MoveTo(p2.getX(), p2.getY()));
		path2.getElements().add(new LineTo(p3.getX(), p3.getY()));
		path2.setStroke(Color.TRANSPARENT);
		controlador.getBarramentoInterno().adicionar(path2);
		controlador.adicionarElemento(path2);
		PathTransition pathTransition2 = new PathTransition();
		pathTransition2.setDuration(Duration.millis(3000));
		pathTransition2.setPath(path2);
		pathTransition2.setNode(txt);
		
		RotateTransition rotateTransition2 = 
				new RotateTransition(Duration.seconds(0.1));
		rotateTransition2.setFromAngle(0);
		rotateTransition2.setToAngle(0);
		rotateTransition2.setNode(txt);
		
		path3 = new Path();
		path3.getElements().add(new MoveTo(p3.getX(), p3.getY()));
		path3.getElements().add(new LineTo(p4.getX(), p4.getY()));
		path3.setStroke(Color.TRANSPARENT);
		controlador.getUcpInterna().adicionar(path3);
		controlador.adicionarElemento(path3);
		PathTransition pathTransition3 = new PathTransition();
		pathTransition3.setDuration(Duration.millis(3000));
		pathTransition3.setPath(path3);
		pathTransition3.setNode(txt);
		
		animation = new SequentialTransition();
		((SequentialTransition)animation).getChildren().addAll(
				pathTransition,
				rotateTransition,
				pathTransition2,
				rotateTransition2,
				pathTransition3
				);
		
	}
	
	protected void alocarIr() {
		
		IR ir = controlador.getUcpInterna().getIr();
		double xIr = ir.getTxtValor().getX();
		double yIr = ir.getTxtValor().getY(); 
		Instrucao instrucaoAtual = controlador.getInstrucaoAtual();
		endereco.setText(instrucaoAtual.enderecoProperty().getValue().toString());
		endereco.setX(ir.getTxtValor().getX());
		endereco.setY(yIr);
		opcode.setText(instrucaoAtual.opcodeProperty().getValue().toString());
		opcode.setX(endereco.getX() + endereco.getWrappingWidth() + 16);
		opcode.setY(yIr);
		op1.setText(instrucaoAtual.referenciaOp1Property().getValue().toString());
		op1.setX(opcode.getX() + opcode.getWrappingWidth() + 16);
		op1.setY(yIr);
		op2.setText(instrucaoAtual.referenciaOp2Property().getValue().toString());
		op2.setX(op1.getX() + op1.getWrappingWidth() + 16);
		op2.setY(yIr);
		
		controlador.getUcpInterna().getIr().atualizarValor("", xIr, yIr);
		controlador.getUcpInterna().atualizarValorUnidadeTela(ir);
		
		controlador.getUcpInterna().adicionar(endereco);
		controlador.adicionarElemento(endereco);
		controlador.getUcpInterna().adicionar(opcode);
		controlador.adicionarElemento(opcode);
		controlador.getUcpInterna().adicionar(op1);
		controlador.adicionarElemento(op1);
		controlador.getUcpInterna().adicionar(op2);
		controlador.adicionarElemento(op2);
		
	}
	
	protected abstract void limparElementosTela();
	
	protected abstract TelaMensagemSimulacao construirTelaMensagem(EstadoCiclo estado);

	public Controlador getControlador() {
		return controlador;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public boolean isContinuar() {
		return continuar;
	}

	public void setContinuar(boolean continuar) {
		this.continuar = continuar;
	}
	
	

}
