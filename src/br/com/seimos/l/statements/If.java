package br.com.seimos.l.statements;

import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.seimos.l.struct.AbstractStatement;

public class If extends AbstractStatement {

	public If() {
	}

	public If(String command) {
		super(command);
	}

	public String getConsigneeKey() {
		Pattern pattern = Pattern.compile("(goto\\s*([a-zA-Z]+))");
		String string = toString();
		Matcher matcher = pattern.matcher(string);
		if (matcher.find()) {
			return matcher.group(2);
		} else {
			return "_";
		}
	}

	@Override
	public String execute(LinkedHashMap<String, String> variables) {
		String variableValue = variables.get(getVariable());
		if (variableValue != null) {
			Integer value = Integer.valueOf(variableValue);
			if (value == null) {
				variables.put(getVariable(), "0");
			} else if (value != 0) {
				return getConsigneeKey();
			}
		}
		return null;
	}

	@Override
	public String getVariable() {
		Pattern pattern = Pattern.compile("\\s*(\\[[a-zA-Z]+\\])?\\s*if\\s*(([xzXZ][0-9]*)|([yY])+)");
		Matcher matcher = pattern.matcher(getCommand());
		String label = null;
		if (matcher.find()) {
			label = matcher.group(2);
		}
		return label;
	}

}