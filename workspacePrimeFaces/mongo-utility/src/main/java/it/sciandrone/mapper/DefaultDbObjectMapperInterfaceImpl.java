package it.sciandrone.mapper;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mongodb.DBObject;
/**
 * <p>
 * Una implementazione di default dell'interfaccia {@link DbObjectMapperInterface}
 * </p>
 * @author csciandrone
 *
 */
public class DefaultDbObjectMapperInterfaceImpl implements DbObjectMapperInterface {
	
	private Logger LOGGER=Logger.getLogger(DefaultDbObjectMapperInterfaceImpl.class.getName());

	@Override
	public void map(DBObject in, DBObject out, Map<String, String> customMappings) throws MapperException {
		if(in!=null && out!=null){
			LOGGER.log(Level.FINEST, "Inizia il mapping");
			Map mappaInDbObject = in.toMap();
			Map mappaOutDbObject= out.toMap();
			LOGGER.log(Level.FINEST, "Mappa i campi con lo stesso nome");
			Set keySetIn = mappaInDbObject.keySet();
			Iterator iterator = keySetIn.iterator();
			while(iterator.hasNext()){
				Object chiave = iterator.next();
				if(mappaOutDbObject.containsKey(chiave)){
					mappaOutDbObject.put(chiave,mappaInDbObject.get(chiave));
				}
			}
			LOGGER.log(Level.FINEST, "Mappa i campi seguendo in configurationMapper");
			if(customMappings!=null){
			manageCustomMappings(customMappings, mappaInDbObject, mappaOutDbObject);
			}
			settaMappaOutSuDbObjectOut(out, mappaOutDbObject);
		}
		else
		{
			throw new MapperException("Attenzione i db object in ingresso devono essere valorizzati");
		}

	}

	private void settaMappaOutSuDbObjectOut(DBObject out, Map mappaOutDbObject) {
		Set keySetOut = mappaOutDbObject.keySet();
		Iterator iteratorMappaOut=keySetOut.iterator();
		while(iteratorMappaOut.hasNext()){
			String chiaveMappaOut = (String)iteratorMappaOut.next();
			out.put(chiaveMappaOut, mappaOutDbObject.get(chiaveMappaOut));
		}
	}

	private void manageCustomMappings(Map<String,String> customMappings, Map mappaInDbObject,
			Map mappaOutDbObject) throws MapperException {
		Set<String> chiaviDaCustomMapping = customMappings.keySet();
		Iterator<String> iteratoreCustomMappings = chiaviDaCustomMapping.iterator();
		while(iteratoreCustomMappings.hasNext()){
			String chiaveIn=iteratoreCustomMappings.next();
			String chiaveOut=customMappings.get(chiaveIn);
			Object valoreMappatoIn=mappaInDbObject.get(chiaveIn);
			if(mappaOutDbObject.containsKey(chiaveOut) && mappaInDbObject.containsKey(chiaveIn)){
			 Object valoreSource=mappaInDbObject.get(chiaveIn);
			 if(valoreSource!=null){
				 mappaOutDbObject.put(chiaveOut, valoreSource);
			 }
			 else
			 {
				 LOGGER.log(Level.FINEST, String.format("Valore della chiave source %s non trovato in oggetto source, non viene rimappato sulla chiave target %s", chiaveIn,chiaveOut));
			 }
			}
			else
			{
				throw new MapperException(String.format("Attenzione la coppia di chiavi %s ,dell'oggetto source, "
						+ "e %s , dell'oggetto target  non si trovano negli oggetti da mappare", chiaveIn,chiaveOut));
			}
		}
	}
	
	

}
