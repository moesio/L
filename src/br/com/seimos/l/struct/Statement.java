package br.com.seimos.l.struct;

import java.util.LinkedHashMap;

public interface Statement {

	String getLabel();

	/**
	 * 
	 * @param variables
	 * @return next label
	 * @throws Exception 
	 */
	String execute(LinkedHashMap<String, String> variables) throws Exception;
	String getVariable();
	String getCommand();

}
