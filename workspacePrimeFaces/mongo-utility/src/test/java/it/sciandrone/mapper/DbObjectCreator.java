package it.sciandrone.mapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class DbObjectCreator {
    
	private DbObjectCreator(){
		
	}
	/**
	 * <p>
	 * Cicla sui declaredMethods dell'oggetto in input , 
	 * prende tutti i getter e li mette in un dbObject con chiave il nome della proprieta' e nel 
	 * value mette il valore restituito dall'invocazione del getter
	 * </p>
	 * @param o Oggetto in input da mappare su dbObject
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static DBObject getDbObject(Object o) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		DBObject dbObject=new BasicDBObject();
		Method[] declaredMethods = o.getClass().getDeclaredMethods();
		for(int i=0;i<declaredMethods.length;i++){
			Method method = declaredMethods[i];
			if(method.getName().startsWith("get")){
				String chiave=method.getName().substring(3).toLowerCase();
				Object result = method.invoke(o, null);
				dbObject.put(chiave, result);
			}
		}
		return dbObject;
	}
	
	public static DBObject getAzienda(Azienda azienda){
		DBObject dbObject=new BasicDBObject();
		dbObject.put("denominazione", azienda.getDenominazione());
		dbObject.put("anniAttivita", azienda.getAnniAttivita());
		return dbObject;
	}
	
	
	public static DBObject getPersona(Persona p){
		DBObject dbObject=new BasicDBObject();
		dbObject.put("nome", p.getNome());
		dbObject.put("cognome", p.getCognome());
		dbObject.put("eta", p.getEta());
		dbObject.put("professione", p.getProfessione());
		return dbObject;
	}
	
	

}
