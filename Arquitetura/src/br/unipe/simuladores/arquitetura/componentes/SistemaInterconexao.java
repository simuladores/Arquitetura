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
import javafx.scene.shape.Polyline;
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
				
				Line linha1 = new Line();
				linha1.setStartX(420);
				linha1.setStartY(517);
				linha1.setEndX(320);
				linha1.setEndY(360);
				linha1.setStrokeWidth(0.8);
                
				Polyline polyline1 = new Polyline(new double[]{
						330, 363,
						320, 360,
						320, 370,
				});
				polyline1.setStrokeWidth(0.8);
				
				Main.adicionarAoPalco(linha1);
				Main.adicionarAoPalco(polyline1);
				
				Line linha2 = new Line();
				linha2.setStartX(516);
				linha2.setStartY(604);
				linha2.setEndX(780);
				linha2.setEndY(660);
				linha2.setStrokeWidth(0.8);
				
				Polyline polyline2 = new Polyline(new double[]{
						770, 665,
						780, 660,
						775, 652,
				});
				polyline2.setStrokeWidth(0.8);
				
				Main.adicionarAoPalco(linha2);
				Main.adicionarAoPalco(polyline2);
				
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
