package br.unipe.simuladores.arquitetura.excecoes;

public class DadosInvalidosException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4106762582299737769L;
	
	public DadosInvalidosException() {
		
		super("Dados inv�lidos");
		
	}
	
	public DadosInvalidosException(String msg) {
		
		super(msg);
		
	}

}
