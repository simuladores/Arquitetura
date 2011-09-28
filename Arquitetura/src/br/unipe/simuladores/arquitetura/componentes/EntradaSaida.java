package br.unipe.simuladores.arquitetura.componentes;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class EntradaSaida extends Componente{

	private Group grupoLegiveisSerHumano;
	private Group grupoLegiveisMaquina;
	private Group grupoComunicacaoDispositivosRemotos;
	
	public EntradaSaida() {
		
		super();
		expanded = false;
		buildContent();
		
	}
	
	@Override
	public void expandir(double fromScale, double toScale, double time) {
		
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
                	new KeyValue(group.translateXProperty(), -200),
                	new KeyValue(group.translateYProperty(), -130)
                )
         );
		
		timeline.play();
		
	}

	@Override
	public void encolher(double fromScale, double toScale, double time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void adicionarTexto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void buildContent() {
		
		//remove todos os elementos do grupo desse componente para evitar a repetição deles
		/*		group.getChildren().removeAll(circulo, grupoLegiveisSerHumano, 
						grupoLegiveisMaquina, grupoComunicacaoDispositivosRemotos);*/
				
		Circle legiveisSerHumano = new Circle();
		Circle legiveisMaquina = new Circle();
		Circle comunicacaoDispositivosRemotos = new Circle();
		
		grupoLegiveisSerHumano = new Group();
		grupoLegiveisSerHumano.getChildren().add(legiveisSerHumano);
		grupoLegiveisMaquina = new Group();
		grupoLegiveisMaquina.getChildren().add(legiveisMaquina);
		grupoComunicacaoDispositivosRemotos = new Group();
		grupoComunicacaoDispositivosRemotos.getChildren().add(comunicacaoDispositivosRemotos);
		
		circulo = new Circle(66, Color.AZURE);
		circulo.setCenterX(520);
		circulo.setCenterY(310);
		
		legiveisSerHumano.setFill(Color.rgb(255, 0, 0, 0.5f));
		legiveisSerHumano.setCenterX(492);
		legiveisSerHumano.setCenterY(295);
		legiveisSerHumano.setRadius(26);
		
		legiveisMaquina.setFill(Color.rgb(0, 255, 0, 0.5f));
		legiveisMaquina.setCenterX(549);
		legiveisMaquina.setCenterY(295);
		legiveisMaquina.setRadius(26);
		
		comunicacaoDispositivosRemotos.setFill(Color.rgb(0, 0, 255, 0.5f));
		comunicacaoDispositivosRemotos.setCenterX(520);
		comunicacaoDispositivosRemotos.setCenterY(345);
		comunicacaoDispositivosRemotos.setRadius(26);
		
		//group.getChildren().addAll(circulo, grupoLegiveisSerHumano, 
			//	grupoLegiveisMaquina, grupoComunicacaoDispositivosRemotos);
		
		group.getChildren().addAll(circulo, grupoLegiveisSerHumano, 
				grupoLegiveisMaquina, grupoComunicacaoDispositivosRemotos);
	}

	@Override
	protected void definirAcoesEspecificas() {
		// TODO Auto-generated method stub
		
	}

}
