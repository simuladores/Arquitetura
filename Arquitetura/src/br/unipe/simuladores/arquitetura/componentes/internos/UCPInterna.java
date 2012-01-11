package br.unipe.simuladores.arquitetura.componentes.internos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import br.unipe.simuladores.arquitetura.componentes.circulos.ConteudoUCP;
import br.unipe.simuladores.arquitetura.componentes.interfaces.ComponenteInterno;
import br.unipe.simuladores.arquitetura.telas.TelaPrincipal;

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
	
	private ConteudoUCP conteudo;
	
	private Group grupoRegistradores; 
	
	
	public UCPInterna() {
		
		super();
		
		conteudo = new ConteudoUCP();
		
		for (int i = 0; i < 4; i++)
			atualizarRegistrador(0, i + 1);
		
		atualizarPC(1);
		atualizarMAR(0);
		atualizarMBR(0);
		atualizarUC("READ");
		
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
		uc.setX(945);
		uc.setY(593);
		uc.setFont(new Font(12));
		
		Text pc = new Text("PC");
		pc.setX(848);
		pc.setY(438);
		pc.setFont(new Font(12));
		
		Text ir = new Text("IR");
		ir.setX(848);
		ir.setY(528);
		ir.setFont(new Font(12));
		
		Text ula = new Text("ULA");
		ula.setX(885);
		ula.setY(630);
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
		ioar.setX(787);
		ioar.setY(588);
		ioar.setFont(new Font(12));
		
		Text iobr = new Text("I/O BR");
		iobr.setX(787);
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
		uc.setX(965);
		uc.setY(575);
			
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
		ir.setY(510);

		
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
		ula.setTranslateY(20);
		ula.setTranslateX(10);
		
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
		ioar.setX(780);
		ioar.setY(550);
		
		iobr = new Rectangle();
		iobr.setWidth(50);
		iobr.setHeight(25);
		iobr.setFill(Color.BURLYWOOD);
		iobr.setX(780);
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
	
	public boolean contemRegistrador(String reg) {
		
		if (conteudo.getMapaRegistradores().containsKey(reg))
			return true;
		
		return false;
		
	}
	
	public Integer obterConteudoRegistrador(String reg) {
		
		if (!contemRegistrador(reg)) 
			return null;
		
		return conteudo.getMapaRegistradores().get(reg);
		
	}
	
	public Integer obterEnderecoRegistrador(String reg) {
		
		if (reg.equalsIgnoreCase("R1")) 
			return 1;
		else if (reg.equalsIgnoreCase("R2")) 
			return 2;
		else if (reg.equalsIgnoreCase("R3")) 
			return 3;
		else if (reg.equalsIgnoreCase("R4")) 
			return 4;
		
		return 0;
		
	}
	
	public void atualizarRegistrador(Integer valor, int numero) {
		
		double yBase = 438;
		
		conteudo.getMapaRegistradores().put("R"+numero, valor);
		atualizarConteudoTela(valor.toString(), 802, yBase + 25 * (numero - 1));
		
	}
	
	public void atualizarPC(Integer valor) {
		
		conteudo.setPc(valor);
		atualizarConteudoTela(valor.toString(), 892, 438);
		
	}
	
	public void atualizarMAR(Integer valor) {
		
		conteudo.setMar(valor);
		atualizarConteudoTela(valor.toString(), 987, 438);
		
	}
	
	public void atualizarMBR(Integer valor) {
		
		conteudo.setMbr(valor);
		atualizarConteudoTela(valor.toString(), 987, 478);
		
	}
	
	public void atualizarUC(String valor) {
		
		conteudo.setUc(valor);
		atualizarConteudoTela(valor, 975, 593);
		
	}
	
	private void atualizarConteudoTela(String valor, double x, double y) {
		
		Text txtValor = new Text(valor);
		txtValor.setX(x);
		txtValor.setY(y);
		txtValor.toFront();
		group.getChildren().add(txtValor);
		
	}

}
