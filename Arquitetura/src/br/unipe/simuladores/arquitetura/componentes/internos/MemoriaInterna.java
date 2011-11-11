package br.unipe.simuladores.arquitetura.componentes.internos;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import br.unipe.simuladores.arquitetura.componentes.interfaces.ComponenteInterno;

public class MemoriaInterna extends ComponenteInterno{
	
	private  ObservableList<Instrucao> instrucoes;
	
	private TableView<Instrucao> tabelaInstrucoes;
	
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
		
		group.getChildren().removeAll(tabelaInstrucoes);
        
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
        
        TableColumn<Instrucao, Integer> enderecoCol = 
        		new TableColumn<Instrucao, Integer>();
        enderecoCol.setText("Endereço");
        enderecoCol.setCellValueFactory(
        		new PropertyValueFactory<Instrucao, Integer>("endereco"));
        TableColumn<Instrucao, Integer> opcodeCol = 
        		new TableColumn<Instrucao, Integer>();
        opcodeCol.setText("Opcode");
        opcodeCol.setCellValueFactory(
        		new PropertyValueFactory<Instrucao, Integer>("opcode"));
        TableColumn<Instrucao, Integer> referenciaOp1Col = 
        		new TableColumn<Instrucao, Integer>();
        referenciaOp1Col.setText("Referência ao Operando 1");
        referenciaOp1Col.setCellValueFactory(
        		new PropertyValueFactory<Instrucao, Integer>("referenciaOp1"));
        TableColumn<Instrucao, Integer> referenciaOp2Col = 
        		new TableColumn<Instrucao, Integer>();
        referenciaOp2Col.setText("Referência ao Operando 2");
        referenciaOp2Col.setCellValueFactory(
        		new PropertyValueFactory<Instrucao, Integer>("referenciaOp2"));
        
        tabelaInstrucoes = new TableView<Instrucao>();
        tabelaInstrucoes.setItems(instrucoes);
        tabelaInstrucoes.getColumns().addAll(enderecoCol, opcodeCol, referenciaOp1Col, 
        		referenciaOp2Col);
        
        tabelaInstrucoes.setTranslateX(700);
        tabelaInstrucoes.setTranslateY(20);
        tabelaInstrucoes.setMaxHeight(320);
		
		group.getChildren().addAll(tabelaInstrucoes);
		
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
