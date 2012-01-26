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
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.MBR;
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
				
				transferirDadoMBRParaMemoria();
				
			}
			
		});
		
		nextStep(EstadoCiclo.TRANSFERIR_IR_MBR);
		
	}
	
	public void transferirDadoMBRParaMemoria() {
		
		Point2D p1 = new Point2D(926, 473);
		Point2D p2 = new Point2D(1215, 473);
		Point2D p3 = new Point2D(1215, 60);
		Point2D p4 = new Point2D(980, 60);
		
		MBR mbr = controlador.getUcpInterna().getMbr();
		
		Text txtDado = new Text(mbr.getTxtValor().getText());
		txtDado.setX(mbr.getTxtValor().getX());
		txtDado.setY(mbr.getTxtValor().getY());
		
		transferirDadoBarramento(p1, p2, p3, p4, txtDado);
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
								
				controlador.operar();
				
			}
			
		});
		
		nextStep(EstadoCiclo.TRANSFERIR_MBR_MEMORIA);
		
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
