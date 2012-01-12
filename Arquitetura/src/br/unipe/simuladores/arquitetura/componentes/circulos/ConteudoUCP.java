package br.unipe.simuladores.arquitetura.componentes.circulos;

import java.util.HashMap;
import java.util.Map;

import br.unipe.simuladores.arquitetura.componentes.internos.unidades.ULA;

public class ConteudoUCP {
	
	private Integer pc;
	private Integer mar;
	private Integer mbr;
	private String uc;
	private String ir;	
	private ULA ula;
	private Map<String, Integer> mapaRegistradores;
	
	public Map<String, Integer> getMapaRegistradores() {
		return mapaRegistradores;
	}

	public void setMapaRegistradores(Map<String, Integer> mapaRegistradores) {
		this.mapaRegistradores = mapaRegistradores;
	}

	public ConteudoUCP() {
		pc = 0;
		mar = 0;
		mbr = 0;
		uc = "";
		ir = "";
		ula = new ULA();
		
		mapaRegistradores = new HashMap<String, Integer>();
		
		mapaRegistradores.put("R1", 0);
		mapaRegistradores.put("R2", 0);
		mapaRegistradores.put("R3", 0);
		mapaRegistradores.put("R4", 0);

	}
	
	public Integer getPc() {
		return pc;
	}
	public void setPc(Integer pc) {
		this.pc = pc;
	}
	public Integer getMar() {
		return mar;
	}
	public void setMar(Integer mar) {
		this.mar = mar;
	}
	public Integer getMbr() {
		return mbr;
	}
	public void setMbr(Integer mbr) {
		this.mbr = mbr;
	}
	public String getUc() {
		return uc;
	}
	public void setUc(String uc) {
		this.uc = uc;
	}
	public String getIr() {
		return ir;
	}
	public void setIr(String ir) {
		this.ir = ir;
	}
	public ULA getUla() {
		return ula;
	}
	public void setUla(ULA ula) {
		this.ula = ula;
	}

	
}
