package br.unipe.simuladores.arquitetura.componentes.circulos;

import br.unipe.simuladores.arquitetura.componentes.interfaces.ComponenteCirculoQuebravel;
import br.unipe.simuladores.arquitetura.componentes.internos.UCPInterna;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class UCP extends ComponenteCirculoQuebravel{
	
	private Group grupoRegistradores;
	private Group grupoALU;
	private Group grupoBarramentoInterno;
	private Group grupoUnidadeControle;
	private UCPInterna ucpInterna;
	
	private static final String UCP_TXT = "Esse figura representa uma UCP " +
			"(Unidade Central de\n Processamento), ou do ingl�s, CPU. Uma UCP � " +
			"formada por\n registradores, unidade l�gica e aritm�tica, barramento " +
			"interno\n e unidade de controle. Para saber mais detalhes sobre cada\n " +
			"um desses componentes, clique com o mouse.";
	
	public UCP() {
		
		super();
		expanded = false;
		buildContent();		
	}

	@Override
	public void expandir(double fromScale, double toScale, final double time) {
		
		expanded = true;
		
		group.setCursor(Cursor.DEFAULT);
		
		//cria a anima��o
	    timeline = new Timeline();
		
		timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, 
                    new KeyValue(group.scaleXProperty(), fromScale),
                    new KeyValue(group.scaleYProperty(), fromScale)
                ),
                new KeyFrame(new Duration(time), 
                    new KeyValue(group.scaleXProperty(), toScale),
                    new KeyValue(group.scaleYProperty(), toScale),
                	new KeyValue(group.translateXProperty(), 300)
                )
         );
		
		
		
	        
	    timeline.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				final Line linha = new Line();
				final Line linha2 = new Line();
				
				TelaPrincipal.adicionarAoPalco(linha);
				TelaPrincipal.adicionarAoPalco(linha2);
				
				linha.toBack();
				linha2.toBack();
				linha.setStrokeWidth(0.5);
				linha2.setStrokeWidth(0.5);
				
				Timeline timeline2 = new Timeline();
				
				timeline2.getKeyFrames().addAll(
		                new KeyFrame(Duration.ZERO, 
			                new KeyValue(linha.startXProperty(), 842),
			                new KeyValue(linha.startYProperty(), 370),
			                new KeyValue(linha.endXProperty(), 842),
			                new KeyValue(linha.endYProperty(), 370),
			                new KeyValue(linha2.startXProperty(), 800),
			                new KeyValue(linha2.startYProperty(), 650),
			                new KeyValue(linha2.endXProperty(), 800),
			                new KeyValue(linha2.endYProperty(), 650)
		                ),
		                new KeyFrame(new Duration(time),		                		
		                	new KeyValue(linha.startXProperty(), 842),
			                new KeyValue(linha.startYProperty(), 370),
			                new KeyValue(linha.endXProperty(), 600),
			                new KeyValue(linha.endYProperty(), 425),
			                new KeyValue(linha2.startXProperty(), 800),
			                new KeyValue(linha2.startYProperty(), 650),
			                new KeyValue(linha2.endXProperty(), 600),
			                new KeyValue(linha2.endYProperty(), 495)
		                )
		         );
				
				
				timeline2.play();
				
				/*Line linha2 = new Line();
				linha2.setStartX(600);
				linha2.setStartY(495);
				linha2.setEndX(800);
				linha2.setEndY(650);

				
				Main.adicionarAoPalco(linha2);
				
				
				linha2.toBack();
				linha2.setStrokeWidth(0.5);*/

			}
	    	
	    });
		
		//executa a anima��o
		timeline.play();
		
		
	}

	@Override
	public void encolher(double fromScale, double toScale, double time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void adicionarTexto() {
		
		Text cpu = new Text("UCP");
		cpu.setX(591);
		cpu.setY(470);
		cpu.setFont(new Font(8));
		
		Text registradores = new Text("Registradores");
		registradores.setX(562);
		registradores.setY(490);
		registradores.setFont(new Font(4));
		
		Text alu = new Text("Unidade L�gica\n e Aritm�tica");
		alu.setX(614);
		alu.setY(484.9);
		alu.setFont(new Font(4));
		
		Text barramentoInterno = new Text("Barramento Interno");
		barramentoInterno.setX(582);
		barramentoInterno.setY(520);
		barramentoInterno.setFont(new Font(4));
		
		Text unidadeControle = new Text("Unidade de Controle");
		unidadeControle.setX(581);
		unidadeControle.setY(560);
		unidadeControle.setFont(new Font(4));
		
		group.getChildren().add(cpu);
		
		grupoRegistradores.getChildren().add(registradores);
		grupoALU.getChildren().add(alu);
		grupoBarramentoInterno.getChildren().add(barramentoInterno);
		grupoUnidadeControle.getChildren().add(unidadeControle);
		
	}

	@Override
	protected void buildContent() {
		
		//remove todos os elementos do grupo desse componente para evitar a repeti��o deles
		group.getChildren().removeAll(circulo, grupoRegistradores, grupoALU, 
						grupoBarramentoInterno, grupoUnidadeControle);
		
		Circle registradores = new Circle();
		Circle alu = new Circle();
		Circle barramentoInterno = new Circle();
		Circle unidadeControle = new Circle();
		
		grupoRegistradores = new Group();
		grupoRegistradores.getChildren().add(registradores);
		grupoALU = new Group();
		grupoALU.getChildren().add(alu);
		grupoBarramentoInterno = new Group();
		grupoBarramentoInterno.getChildren().add(barramentoInterno);
		grupoUnidadeControle = new Group();
		grupoUnidadeControle.getChildren().add(unidadeControle);
		
	    circulo = new Circle(66, Color.AZURE);
		circulo.setCenterX(600);
		circulo.setCenterY(520);
		
		registradores.setFill(Color.rgb(255, 0, 0, 0.5f));
		registradores.setCenterX(573);
		registradores.setCenterY(490);
		registradores.setRadius(23);
		
		alu.setFill(Color.rgb(0, 255, 0, 0.5f));
		alu.setCenterX(627);
		alu.setCenterY(490);
		alu.setRadius(23);
		
		barramentoInterno.setFill(Color.rgb(0, 0, 255, 0.5f));
		barramentoInterno.setCenterX(600);
		barramentoInterno.setCenterY(520);
		barramentoInterno.setRadius(23);
		
		unidadeControle.setFill(Color.rgb(126, 126, 0, 0.5f));
		unidadeControle.setCenterX(600);
		unidadeControle.setCenterY(560);
		unidadeControle.setRadius(23);
		
		group.getChildren().addAll(circulo, grupoRegistradores, grupoALU, 
				grupoBarramentoInterno, grupoUnidadeControle);
		
		adicionarTexto();
		
		definirAcoesEspecificas();
		
	}


	@Override
	protected void definirAcoesEspecificas() {
		
		 group.setOnMouseEntered(new EventHandler<MouseEvent>(){

			   @Override
			   public void handle(MouseEvent e) {
				 
				   if (!broken) {
					   
					   group.setCursor(Cursor.HAND);
					   exibirMensagemExplicativa();
					
					   
				   } else {
					   
					   group.setCursor(Cursor.DEFAULT);
						
					   esconderMensagemExplicativa();
					   
				   }
				  
			   }
			   
		   });
		
		group.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				
				if(group.getOpacity() != 0.0f) {

					ucpInterna = new UCPInterna();
					TelaPrincipal.adicionarAoPalco(ucpInterna.getContent());
					
					quebrar(3000);
					ucpInterna.surgir(3000);
					
				}
				
			}
			
		});
		
	}

	@Override
	public void quebrar(double time) {
		
		//cria a anima��o
	    timeline = new Timeline();
		
		timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, 
                    new KeyValue(group.opacityProperty(), 0.5f)
                ),
                new KeyFrame(new Duration(time), 
                		new KeyValue(group.opacityProperty(), 0.0f)
                )
         );
		
		timeline.play();
		
		broken = true;
		
		exibirMenus();
		
	}
	
	public UCPInterna getUCPInterna() {
		
		return ucpInterna;
		
	}

	@Override
	public String obterTextoExplicativo() {

		return UCP_TXT;
		
	}
	
	

}
