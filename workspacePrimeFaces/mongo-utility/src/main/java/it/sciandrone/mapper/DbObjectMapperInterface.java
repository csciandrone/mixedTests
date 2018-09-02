package it.sciandrone.mapper;

import java.util.Map;

import com.mongodb.DBObject;
/**
 * <p>
 * Si occupa di mappare un dbObject su un altro dbObject.
 * </p>
 * @author csciandrone
 *
 */
public interface DbObjectMapperInterface {
 /**
  * <p>
  * Si occupa di mappare un dbObject in ingresso su uno in uscita.<br/>
  *  Verifica che entrambi i dbObject non siano nulli.<br/>
  * Mappa di default i dati con stessa chiave dall'input all'output.<br/>
  * Se presenti i custom mappings allora li setta sull'output.<br/>
  * I custom mappings sono una mappa Stringa-Stringa dove la chiave e' il nome chiave dell'input e il valore il nome chiave dell'output.<br/>
  * Es. se voglio mappare il campo nome del DbObject in su denominazione del dbObject out la mappa avra' una entry del tipo <code>{"nome","denominazione"}</code>
  * </p>
  * @param in DbObject in ingresso
  * @param out DbObject in uscita
  * @param customMappings eventuali mappature custom (del tipo Stringa/Stringa )
  * @throws MapperException
  */
 public void map(DBObject in,DBObject out,Map<String, String> customMappings)throws MapperException;
}
