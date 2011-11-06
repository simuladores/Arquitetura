package br.unipe.simuladores.arquitetura.componentes.internos;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import br.unipe.simuladores.arquitetura.componentes.interfaces.ComponenteInterno;

public class UCPInterna extends ComponenteInterno{
	
	private List<Rectangle> registradores;
	private Rectangle uc;
	private Rectangle pc;
	private Rectangle ir;
	private Polyline ula;
	private Rectangle mar;
	private Rectangle mbr;
	private Rectangle ioar;
	private Rectangle iobr;
	
	private Group grupoRegistradores; 
	
	
	public UCPInterna() {
		
		super();
		
	}

	@Override
	protected void adicionarTexto() {
		
		Text ucp = new Text("UCP");
		ucp.setX(880);
		ucp.setY(400);
		ucp.setFont(new Font(14));
		
		List<Text> registradoresTxt = new ArrayList<Text>();
		
		int i, y = 438;
		Text regTxt; 
		for (i = 0; i < 4; i++, y += 25) {
			
			regTxt = new Text("R"+(i+1));
			regTxt.setX(760);
			regTxt.setY(y);
			regTxt.setFont(new Font(12));
			
			registradoresTxt.add(regTxt);
		
		}
		
		
		for (Text rg : registradoresTxt)
			group.getChildren().add(rg);
		
		Text uc = new Text("UC");
		uc.setX(760);
		uc.setY(618);
		uc.setFont(new Font(12));
		
		Text pc = new Text("PC");
		pc.setX(848);
		pc.setY(438);
		pc.setFont(new Font(12));
		
		Text ir = new Text("IR");
		ir.setX(848);
		ir.setY(508);
		ir.setFont(new Font(12));
		
		Text ula = new Text("ULA");
		ula.setX(875);
		ula.setY(610);
		ula.setFont(new Font(12));
		
		Text mar = new Text("MAR");
		mar.setX(932);
		mar.setY(438);
		mar.setFont(new Font(12));
		
		Text mbr = new Text("MBR");
		mbr.setX(932);
		mbr.setY(478);
		mbr.setFont(new Font(12));
		
		Text ioar = new Text("I/O AR");
		ioar.setX(972);
		ioar.setY(588);
		ioar.setFont(new Font(12));
		
		Text iobr = new Text("I/O BR");
		iobr.setX(972);
		iobr.setY(638);
		iobr.setFont(new Font(12));
		
		group.getChildren().addAll(ucp, uc, pc, ir, ula, mar, mbr, ioar, iobr);
		
	}

	@Override
	protected void buildContent() {
		
		group.getChildren().removeAll(retangulo, grupoRegistradores, uc, pc, ir, ula,
				mar, mbr, ioar, iobr);
		
		registradores = new ArrayList<Rectangle>();
		grupoRegistradores = new Group();
		
		retangulo.setWidth(280);
		retangulo.setHeight(280);
		retangulo.setFill(Color.WHEAT);
		retangulo.setX(750);
		retangulo.setY(370);
		
		int i, y = 420;
		Rectangle registrador;
		for (i = 0; i < 4; i++, y += 25) {
			
			registrador = new Rectangle();
			registrador.setWidth(50);
			registrador.setHeight(25);
			registrador.setFill(Color.CHARTREUSE);
			registrador.setX(780);
			registrador.setY(y);
			registrador.setStroke(Color.BLACK);
			registrador.setStrokeWidth(0.5);
			
			registradores.add(registrador);
			
		}
		
		for (Rectangle reg : registradores) 
			grupoRegistradores.getChildren().add(reg);
		
		uc = new Rectangle();
		uc.setWidth(50);
		uc.setHeight(25);
		uc.setFill(Color.CORAL);
		uc.setX(780);
		uc.setY(600);
		
		pc = new Rectangle();
		pc.setWidth(50);
		pc.setHeight(25);
		pc.setFill(Color.SANDYBROWN);
		pc.setX(870);
		pc.setY(420);
		
		ir = new Rectangle();
		ir.setWidth(50);
		ir.setHeight(25);
		ir.setFill(Color.TURQUOISE);
		ir.setX(870);
		ir.setY(490);

		
	    ula = new Polyline(new double[]{
				850, 550,
				880, 550,
				885, 560,
				890, 550,
				920, 550,
				910, 590,
				860, 590,
				850, 550
		});
		
		ula.setStrokeWidth(0.5);
		
		ula.setFill(Color.YELLOW);
		
		mar = new Rectangle();
		mar.setWidth(50);
		mar.setHeight(25);
		mar.setFill(Color.THISTLE);
		mar.setX(965);
		mar.setY(420);
		
		mbr = new Rectangle();
		mbr.setWidth(50);
		mbr.setHeight(25);
		mbr.setFill(Color.LIGHTSALMON);
		mbr.setX(965);
		mbr.setY(460);
		
		ioar = new Rectangle();
		ioar.setWidth(50);
		ioar.setHeight(25);
		ioar.setFill(Color.LIGHTGRAY);
		ioar.setX(965);
		ioar.setY(550);
		
		iobr = new Rectangle();
		iobr.setWidth(50);
		iobr.setHeight(25);
		iobr.setFill(Color.BURLYWOOD);
		iobr.setX(965);
		iobr.setY(600);
		
		group.getChildren().addAll(retangulo, grupoRegistradores, uc, pc, ir, ula, 
				mar, mbr, ioar, iobr);
		group.setVisible(false);
		
		adicionarTexto();
		
	}

	@Override
	protected void definirAcoesGerais() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void definirAcoesEspecificas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surgir(double time) {
		
		group.setVisible(true);
		
		  timeline = new Timeline();
			
		  timeline.getKeyFrames().addAll(
	               new KeyFrame(Duration.ZERO, 
	                   new KeyValue(group.opacityProperty(), 0.0f)
	               ),
	               new KeyFrame(new Duration(time), 
	                	new KeyValue(group.opacityProperty(), 1.0f)
	               )
	       );
			
	      timeline.play();
		
	}

	

}
