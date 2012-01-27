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
	
	private Text txtDado;
	private Text txtReferencia;
	
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
					
					copiarValorMarParaBarramentoExecucao();
					
				}
				
			});
		
		nextStep(EstadoCiclo.TRANSFERIR_IR_MAR);
		
	}
	
	public void copiarValorMarParaBarramentoExecucao() {
		
		copiarValorMARParaBarramento();
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
			
				copiarValorMbrBarramento();
			
			}
		
		});
		
		nextStep(EstadoCiclo.COPIAR_VALOR_MAR_BARRAMENTO_EXECUCAO);
		
	}
	
	public void copiarValorMbrBarramento() {
		
		Integer valor = new Integer(controlador.getUcpInterna().getMbr().getTxtValor().getText());
		
		double xDe = controlador.getUcpInterna().getMbr().getTxtValor().getX();
		double yDe = controlador.getUcpInterna().getMbr().getTxtValor().getY();
		double xPara = 1210;
	
		valorMbr = new Text(valor.toString());
		valorMbr.setX(xDe);
		valorMbr.setY(yDe);
	
		controlador.getBarramentoInterno().adicionar(valorMbr);
		controlador.adicionarElemento(valorMbr);
		valorMbr.toFront();
	
		animation = new Timeline();
	
		((Timeline)animation).getKeyFrames().addAll(
               new KeyFrame(Duration.ZERO, 
                   new KeyValue(valorMbr.xProperty(), xDe),
                   new KeyValue(valorMbr.yProperty(), yDe)
               ),
               new KeyFrame(new Duration(3000), 
                	new KeyValue(valorMbr.xProperty(), xPara),
	                new KeyValue(valorMbr.yProperty(), yDe)
               )
		);
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
			
				copiarWRITEParaBarramento();
			
			}
		
		});
		
		nextStep(EstadoCiclo.COPIAR_VALOR_MBR_BARRAMENTO_EXECUCAO);
		
	}
	
	
	public void copiarWRITEParaBarramento() {
		
		ValorUCParaBarramento(965, 593, 1098, "WRITE");
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				moverDadosEscritaParaMemoria();
				
			}
			
		});
		
		nextStep(EstadoCiclo.COPIAR_WRITE_BARRAMENTO);
		
	}
	
	public void moverDadosEscritaParaMemoria() {
		
		double xWrite = valorUc.getX();
		double yDeWrite = valorUc.getY();
		double xEnd = valorMar.getX();
		double yDeEnd = valorMar.getY();
		double xDado = valorMbr.getX();
		double yDeDado = valorMbr.getY();
		
		double yPara = 40;
		
		animation = new Timeline();
		
		((Timeline)animation).getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(valorUc.xProperty(), xWrite),
	                   new KeyValue(valorUc.yProperty(), yDeWrite),
	                   new KeyValue(valorMar.xProperty(), xEnd),
	                   new KeyValue(valorMar.yProperty(), yDeEnd),
	                   new KeyValue(valorMbr.xProperty(), xDado),
	                   new KeyValue(valorMbr.yProperty(), yDeDado)
	               ),
	               new KeyFrame(new Duration(3000), 
	            	   new KeyValue(valorUc.xProperty(), xWrite),
		               new KeyValue(valorUc.yProperty(), yPara),
		               new KeyValue(valorMar.xProperty(), xEnd),
		               new KeyValue(valorMar.yProperty(), yPara),
		               new KeyValue(valorMbr.xProperty(), xDado),
		               new KeyValue(valorMbr.yProperty(), yPara)
	               )
			);
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				controlador.operar();
				
			}
			
		});
		
		nextStep(EstadoCiclo.MOVER_DADOS_BARRAMENTO_MEMORIA_ESCRITA);
		
	}
	
	
	/*public void transferirReferenciaDadoParaMemoria() {
		
		Integer valor = (Integer)controlador.getUcpInterna().getMar().getValor();
		
		double xDeMar = controlador.getUcpInterna().getMar().getTxtValor().getX();
		double yDeMar = controlador.getUcpInterna().getMar().getTxtValor().getY();
		double xParaMar = 1160;
		double xDeRead = controlador.getUcpInterna().getUc().getTxtValor().getX();
		double yDeRead = controlador.getUcpInterna().getUc()
	
		valorMar = new Text(valor.toString());
		valorMar.setX(xDeMar);
		valorMar.setY(yDeMar);
	
		controlador.getBarramentoInterno().adicionar(valorMar);
		controlador.adicionarElemento(valorMar);
		valorMar.toFront();
	
		Timeline timeline = new Timeline();
	
		timeline.getKeyFrames().addAll(
               new KeyFrame(Duration.ZERO, 
                   new KeyValue(valorMar.xProperty(), xDeMar),
                   new KeyValue(valorMar.yProperty(), yDeMar)
               ),
               new KeyFrame(new Duration(3000), 
                	new KeyValue(valorMar.xProperty(), xParaMar),
	                new KeyValue(valorMar.yProperty(), yDeMar)
               )
		);
		
		
		
		
		nextStep(EstadoCiclo.TRANSFERIR_MAR_MBR_MEMORIA);
		
	}*/
	
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
