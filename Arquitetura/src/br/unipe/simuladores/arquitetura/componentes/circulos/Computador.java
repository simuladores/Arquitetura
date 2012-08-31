package br.unipe.simuladores.arquitetura.componentes.circulos;

import br.unipe.simuladores.arquitetura.componentes.interfaces.ComponenteCirculo;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;
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

public class Computador extends ComponenteCirculo {

	private Group grupoES;
	private Group grupoMemoriaPrincipal;
	private Group grupoSistemaInterconexao;
	private Group grupoUCP;
	
	private UCP ucp;
	private MemoriaPrincipal memoriaPrincipal;
	private EntradaSaida entradaSaida;
	private SistemaInterconexao sistemaInterconexao;
	
	private Text textClique;
	private Text txtComputador;
	
	private boolean encolhido = false;
	
	private static final String COMPUTADOR_NAO_EPANDIDO_TXT = "Este c�rculo " +
			"representa um computador.\n Clique nesse c�rculo para" +
			" ver seus componentes\n internos e para iniciar a simula��o.";
	private static final String COMPUTADOR_EXPANDIDO_TXT = "Essa figura representa um computador com seus\n componentes " +
			"principais representados por c�rculos.\n Para ver datalhes de cada" +
			" um deles e iniciar a simula��o\n com eles envolvidos, clique no " +
			"c�rculo que os representam. ";
	
	public Computador() {
		
		super();
		expanded = false;
		ucp = new UCP();
		memoriaPrincipal = new MemoriaPrincipal();
		entradaSaida = new EntradaSaida();
		sistemaInterconexao = new SistemaInterconexao();
		
		
		
	}
	
	@Override
	public void expandir(double fromScale, double toScale, double time) {
		
		expanded = true;
		
		group.setCursor(Cursor.DEFAULT);
		
		//reconstr�i o conte�do interno do computador
		buildContent();
		
		//cria a anima��o
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
		
		//executa a anima��o
		timeline.play();
		
	}

	@Override
	public void encolher(double fromScale, double toScale, double time) {
		
		if(!encolhido) {
			
			//cria a anima��o
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
			
			//executa a anima��o
			timeline.play();
			
		}
		
		encolhido = true;
		
	}
	
	@Override
	protected void adicionarTexto() {
		
		group.getChildren().remove(txtComputador);
		
        txtComputador = new Text("COMPUTADOR");
		
		//se for expandir adiciona outros elementos ao c�rculo do computador
		if (expanded) {
				
			txtComputador.setX(560);
			txtComputador.setY(240);
			
			Text txtEntradaSaida = new Text("Entrada/Sa�da");
			txtEntradaSaida.setX(480);
			txtEntradaSaida.setY(300);
			
			Text txtMemoriaPrincipal = new Text("Mem�ria principal");
			txtMemoriaPrincipal.setX(640);
			txtMemoriaPrincipal.setY(300);
			
			Text txtSistema = new Text("Sistema de interconex�o");
			txtSistema.setX(535);
			txtSistema.setY(400);
			
			Text txtUcp = new Text("Unidade central de\n processamento");
			txtUcp.setX(550);
			txtUcp.setY(520);
			
			grupoES.getChildren().add(txtEntradaSaida);
			grupoMemoriaPrincipal.getChildren().add(txtMemoriaPrincipal);
			grupoSistemaInterconexao.getChildren().add(txtSistema);
			grupoUCP.getChildren().add(txtUcp);
			
			group.getChildren().add(txtComputador);
			
		} else {
			
			textClique = new Text("Clique para expandir");
			textClique.setX(550);
			textClique.setY(300);
			
			txtComputador.setFont(new Font(11));
			txtComputador.setX(562);
			txtComputador.setY(402);
			group.getChildren().addAll(txtComputador, textClique);
			
		}
		
	}

