package br.unipe.simuladores.arquitetura.componentes.internos;

import java.util.HashMap;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import br.unipe.simuladores.arquitetura.componentes.interfaces.ComponenteInterno;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.Instrucao;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.Variavel;
import br.unipe.simuladores.arquitetura.excecoes.VariavelExistenteException;

public class MemoriaInterna extends ComponenteInterno{
	
	private  ObservableList<Instrucao> instrucoes;

	private  ObservableList<Variavel> variaveis;
	
	private TableView<Instrucao> tabelaInstrucoes;
	
	private TableView<Variavel> tabelaVariaveis;
	
	private Map<String, Integer> mapaEnderecos;
	
	private TabPane tabPane;
	
	private Integer nextEnd;
	
	private static final String MEMORIA_INTERNATXT = "A mem�ria principal est� aqui " +
			"representada por duas tabelas:\n " +
			"uma que armazena instru��es e outra que armazena dados.\n Na tabela " +
			"de instru��es, as colunas s�o as seguintes:\n" +
			"- Endere�o : O endere�o da mem�ria que a instru��o ocupa\n" +
			"- Opcode: Um c�digo que identifica o tipo da instru��o,\n relacionado " +
			"com os operandos 1 e 2.\n" +
			"- Operandos 1 e 2: N�meros que indentificam o endere�o de\n mem�ria de " +
			"um dado, um dado ou um dos registradores\n da UCP, dependendo do modo de" +
			" endere�amento desses\n operandos.\n" +
			"Cada linha da tabela de dados, cont�m o que programadores\n definem como " +
			"vari�vies. Essa tabela possui duas colunas,\n na primeira est� contido " +
			"o endere�o de uma vari�vel\n inserida e na segunda, o dado que essa " +
			"vari�vel armazena.";
	
	public MemoriaInterna() {
		
		super();
		variaveis = FXCollections.observableArrayList();
		instrucoes = FXCollections.observableArrayList();
		mapaEnderecos = new HashMap<String, Integer>();
		nextEnd = 1;
		
	}

	@Override
	protected void adicionarTexto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void buildContent() {
		
		group.getChildren().removeAll(tabPane);
		
		tabPane = new TabPane();
		tabPane.setSide(Side.TOP);
		tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        TableColumn<Instrucao, Integer> enderecoColInst = 
        		new TableColumn<Instrucao, Integer>();
        enderecoColInst.setText("Endere�o");
        enderecoColInst.setCellValueFactory(
        		new PropertyValueFactory<Instrucao, Integer>("endereco"));
        TableColumn<Instrucao, Integer> opcodeCol = 
        		new TableColumn<Instrucao, Integer>();
        opcodeCol.setText("Opcode");
        opcodeCol.setCellValueFactory(
        		new PropertyValueFactory<Instrucao, Integer>("opcode"));
        TableColumn<Instrucao, Integer> referenciaOp1Col = 
        		new TableColumn<Instrucao, Integer>();
        referenciaOp1Col.setText("Operando 1");
        referenciaOp1Col.setCellValueFactory(
        		new PropertyValueFactory<Instrucao, Integer>("referenciaOp1"));
        TableColumn<Instrucao, Integer> referenciaOp2Col = 
        		new TableColumn<Instrucao, Integer>();
        referenciaOp2Col.setText("Operando 2");
        referenciaOp2Col.setCellValueFactory(
        		new PropertyValueFactory<Instrucao, Integer>("referenciaOp2"));
        
        TableColumn<Variavel, Integer> enderecoColDado = 
        		new TableColumn<Variavel, Integer>();
        enderecoColDado.setText("Endere�o");
        enderecoColDado.setCellValueFactory(
        		new PropertyValueFactory<Variavel, Integer>("endereco"));
        TableColumn<Variavel, String> dataCol = 
        		new TableColumn<Variavel, String>();
        dataCol.setText("Dado");
        dataCol.setCellValueFactory(
        		new PropertyValueFactory<Variavel, String>("data"));
               
        tabelaInstrucoes = new TableView<Instrucao>();
        tabelaInstrucoes.setItems(instrucoes);
        tabelaInstrucoes.getColumns().addAll(enderecoColInst, opcodeCol, referenciaOp1Col, 
        		referenciaOp2Col);
        
        tabelaVariaveis = new TableView<Variavel>();
        tabelaVariaveis.setItems(variaveis);
        tabelaVariaveis.getColumns().clear();
        dataCol.setMinWidth(230);
        tabelaVariaveis.getColumns().addAll(enderecoColDado, dataCol);
        
        tabPane.setTranslateX(700);
        tabPane.setTranslateY(20);
        tabPane.setMaxHeight(330);
        
        Tab instrucoesTab = new Tab();
        instrucoesTab.setText("Instru��es");
        instrucoesTab.setContent(tabelaInstrucoes);
        
        tabPane.getTabs().add(instrucoesTab);
        
        Tab dadosTab = new Tab();
        dadosTab.setText("Dados");
        dadosTab.setContent(tabelaVariaveis);
        
        tabPane.getTabs().add(dadosTab);
		
		group.getChildren().addAll(tabPane);
		
	}
	
