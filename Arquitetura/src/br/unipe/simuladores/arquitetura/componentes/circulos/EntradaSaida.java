package br.unipe.simuladores.arquitetura.componentes.circulos;

import br.unipe.simuladores.arquitetura.componentes.interfaces.ComponenteCirculo;
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

public class EntradaSaida extends ComponenteCirculo{

	private Group grupoLegiveisSerHumano;
	private Group grupoLegiveisMaquina;
	private Group grupoComunicacaoDispositivosRemotos;
	
	public EntradaSaida() {
		
		super();
		expanded = false;
		
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
                	new KeyValue(group.translateXProperty(), -200),
                	new KeyValue(group.translateYProperty(), -130)
                )
         );
		
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
				                new KeyValue(linha.startXProperty(), 463),
				                new KeyValue(linha.startYProperty(), 100),
				                new KeyValue(linha.endXProperty(), 463),
				                new KeyValue(linha.endYProperty(), 100),
				                new KeyValue(linha2.startXProperty(), 315),
				                new KeyValue(linha2.startYProperty(), 346),
				                new KeyValue(linha2.endXProperty(), 315),
				                new KeyValue(linha2.endYProperty(), 346)
			                ),
			                new KeyFrame(new Duration(time),		                		
			                	new KeyValue(linha.startXProperty(), 463),
				                new KeyValue(linha.startYProperty(), 100),
				                new KeyValue(linha.endXProperty(), 565),
				                new KeyValue(linha.endYProperty(), 320),
				                new KeyValue(linha2.startXProperty(), 315),
				                new KeyValue(linha2.startYProperty(), 346),
				                new KeyValue(linha2.endXProperty(), 538),
				                new KeyValue(linha2.endYProperty(), 382)
			                )
			         );
					
					
					
					timeline2.play();
					
					/*Line linha = new Line();
					linha.setStartX(463);
					linha.setStartY(100);
					linha.setEndX(565);
					linha.setEndY(320);
					
					Line linha2 = new Line();
					linha2.setStartX(315);
					linha2.setStartY(346);
					linha2.setEndX(538);
					linha2.setEndY(381);

					
					Main.adicionarAoPalco(linha);
					Main.adicionarAoPalco(linha2);
					
					
					linha.toBack();
					linha.setStrokeWidth(0.5);
					
					linha2.toBack();
					linha2.setStrokeWidth(0.5);*/

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

		Text es = new Text("Entrada/Saída");
		es.setX(500);
		es.setY(260);
		es.setFont(new Font(6));
		
		Text legiveisSerHumano = new Text("Legíveis\n ao ser\n humano");
		legiveisSerHumano.setX(482);
		legiveisSerHumano.setY(288);
		legiveisSerHumano.setFont(new Font(5));
		
		Text legiveisMaquina = new Text("Legíveis\n à Máquina");
		legiveisMaquina.setX(537);
		legiveisMaquina.setY(292);
		legiveisMaquina.setFont(new Font(5));
		
		Text comunicacaoDispositivosRemotos = 
				new Text("   Comunicação \ncom dispositivos\n       remotos");
		comunicacaoDispositivosRemotos.setX(500);
		comunicacaoDispositivosRemotos.setY(340);
		comunicacaoDispositivosRemotos.setFont(new Font(5));
		
		group.getChildren().add(es);
		
		grupoLegiveisSerHumano.getChildren().add(legiveisSerHumano);
		grupoLegiveisMaquina.getChildren().add(legiveisMaquina);
		grupoComunicacaoDispositivosRemotos.getChildren()
			.add(comunicacaoDispositivosRemotos);
		
	}

	@Override
	protected void buildContent() {
		
		//remove todos os elementos do grupo desse componente para evitar a repetição deles
			group.getChildren().removeAll(circulo, grupoLegiveisSerHumano, 
						grupoLegiveisMaquina, grupoComunicacaoDispositivosRemotos);
				
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
		
		adicionarTexto();
		
	}

	@Override
	protected void definirAcoesEspecificas() {
		// TODO Auto-generated method stub
		
	}

}
