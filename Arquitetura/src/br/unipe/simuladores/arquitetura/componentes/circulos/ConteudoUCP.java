package br.unipe.simuladores.arquitetura.componentes.circulos;

import java.util.ArrayList;
import java.util.List;

public class ConteudoUCP {
	
	private Integer pc;
	private Integer mar;
	private Integer mbr;
	private String uc;
	private String ir;	
	private ULA ula;
	private List<Integer> registradores;
	
	public ConteudoUCP() {
		pc = 0;
		mar = 0;
		mbr = 0;
		uc = "";
		ir = "";
		ula = new ULA();
		registradores = new ArrayList<Integer>();
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

	public List<Integer> getRegistradores() {
		return registradores;
	}

	public void setRegistradores(List<Integer> registradores) {
		this.registradores = registradores;
	}
	
}
