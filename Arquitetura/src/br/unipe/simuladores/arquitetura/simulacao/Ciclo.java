package br.unipe.simuladores.arquitetura.simulacao;

import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;
import br.unipe.simuladores.arquitetura.telas.TelaMensagemCicloBusca;
import br.unipe.simuladores.arquitetura.telas.TelaMensagemSimulacao;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;
import br.unipe.simuladores.arquitetura.telas.TelaSimplesMensagem;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public abstract class Ciclo {
	
	protected Controlador controlador;
	protected Animation animation;
	protected boolean continuar = false;
	protected TelaMensagemSimulacao telaMensagem;
	protected Text read;
	protected Text valorMar;
	
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
	
	protected void READParaBarramento() {
		
		double xDe = 968, yDe = 593, xPara = 1100;
		
		controlador.getUcpInterna().getUc().atualizarValor("READ", xDe, yDe);
		controlador.getUcpInterna()
			.atualizarUnidadeTela(controlador.getUcpInterna().getUc());
	
		read = new Text("READ");
		read.setX(xDe);
		read.setY(yDe);
		read.setFont(new Font(12));
		controlador.getUcpInterna().getUc().getTxtValor().setVisible(false);
		read.setVisible(false);
	
		controlador.getBarramentoInterno().adicionar(read);
		controlador.adicionarElemento(read);
		read.toFront();
	
		animation = new Timeline();
		
		((Timeline)animation).getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(read.xProperty(), xDe),
	                   new KeyValue(read.visibleProperty(), true),
	                   new KeyValue(controlador.getUcpInterna().getUc()
	                		   .getTxtValor().visibleProperty(), true),
	                   new KeyValue(read.yProperty(), yDe)
	               ),
	               new KeyFrame(new Duration(3000), 
	                	new KeyValue(read.xProperty(), xPara),
	                	new KeyValue(read.visibleProperty(), true),
	                	new KeyValue(controlador.getUcpInterna().getUc()
	                			.getTxtValor().visibleProperty(), true),
		                new KeyValue(read.yProperty(), yDe)
	               )
			);
		
	}
	
	protected void copiarValorMARParaBarramento() {
		
		Integer valor = (Integer)controlador.getUcpInterna().getMar().getValor();
		
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
		
		double xDeRead = read.getX();
		double yDeRead = read.getY();
		double yPara = 40;
		
		double xDeMar = valorMar.getX();
		double yDeMar = valorMar.getY();
		
		animation = new Timeline();
		
		((Timeline)animation).getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(read.xProperty(), xDeRead),
	                   new KeyValue(read.yProperty(), yDeRead),
	               	   new KeyValue(valorMar.xProperty(), xDeMar),
                       new KeyValue(valorMar.yProperty(), yDeMar)
	               ),
	               new KeyFrame(new Duration(3000), 
	                	new KeyValue(read.xProperty(), xDeRead),
		                new KeyValue(read.yProperty(), yPara),
		                new KeyValue(valorMar.xProperty(), xDeMar),
		                new KeyValue(valorMar.yProperty(), yPara)
	               )
	     );
		
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
