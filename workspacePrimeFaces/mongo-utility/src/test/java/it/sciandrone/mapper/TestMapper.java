package it.sciandrone.mapper;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.mongodb.DBObject;

public class TestMapper {

	public TestMapper() {
		// TODO Auto-generated constructor stub
	}
	@Test
	public void testMapperPersona(){
		DbObjectMapperInterface mapperInterface=new DefaultDbObjectMapperInterfaceImpl();
		DBObject in=DbObjectCreator.getPersona(new Persona("carlo","sciandrone",40,"impiegato"));
		DBObject out=DbObjectCreator.getPersona(new Persona());
		try {
			mapperInterface.map(in, out, null);
			Assert.assertEquals("carlo", (String)out.get("nome"));
			Assert.assertEquals("sciandrone", (String)out.get("cognome"));
			Assert.assertEquals(40, (int)out.get("eta"));
			Assert.assertEquals("impiegato", (String)out.get("professione"));
			System.out.println(out.toString());
		} catch (MapperException e) {
			
			e.printStackTrace();
			Assert.fail();
		}
	}
	
	
	@Test
	public void testMapperPersonaWithCustomConverter(){
		DbObjectMapperInterface mapperInterface=new DefaultDbObjectMapperInterfaceImpl();
		DBObject in=DbObjectCreator.getPersona(new Persona("carlo","sciandrone",40,"impiegato"));
		DBObject out=DbObjectCreator.getAzienda(new Azienda());
		Map<String, String> mappaCustom=new HashMap<>();
		mappaCustom.put("cognome", "denominazione");
		mappaCustom.put("eta", "anniAttivita");
		try {
			mapperInterface.map(in, out, mappaCustom);
			Assert.assertEquals("sciandrone", (String)out.get("denominazione"));
			Assert.assertEquals(40, (int)out.get("anniAttivita"));
			System.out.println(out.toString());
		} catch (MapperException e) {
			
			e.printStackTrace();
			Assert.fail();
		}
	}
	@Test(expected=MapperException.class)
	public void testError() throws MapperException{
		DbObjectMapperInterface mapperInterface=new DefaultDbObjectMapperInterfaceImpl();
		DBObject in=DbObjectCreator.getPersona(new Persona("carlo","sciandrone",40,"impiegato"));
		mapperInterface.map(in, null, null);
	}
	@Test
	public void testMapperObjectToDbObject() throws Exception{
		Persona p=new Persona("mario","rossi",45,"sergente");
		DBObject dbObject = DbObjectCreator.getDbObject(p);
		Assert.assertEquals("mario", (String)dbObject.get("nome"));
		Assert.assertEquals("rossi", (String)dbObject.get("cognome"));
		Assert.assertEquals(45, (int)dbObject.get("eta"));
		Assert.assertEquals("sergente", (String)dbObject.get("professione"));
		System.out.println(dbObject);
	}
	

}
