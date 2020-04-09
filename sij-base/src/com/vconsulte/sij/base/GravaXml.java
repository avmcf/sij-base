package com.vconsulte.sij.base;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GravaXml {
	static String bloco = "";
	static int k = 0;
	static Edital edital = new Edital();
//	static String path = "/Users/avmcf/sij/saida_editais/";
	static String path = "";
	
	public static void main() throws IOException {
		
		path = "/Users/avmcf/vms/shared/teste/" + edital.getFileName() + ".metadata.properties.xml";
		formataArquivo();
		gravaArquivo();
		k++;
	}
	
	public static void gravaArquivo() throws IOException {
		File file = new File(path);
		long begin = System.currentTimeMillis();
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(bloco);
	//	long end = System.currentTimeMillis();
	//	writer.write("Tempo de gravação: " + (end - begin) + "ms.");
		//Criando o conteúdo do arquivo
		writer.flush();
		//Fechando conexão e escrita do arquivo.
		writer.close();
		
		//System.out.println("Arquivo gravado em: " + path);
	}
	
	public static void formataArquivo(){

		bloco = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n";
		bloco = bloco + "<!DOCTYPE properties SYSTEM \"http://java.sun.com/dtd/properties.dtd\">" + "\n";
		bloco = bloco + "<properties>" + "\n";
		bloco = bloco + "<entry key=\"type\">sij:documento</entry>" + "\n";
		bloco = bloco + "<entry key=\"aspects\">cm:versionable,cm:generalclassifiable,sij:publicacao,sij:docJuridico</entry>" + "\n";
		bloco = bloco + "<entry key=\"cm:title\">" + "Andamento" + "</entry>" + "\n";
		bloco = bloco + "<entry key=\"cm:description\">" + edital.getDescricao() + "</entry>" + "\n";
		bloco = bloco + "<entry key=\"cm:author\">VCSplitDO30" + "</entry>" + "\n";
		bloco = bloco + "<entry key=\"cm:publisher\">" + ">VeC Consultoria Ltda." + "</entry>" + "\n";
		bloco = bloco + "<entry key=\"cm:type\">" + "Adobe PDF Document" + "</entry>" + "\n";
		bloco = bloco + "<entry key=\"cm:identifier\">" + edital.getFileName() + "</entry>" + "\n";
		bloco = bloco + "<entry key=\"cm:rights\">VeC Consultoria Ltda.</entry>" + "\n";
		
		bloco = bloco + "<entry key=\"sij:docTipo\">" + edital.getTipoDocumento() +"</entry>" + "\n";
		bloco = bloco + "<entry key=\"sij:docSituacao\">Novo</entry>" + "\n";
		bloco = bloco + "<entry key=\"sij:docAtivo\">False</entry>" + "\n";
		bloco = bloco + "<entry key=\"sij:docCliente\">" + edital.getCliente() +"</entry>" + "\n";
		
		bloco = bloco + "<entry key=\"sij:pubTribunal\">" +  edital.getTribunal() + "</entry>" + "\n";
		bloco = bloco + "<entry key=\"sij:pubEdicao\">" +  edital.getStrEdicao() + "</entry>" + "\n";
		bloco = bloco + "<entry key=\"sij:pubDtEdicao\">" +  edital.getStrEdicao() + "</entry>" + "\n";
		bloco = bloco + "<entry key=\"sij:pubVara\">" + edital.getVara() +"</entry>" + "\n";
		bloco = bloco + "<entry key=\"sij:pubGrupo\">" + edital.getGrupo()  + "</entry>" + "\n";
		bloco = bloco + "<entry key=\"sij:pubAssunto\">" + edital.getAssunto() + "</entry>" + "\n";
		bloco = bloco + "<entry key=\"sij:pubFolder\">" + edital.getFolder() + "</entry>" + "\n";
		bloco = bloco + "<entry key=\"sij:pubAdvogados\">" + edital.getAtores() + "</entry>" + "\n";
		bloco = bloco + "<entry key=\"sij:pubIntimados\">" + edital.getIntimados() + "</entry>" + "\n";
		
		bloco = bloco + "<entry key=\"sij:jurNumProcesso\">" + edital.getProcesso() + "</entry>" + "\n";
		bloco = bloco + "<entry key=\"sij:jurTribunal\">" + edital.getTribunal() + "</entry>" + "\n";
		bloco = bloco + "<entry key=\"sij:jurVara\">" + edital.getVara() + "</entry>" + "\n";
		bloco = bloco + "</properties>";
	}
}