package br.unipe.simuladores.simulacao.execucao.instrucoes;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import br.unipe.simuladores.arquitetura.componentes.internos.MemoriaInterna;
import br.unipe.simuladores.arquitetura.componentes.internos.UCPInterna;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.Instrucao;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.PC;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;

public class Movimentador{
	
	private MemoriaInterna memoriaInterna;
	private UCPInterna ucpInterna;
	private Instrucao instrucaoAtual;
	private Text valorTxt;
	private Lock lock;
	private Condition condition;
	
	private boolean continua1;
	private boolean continua2;
	private int qtdInstrucoes;
	
	private Service<Void> controladora;
	private Service<Void> animadora;
	
	private Executor executor;
	
	public Movimentador() {
		
		//super(2, 2, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2));		
		
		memoriaInterna = TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna();
		ucpInterna = TelaPrincipal.getComputador().getUCP().getUCPInterna();
		
		valorTxt = new Text();
		
		//lock = new ReentrantLock();
		//condition = lock.newCondition();
		
		//executor = exe;
		
		continua1 = true;
		continua2 = false;
		qtdInstrucoes = 1;
		
		
	}
	
	public Movimentador(String valor) {
		
		//super(2, 2, 0, TimeUnit.SECONDS, null);
		
		memoriaInterna = TelaPrincipal.getComputador().getMemoriaPrincipal().getMemoriaInterna();
		ucpInterna = TelaPrincipal.getComputador().getUCP().getUCPInterna();
		
		this.valorTxt = new Text(valor);
		
	}
	
	
	public synchronized void operar() {
			
			
				ObservableList<Instrucao> instrucoes = memoriaInterna.getInstrucoes();
				
				for (Instrucao instr : instrucoes) {
					
					qtdInstrucoes = instrucoes.size();
					
					//if (continua) {
					try{
					//lock.lock();
					
					PC pc = ucpInterna.getPc();
			
					System.out.println("a");
					Integer x = instr.enderecoProperty().getValue();
					pc.atualizarValor(x, 880, 438);
					ucpInterna.atualizarValorUnidadeTela(pc);
					instrucaoAtual = instr;
					
					TableViewSelectionModel <Instrucao> selectionModel =  
							memoriaInterna.getTabelaInstrucoes().getSelectionModel();
					selectionModel.select(instrucaoAtual);
					memoriaInterna.getTabelaInstrucoes().selectionModelProperty().setValue(selectionModel);
					
					//Animadora animadora = new Animadora(this);
					//animadora.getTask().run();
					
					//task.run();
					
					//animadora.executorProperty().getValue().execute(animadora.getTask());
					//((ThreadPoolExecutor)animadora.executorProperty().getValue()).shutdown();
					
					//lock.unlock();
					
					//System.out.println(animadora.getState());
					//System.out.println(animadora.isRunning());
					
					continua1 = false;
					continua2 = true;

					//controladora.notify();
					while(!continua1)
						controladora.wait();
						//condition.await();*/
					
					//this.buscarInstrucao(animadora);
						
					System.out.println("acordou!");
					
					} catch (InterruptedException e) {
						 //TODO Auto-generated catch block
						e.printStackTrace();
					}/*finally{
						lock.unlock();
					}*/
					
					//}
				}
			
			
		
	}
	
	public synchronized void mostrarAnimacoes() {
		
		do {
			
			while (!continua2) {
				try {
					animadora.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (continua2) {
				buscarInstrucao();
				qtdInstrucoes--;
			}
			
			continua2 = false;
			
		}while(qtdInstrucoes > 0);
		
	}
	
	public synchronized void buscarInstrucao() {
		
		moverEnderecoPCParaMAR();
		
	}
	
	private synchronized void moverEnderecoPCParaMAR() {
		
		
		
		//lock.lock();
		
		System.out.println("b");
		
		double xDe = ucpInterna.getPc().getTxtValor().getX();
		double yDe = ucpInterna.getPc().getTxtValor().getY();
		
		final double xPara = ucpInterna.getMar().getTxtValor().getX();
		final double yPara = ucpInterna.getMar().getTxtValor().getY();
		
		final Integer valor = (Integer)ucpInterna.getPc().getValor();
		
		valorTxt.setText(valor.toString());
		valorTxt.setX(xDe);
		valorTxt.setY(yDe);
		valorTxt.setFont(new Font(12));
		
		TelaPrincipal.removerDoPalco(valorTxt);
		TelaPrincipal.adicionarAoPalco(valorTxt);
		
		/*Timeline timeline = new Timeline();
			
		timeline.getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(valorTxt.xProperty(), xDe),
	                   new KeyValue(valorTxt.yProperty(), yDe)
	               ),
	               new KeyFrame(new Duration(3000), 
	                	new KeyValue(valorTxt.xProperty(), xPara),
		                new KeyValue(valorTxt.yProperty(), yPara)
	               )
	     );
		
		timeline.setOnFinished(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				TelaPrincipal.removerDoPalco(valorTxt);
				ucpInterna.getMar().atualizarValor(valor, xPara, yPara);
				ucpInterna.atualizarValorUnidadeTela(ucpInterna.getMar());				
				
				continua = true;
				
				System.out.println("pode continuar!");
				
			}
			
		});
		
		System.out.println("iniciando animação");
		timeline.play();
		timeline.
		System.out.println(timeline.getStatus());*/
		
		//lock.unlock();
		
		TranslateTransition translate = new 
				TranslateTransition(Duration.millis(3000), valorTxt);
		translate.setFromX(xDe);
		translate.setFromY(yDe);
		translate.setToX(xPara);
		translate.setToY(yPara);
		
		final Movimentador este = this;
		
		translate.setOnFinished(new EventHandler<ActionEvent>(){
		
			@Override
			public synchronized void handle(ActionEvent arg0) {
				
				//lock.lock();
				TelaPrincipal.removerDoPalco(valorTxt);
				ucpInterna.getMar().atualizarValor(valor, xPara, yPara);
				ucpInterna.atualizarValorUnidadeTela(ucpInterna.getMar());				
				
				
				
				//condition.signal();
				
				continua1 = true;
				
				//System.out.println(controladora.getState());
				
				synchronized (este) {
					
					System.out.println(controladora.getState());
					System.out.println(controladora.isRunning());
					
					System.out.println(animadora.getState());
					System.out.println(animadora.isRunning());
					
					controladora.start();
					
					
				}
							
				System.out.println("pode continuar!");
				
			}
			
		});
		
		
		
		translate.play();
		
		//lock.unlock();
		
		
			
	}

	public Executor getExecutor() {
		return executor;
	}

	public void setExecutor(Executor executor) {
		this.executor = executor;
	}

	public Service<Void> getControladora() {
		return controladora;
	}

	public void setControladora(Service<Void> controladora) {
		this.controladora = controladora;
	}

	public Service<Void> getAnimadora() {
		return animadora;
	}

	public void setAnimadora(Service<Void> animadora) {
		this.animadora = animadora;
	}	

}
