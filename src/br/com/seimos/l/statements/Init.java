package br.com.seimos.l.statements;

import java.util.LinkedHashMap;

import br.com.seimos.l.struct.AbstractStatement;

public class Init extends AbstractStatement {
	
	public static final String pattern = "((\\s*([xX][0-9]+)\\s*=\\s*[0-9]+\\s*)(,(\\s*([xX][0-9]+)\\s*=\\s*[0-9]+\\s*))*\\s*$)";
	
	public Init() {
	}

	public Init(String command) {
		super(command);
	}

	@Override
	public String execute(LinkedHashMap<String, String> variables) {
		String[] vars = getCommand().split(",");
		for (String var : vars) {
			String[] split = var.split("=");
			variables.put(split[0].trim(), split[1].trim());
		}
		return null;
	}

	@Override
	public String getVariable() {
		return null;
	}

}
