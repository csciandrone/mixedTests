package it.cifrature.vigenere;

import java.util.HashMap;
import java.util.Map;

public class Util {
	private Util(){
		
	}
	
	private static String[] alfabeto=new String[]{"A","B","C","D","E","F","G","H","I",
			"J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	private static String[] numeri=new String[]{"0","1","2","3","4","5","6","7","8","9"};
	
	public static Map<String, Integer> mappaAlfabeto=null;
	public static Map<String,Integer> mappaNumeri=null;
	
	
	public static void caricaMappaAlfabeto(){
		if(mappaAlfabeto==null){
			mappaAlfabeto=new HashMap<>();
			int i=0;
			mappaAlfabeto.put("A", i);
			mappaAlfabeto.put("B", ++i);
			mappaAlfabeto.put("C", ++i);
			mappaAlfabeto.put("D", ++i);
			mappaAlfabeto.put("E", ++i);
			mappaAlfabeto.put("F", ++i);
			mappaAlfabeto.put("G", ++i);
			mappaAlfabeto.put("H", ++i);
			mappaAlfabeto.put("I", ++i);
			mappaAlfabeto.put("J", ++i);
			mappaAlfabeto.put("K", ++i);
			mappaAlfabeto.put("L", ++i);
			mappaAlfabeto.put("M", ++i);
			mappaAlfabeto.put("N", ++i);
			mappaAlfabeto.put("O", ++i);
			mappaAlfabeto.put("P", ++i);
			mappaAlfabeto.put("Q", ++i);
			mappaAlfabeto.put("R", ++i);
			mappaAlfabeto.put("S", ++i);
			mappaAlfabeto.put("T", ++i);
			mappaAlfabeto.put("U", ++i);
			mappaAlfabeto.put("V", ++i);
			mappaAlfabeto.put("W", ++i);
			mappaAlfabeto.put("X", ++i);
			mappaAlfabeto.put("Y", ++i);
			mappaAlfabeto.put("Z", ++i);
			
		}
		if(mappaNumeri==null){
			int i=0;
			mappaNumeri=new HashMap<>();
			mappaNumeri.put("0", i);
			mappaNumeri.put("1", ++i);
			mappaNumeri.put("2", ++i);
			mappaNumeri.put("3", ++i);
			mappaNumeri.put("4", ++i);
			mappaNumeri.put("5", ++i);
			mappaNumeri.put("6", ++i);
			mappaNumeri.put("7", ++i);
			mappaNumeri.put("8", ++i);
			mappaNumeri.put("9", ++i);
			
		}
	}
	
	private static boolean isNumber(String carattere){
		boolean retVal=false;
		switch(carattere){
		case "0":
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
			retVal=true;
			break;
		default:
			retVal=false;
			break;
			
		}
		return retVal;
	}
	
	public static String cifraCarattere(String carattere,int passo){
		caricaMappaAlfabeto();
		boolean isNumber=isNumber(carattere);
	    Integer posizione = isNumber?getPosizione(mappaNumeri, carattere):getPosizione(mappaAlfabeto, carattere);	
	    if(posizione==-1) return carattere;
	    int nuovaPosizione=posizione+passo;
	    String[] listaDaConsiderare=isNumber?numeri:alfabeto;
	    if(listaDaConsiderare.length<=nuovaPosizione ){
	    	int fattoreMoltiplicativo=nuovaPosizione/listaDaConsiderare.length;
	    	nuovaPosizione=nuovaPosizione-listaDaConsiderare.length*fattoreMoltiplicativo;
	    }
	    return listaDaConsiderare[nuovaPosizione];
	    
	}
	private static Integer getPosizione(Map<String,Integer> mappa,String carattere){
		return mappa.containsKey(carattere)?mappa.get(carattere):-1;
		
		
	}
	
	public static String decifraCarattere(String carattereDaDecifrare,int passo){
		caricaMappaAlfabeto();
		boolean isNumber=isNumber(carattereDaDecifrare);
		Integer posizione = isNumber?getPosizione(mappaNumeri, carattereDaDecifrare):getPosizione(mappaAlfabeto, carattereDaDecifrare);		
		 if(posizione==-1) return carattereDaDecifrare;
		 String[] listaDaConsiderare=isNumber?numeri:alfabeto;
		int posizioneOriginale=posizione-passo;
		if(posizioneOriginale<0){
			int valoreAssoluto=Math.abs(posizioneOriginale);
			int fattoreMoltiplicativo=valoreAssoluto/listaDaConsiderare.length;
			int resto=valoreAssoluto%listaDaConsiderare.length;
			posizioneOriginale=listaDaConsiderare.length+ (posizioneOriginale+fattoreMoltiplicativo*listaDaConsiderare.length) ;
		    if(resto==0){
		    	posizioneOriginale=0;
		    }
		}
	    return listaDaConsiderare[posizioneOriginale];
	}
	
	/**
	 * <p>
	 * Data la chiave prescelta torna in base alla posizione del carattere in esame il numero del valore  
	 * nell'array per la chiave associata
	 * </p>
	 * @param key
	 * @param carattere
	 * @return
	 */
	public static int getCarattereChiave(char[] key,int carattere){
		caricaMappaAlfabeto();
		int length = key.length;
		if(carattere>=length){
			int diff=carattere/length;
			return mappaAlfabeto.get(Character.toString(key[carattere -length*diff]));
		}
		else
		{
			return mappaAlfabeto.get(Character.toString(key[carattere]));
		}
	}
	
	public static boolean isNullOrEmpty(String s){
		if(s==null || s.trim().equals("")){
			return true;
		}
		else
		{
			return false;
		}
	}

}
