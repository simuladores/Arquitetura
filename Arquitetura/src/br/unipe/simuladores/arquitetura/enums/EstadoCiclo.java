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
	VERIFICAR_IR,
	TRANSFERIR_OPERANDO_MAR,
	COPIAR_READ_BARRAMENTO_IND,
	COPIAR_VALOR_MAR_BARRAMENTO_IND,
	MOVER_DADOS_BARRAMENTO_MEMORIA_IND,
	TRANSFERIR_DADO_REFERENCIA_INDIRETA_BARRAMENTO,
	TRANSFERIR_MBR_PARA_IR,
	
	//ciclo de execu��o
	TRANSFERIR_IR_MBR,
	TRANSFERIR_IR_MAR,
	COPIAR_VALOR_MAR_BARRAMENTO_EXECUCAO,
	COPIAR_VALOR_MBR_BARRAMENTO_EXECUCAO,
	COPIAR_WRITE_BARRAMENTO,
	MOVER_DADOS_BARRAMENTO_MEMORIA_ESCRITA,
	MOVER_DADO_MEMORIA,
	MOVER_DADO_REGISTRADOR,
	MOVER_REFERENCIA_INDIRETA_REGISTRADOR_IR,
	TRANSFERIR_IR_MAR_2,
	COPIAR_READ_BARRAMENTO_EXECUCAO,
	MOVER_DADOS_BARRAMENTO_MEMORIA_LEITURA,
	TRANSFERIR_DADO_LEITURA_BARRAMENTO,
	TRANSFERIR_MBR_PARA_IR_EXECUCAO,
	MOVER_DADO_REGISTRADOR_IR,
	FIM_EXECUCAO,
	TRANSFERIR_IR_MAR_1,
	TRANSFERIR_MBR_IR_ULA

}
