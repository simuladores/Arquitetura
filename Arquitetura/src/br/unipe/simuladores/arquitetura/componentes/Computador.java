package br.unipe.simuladores.arquitetura.componentes;

import br.unipe.simuladores.arquitetura.principal.Main;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Computador extends Componente {

	private Group grupoES;
	private Group grupoMemoriaPrincipal;
	private Group grupoBarramento;
	private Group grupoUCP;
	
	private UCP ucp;
	private MemoriaPrincipal memoriaPrincipal;
	
	public Computador() {
		
		super();
		expanded = false;
		buildContent();
		ucp = new UCP();
		memoriaPrincipal = new MemoriaPrincipal();
		
	}
	
	@Override
	public void expandir(double fromScale, double toScale, double time) {
		
		expanded = true;
		
		group.setCursor(Cursor.DEFAULT);
		
		//reconstrói o conteúdo interno do computador
		buildContent();
		
		//cria a animação
	    timeline = new Timeline();
		
		timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, 
                    new KeyValue(group.scaleXProperty(), fromScale),
                    new KeyValue(group.scaleYProperty(), fromScale)
                ),
                new KeyFrame(new Duration(time), 
                    new KeyValue(group.scaleXProperty(), toScale),
                    new KeyValue(group.scaleYProperty(), toScale)
                )
         );
		
		//executa a animação
		timeline.play();
		
	}

	@Override
	public void encolher(double fromScale, double toScale, double time) {
		
		if(expanded) {
			
			//cria a animação
		    timeline = new Timeline();
			
			timeline.getKeyFrames().addAll(
	                new KeyFrame(Duration.ZERO, 
	                    new KeyValue(group.scaleXProperty(), fromScale),
	                    new KeyValue(group.scaleYProperty(), fromScale)
	                ),
	                new KeyFrame(new Duration(time), 
	                    new KeyValue(group.scaleXProperty(), toScale),
	                    new KeyValue(group.scaleYProperty(), toScale)
	                )
	         );
			
			//executa a animação
			timeline.play();
			
		}
		
		expanded = false;
		
	}

	@Override
	public Group getContent() {
		
		/*retorna a referência do grupo que contém os elementos desse componente para 
		cena. Assim, qualquer alteração que for feita aqui será refletida lá*/
		return group;
		
	}
	
	@Override
	protected void adicionarTexto() {
		
		Text txtComputador = new Text("COMPUTADOR");
		
		//se for expandir adiciona outros elementos ao círculo do computador
		if (expanded) {
						
			txtComputador.setX(560);
			txtComputador.setY(240);
			
			Text txtEntradaSaida = new Text("Entrada/Saída");
			txtEntradaSaida.setX(480);
			txtEntradaSaida.setY(300);
			
			Text txtMemoriaPrincipal = new Text("Memória principal");
			txtMemoriaPrincipal.setX(640);
			txtMemoriaPrincipal.setY(300);
			
			Text txtSistema = new Text("Sistema de interconexão");
			txtSistema.setX(535);
			txtSistema.setY(400);
			
			Text txtUcp = new Text("Unidade central de\n processamento");
			txtUcp.setX(550);
			txtUcp.setY(520);
			
			grupoES.getChildren().add(txtEntradaSaida);
			grupoMemoriaPrincipal.getChildren().add(txtMemoriaPrincipal);
			grupoBarramento.getChildren().add(txtSistema);
			grupoUCP.getChildren().add(txtUcp);
			
			group.getChildren().add(txtComputador);
			
		} else {
			
			txtComputador.setFont(new Font(11));
			txtComputador.setX(562);
			txtComputador.setY(402);
			group.getChildren().add(txtComputador);
			
		}
		
	}

	@Override
	protected void buildContent() {

		//remove todos os elementos do grupo desse componente para evitar a repetição deles
		group.getChildren().removeAll(circulo, grupoES, grupoMemoriaPrincipal, 
				grupoBarramento, grupoUCP);
		
		//se for expandir adiciona outros textos ao círculo do computador
		if (expanded) {
			
			Circle es = new Circle();
			Circle memoriaPrincipal = new Circle();
			Circle barramento = new Circle();
			Circle cpu = new Circle();
			
			grupoES = new Group();
			grupoES.getChildren().add(es);
			grupoMemoriaPrincipal = new Group();
			grupoMemoriaPrincipal.getChildren().add(memoriaPrincipal);
			grupoBarramento = new Group();
			grupoBarramento.getChildren().add(barramento);
			grupoUCP = new Group();
			grupoUCP.getChildren().add(cpu);
			
			circulo.setFill(Color.AZURE);
			circulo.setRadius(200);
			
			es.setFill(Color.rgb(255, 0, 0, 0.5f));
			es.setCenterX(520);
			es.setCenterY(310);
			es.setRadius(70);
			
			memoriaPrincipal.setFill(Color.rgb(0, 255, 0, 0.5f));
			memoriaPrincipal.setCenterX(680);
			memoriaPrincipal.setCenterY(310);
			memoriaPrincipal.setRadius(70);
			
			barramento.setFill(Color.rgb(0, 0, 255, 0.5f));
			barramento.setCenterX(600);
			barramento.setCenterY(400);
			barramento.setRadius(70);
			
			cpu.setFill(Color.rgb(126, 126, 0, 0.5f));
			cpu.setCenterX(600);
			cpu.setCenterY(520);
			cpu.setRadius(70);
			
			group.setBlendMode(BlendMode.MULTIPLY);
			
			group.getChildren().addAll(circulo, grupoES, grupoMemoriaPrincipal, grupoBarramento,
					grupoUCP);
			
			definirAcoesEspecificas();
		
		} else {
			
			circulo = new Circle(40, Color.BLUE);
			circulo.setCenterX(600);
			circulo.setCenterY(400);
			
			group.getChildren().addAll(circulo);
			
		}
		
		adicionarTexto();	

		
	}
	

	public void definirAcoesEspecificas() {
		
		grupoES.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
								
				grupoES.setCursor(Cursor.HAND);
				
			}
			
		});
		
		
		grupoMemoriaPrincipal.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
								
				grupoMemoriaPrincipal.setCursor(Cursor.HAND);
				
			}
			
		});
		
		grupoMemoriaPrincipal.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				
				encolher(1, 0.5, 3000);
			    
				if (!memoriaPrincipal.isExpanded()) { 
					Main.adicionarAoPalco(memoriaPrincipal.getContent());
					memoriaPrincipal.expandir(1, 2, 3000);
				}
	
				
			}
			
		});
		
		grupoBarramento.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
								
				grupoBarramento.setCursor(Cursor.HAND);
				
			}
			
		});
		
		grupoUCP.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
								
				grupoUCP.setCursor(Cursor.HAND);
				
			}
			
		});
		
		grupoUCP.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				
				encolher(1, 0.5, 3000);
			    
				if (!ucp.isExpanded()) { 
					Main.adicionarAoPalco(ucp.getContent());
					ucp.expandir(1, 2.5, 3000);
				}
	
				
			}
			
		});
		
		
	}


}
