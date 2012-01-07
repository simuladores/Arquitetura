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
import br.unipe.simuladores.arquitetura.excecoes.VariavelExistenteException;

public class MemoriaInterna extends ComponenteInterno{
	
	private  ObservableList<Instrucao> instrucoes;
	
	private  ObservableList<Variavel> variaveis;
	
	private TableView<Instrucao> tabelaInstrucoes;
	
	private TableView<Variavel> tabelaVariaveis;
	
	private Map<String, Integer> mapaEnderecos;
	
	private TabPane tabPane;
	
	private Integer nextEnd;
	
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

	@Override
	protected void definirAcoesGerais() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void definirAcoesEspecificas() {
		// TODO Auto-generated method stub
		
	}

}
