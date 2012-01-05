package br.unipe.simuladores.arquitetura.telas;

import br.unipe.simuladores.arquitetura.excecoes.DadosInvalidosException;

public interface Formulario {

	void validarDados() throws DadosInvalidosException;
}
