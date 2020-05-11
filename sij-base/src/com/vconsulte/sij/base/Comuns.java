package com.vconsulte.sij.base;

public class Comuns {
	
	public static String formataPalavra(String palavra) {    			// retira acentos e transforma para minusculas
		 
		palavra = palavra.replaceAll("[aáàãâäåAÁÀÃÂÄÅ]","a");
        palavra = palavra.replaceAll("[eéèêëEÉÈÊË]","e");
        palavra = palavra.replaceAll("[iíìîïIÍÌÎÏ]","i");
        palavra = palavra.replaceAll("[oóòôöOÓÒÔÖÕ]","o");
        palavra = palavra.replaceAll("[uúùûüUÚÙÛÜ]","u");
        palavra = palavra.replaceAll("[çÇ]","c");
        palavra = palavra.replaceAll("[B]","b");
        palavra = palavra.replaceAll("[C]","c");
        palavra = palavra.replaceAll("[D]","d");
        palavra = palavra.replaceAll("[F]","f");
        palavra = palavra.replaceAll("[G]","g");
        palavra = palavra.replaceAll("[H]","h");
        palavra = palavra.replaceAll("[J]","j");
        palavra = palavra.replaceAll("[L]","l");
        palavra = palavra.replaceAll("[M]","m");
        palavra = palavra.replaceAll("[N]","n");
        palavra = palavra.replaceAll("[P]","p");
        palavra = palavra.replaceAll("[R]","r");
        palavra = palavra.replaceAll("[S]","s");
        palavra = palavra.replaceAll("[T]","t");
        palavra = palavra.replaceAll("[Q]","q");
        palavra = palavra.replaceAll("[V]","v");
        palavra = palavra.replaceAll("[X]","x");
        palavra = palavra.replaceAll("[Z]","z");
        palavra = palavra.replaceAll("[W]","w");
        palavra = palavra.replaceAll("[Y]","y");
        palavra = palavra.replaceAll("[K]","k");
        
        String palavraFormatada = "";

        for(int i=0; i <= palavra.length()-1; i++) {
        	if ((palavra.charAt(i) == ' ' && (i > 0 && palavra.charAt(i-1) == ' '))){
        		continue;
        	} else {
        		palavraFormatada = palavraFormatada + palavra.charAt(i);
        	}        	
        }      
    return palavraFormatada;  
    }
	
}