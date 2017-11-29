package it.cifrature.vigenere;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

public class CipherTest {
	@Test
	public void testCifratura() {
		Cipher cipher = CipherImpl.getInstance();
		cipher.setKey("akdhflkhdflkjhsdkhdslkhfjklsjdljldkfj");
		String testoInChiaro = "Non chiederci la parola che squadri l'animo nostro informe e a lettere di fuoco lo dichiari e risplenda come un croco perduto in mezzo a un polveroso prato";
		String testoCifrato = cipher.cipher(testoInChiaro);
		String testoDecifrato = cipher.decipher(testoCifrato);
		Assert.assertEquals(testoInChiaro.toUpperCase(), testoDecifrato);
	}

	@Test
	public void testCifraturaCodiceFiscale() {
		Cipher cipher = CipherImpl.getInstance();
		cipher.setKey("akdhflkhdflkjhsdkhdslkhfjklsjdljldkfj");
		String codiceFiscale = "SCNCRL78D20A123V";
		String codiceFiscaleCifrato = cipher.cipher(codiceFiscale);
		String codiceFiscaleDecifrato = cipher.decipher(codiceFiscaleCifrato);
		Assert.assertEquals(codiceFiscaleDecifrato, codiceFiscale.toUpperCase());
	}

	@Test
	public void testCifraturaPiva() {
		Cipher cipher = CipherImpl.getInstance();
		cipher.setKey("akdhflkhdflkjhsdkhdslkhfjklsjdljldkfj");
		String piva = "19099012987";
		String pivaCifrata = cipher.cipher(piva);
		String pivaDecifrata = cipher.decipher(pivaCifrata);
		Assert.assertEquals(pivaDecifrata, piva.toUpperCase());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFail1() {
		Cipher cipher = CipherImpl.getInstance();
		cipher.cipher("ddfssdfd");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFail2() {
		Cipher cipher = CipherImpl.getInstance();
		cipher.setKey("prova");
		cipher.cipher("   ");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testFail3() {
		Cipher cipher = CipherImpl.getInstance();
		cipher.setKey("prova");
		cipher.cipher(null);
	}

	@Test
	public void testMassivo() {
		Cipher cipher = CipherImpl.getInstance();
		cipher.setKey("akdhflkhdflkjhsdkhdslkhfjklsjdljldkfj");
		for (int i = 0; i < 10000000; i++) {
			String text = UUID.randomUUID().toString().toUpperCase();
			String testoCifrato = cipher.cipher(text);
			String decifrato = cipher.decipher(testoCifrato);
			Assert.assertEquals(decifrato, text);
		}
	}

}
