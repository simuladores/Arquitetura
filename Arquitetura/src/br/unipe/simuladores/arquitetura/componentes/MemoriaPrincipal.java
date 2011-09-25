package br.unipe.simuladores.arquitetura.componentes;

import br.unipe.simuladores.arquitetura.principal.Main;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MemoriaPrincipal extends Componente{
	
	private Group grupoInstrucoes;
	private Group grupoDados;
	
	public MemoriaPrincipal(){
		
		super();
		expanded = false;
		buildContent();
		
	}

	@Override
	public void expandir(double fromScale, double toScale, final double time) {
		
		expanded = true;
		
		group.setCursor(Cursor.DEFAULT);
		
		//cria a animação
	    timeline = new Timeline();
		
		timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, 
                    new KeyValue(group.scaleXProperty(), fromScale),
                    new KeyValue(group.scaleYProperty(), fromScale)
                ),
                new KeyFrame(new Duration(time), 
                    new KeyValue(group.scaleXProperty(), toScale),
                    new KeyValue(group.scaleYProperty(), toScale),
                	new KeyValue(group.translateXProperty(), 200),
                	new KeyValue(group.translateYProperty(), -133)
                )
         );
		
		timeline.play();
		
	     
		timeline.setOnFinished(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					
					final Line linha = new Line();
					final Line linha2 = new Line();
					
					Main.adicionarAoPalco(linha);
					Main.adicionarAoPalco(linha2);
					
					linha.toBack();
					linha2.toBack();
					linha.setStrokeWidth(0.5);
					linha2.setStrokeWidth(0.5);
					
					Timeline timeline2 = new Timeline();
					
					timeline2.getKeyFrames().addAll(
			                new KeyFrame(Duration.ZERO, 
				                new KeyValue(linha.startXProperty(), 773),
				                new KeyValue(linha.startYProperty(), 90),
				                new KeyValue(linha.endXProperty(), 773),
				                new KeyValue(linha.endYProperty(), 90),
				                new KeyValue(linha2.startXProperty(), 923),
				                new KeyValue(linha2.startYProperty(), 308),
				                new KeyValue(linha2.endXProperty(), 923),
				                new KeyValue(linha2.endYProperty(), 308)
			                ),
			                new KeyFrame(new Duration(time),		                		
			                	new KeyValue(linha.startXProperty(), 773),
				                new KeyValue(linha.startYProperty(), 90),
				                new KeyValue(linha.endXProperty(), 640),
				                new KeyValue(linha.endYProperty(), 320),
				                new KeyValue(linha2.startXProperty(), 923),
				                new KeyValue(linha2.startYProperty(), 308),
				                new KeyValue(linha2.endXProperty(), 664),
				                new KeyValue(linha2.endYProperty(), 380)
			                )
			         );
					
					
					timeline2.play(); 
					
					/*Line linha = new Line();
					linha.setStartX(640);
					linha.setStartY(320);
					linha.setEndX(773);
					linha.setEndY(90);
					
					Line linha2 = new Line();
					linha2.setStartX(664);
					linha2.setStartY(380);
					linha2.setEndX(923);
					linha2.setEndY(308);

					Main.adicionarAoPalco(linha);
					
					Main.adicionarAoPalco(linha2);
					
					linha.toBack();
					linha.setStrokeWidth(0.5);
					linha2.toBack();
					linha2.setStrokeWidth(0.5);*/
					
				}
					
		
		});		
		
	}

	@Override
	public void encolher(double fromScale, double toScale, double time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Group getContent() {
		
		return group;
		
	}

	@Override
	protected void adicionarTexto() {
		
		Text memoriaPrincipal = new Text("Memória Principal");
		memoriaPrincipal.setX(655);
		memoriaPrincipal.setY(255);
		memoriaPrincipal.setFont(new Font(6));
		
		Text instrucoes = new Text("Instruções");
		instrucoes.setX(635);
		instrucoes.setY(295);
		instrucoes.setFont(new Font(6));
		
		Text dados = new Text("Dados");
		dados.setX(702);
		dados.setY(330);
		dados.setFont(new Font(6));
		
		group.getChildren().add(memoriaPrincipal);
		
		grupoInstrucoes.getChildren().add(instrucoes);
		grupoInstrucoes.getChildren().add(dados);
		
	}

	@Override
	protected void buildContent() {
		
		//remove todos os elementos do grupo desse componente para evitar a repetição deles
		group.getChildren().removeAll(circulo, grupoInstrucoes, grupoDados);
		
		Circle instrucoes = new Circle();
		Circle dados = new Circle();
		
		grupoInstrucoes = new Group();
		grupoInstrucoes.getChildren().add(instrucoes);
		grupoDados = new Group();
		grupoDados.getChildren().add(dados);
		
		circulo = new Circle(70, Color.AZURE);
		circulo.setCenterX(680);
		circulo.setCenterY(310);
		
		instrucoes.setFill(Color.rgb(255, 0, 0, 0.5f));
		instrucoes.setCenterX(650);
		instrucoes.setCenterY(295);
		instrucoes.setRadius(32);
		
		dados.setFill(Color.rgb(0, 255, 0, 0.5f));
		dados.setCenterX(710);
		dados.setCenterY(327);
		dados.setRadius(32);
		
		group.getChildren().addAll(circulo, grupoInstrucoes, grupoDados);
		
		adicionarTexto();
		
	}

	@Override
	protected void definirAcoesEspecificas() {
		// TODO Auto-generated method stub
		
	}

}
