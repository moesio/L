package br.com.seimos.l.struct;

import java.util.LinkedHashMap;

public interface Statement {

	String getLabel();

	/**
	 * 
	 * @param variables
	 * @return next label
	 */
	String execute(LinkedHashMap<String, String> variables);
	String getVariable();
	String getCommand();

}
