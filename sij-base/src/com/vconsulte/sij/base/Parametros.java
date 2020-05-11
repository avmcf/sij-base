package com.vconsulte.sij.base;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Parametros {
	
	public static final String USUARIO 					= "sgj";
    public static final String PASSWORD 				= "934769386";
    public static final String TIPODOCUMENTO 			= "publicacao";
    public static final String BASEFOLDER 				= "/Sites/advocacia/documentLibrary/secretaria/carregamento";
    public static final String VERSAOSPLITER 			= "v2.3.21b";
    
    public static String URL 							= "";	
    public static String CLIENTE 						= "V&C Consultoria Ltda";			
    public static String TIPOSAIDA 						= "TEXTO";						
    public static String SYSOP 							= "LINUX";
    public static String LOGFOLDER 						= "";
    
    public static List <String> TABELAUTORES = new ArrayList<String>();
    public static List <String> TERMOSCHAVES = new ArrayList<String>();
    public static List <String> FALSOFINAL = new ArrayList<String>();

	public static void carregaTabelas() { 
    	
		TABELAUTORES.add("advogado");
        TABELAUTORES.add("agravado");
        TABELAUTORES.add("agravante");
        TABELAUTORES.add("autor");
        TABELAUTORES.add("autoridade coatora");
        TABELAUTORES.add("custos legais");
        TABELAUTORES.add("custos legis");
        TABELAUTORES.add("exequente");
        TABELAUTORES.add("executado");
        TABELAUTORES.add("impetrante");
        TABELAUTORES.add("impetrado");
        TABELAUTORES.add("juizo recorrente");
        TABELAUTORES.add("perito");
        TABELAUTORES.add("procedencia");
        TABELAUTORES.add("reclamante");
        TABELAUTORES.add("reclamado");
        TABELAUTORES.add("recorrido");
        TABELAUTORES.add("recorrente");
        TABELAUTORES.add("requerente");
        TABELAUTORES.add("requerido");
        TABELAUTORES.add("relator");
        TABELAUTORES.add("reu");
        TABELAUTORES.add("solicitado");
        TABELAUTORES.add("solicitante");
        TABELAUTORES.add("terceiro interessado");
        TABELAUTORES.add("testemunha");
        
		TERMOSCHAVES.add("fundamentacao");
		TERMOSCHAVES.add("ementa");
		TERMOSCHAVES.add("merito");
		TERMOSCHAVES.add("conclusao");
		TERMOSCHAVES.add("agravo de instrumento");
		TERMOSCHAVES.add("recurso de revista");
		TERMOSCHAVES.add("pressupostos extrinsecos");
		TERMOSCHAVES.add("pressupostos intrinsecos");
		TERMOSCHAVES.add("vinculo empregaticio");
		TERMOSCHAVES.add("conclusao");
		TERMOSCHAVES.add("despacho");
		TERMOSCHAVES.add("justica do trabalho");
		TERMOSCHAVES.add("intimacao");
		TERMOSCHAVES.add("edital de intimacao em processo eletronico");
		TERMOSCHAVES.add("consideracoes preliminares");
		TERMOSCHAVES.add("1.");
		TERMOSCHAVES.add("2.");
		TERMOSCHAVES.add("3.");
		TERMOSCHAVES.add("4.");
		TERMOSCHAVES.add("5.");
		TERMOSCHAVES.add("6.");
		TERMOSCHAVES.add("7.");
		TERMOSCHAVES.add("8.");
		TERMOSCHAVES.add("9.");
		TERMOSCHAVES.add("0.");
		
		FALSOFINAL.add("ltda.");
		FALSOFINAL.add("a/a.");
		FALSOFINAL.add("v. sa.");
		FALSOFINAL.add("v.sa.");
		FALSOFINAL.add("arts.");
		FALSOFINAL.add("CLT.");
		FALSOFINAL.add("pag.");
		FALSOFINAL.add("art.");
		FALSOFINAL.add("inc.");
    }
    
}