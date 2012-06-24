package br.com.seimos.l.statements;

import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.seimos.l.struct.AbstractStatement;

public class Multiplication extends AbstractStatement {

	public static final String pattern = "\\s*(\\[[a-zA-Z0-9_]+\\])?\\s*(([zZ][0-9]*)|([yY]))\\s*=\\s*\\2\\s*\\*\\s*([0-9]+)\\s*";

	public Multiplication() {
	}

	public Multiplication(String command) {
		super(command);
	}

	@Override
	public String execute(LinkedHashMap<String, String> variables) {
		String variable = getVariable();
		int mulValue = Integer.parseInt(getValue());
		if (variables.containsKey(variable)) {
			int value = Integer.valueOf(variables.get(variable));
			value *= mulValue;
			variables.put(variable, Integer.toString(value));
		} else {
			variables.put(variable, "0");
		}
		return null;
	}

	@Override
	public String getVariable() {
		Pattern pattern = Pattern.compile("\\s*(\\[[a-zA-Z]+\\])?\\s*(([xzXZ][0-9]*)|([yY])+)\\s*=\\s*\\2\\s*\\*\\s*([0-9]+)\\s*");
		Matcher matcher = pattern.matcher(getCommand());
		String label = null;
		if (matcher.find()) {
			label = matcher.group(2);
		}
		return label;
	}

	public String getValue() {
		Pattern pattern = Pattern.compile("\\s*(\\[[a-zA-Z]+\\])?\\s*(([xzXZ][0-9]*)|([yY])+)\\s*=\\s*\\2\\s*\\*\\s*([0-9]+)\\s*");
		Matcher matcher = pattern.matcher(getCommand());
		String label = null;
		if (matcher.find()) {
			label = matcher.group(5);
		}
		return label;
	}

}