	@Override
	protected void buildContent() {

		//remove todos os elementos do grupo desse componente para evitar a repeti��o deles
		group.getChildren().removeAll(circulo, grupoES, grupoMemoriaPrincipal, 
				grupoSistemaInterconexao, grupoUCP);
		
		
		//se for expandir adiciona outros textos ao c�rculo do computador
		if (expanded) {
			
			//group = new Group();
			
			Circle es = new Circle();
			Circle memoriaPrincipal = new Circle();
			Circle barramento = new Circle();
			Circle cpu = new Circle();
			
			grupoES = new Group();
			grupoES.getChildren().add(es);
			grupoMemoriaPrincipal = new Group();
			grupoMemoriaPrincipal.getChildren().add(memoriaPrincipal);
			grupoSistemaInterconexao = new Group();
			grupoSistemaInterconexao.getChildren().add(barramento);
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
			
			group.getChildren().addAll(circulo, grupoES, grupoMemoriaPrincipal, grupoSistemaInterconexao,
					grupoUCP);
			
		
		} else {
			
			circulo = new Circle(40, Color.BLUE);
			circulo.setCenterX(600);
			circulo.setCenterY(400);
			
			group.getChildren().add(circulo);
			
		}
		
		if (expanded)
			definirAcoesEspecificasExpandido();
		else
			definirAcoesEspecificas();
		
		adicionarTexto();

		
	}
	
	public boolean todosComponentesInternosExpandidos() {
		
		return ucp.getUCPInterna() != null && 
				memoriaPrincipal.getMemoriaInterna() != null && 
				sistemaInterconexao.getBarramentoInterno() != null;
		
	}
	

	public void definirAcoesEspecificas() {
		
		group.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				
				//expande o computador
				if (!expanded) {
					textClique.setVisible(false);
					expandir(0.2, 1, 3000);
					expanded = true;
				}
				
			}
			
		});
		
		/*Define as a��es que ocorrem com os componentes*/
		group.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				
				if (!encolhido) {
					if (expanded) {
						
						group.setCursor(Cursor.DEFAULT);
						
						exibirMensagemExplicativa();
					}
					else{
						
						group.setCursor(Cursor.HAND);
						
						exibirMensagemExplicativa();
						
					}
				} else {
					
					esconderMensagemExplicativa();
					
				}
					
			}
				
		});
		
	}
	
	public void definirAcoesEspecificasExpandido() {
		
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
					TelaPrincipal.adicionarAoPalco(memoriaPrincipal.getContent());
					memoriaPrincipal.expandir(1, 2, 3000);
				}
	
				
			}
			
		});
		
		grupoSistemaInterconexao.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
								
				grupoSistemaInterconexao.setCursor(Cursor.HAND);
				
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
					TelaPrincipal.adicionarAoPalco(ucp.getContent());
					ucp.expandir(1, 2.5, 3000);
				}
	
				
			}
			
		});
		
		grupoES.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				
				encolher(1, 0.5, 3000);
			    
				if (!entradaSaida.isExpanded()) { 
					TelaPrincipal.adicionarAoPalco(entradaSaida.getContent());
					entradaSaida.expandir(1, 2.5, 3000);
				}
	
				
			}
			
		});
		
		grupoSistemaInterconexao.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				
				if (ucp.isExpanded() && memoriaPrincipal.isExpanded() && entradaSaida.isExpanded() ) {
					
					if (! sistemaInterconexao.isExpanded()) {
						
						TelaPrincipal.adicionarAoPalco(sistemaInterconexao.getContent());
						sistemaInterconexao.expandir(1, 2.5, 3000);
						
					}
					
				}
				
			}
			
		});
		
	}
	
	public MemoriaPrincipal getMemoriaPrincipal() {
		
		return memoriaPrincipal;
	}
	
	public UCP getUCP() {
		
		return ucp;
	}

	@Override
	public String obterTextoExplicativo() {
		
		if (!expanded)
			return COMPUTADOR_NAO_EPANDIDO_TXT;
		else
			return COMPUTADOR_EXPANDIDO_TXT;
		
	}

	public SistemaInterconexao getSistemaInterconexao() {
		return sistemaInterconexao;
	}

	public void setSistemaInterconexao(SistemaInterconexao sistemaInterconexao) {
		this.sistemaInterconexao = sistemaInterconexao;
	}


}
