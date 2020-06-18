package com.vconsulte.sij.base;

	import java.io.BufferedReader;
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.net.URISyntaxException;
	
	import com.vconsulte.sij.base.*;

public class Configuracao {

	static String caminho = "";
	static File config;
	
	//public static void carregaConfig(File config) throws IOException {
	public static void carregaConfig() throws IOException {

		//gravaLog(obtemHrAtual() + "Carga configuração");
		String linha = "";
		String linhaTratada = "";
		int x = 0;
		pastaCorrente();
		config = new File("/"+caminho+"split.cnf");
        FileInputStream arquivoIn = new FileInputStream(config);
		BufferedReader registro = new BufferedReader(new InputStreamReader((arquivoIn), "UTF-8"));
        
        while(linha != null){
	    	linha = registro.readLine();
	    	
	    	if(linha == null) {
	    		break;
	    	} else {
	    		linhaTratada = com.vconsulte.sij.base.Comuns.formataPalavra(linha);
	    	}
	    	switch(x) {
				case 0:
					com.vconsulte.sij.base.Parametros.CLIENTE = linha;
					break;
				case 1:
					com.vconsulte.sij.base.Parametros.TIPOSAIDA = linha;
					break;
				case 2:
					com.vconsulte.sij.base.Parametros.SYSOP = linha;
					break;
				case 3:
					com.vconsulte.sij.base.Parametros.URL = linha;
					break;
				case 4:
					com.vconsulte.sij.base.Parametros.LOGFOLDER = linha;
					break;
				case 5:
					com.vconsulte.sij.base.Parametros.PASTABASE = linha;
					break;
				case 6:
					com.vconsulte.sij.base.Parametros.PASTAINDEXADOS = linha;
					break;
	    	}
	    	x++;
        }
        registro.close();
	}
	
	public static void pastaCorrente() {
		try {
			caminho = Configuracao.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
			caminho = caminho.substring(1, caminho.lastIndexOf('/') + 1);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
}