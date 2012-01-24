package br.unipe.simuladores.arquitetura.simulacao;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.util.Duration;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.Instrucao;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.UC;
import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;
import br.unipe.simuladores.arquitetura.enums.OperandoCicloIndireto;
import br.unipe.simuladores.arquitetura.telas.TelaMensagemCicloIndireto;
import br.unipe.simuladores.arquitetura.telas.TelaMensagemSimulacao;
import br.unipe.simuladores.arquitetura.telas.TelaSimplesMensagem;

public class Indireto extends Ciclo{
	
	private OperandoCicloIndireto operando;

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
				
				controlador.operar();
				
			}
			
		});
		
		nextStep(EstadoCiclo.VERIFICAR_IR);
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	protected TelaMensagemSimulacao construirTelaMensagem(EstadoCiclo estado) {
		
		return new TelaMensagemCicloIndireto(estado);
		
	}

}
