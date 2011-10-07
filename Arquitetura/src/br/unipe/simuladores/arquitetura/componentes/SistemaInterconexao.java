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

public class SistemaInterconexao extends Componente{

	private Group grupoBarramento;
	private Group grupoModuloES;
	
	private Circle barramentoCirc;
	private Circle moduloESCirc;
	private Text barramentoTxt;
	private Text moduloESTxt;
	
	public SistemaInterconexao() {
		
		super();
		expanded = false;
		
	}
	
	@Override
	public void expandir(double fromScale, double toScale, double time) {
		
		expanded = true;
		
       grupoBarramento.setCursor(Cursor.DEFAULT);
       grupoModuloES.setCursor(Cursor.DEFAULT);
		
		//cria a animação
	    timeline = new Timeline();
		
		timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, 
                    new KeyValue(barramentoCirc.opacityProperty(), 0.0f),
                    new KeyValue(moduloESCirc.opacityProperty(), 0.0f),
                    new KeyValue(barramentoTxt.opacityProperty(), 0.0f),
                    new KeyValue(moduloESTxt.opacityProperty(), 0.0f)
                ),
                new KeyFrame(new Duration(time), 
                		 new KeyValue(barramentoCirc.opacityProperty(), 0.5f),
                         new KeyValue(moduloESCirc.opacityProperty(), 0.5f),
                         new KeyValue(barramentoTxt.opacityProperty(), 1.0f),
                         new KeyValue(moduloESTxt.opacityProperty(), 1.0f)
                )
         );
		
		timeline.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				/*final Line linha = new Line();
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
			                new KeyValue(linha.startXProperty(), 892),
			                new KeyValue(linha.startYProperty(), 356),
			                new KeyValue(linha.endXProperty(), 892),
			                new KeyValue(linha.endYProperty(), 356),
			                new KeyValue(linha2.startXProperty(), 800),
			                new KeyValue(linha2.startYProperty(), 650),
			                new KeyValue(linha2.endXProperty(), 800),
			                new KeyValue(linha2.endYProperty(), 650)
		                ),
		                new KeyFrame(new Duration(time),		                		
		                	new KeyValue(linha.startXProperty(), 892),
			                new KeyValue(linha.startYProperty(), 356),
			                new KeyValue(linha.endXProperty(), 600),
			                new KeyValue(linha.endYProperty(), 425),
			                new KeyValue(linha2.startXProperty(), 800),
			                new KeyValue(linha2.startYProperty(), 650),
			                new KeyValue(linha2.endXProperty(), 600),
			                new KeyValue(linha2.endYProperty(), 495)
		                )
		         );
				
				
				timeline2.play();*/
				
				Line linha2 = new Line();
				linha2.setStartX(600);
				linha2.setStartY(495);
				linha2.setEndX(800);
				linha2.setEndY(650);

				
				Main.adicionarAoPalco(linha2);
				
				
				linha2.toBack();
				linha2.setStrokeWidth(0.5);

			}
	    	
	    });
		
		timeline.play();
		
	}

	@Override
	public void encolher(double fromScale, double toScale, double time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void adicionarTexto() {
		
		barramentoTxt = new Text("Barramento");
		barramentoTxt.setX(1070);
		barramentoTxt.setY(320);
		barramentoTxt.setFont(new Font(12));
		
		moduloESTxt = new Text("Módulo de E/S");
		moduloESTxt.setX(410);
		moduloESTxt.setY(580);
		moduloESTxt.setFont(new Font(12));
		
		grupoBarramento.getChildren().add(barramentoTxt);
		grupoModuloES.getChildren().add(moduloESTxt);
		
	}

	@Override
	protected void buildContent() {
		
		//remove todos os elementos do grupo desse componente para evitar a repetição deles
		group.getChildren().removeAll(grupoBarramento,grupoModuloES);
		
		barramentoCirc = new Circle();
	    moduloESCirc = new Circle();
		
		grupoBarramento = new Group();
		grupoBarramento.getChildren().add(barramentoCirc);
		grupoModuloES = new Group();
		grupoModuloES.getChildren().add(moduloESCirc);
		
		barramentoCirc.setFill(Color.rgb(126, 126, 0));
		barramentoCirc.setCenterX(1100);
		barramentoCirc.setCenterY(320);
		barramentoCirc.setRadius(70);
		
		moduloESCirc.setFill(Color.rgb(126, 126, 0));
		moduloESCirc.setCenterX(450);
		moduloESCirc.setCenterY(580);
		moduloESCirc.setRadius(70);
		
		group.getChildren().addAll(grupoBarramento,grupoModuloES);
		
		adicionarTexto();
		
		
	}

	@Override
	protected void definirAcoesEspecificas() {
		// TODO Auto-generated method stub
		
	}

}
