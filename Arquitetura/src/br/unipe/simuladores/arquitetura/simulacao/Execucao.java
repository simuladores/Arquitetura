package br.unipe.simuladores.arquitetura.simulacao;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.util.Duration;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.IR;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.Instrucao;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.MAR;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.MBR;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.Registrador;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.ULA;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.Variavel;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.VariavelIdentificador;
import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;
import br.unipe.simuladores.arquitetura.enums.ModoEnderecamento;
import br.unipe.simuladores.arquitetura.enums.Operacao;
import br.unipe.simuladores.arquitetura.enums.OperacaoAritmetica;
import br.unipe.simuladores.arquitetura.telas.TelaMensagemCicloExecucao;
import br.unipe.simuladores.arquitetura.telas.TelaMensagemSimulacao;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;

public class Execucao extends Ciclo {

	private Operacao operacao;
	private ModoEnderecamento modEndOp1;
	private ModoEnderecamento modEndOp2;
	private int opcode;
	private boolean leitura;
	
	private Text operando1;
	private Text operando2;
	private Text txtOperacao;
	private Text igual;
	private Text resultado;
	
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
	
	private void direcionarMovimentacaoDado() {
		
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
	
	public void animarOperacaoMovimentacao() {
		
		if (modEndOp2 == ModoEnderecamento.IMEDIATO){
			
			direcionarMovimentacaoDado();
			
		} else if (modEndOp2 == ModoEnderecamento.DIRETO 
				|| modEndOp2 == ModoEnderecamento.INDIRETO) {
			
			Text text = new Text(op2.getText());
			text.setX(op2.getX());
			text.setY(op2.getY());
			
			controlador.getUcpInterna().adicionar(text);
			controlador.adicionarElemento(text);
			
			moverRefenciaParaMAR(text);
			
		} else if (modEndOp2 == ModoEnderecamento.REGISTRADOR 
				|| modEndOp2 == ModoEnderecamento.INDIRETO_REGISTRADOR) {
			
			moverDadoRegistradorParaIr();
			
		}
		
	}
	
	public void animarOperacaoAritmetica() {
		
		if (modEndOp2 == ModoEnderecamento.IMEDIATO) {
			
			if (modEndOp1 == ModoEnderecamento.DIRETO 
					|| modEndOp1 == ModoEnderecamento.INDIRETO) {
				
				Text text = new Text(op1.getText());
				text.setX(op1.getX());
				text.setY(op1.getY());
				
				controlador.getUcpInterna().adicionar(text);
				controlador.adicionarElemento(text);
				
				primeiroOperando = true;
				leitura = true;
				
				moverRefenciaParaMAR(text);
				
			} else if (modEndOp1 == ModoEnderecamento.REGISTRADOR) {
				
				transferirRegistradorIrParaULA();
				
			} else if (modEndOp1 == ModoEnderecamento.INDIRETO_REGISTRADOR) {
				
				moverRefenciaIndiretaRegistradorParaIr(null);
				
			}
			
			
		} else if (modEndOp2 == ModoEnderecamento.DIRETO 
				|| modEndOp2 == ModoEnderecamento.INDIRETO) {
			
			Text text = new Text(op2.getText());
			text.setX(op2.getX());
			text.setY(op2.getY());
			
			controlador.getUcpInterna().adicionar(text);
			controlador.adicionarElemento(text);
			
			leitura = true;
			primeiroOperando = false;
			
			moverRefenciaParaMAR(text);
			
		} else if (modEndOp2 == ModoEnderecamento.REGISTRADOR) {
			
			if (modEndOp1 == ModoEnderecamento.DIRETO 
					|| modEndOp1 == ModoEnderecamento.INDIRETO) {
				
				Text text = new Text(op1.getText());
				text.setX(op1.getX());
				text.setY(op1.getY());
				
				controlador.getUcpInterna().adicionar(text);
				controlador.adicionarElemento(text);
				
				leitura = true;
				primeiroOperando = true;
				
				moverRefenciaParaMAR(text);
				
			} else if (modEndOp1 == ModoEnderecamento.REGISTRADOR) {
				
				transferirRegistradorRegistradorParaULA();
				
			} else if (modEndOp1 == ModoEnderecamento.INDIRETO_REGISTRADOR) {
				
				moverRefenciaIndiretaRegistradorParaIr(null); 
				
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
		
		if (!leitura && !(operacao == Operacao.MOV))
			nextStep(EstadoCiclo.TRANSFERIR_ULA_MBR);
		else
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
			
		if (operacao == Operacao.MOV) {
		
			if (modEndOp2 == ModoEnderecamento.IMEDIATO)
			
				nextStep(EstadoCiclo.TRANSFERIR_IR_MAR);
		
			else 
			
				nextStep(EstadoCiclo.TRANSFERIR_IR_MAR_2);
		} else {
			
			if (primeiroOperando)
				
				nextStep(EstadoCiclo.TRANSFERIR_IR_MAR_1);
			
			else
				
				nextStep(EstadoCiclo.TRANSFERIR_IR_MAR_2);
		}
		
	}
	
	public void copiarValorMarParaBarramentoExecucao() {
		
		copiarValorMARParaBarramento();
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				if (operacao == Operacao.MOV) {
					
					if (modEndOp2 == ModoEnderecamento.IMEDIATO || 
							modEndOp2 == ModoEnderecamento.REGISTRADOR)
					
						copiarValorMbrBarramento();
					
					else if (modEndOp2 == ModoEnderecamento.DIRETO 
							|| modEndOp2 == ModoEnderecamento.INDIRETO)
						
						copiarREADParaBarramento();
					
				} else {
					
					if (leitura) 
						
						copiarREADParaBarramento();
					
					else
						
						copiarValorMbrBarramento();
					
				}
			
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
	
	public void copiarREADParaBarramento() {
		
		ValorUCParaBarramento(965, 593, 1098, "READ");
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				moverDadosLeituraParaMemoria();
				
			}
			
		});
		
		nextStep(EstadoCiclo.COPIAR_READ_BARRAMENTO_EXECUCAO);
		
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
	
	public void moverDadosLeituraParaMemoria() {
		
		double xWrite = valorUc.getX();
		double yDeWrite = valorUc.getY();
		double xEnd = valorMar.getX();
		double yDeEnd = valorMar.getY();
		
		double yPara = 40;
		
		animation = new Timeline();
		
		((Timeline)animation).getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(valorUc.xProperty(), xWrite),
	                   new KeyValue(valorUc.yProperty(), yDeWrite),
	                   new KeyValue(valorMar.xProperty(), xEnd),
	                   new KeyValue(valorMar.yProperty(), yDeEnd)
	               ),
	               new KeyFrame(new Duration(3000), 
	            	   new KeyValue(valorUc.xProperty(), xWrite),
		               new KeyValue(valorUc.yProperty(), yPara),
		               new KeyValue(valorMar.xProperty(), xEnd),
		               new KeyValue(valorMar.yProperty(), yPara)
	               )
			);
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				transferirDadoLeituraPeloBarramento();
				
			}
			
		});
		
		nextStep(EstadoCiclo.MOVER_DADOS_BARRAMENTO_MEMORIA_LEITURA);
		
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
				
				fimExecucao();
				
				
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
				
				fimExecucao();
				
			}
			
		});
		
		if (operacao == Operacao.MOV)
			
			nextStep(EstadoCiclo.MOVER_DADO_REGISTRADOR);
		
		else
			
			nextStep(EstadoCiclo.MOVER_DADO_ULA_REGISTRADOR);
		
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
				
				if (operacao == Operacao.MOV)
				
					moverDadoParaMBR(text);
				
				else {
					
					modEndOp1 = ModoEnderecamento.DIRETO;
					animarOperacaoAritmetica();
					
				}
				
			}
			
		});
		
		nextStep(EstadoCiclo.MOVER_REFERENCIA_INDIRETA_REGISTRADOR_IR);
		
		
	}
	
	public void transferirDadoLeituraPeloBarramento() {
		
		transferirDadoMemoriaPeloBarramento();
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				controlador.getBarramentoInterno().remover(txtDadoTransferencia);
				MBR mbr = controlador.getUcpInterna().getMbr();
				mbr.atualizarValor(dado, 923, 478);
				controlador.getUcpInterna().atualizarValorUnidadeTela(mbr);
				
				limparElementosTela();
				
				if (operacao == Operacao.MOV) {
				
					primeiroOperando = false;
					
					transferirMbrParaIrExecucao();
				
				} else {
					
					if (primeiroOperando)
					
						direcionarOperandoOperacaoAritmetica();
					
					else
						
						transferirMbrParaIrExecucao();
				}

				
			}
			
		});
		
		nextStep(EstadoCiclo.TRANSFERIR_DADO_LEITURA_BARRAMENTO);
		
	}
	
	public void transferirMbrParaIrExecucao() {
		
		transferirMbrParaIr();
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				controlador.getUcpInterna().remover(valorMbr);
								
				op2.setText(valorMbr.getText());
					
				modEndOp2 = ModoEnderecamento.IMEDIATO;
					
				limparElementosTela();
				
				if (operacao == Operacao.MOV) {
					
					direcionarMovimentacaoDado();
					
				} else {
					
					direcionarOperandoOperacaoAritmetica();
					
				}
							
			}
			
		});
		
		nextStep(EstadoCiclo.TRANSFERIR_MBR_PARA_IR_EXECUCAO);
		
	}
	
	public void moverDadoRegistradorParaIr() {
		
		
		Registrador registrador = 
				controlador.getUcpInterna().obterRegistrador(new Integer(op2.getText()));
		
		double xDe = registrador.getTxtValor().getX();
		double yDe = registrador.getTxtValor().getY();
		double xPara = op2.getX();
		double yPara = op2.getY();
		
		final Text text = new Text(registrador.getTxtValor().getText());
		text.setX(xDe);
		text.setY(yDe);
		controlador.getUcpInterna().adicionar(text);
		controlador.adicionarElemento(text);
		
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
				op2.setText(text.getText());
				
				if (modEndOp2 == ModoEnderecamento.REGISTRADOR)
					
					direcionarMovimentacaoDado();
				
				else {
					
					modEndOp2 = ModoEnderecamento.DIRETO;
					
					Text text = new Text(op2.getText());
					text.setX(op2.getX());
					text.setY(op2.getY());
					
					controlador.getUcpInterna().adicionar(text);
					controlador.adicionarElemento(text);
					
					moverRefenciaParaMAR(text);
					
				}

				
			}
			
		});
		
		nextStep(EstadoCiclo.MOVER_DADO_REGISTRADOR_IR);
		
		
	}
	
	public void direcionarOperandoOperacaoAritmetica() {
		
		if (primeiroOperando) {
			
			if (modEndOp2 == ModoEnderecamento.IMEDIATO)
				
				transferirMbrIrParaULA();
			
			else if (modEndOp2 == ModoEnderecamento.REGISTRADOR) 
				
				transferirMbrRegistradorParaULA();

		} else {
			
			animarOperacaoAritmetica();
			
		}
		
	}
	
	public void transferirMbrIrParaULA() {
		
		MBR mbr = controlador.getUcpInterna().getMbr();
		
		double xDeMbr = mbr.getTxtValor().getX();
		double yDeMbr = mbr.getTxtValor().getY();
		double xDeIr = op2.getX();
		double yDeIr = op2.getY();
		double xParaMbr = 846;
		double yPara = 587;
		double xParaIr = 898;
		
		operando1 = new Text(mbr.getTxtValor().getText());
		operando1.setX(xDeMbr);
		operando1.setY(yDeMbr);
		controlador.getUcpInterna().adicionar(operando1);
		controlador.adicionarElemento(operando1);
		
		operando2 = new Text(op2.getText());
		operando2.setX(xDeIr);
		operando2.setY(yDeIr);
		controlador.getUcpInterna().adicionar(operando2);
		controlador.adicionarElemento(operando2);
		
		animation = new Timeline();
		
		((Timeline)animation).getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(operando1.xProperty(), xDeMbr),
	                   new KeyValue(operando1.yProperty(), yDeMbr),
	                   new KeyValue(operando2.xProperty(), xDeIr),
	                   new KeyValue(operando2.yProperty(), yDeIr)
	               ),
	               new KeyFrame(Duration.millis(3000), 
	            	   new KeyValue(operando1.xProperty(), xParaMbr),
		               new KeyValue(operando1.yProperty(), yPara),
		               new KeyValue(operando2.xProperty(), xParaIr),
		               new KeyValue(operando2.yProperty(), yPara)
		           )
		           
	     );
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				realizarOperacaoAritmetica();
				
			}
			
		});
		
		nextStep(EstadoCiclo.TRANSFERIR_MBR_IR_ULA);
		
		
	}
	
	public void transferirRegistradorIrParaULA() {
		
		Integer numeroRegistrador = new Integer(op1.getText());
		Registrador registrador 
			= controlador.getUcpInterna().obterRegistrador(numeroRegistrador);
		double xDeReg = registrador.getTxtValor().getX();
		double yDeReg = registrador.getTxtValor().getY();
		double xParaReg = 846;
		double yPara = 587;
		double xDeIr = op2.getX();
		double yDeIr = op2.getY();
		double xParaIr = 898;
		
		operando1 = new Text(registrador.getTxtValor().getText());
		operando1.setX(xDeReg);
		operando1.setY(yDeReg);
		controlador.getUcpInterna().adicionar(operando1);
		controlador.adicionarElemento(operando1);
		
		operando2 = new Text(op2.getText());
		operando2.setX(xDeIr);
		operando2.setY(yDeIr);
		controlador.getUcpInterna().adicionar(operando2);
		controlador.adicionarElemento(operando2);
		
		animation = new Timeline();
		
		((Timeline)animation).getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(operando1.xProperty(), xDeReg),
	                   new KeyValue(operando1.yProperty(), yDeReg),
	                   new KeyValue(operando2.xProperty(), xDeIr),
	                   new KeyValue(operando2.yProperty(), yDeIr)
	               ),
	               new KeyFrame(Duration.millis(3000), 
	            	   new KeyValue(operando1.xProperty(), xParaReg),
		               new KeyValue(operando1.yProperty(), yPara),
		               new KeyValue(operando2.xProperty(), xParaIr),
		               new KeyValue(operando2.yProperty(), yPara)
		           )
		           
	     );
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				realizarOperacaoAritmetica();
				
			}
			
		});
		
		nextStep(EstadoCiclo.TRANSFERIR_REGISTRADOR_IR_ULA);
		
	}
	
	public void transferirMbrRegistradorParaULA() {
		
		MBR mbr = controlador.getUcpInterna().getMbr();
		Registrador registrador 
			= controlador.getUcpInterna().obterRegistrador(new Integer(op2.getText()));
		
		double xDeReg = registrador.getTxtValor().getX();
		double yDeReg = registrador.getTxtValor().getY();
		double xDeMbr = mbr.getTxtValor().getX();
		double yDeMbr = mbr.getTxtValor().getY();
		double xParaMbr = 846;
		double xParaReg = 898;
		double yPara = 587;
		
		operando1 = new Text(mbr.getTxtValor().getText());
		operando1.setX(xDeMbr);
		operando1.setY(yDeMbr);
		controlador.getUcpInterna().adicionar(operando1);
		controlador.adicionarElemento(operando1);
		
		operando2 = new Text(registrador.getTxtValor().getText());
		operando2.setX(xDeReg);
		operando2.setY(yDeReg);
		controlador.getUcpInterna().adicionar(operando2);
		controlador.adicionarElemento(operando2);
		
		animation = new Timeline();
		
		((Timeline)animation).getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(operando1.xProperty(), xDeMbr),
	                   new KeyValue(operando1.yProperty(), yDeMbr),
	                   new KeyValue(operando2.xProperty(), xDeReg),
	                   new KeyValue(operando2.yProperty(), yDeReg)
	               ),
	               new KeyFrame(Duration.millis(3000), 
	            	   new KeyValue(operando1.xProperty(), xParaMbr),
		               new KeyValue(operando1.yProperty(), yPara),
		               new KeyValue(operando2.xProperty(), xParaReg),
		               new KeyValue(operando2.yProperty(), yPara)
		           )
		           
	     );
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				realizarOperacaoAritmetica();
				
			}
			
		});
		
		nextStep(EstadoCiclo.TRANSFERIR_MBR_EGISTRADOR_ULA);
		
		
	}
	
	public void transferirRegistradorRegistradorParaULA() {
		
		Registrador reg1 
			= controlador.getUcpInterna()
			.obterRegistrador(new Integer(op1.getText()));
		
		Registrador reg2 
			= controlador.getUcpInterna()
			.obterRegistrador(new Integer(op2.getText()));
		
		double xDeReg1 = reg1.getTxtValor().getX();
		double yDeReg1 = reg1.getTxtValor().getY();
		double xDeReg2 = reg2.getTxtValor().getX();
		double yDeReg2 = reg2.getTxtValor().getY();
		double xParaReg1 = 846;
		double xParaReg2 = 898;
		double yPara = 587;
		
		operando1 = new Text(reg1.getTxtValor().getText());
		operando1.setX(xDeReg1);
		operando1.setY(yDeReg1);
		controlador.getUcpInterna().adicionar(operando1);
		controlador.adicionarElemento(operando1);
		
		operando2 = new Text(reg2.getTxtValor().getText());
		operando2.setX(xDeReg2);
		operando2.setY(yDeReg2);
		controlador.getUcpInterna().adicionar(operando2);
		controlador.adicionarElemento(operando2);
		
		animation = new Timeline();
		
		((Timeline)animation).getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(operando1.xProperty(), xDeReg1),
	                   new KeyValue(operando1.yProperty(), yDeReg1),
	                   new KeyValue(operando2.xProperty(), xDeReg2),
	                   new KeyValue(operando2.yProperty(), yDeReg2)
	               ),
	               new KeyFrame(Duration.millis(3000), 
	            	   new KeyValue(operando1.xProperty(), xParaReg1),
		               new KeyValue(operando1.yProperty(), yPara),
		               new KeyValue(operando2.xProperty(), xParaReg2),
		               new KeyValue(operando2.yProperty(), yPara)
		           )
		           
	     );
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				realizarOperacaoAritmetica();
				
			}
			
		});
		
		nextStep(EstadoCiclo.TRANSFERIR_REG_REG_ULA);
		
	}
	
	public void realizarOperacaoAritmetica() {
		
		ULA ula = controlador.getUcpInterna().getUla();
		txtOperacao = new Text();
		txtOperacao.setX(865);
		txtOperacao.setY(600);
		
		switch(operacao) {
		case ADD: {
			ula.setOperacao(OperacaoAritmetica.SOMA);
			txtOperacao.setText("+");
		}break;
		case SUB: {
			ula.setOperacao(OperacaoAritmetica.SUBTRACAO);
			txtOperacao.setText("-");
		}break;
		case MUL: {
			ula.setOperacao(OperacaoAritmetica.MULTIPLICACAO);
			txtOperacao.setText("x");
		}break;
		case DIV: {
			ula.setOperacao(OperacaoAritmetica.DIVISAO);
			txtOperacao.setText("/");
		}break;
		}
		
		controlador.getUcpInterna().adicionar(txtOperacao);
		controlador.adicionarElemento(txtOperacao);
		igual = new Text("=");
		igual.setX(915);
		igual.setY(600);
		controlador.getUcpInterna().adicionar(igual);
		controlador.adicionarElemento(igual);
		
		ula.setOperando1(new Integer(operando1.getText()));
		ula.setOperando2(new Integer(operando2.getText()));
		ula.operar();
		
		resultado = new Text(ula.getResultado().toString());
		resultado.setX(890);
		resultado.setY(610);
		resultado.setOpacity(0.0f);
		controlador.getUcpInterna().adicionar(resultado);
		controlador.adicionarElemento(resultado);
		
		animation = new Timeline();
		
		((Timeline)animation).getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(resultado.opacityProperty(), 0.0f)
	               ),
	               new KeyFrame(Duration.millis(3000), 
	            	   new KeyValue(resultado.opacityProperty(), 1.0f)
		           )
		           
	     );
		
		animation.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent e) {
				
				if (modEndOp1 == ModoEnderecamento.DIRETO 
						|| modEndOp1 == ModoEnderecamento.INDIRETO) {
					
					leitura = false;
					moverDadoParaMBR(resultado);
					
				} else if (modEndOp1 == ModoEnderecamento.REGISTRADOR)
				
					moverDadoParaRegistrador(resultado);
				
			}
			
		});
		
		nextStep(EstadoCiclo.EFETUAR_OPERACAO_ARITMETICA);
		
	}
	
	public void fimExecucao() {
		
		limparElementosTela();
		
		animation = new Timeline();
		
		nextStep(EstadoCiclo.FIM_EXECUCAO);
		
		controlador.operar();
		
	}
	

	@Override
	protected void limparElementosTela() {
		
		controlador.getBarramentoInterno().remover(valorMar);
		controlador.getMemoriaInterna().remover(valorMbr);
		controlador.getBarramentoInterno().remover(valorUc);
		controlador.getUcpInterna().remover(operando1);
		controlador.getUcpInterna().remover(operando2);
		controlador.getUcpInterna().remover(txtOperacao);
		controlador.getUcpInterna().remover(igual);
		controlador.getUcpInterna().remover(resultado);
		
	}

	@Override
	protected TelaMensagemSimulacao construirTelaMensagem(EstadoCiclo estado) {

		return new TelaMensagemCicloExecucao(estado);
		
	}
}
