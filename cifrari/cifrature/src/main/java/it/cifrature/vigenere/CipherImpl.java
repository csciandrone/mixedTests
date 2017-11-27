package it.cifrature.vigenere;



public class CipherImpl implements Cipher {
	
	private String key=null;
	
	
	private  CipherImpl() {
	}
	
	private static CipherImpl cipherImpl=new CipherImpl();
	
	public static CipherImpl getInstance(){
		return cipherImpl;
	}
	
	

	@Override
	public String cipher(String text) {
		if(Util.isNullOrEmpty(key)) throw new IllegalArgumentException("Cannot call cipher without setting a key");
		if(Util.isNullOrEmpty(text)) throw new IllegalArgumentException("Cannot call cipher on a null text");
		text=text.toUpperCase();
		char[] charArrayText = text.toCharArray();
		char[] charArrayKey = key.toUpperCase().toCharArray();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<charArrayText.length;i++){
			int posizioneCarattereChiave = Util.getCarattereChiave(charArrayKey, i);
			String carattereCifrato = Util.cifraCarattere(Character.toString(charArrayText[i]), posizioneCarattereChiave);
		    sb.append(carattereCifrato);
		}
		return sb.toString();
	}

	@Override
	public String decipher(String text) {
		if(Util.isNullOrEmpty(key)) throw new IllegalArgumentException("Cannot call decipher without setting a key");
		if(Util.isNullOrEmpty(text)) throw new IllegalArgumentException("Cannot call decipher on a null text");
		text=text.toUpperCase();
		char[] charArrayText = text.toCharArray();
		char[] charArrayKey = key.toUpperCase().toCharArray();
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<charArrayText.length;i++){
			int posizioneCarattereChiave = Util.getCarattereChiave(charArrayKey, i);
			String carattereDeCifrato = Util.decifraCarattere(Character.toString(charArrayText[i]), posizioneCarattereChiave);
		    sb.append(carattereDeCifrato);
		}
		return sb.toString();
	}

	@Override
	public void setKey(String key) {
		this.key=key;
		
	}

}
