package br.unipe.simuladores.arquitetura.simulacao;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.util.Duration;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.Instrucao;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.MAR;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.MBR;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.Registrador;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.Variavel;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.VariavelIdentificador;
import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;
import br.unipe.simuladores.arquitetura.enums.ModoEnderecamento;
import br.unipe.simuladores.arquitetura.enums.Operacao;
import br.unipe.simuladores.arquitetura.telas.TelaMensagemCicloExecucao;
import br.unipe.simuladores.arquitetura.telas.TelaMensagemSimulacao;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;

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
			
			if (modEndOp1 == ModoEnderecamento.DIRETO || 
					modEndOp1 == ModoEnderecamento.INDIRETO) 
				
				moverDadoParaMBR(txt);
				
		    else if (modEndOp1 == ModoEnderecamento.REGISTRADOR)
				
				moverDadoParaRegistrador(txt);
			
			else if (modEndOp1 == ModoEnderecamento.INDIRETO_REGISTRADOR)
				
				moverRefenciaIndiretaRegistradorParaIr(txt);
			
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
				
				moverDadoParaMemoria();
				
			}
			
		});
		
		nextStep(EstadoCiclo.MOVER_DADOS_BARRAMENTO_MEMORIA_ESCRITA);
		
	}
	
	public void moverDadoParaMemoria() {
		
		double xDe = valorMbr.getX();
		double yDe = valorMbr.getY();
		double xPara = 1010;
		double yPara = 70;
		
		controlador.getBarramentoInterno().remover(valorMbr);
		controlador.getMemoriaInterna().adicionar(valorMbr);
		valorMbr.toBack();
		
		animation = new Timeline();
		
		((Timeline)animation).getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(valorMbr.xProperty(), xDe),
	                   new KeyValue(valorMbr.yProperty(), yDe)
	               ),
	               new KeyFrame(new Duration(3000), 
	                	new KeyValue(valorMbr.xProperty(), xPara),
		                new KeyValue(valorMbr.yProperty(), yPara)
	               )
			);
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				Variavel variavel = controlador.getMemoriaInterna()
						.obterVariavel(new Integer(valorMar.getText()));
				variavel.dataProperty().setValue(valorMbr.getText());
				controlador.getMemoriaInterna().atualizarValoresVariaveis();
				
				ObservableList<VariavelIdentificador> variaveis 
					= TelaPrincipal.getTabVariaveis().getItems();
				
				for (VariavelIdentificador var : variaveis) {
					
					if (var.enderecoProperty().getValue().equals(valorMar.getText()))
						var.dataProperty().setValue(valorMbr.getText());
					
				}
				
				TelaPrincipal.getTabVariaveis().setItems(variaveis);
				
				limparElementosTela();
				
				controlador.operar();
				
				
			}
			
		});
		
		nextStep(EstadoCiclo.MOVER_DADO_MEMORIA);
		
	}
	
	public void moverDadoParaRegistrador(final Text text) {
		
		double xDe = text.getX();
		double yDe = text.getY();
		
		final Registrador registrador = controlador.getUcpInterna()
				.obterRegistrador(new Integer(op1.getText())); 
		
		final double xPara = registrador.getTxtValor().getX();
		final double yPara = registrador.getTxtValor().getY();
		
		controlador.getUcpInterna().getUc().atualizarValor("WRITE", 965, 593);
		controlador.getUcpInterna()
			.atualizarUnidadeTela(controlador.getUcpInterna().getUc());
		
		animation = new Timeline();
		
		((Timeline)animation).getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(text.xProperty(), xDe),
	                   new KeyValue(text.yProperty(), yDe)
	               ),
	               new KeyFrame(new Duration(3000), 
	                	new KeyValue(text.xProperty(), xPara),
		                new KeyValue(text.yProperty(), yPara)
	               )
			);
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				controlador.getUcpInterna().remover(text);
				registrador.atualizarValor(text.getText(), xPara, yPara);
				controlador.getUcpInterna().atualizarValorUnidadeTela(registrador);
				
				controlador.operar();
				
			}
			
		});
		
		nextStep(EstadoCiclo.MOVER_DADO_REGISTRADOR);
		
	}
	
	public void moverRefenciaIndiretaRegistradorParaIr(final Text text) {
		
		final Registrador registrador = controlador.getUcpInterna().
				obterRegistrador(new Integer(op1.getText()));
		
		double xDe = registrador.getTxtValor().getX();
		double yDe = registrador.getTxtValor().getY();
		final double xPara = op1.getX();
		final double yPara = op1.getY();
		
		final Text txt = new Text(registrador.getTxtValor().getText());
		txt.setX(xDe);
		txt.setY(yDe);
		controlador.getUcpInterna().adicionar(txt);
		controlador.adicionarElemento(txt);
		
		animation = new Timeline();
		
		((Timeline)animation).getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(txt.xProperty(), xDe),
	                   new KeyValue(txt.yProperty(), yDe)
	               ),
	               new KeyFrame(new Duration(3000), 
	                	new KeyValue(txt.xProperty(), xPara),
		                new KeyValue(txt.yProperty(), yPara)
	               )
			);
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				controlador.getUcpInterna().remover(txt);
				op1.setText(txt.getText());
				
				moverDadoParaMBR(text);
				
			}
			
		});
		
		nextStep(EstadoCiclo.MOVER_REFERENCIA_INDIRETA_REGISTRADOR_IR);
		
		
	}
	
	
	public void animarOperacaoAritmetica() {
		
		
		
	}

	@Override
	protected void limparElementosTela() {
		
		controlador.getBarramentoInterno().remover(valorMar);
		controlador.getMemoriaInterna().remover(valorMbr);
		controlador.getBarramentoInterno().remover(valorUc);
		
	}

	@Override
	protected TelaMensagemSimulacao construirTelaMensagem(EstadoCiclo estado) {

		return new TelaMensagemCicloExecucao(estado);
		
	}
}
