package br.unipe.simuladores.arquitetura.componentes.internos;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Side;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import br.unipe.simuladores.arquitetura.componentes.interfaces.ComponenteInterno;

public class MemoriaInterna extends ComponenteInterno{
	
	private  ObservableList<Instrucao> instrucoes;
	
	private  ObservableList<Dado> dados;
	
	private TableView<Instrucao> tabelaInstrucoes;
	
	private TableView<Dado> tabelaDados;
	
	private TabPane tabPane;
	
	public MemoriaInterna() {
		
		super();
		
	}

	@Override
	public void surgir(double time) {
		
		group.setVisible(true);
		
		  timeline = new Timeline();
			
		  timeline.getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(group.opacityProperty(), 0.0f)
	               ),
	               new KeyFrame(new Duration(time), 
	                	new KeyValue(group.opacityProperty(), 1.0f)
	               )
	       );
			
	      timeline.play();
		
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
        
        instrucoes = FXCollections.observableArrayList(
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118),
    			new Instrucao(1, 4, 123, 118)
    	);
        
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
        referenciaOp1Col.setText("Refer�ncia ao Operando 1");
        referenciaOp1Col.setCellValueFactory(
        		new PropertyValueFactory<Instrucao, Integer>("referenciaOp1"));
        TableColumn<Instrucao, Integer> referenciaOp2Col = 
        		new TableColumn<Instrucao, Integer>();
        referenciaOp2Col.setText("Refer�ncia ao Operando 2");
        referenciaOp2Col.setCellValueFactory(
        		new PropertyValueFactory<Instrucao, Integer>("referenciaOp2"));
        
        dados = FXCollections.observableArrayList(
    			new Dado(1, 12345),
    			new Dado(1, 12345),
    			new Dado(1, 12345),
    			new Dado(1, 12345),
    			new Dado(1, 12345),
    			new Dado(1, 12345),
    			new Dado(1, 12345),
    			new Dado(1, 12345),
    			new Dado(1, 12345),
    			new Dado(1, 12345),
    			new Dado(1, 12345),
    			new Dado(1, 12345),
    			new Dado(1, 12345),
    			new Dado(1, 12345),
    			new Dado(1, 12345),
    			new Dado(1, 12345),
    			new Dado(1, 12345),
    			new Dado(1, 12345),
    			new Dado(1, 12345),
    			new Dado(1, 12345)
    	);
        
        TableColumn<Dado, Integer> enderecoColDado = 
        		new TableColumn<Dado, Integer>();
        enderecoColDado.setText("Endere�o");
        enderecoColDado.setCellValueFactory(
        		new PropertyValueFactory<Dado, Integer>("endereco"));
        TableColumn<Dado, Integer> dataCol = 
        		new TableColumn<Dado, Integer>();
        dataCol.setText("Dado");
        dataCol.setCellValueFactory(
        		new PropertyValueFactory<Dado, Integer>("data"));
               
        tabelaInstrucoes = new TableView<Instrucao>();
        tabelaInstrucoes.setItems(instrucoes);
        tabelaInstrucoes.getColumns().addAll(enderecoColInst, opcodeCol, referenciaOp1Col, 
        		referenciaOp2Col);
        
        tabelaDados = new TableView<Dado>();
        tabelaDados.setItems(dados);
        tabelaDados.getColumns().clear();
        dataCol.setMinWidth(230);
        tabelaDados.getColumns().addAll(enderecoColDado, dataCol);
        
        tabPane.setTranslateX(700);
        tabPane.setTranslateY(20);
        tabPane.setMaxHeight(330);
        
        Tab instrucoesTab = new Tab();
        instrucoesTab.setText("Instru��es");
        instrucoesTab.setContent(tabelaInstrucoes);
        
        tabPane.getTabs().add(instrucoesTab);
        
        Tab dadosTab = new Tab();
        dadosTab.setText("Dados");
        dadosTab.setContent(tabelaDados);
        
        tabPane.getTabs().add(dadosTab);
		
		group.getChildren().addAll(tabPane);
		
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