	public void inserirDado(Variavel v, String var) throws VariavelExistenteException {
		
		if (contemVar(var, false))
			throw new VariavelExistenteException("A vari�vel j� existe. Informe outro identificador");
			
		v.endereco.setValue(nextEnd);
		variaveis.add(v);
		tabelaVariaveis.setItems(variaveis);
		mapaEnderecos.put(var, nextEnd);
		nextEnd++;
		
	}
	
	public void inserirInstrucao(Instrucao i) {
		
		i.endereco.setValue(nextEnd);
		instrucoes.add(i);
		tabelaInstrucoes.setItems(instrucoes);
		nextEnd++;
		
	}
	
	public boolean contemVar(Object valor, boolean endereco) {
		
		if(endereco) {
			
			if (mapaEnderecos.containsValue((Integer)valor))
				return true;
			
			return false;
		}
		
		if (mapaEnderecos.containsKey((String)valor))
			return true;
		
		return false;
		
	}
	
	public boolean ehPonteiro(Object valor, boolean endereco) {
		
		if (!contemVar(valor, endereco))
			return false;
		
		Variavel v;
		
		if (endereco) 
			v = procurarVariavelPorEndereco(valor);
		else
			v = procurarVariavelPorIdentificador(valor);
		
		if (v.getNormal())
			return false;
		else 
			return true;
		
		
	}
	
	private Variavel procurarVariavelPorEndereco(Object endereco) {
		
		for (Variavel v : variaveis) {
			
			if (v.endereco.getValue().equals((Integer)endereco)) 
				return v;
			
		}
		
		return null;
		
	}
	
	private Variavel procurarVariavelPorIdentificador(Object id) {
		
		Integer endereco = mapaEnderecos.get((String)id);
		
		return procurarVariavelPorEndereco(endereco);
		
	}
	
	public Integer obterEnderecoVariavel(String identificador) {
		
		Variavel variavel = procurarVariavelPorIdentificador(identificador);
		
		return variavel.endereco.getValue();
		
	}
	
	public String obterDadoVariavel(Integer end) {
		
		for (Variavel var : variaveis) {
			
			if (var.endereco.getValue().equals(end))
				return var.dataProperty().getValue();
			
		}
		
		return null;
		
	}
	
	public void atualizarValoresVariaveis() {
		
		tabelaVariaveis.setItems(variaveis);
		
	}

	@Override
	protected void definirAcoesEspecificas() {
		
		//TODO 
		
	}
	
	public Instrucao obterInstrucao(Integer endereco) {
		
		
		for (Instrucao instr : instrucoes) {
			
			if (instr.endereco.getValue().equals(endereco))
				return instr;
			
		}
		
		return null;
	}
	
	public Variavel obterVariavel(Integer endereco) {
		
		for (Variavel var : variaveis) {
			
			if (var.enderecoProperty().getValue().equals(endereco))
				return var;
			
		}
		
		return null;
		
	}
	
	public ObservableList<Instrucao> getInstrucoes() {
		return instrucoes;
	}

	public void setInstrucoes(ObservableList<Instrucao> instrucoes) {
		this.instrucoes = instrucoes;
	}

	public ObservableList<Variavel> getVariaveis() {
		return variaveis;
	}

	public void setVariaveis(ObservableList<Variavel> variaveis) {
		this.variaveis = variaveis;
	}

	public TableView<Instrucao> getTabelaInstrucoes() {
		return tabelaInstrucoes;
	}

	public void setTabelaInstrucoes(TableView<Instrucao> tabelaInstrucoes) {
		this.tabelaInstrucoes = tabelaInstrucoes;
	}

	public TableView<Variavel> getTabelaVariaveis() {
		return tabelaVariaveis;
	}

	public void setTabelaVariaveis(TableView<Variavel> tabelaVariaveis) {
		this.tabelaVariaveis = tabelaVariaveis;
	}

	public Map<String, Integer> getMapaEnderecos() {
		return mapaEnderecos;
	}

	public void setMapaEnderecos(Map<String, Integer> mapaEnderecos) {
		this.mapaEnderecos = mapaEnderecos;
	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

	public Integer getNextEnd() {
		return nextEnd;
	}

	public void setNextEnd(Integer nextEnd) {
		this.nextEnd = nextEnd;
	}

	@Override
	public String obterTextoExplicativo() {
		
		return MEMORIA_INTERNATXT;
		
	}
	
	

}
