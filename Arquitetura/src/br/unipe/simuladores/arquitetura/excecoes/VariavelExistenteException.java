package br.unipe.simuladores.arquitetura.excecoes;

public class VariavelExistenteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3264146742109816619L;

	public VariavelExistenteException() {
		
		super("A vari�vel j� existe");
		
	}
	
	public VariavelExistenteException(String msg) {
		
		super(msg);
		
	}
}
