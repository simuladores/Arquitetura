package br.unipe.simuladores.arquitetura.componentes.internos.unidades;

import br.unipe.simuladores.arquitetura.componentes.interfaces.Componente;
import br.unipe.simuladores.arquitetura.enums.OpcaoJanelaMensagem;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

public abstract class UnidadeUCP extends Componente{
	
	protected Shape forma;
	protected Text txtNome;
	protected Text txtValor;
	protected Object valor;
	
	protected Group group;
	
	public UnidadeUCP() {
		
		txtValor = new Text();
		
		group = new Group();
		
	}
	
	public abstract void construirForma(double x, double y);
	
	public abstract void adicionarTexto(double x, double y);
	
	public abstract void atualizarValor(Object valor, double x, double y);
	
	protected void atualizarTexto(String valor, double x, double y) {
		
		txtValor.setText(valor);
		txtValor.setX(x);
	    txtValor.setY(y);
		
	}
	
	public void definirAcoes() {
		
		group.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				exibirMensagemExplicativa();
				
			}
			
		});
		
		group.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				esconderMensagemExplicativa();
				
			}
			
		});
		
	}
	
	public Shape getForma() {
		return forma;
	}
	public void setForma(Shape forma) {
		this.forma = forma;
	}
	public Text getTxtNome() {
		return txtNome;
	}
	public void setTxtNome(Text txtNome) {
		this.txtNome = txtNome;
	}
	public Text getTxtValor() {
		return txtValor;
	}
	public void setTxtValor(Text txtValor) {
		this.txtValor = txtValor;
	}
	public Object getValor() {
		
		if (valor.equals(""))
			return new Integer(0);
		
		return valor;
	}
	public void setValor(Object valor) {
		this.valor = valor;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

}
