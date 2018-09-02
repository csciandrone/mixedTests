package it.sciandrone.mapper;

import java.util.UUID;
/**
 * <p>
 * Eccezione checked del mapper, torna un GUID generato al momento della sua creazione
 * </p>
 * @author csciandrone
 *
 */
public class MapperException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8352807085555829163L;
	private String guid=UUID.randomUUID().toString();

	public MapperException() {
	}

	public MapperException(String message) {
		super(message);
	}

	public MapperException(Throwable cause) {
		super(cause);
	}

	public MapperException(String message, Throwable cause) {
		super(message, cause);
	}

	public MapperException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public String getGuid() {
		return guid;
	}


}
