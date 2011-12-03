package br.unipe.simuladores.arquitetura.componentes.circulos;

import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

public class CaixaFormulario extends Group{
	
	private Line linha1;
	private Line linha2;
	private Line linha3;
	private Line linha4;
	private Line linha5;
	
	public CaixaFormulario (double pontos[][], Paint cor) {
		
		linha1 = new Line(pontos[0][0], pontos[0][1], pontos[1][0], pontos[1][1]);
		linha2 = new Line(pontos[2][0], pontos[2][1], pontos[3][0], pontos[3][1]);
		linha3 = new Line(pontos[3][0], pontos[3][1], pontos[4][0], pontos[4][1]);
		linha4 = new Line(pontos[4][0], pontos[4][1], pontos[5][0], pontos[5][1]);
		linha5 = new Line(pontos[5][0], pontos[5][1], pontos[0][0], pontos[0][1]);
		
		linha1.setFill(cor);
		linha2.setFill(cor);
		linha3.setFill(cor);
		linha4.setFill(cor);
		linha5.setFill(cor);
		
		this.getChildren().addAll(linha1, linha2, linha3, linha4, linha5);
		
	}

}
