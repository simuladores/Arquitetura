package br.unipe.simuladores.arquitetura.componentes.internos;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import br.unipe.simuladores.arquitetura.componentes.interfaces.ComponenteInterno;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.IR;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.MAR;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.MBR;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.PC;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.Registrador;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.UC;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.ULA;
import br.unipe.simuladores.arquitetura.componentes.internos.unidades.UnidadeUCP;

public class UCPInterna extends ComponenteInterno{
	
	private UC uc;
	private PC pc;
	private IR ir;
	private ULA ula;
	private MAR mar;
	private MBR mbr;
	
	private Rectangle ioar;
	private Rectangle iobr;
	private Map<String, Registrador> mapaRegistradores;
	
	private static final String UCP_INTERNATXT = "Essa figura representa a UCP " +
			"interna.\n A UCP � respons�vel pela execu��o das instru��es\n da mem�ria " +
			"principal. Para isso, ela possui alguns\n componentes internos para " +
			"auxiliar nessa tarefa.\n Para saber mais detalhes sobre cada um desses\n " +
			"componentes, passe o mouse por cima de cada um deles.";
	
	public UCPInterna() {
		
		super();
		
	}

	@Override
	protected void adicionarTexto() {
		
		Text ucp = new Text("UCP");
		ucp.setX(880);
		ucp.setY(400);
		ucp.setFont(new Font(14));
		
		Text ioar = new Text("I/O AR");
		ioar.setX(787);
		ioar.setY(588);
		ioar.setFont(new Font(12));
		
		Text iobr = new Text("I/O BR");
		iobr.setX(787);
		iobr.setY(638);
		iobr.setFont(new Font(12));
		
		group.getChildren().addAll(ucp, ioar, iobr);
		
	}

	@Override
	protected void buildContent() {
		
		group.getChildren().remove(retangulo);
		
		group.getChildren().removeAll(ioar, iobr);
			
		retangulo.setWidth(280);
		retangulo.setHeight(280);
		retangulo.setFill(Color.WHEAT);
		retangulo.setX(750);
		retangulo.setY(370);
		
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
		
		group.getChildren().addAll(retangulo, ioar, iobr);
		
		ir = new IR();
		atualizarUnidadeTela(ir);
		
		mar = new MAR();
		atualizarUnidadeTela(mar);
		
		mbr = new MBR();
		atualizarUnidadeTela(mbr);
		
		pc = new PC();
		atualizarUnidadeTela(pc);
		
		uc = new UC();
		atualizarUnidadeTela(uc);
		
		ula = new ULA();
		atualizarUnidadeTela(ula);
		
		mapaRegistradores = new HashMap<String, Registrador>();
		Registrador registrador;
		int i, y;
		for (i = 0, y = 0; i < 4; i++, y += 25) {
			registrador = new Registrador("R"+(i + 1), y);
			atualizarUnidadeTela(registrador);
			mapaRegistradores.put("R"+(i + 1), registrador);
		}
		
		group.setVisible(false);
		
		adicionarTexto();
		
	}

	@Override
	protected void definirAcoesEspecificas() {
		
		//TODO
		
	}
	
	public boolean contemRegistrador(String reg) {
		
		if (mapaRegistradores.containsKey(reg))
			return true;
		
		return false;
		
	}
	
	public Integer obterConteudoRegistrador(String reg) {
		
		if (!contemRegistrador(reg)) 
			return null;
		
		Registrador registrador = mapaRegistradores.get(reg);
		return new Integer(registrador.getValor().toString());
		
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
	
	public void atualizarUnidadeTela(UnidadeUCP unidade) {
		
		/*group.getChildren().remove(unidade.getTxtNome());
		group.getChildren().remove(unidade.getForma());
		group.getChildren().remove(unidade.getTxtValor());
		group.getChildren().add(unidade.getTxtNome());
		group.getChildren().add(unidade.getForma());
		group.getChildren().add(unidade.getTxtValor());*/
		
		group.getChildren().remove(unidade.getGroup());
		group.getChildren().add(unidade.getGroup());
		
	}
	
	public void atualizarValorUnidadeTela(UnidadeUCP unidade) {
		
		/*group.getChildren().remove(unidade.getTxtValor());
		group.getChildren().add(unidade.getTxtValor());*/
		
	}
	
	public Registrador obterRegistrador(Integer numero) {
		
		return mapaRegistradores.get("R"+numero);
		
	}

	public UC getUc() {
		
		return uc;
		
	}

	public void setUc(UC uc) {
		this.uc = uc;
	}

	public PC getPc() {
		return pc;
	}

	public void setPc(PC pc) {
		this.pc = pc;
	}

	public IR getIr() {
		return ir;
	}

	public void setIr(IR ir) {
		this.ir = ir;
	}

	public ULA getUla() {
		return ula;
	}

	public void setUla(ULA ula) {
		this.ula = ula;
	}

	public MAR getMar() {
		return mar;
	}

	public void setMar(MAR mar) {
		this.mar = mar;
	}

	public MBR getMbr() {
		return mbr;
	}

	public void setMbr(MBR mbr) {
		this.mbr = mbr;
	}

	public Map<String, Registrador> getMapaRegistradores() {
		return mapaRegistradores;
	}

	public void setMapaRegistradores(Map<String, Registrador> mapaRegistradores) {
		this.mapaRegistradores = mapaRegistradores;
	}

	@Override
	public String obterTextoExplicativo() {

		return UCP_INTERNATXT;
		
	}

}
