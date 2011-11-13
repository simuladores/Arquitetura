package br.unipe.simuladores.arquitetura.componentes.circulos;

import javafx.scene.Group;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;

public class Seta extends Group{
	
	private Line linha;
	private Polyline ponta;
	
	public Seta(double pontosLinha[], double pontosPonta[]) {
		
	    linha = new Line();
		linha.setStartX(pontosLinha[0]);
		linha.setStartY(pontosLinha[1]);
		linha.setEndX(pontosLinha[2]);
		linha.setEndY(pontosLinha[3]);
		linha.setStrokeWidth(0.8);
		
		ponta = new Polyline(pontosPonta);
		ponta.setStrokeWidth(0.8);
		
		this.getChildren().addAll(linha, ponta);
		
	}

}
