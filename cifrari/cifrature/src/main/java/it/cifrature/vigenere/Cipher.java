package it.cifrature.vigenere;

public interface Cipher {
	
	
	public String cipher(String text);
	public String decipher(String text);
	
	public void setKey(String key);

}
