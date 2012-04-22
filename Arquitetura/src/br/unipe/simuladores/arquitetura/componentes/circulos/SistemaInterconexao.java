package br.unipe.simuladores.arquitetura.componentes.circulos;

import br.unipe.simuladores.arquitetura.componentes.interfaces.ComponenteCirculoQuebravel;
import br.unipe.simuladores.arquitetura.componentes.internos.BarramentoInterno;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class SistemaInterconexao extends ComponenteCirculoQuebravel{

	private Group grupoBarramento;
	private Group grupoModuloES;
	
	private boolean barramentoQuebrado = false;
	private boolean moduloESQuebrado = false;
	
	private Circle barramentoCirc;
	private Circle moduloESCirc;
	private Text barramentoTxt;
	private Text moduloESTxt;
	
	private Seta seta1;
	private Seta seta2;
	private Seta seta3;
	private Seta seta4;
	
	private BarramentoInterno barramentoInterno;
	
	public SistemaInterconexao() {
		
		super();
		expanded = false;
		definirAcoesEspecificas();
		
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
				
				seta1 = new Seta(new double[]{420, 517, 320, 360}, 
						new double[]{330, 363, 320, 360, 320, 370});
				group.getChildren().add(seta1);
				
				seta2 = new Seta(new double[]{516, 604, 780, 660}, 
						new double[]{770, 665, 780, 660, 775, 652});
				group.getChildren().add(seta2);
				
				seta3 = new Seta(new double[]{1116, 252, 1030, 200}, 
						new double[]{1040, 198, 1030, 200, 1035, 210});
				group.getChildren().add(seta3);
								
				seta4 = new Seta(new double[]{1116, 388, 1060, 440}, 
						new double[]{1062, 430, 1060, 440, 1071, 438});
				group.getChildren().add(seta4);
								
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
		
		 group.setOnMouseEntered(new EventHandler<MouseEvent>(){

			   @Override
			   public void handle(MouseEvent e) {
				   
				   if (!broken) {
					   
					   exibirMensagemExplicativa();
					   
				   } else {
					   
					   group.setCursor(Cursor.DEFAULT);
					   
					   esconderMensagemExplicativa();
					   
				   }
				   
				
			   }
			   
		   });
		
		grupoBarramento.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
								
				grupoBarramento.setCursor(Cursor.HAND);
				
			}
			
		});
		
		grupoBarramento.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				
				if(grupoBarramento.getOpacity() != 0.0f) {

					barramentoInterno = new BarramentoInterno();
					TelaPrincipal.adicionarAoPalco(barramentoInterno.getContent());
					
					barramentoQuebrado = true;
					quebrar(3000);
					barramentoInterno.surgir(3000);
					
				}
				
			}
			
		});
		
	}

	@Override
	public void quebrar(double time) {
		
		Group grupo;
		Seta st1;
		Seta st2;
		
		if (barramentoQuebrado) { 
			grupo = grupoBarramento;
			st1 = seta3;
			st2 = seta4;
		}
		else if (moduloESQuebrado) {
			grupo = grupoModuloES;
			st1 = seta1;
			st2 = seta2;
		}
		else
			return;
		
		//cria a animação
	    timeline = new Timeline();
		
		timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, 
                    new KeyValue(grupo.opacityProperty(), 0.5f),
                    new KeyValue(st1.opacityProperty(), 0.5f),
                    new KeyValue(st2.opacityProperty(), 0.5f)
                ),
                new KeyFrame(new Duration(time), 
                		new KeyValue(grupo.opacityProperty(), 0.0f),
                		new KeyValue(st1.opacityProperty(), 0.0f),
                        new KeyValue(st2.opacityProperty(), 0.0f)
                )
         );
		
		timeline.play();
		
		if (TelaPrincipal.getComputador().todosComponentesInternosExpandidos())
			TelaPrincipal.getMenuSuperior().getMenus().get(0).getItems().get(0)
				.setDisable(false);
		
		broken = true;
		
		exibirMenus();
		
	}

	@Override
	public String obterTextoExplicativo() {
		
		return "Esse componente faz parte do sistema de\n interconexão. " +
				"Para ver mais detalhes sobre\n algum desses componentes" +
				" clique no círculo\n \"Barramento\" ou \"Módulo de E/S\"";
		
	}

	public BarramentoInterno getBarramentoInterno() {
		return barramentoInterno;
	}

	public void setBarramentoInterno(BarramentoInterno barramentoInterno) {
		this.barramentoInterno = barramentoInterno;
	}

}
