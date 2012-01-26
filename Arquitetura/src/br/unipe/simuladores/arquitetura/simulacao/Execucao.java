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
import br.unipe.simuladores.arquitetura.enums.ModoEnderecamento;
import br.unipe.simuladores.arquitetura.enums.Operacao;
import br.unipe.simuladores.arquitetura.telas.TelaMensagemCicloExecucao;
import br.unipe.simuladores.arquitetura.telas.TelaMensagemSimulacao;

public class Execucao extends Ciclo {

	private Operacao operacao;
	private ModoEnderecamento modEndOp1;
	private ModoEnderecamento modEndOp2;
	private int opcode;
	
	public Execucao(Text end, Text opcode, Text op1, Text op2, Controlador c) {
		
		super(c);
		
		super.endereco = end;
		super.opcode = opcode;
		super.op1 = op1;
		super.op2 = op2;
		
	}

	@Override
	public void mostrarAnimacoes() {
		
		Instrucao instrucaoAtual = controlador.getInstrucaoAtual();
		opcode = instrucaoAtual.opcodeProperty().getValue().intValue();
		obterOperacao();
		obterModoOperandos();
		
		if (operacao == Operacao.MOV)
			animarOperacaoMovimentacao();
		else
			animarOperacaoAritmetica();
		
		
	}
	
	private void obterOperacao() {
		
		if (opcode <= 20 )
			operacao = Operacao.MOV;
		else if (opcode <= 40)
			operacao = Operacao.ADD;
		else if (opcode <= 60)
			operacao = Operacao.SUB;
		else if (opcode <= 80)
			operacao = Operacao.MUL;
		else 
			operacao = Operacao.DIV;
		
	}
	
	private void obterModoOperandos() {
		
		int codigo = 0;
		switch(operacao) {
		case MOV: codigo = opcode;break;
		case ADD: codigo = opcode - 20;break;
		case SUB: codigo = opcode - 40;break;
		case MUL: codigo = opcode - 60;break;
		case DIV: codigo = opcode - 80;break;
		}
		
		if (codigo >= 1 && codigo <= 5)
			modEndOp1 = ModoEnderecamento.DIRETO;
		else if (codigo >= 6 && codigo <= 10)
			modEndOp1 = ModoEnderecamento.INDIRETO;
		else if (codigo >= 11 && codigo <= 15)
			modEndOp1 = ModoEnderecamento.REGISTRADOR;
		else
			modEndOp1 = ModoEnderecamento.INDIRETO_REGISTRADOR;
		
		obterModoOperando2(codigo);
		
	}
	
	private void obterModoOperando2(int codigo) {
		
		int cod2 = codigo % 5;
		
		switch(cod2) {
		case 1: modEndOp2 = ModoEnderecamento.IMEDIATO;break;
		case 2: modEndOp2 = ModoEnderecamento.DIRETO;break;
		case 3: modEndOp2 = ModoEnderecamento.INDIRETO;break;
		case 4: modEndOp2 = ModoEnderecamento.REGISTRADOR;break;
		case 0: modEndOp2 = ModoEnderecamento.INDIRETO_REGISTRADOR;break;
		}
		
	}
	
	public void animarOperacaoMovimentacao() {
		
		if (modEndOp2 == ModoEnderecamento.IMEDIATO){
			
			Text txt = new Text(op2.getText());
			txt.setX(op2.getX());
			txt.setY(op2.getY());
			controlador.getUcpInterna().adicionar(txt);
			controlador.adicionarElemento(txt);
			
			if (modEndOp1 == ModoEnderecamento.DIRETO) {
				
				moverDadoParaMBR(txt);
				
			}			
			
		}
		
	}
	
