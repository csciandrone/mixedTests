package it.sciandrone.parser;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class TestParser {
	@Test
	public void testFileOk(){
		ClassLoader classLoader=getClass().getClassLoader();
		String file = classLoader.getResource("persone.json").getFile();
		Validator validator=new JsonListValidator();
		Assert.assertTrue(validator.validate(new File(file)));
		
	}
	@Test
	public void testFileKo(){
		ClassLoader classLoader=getClass().getClassLoader();
		String file = classLoader.getResource("persone2.json").getFile();
		Validator validator=new JsonListValidator();
		Assert.assertFalse(validator.validate(new File(file)));
	}

}
