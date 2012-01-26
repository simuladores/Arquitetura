package br.unipe.simuladores.arquitetura.simulacao;

import javafx.scene.text.Text;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.Instrucao;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.MBR;
import br.unipe.simuladores.arquitetura.enums.EstadoCiclo;
import br.unipe.simuladores.arquitetura.enums.ModoEnderecamento;
import br.unipe.simuladores.arquitetura.enums.Operacao;
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
	
	public void moverDadoParaMBR(Text text) {
		
		double xDe = text.getX();
		double yDe = text.getY();
		MBR mbr = controlador.getUcpInterna().getMbr();
		double xPara = 923;
		double yPara = 478;
		
		
		
	}
	
	public void animarOperacaoAritmetica() {
		
	}

	@Override
	protected void limparElementosTela() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected TelaMensagemSimulacao construirTelaMensagem(EstadoCiclo estado) {
		// TODO Auto-generated method stub
		return null;
	}
}
