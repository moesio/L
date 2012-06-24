package br.com.seimos.l.statements;

public class Goto extends If {

	public static String pattern = "\\s*(\\[[a-zA-Z_][a-zA-Z0-9_]*\\])?\\s*goto\\s*([a-zA-Z_][a-zA-Z0-9_]*)\\s*";

	public Goto() {
	}
	
	public Goto(String command) {
		super(command);
	}

	@Override
	public String getVariable() {
		return null;
	}
}
