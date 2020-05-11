package com.vconsulte.sij.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import com.vconsulte.sij.base.*;

public class Configuracao {
	
	public static void carregaConfig(File config) throws IOException {

		//gravaLog(obtemHrAtual() + "Carga configuração");
		String linha = "";
		String linhaTratada = "";
		int x = 0;
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
	    	}
	    	x++;
        }
        registro.close();
	}
}