	public void moverDadoParaMBR(final Text text) {
		
		double xDe = text.getX();
		double yDe = text.getY();
		final MBR mbr = controlador.getUcpInterna().getMbr();
		final double xPara = 923;
		final double yPara = 478;
		
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
				mbr.atualizarValor(text.getText(), xPara, yPara);
				controlador.getUcpInterna().atualizarUnidadeTela(mbr);
				
				Text txt = new Text(op1.getText());
				txt.setX(op1.getX());
				txt.setY(op1.getY());
				
				controlador.getUcpInterna().adicionar(txt);
				controlador.adicionarElemento(txt);
				
				moverRefenciaParaMAR(txt);
				
			}
			
		});
		
		nextStep(EstadoCiclo.TRANSFERIR_IR_MBR);
		
	}
		
	public void moverRefenciaParaMAR(final Text text) {
			
			double xDe = text.getX();
			double yDe = text.getY();
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
					mar.atualizarValor(text.getText(), xPara, yPara);
					controlador.getUcpInterna().atualizarUnidadeTela(mar);
					
					transferirReferenciaDadoParaMemoria();
					
				}
				
			});
		
		nextStep(EstadoCiclo.TRANSFERIR_IR_MAR);
		
	}
	
	public void transferirReferenciaDadoParaMemoria() {
		
		Point2D p1 = new Point2D(926, 473);
		Point2D p2 = new Point2D(1215, 473);
		Point2D p3 = new Point2D(1215, 60);
		Point2D p4 = new Point2D(980, 60);
		
		MBR mbr = controlador.getUcpInterna().getMbr();
		MAR mar = controlador.getUcpInterna().getMar();
		
		UC uc = controlador.getUcpInterna().getUc();
		uc.atualizarValor("MOV_DATA", 951, 593);
		controlador.getUcpInterna().atualizarValorUnidadeTela(uc);
		
		final Text txtDado = new Text(mbr.getTxtValor().getText());
		txtDado.setX(mbr.getTxtValor().getX());
		txtDado.setY(mbr.getTxtValor().getY());
		
		Text txtReferencia = new Text(mar.getTxtValor().getText());
		txtReferencia.setX(mar.getTxtValor().getX());
		txtReferencia.setY(mar.getTxtValor().getY());
		
		controlador.getMemoriaInterna().adicionar(txtDado);
		controlador.adicionarElemento(txtDado);
		
		controlador.getMemoriaInterna().adicionar(txtReferencia);
		controlador.adicionarElemento(txtReferencia);
		
		path = new Path();
		path.getElements().add(new MoveTo(p1.getX(), p1.getY()));
		path.getElements().add(new LineTo(p2.getX(), p2.getY()));
		path.setStroke(Color.TRANSPARENT);
		controlador.getMemoriaInterna().adicionar(path);
		controlador.adicionarElemento(path);
		PathTransition pathTransition = new PathTransition();
		pathTransition.setDuration(Duration.millis(3000));
		pathTransition.setPath(path);
		pathTransition.setNode(txtDado);
		pathTransition.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				controlador.getUcpInterna().remover(txtDado);
				controlador.getBarramentoInterno().adicionar(txtDado);
				
			}
			
		});
		
		RotateTransition rotateTransition = 
				new RotateTransition(Duration.seconds(0.1));
		rotateTransition.setFromAngle(0);
		rotateTransition.setToAngle(-90);
		rotateTransition.setNode(txtDado);
		
		path2 = new Path();
		path2.getElements().add(new MoveTo(p2.getX(), p2.getY()));
		path2.getElements().add(new LineTo(p3.getX(), p3.getY()));
		path2.setStroke(Color.TRANSPARENT);
		controlador.getBarramentoInterno().adicionar(path2);
		controlador.adicionarElemento(path2);
		PathTransition pathTransition2 = new PathTransition();
		pathTransition2.setDuration(Duration.millis(3000));
		pathTransition2.setPath(path2);
		pathTransition2.setNode(txtDado);
		pathTransition2.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				controlador.getBarramentoInterno().remover(txtDado);
				controlador.getMemoriaInterna().adicionar(txtDado);
				txtDado.toBack();
				controlador.adicionarElemento(txtDado);
				
			}
			
		});
		
		RotateTransition rotateTransition2 = 
				new RotateTransition(Duration.seconds(0.1));
		rotateTransition2.setFromAngle(0);
		rotateTransition2.setToAngle(0);
		rotateTransition2.setNode(txtDado);
		
		path3 = new Path();
		path3.getElements().add(new MoveTo(p3.getX(), p3.getY()));
		path3.getElements().add(new LineTo(p4.getX(), p4.getY()));
		path3.setStroke(Color.TRANSPARENT);
		controlador.getUcpInterna().adicionar(path3);
		controlador.adicionarElemento(path3);
		PathTransition pathTransition3 = new PathTransition();
		pathTransition3.setDuration(Duration.millis(3000));
		pathTransition3.setPath(path3);
		pathTransition3.setNode(txtDado);
		
		SequentialTransition sequencialTransition = new SequentialTransition();
		((SequentialTransition)animation).getChildren().addAll(
				pathTransition,
				rotateTransition,
				pathTransition2,
				rotateTransition2,
				pathTransition3
				);
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
								
				controlador.operar();
				
			}
			
		});
		
		nextStep(EstadoCiclo.TRANSFERIR_MAR_MBR_MEMORIA);
		
	}
	
	public void animarOperacaoAritmetica() {
		
	}

	@Override
	protected void limparElementosTela() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected TelaMensagemSimulacao construirTelaMensagem(EstadoCiclo estado) {

		return new TelaMensagemCicloExecucao(estado);
		
	}
}
