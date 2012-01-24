package br.unipe.simuladores.arquitetura.enums;

public enum EstadoCiclo {
	
	//ciclo de busca
	INICIAL,
	ATUALIZAR_PC,
	MOVER_MAR,
	COPIAR_READ_BARRAMENTO,
	COPIAR_VALOR_MAR_BARRAMENTO,
	MOVER_DADOS_BARRAMENTO_MEMORIA,
	TRANSFERIR_INSTRUCAO,
	ATUALIZAR_PC_PROX_INSTRUCAO,
	NAO_HA_PROX_INSTRUCAO,
	COPIAR_MBR_PARA_IR,
	
	//ciclo indireto
	VERIFICAR_IR

}
