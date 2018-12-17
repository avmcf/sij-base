package com.vconsulte.sij.base;

import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.chemistry.opencmis.client.api.Session;

/**
 * This class holds common properties and methods for the example classes.
 */

public class Edital extends Base{
    private static Date edicao;
    private static Date dtEdicao;
	private static String sequencial;
	private static String seqEdicao;
	private static String tribunal;
    private static String tipoDocumento;
    private static String descricao;
    private static String processo;
   
    static Collection<String> texto;
	
	public Date getEdicao() {
		return Edital.edicao;
	}

	public void setEdicao(Date edicao) {
		Edital.edicao = edicao;
	}
	
	public Date getDtEdicao() {
		return Edital.dtEdicao;
	}

	public void setDtEdicao(Date dtEdicao) {
		Edital.dtEdicao = dtEdicao;
	}
	
	public static String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String setTipoDocumento) {
		Edital.tipoDocumento = setTipoDocumento;
	}

	public Collection<String> getTexto() {							// corpo da pubicacao
		return texto;
	}
	
	public void setTexto(Collection<String> texto) {				// corpo da pubicacao
		Edital.texto = texto;
	}
	
	// ------------------------------------
	
	public String getSeqEdicao() { 
		return seqEdicao; 
	}
	
	public void setSeqEdicao(String seqEdicao) {
		Edital.seqEdicao = seqEdicao;
	}
	
	// ------------------------------------
	
	public String getSequencial() { 
		return sequencial; 
	}
	
	public void setSequencial(String sequencial) {
		Edital.sequencial = sequencial;
	}
	
	// ------------------------------------
	
	public String getTribunal() { 
		return tribunal; 
	}
	
	public void setTribunal(String tribunal) {
		Edital.tribunal = tribunal;
	}
	
	// ------------------------------------
	
	public String getDescricao() { 
		return descricao; 
	}
	
	public void setDescricao(String descricao) {
		Edital.descricao = descricao;
	}
	
	public String getProcesso() { 
		return processo; 
	}
	
	public void setProcesso(String processo) {
		Edital.processo = processo;
	}
	

}
