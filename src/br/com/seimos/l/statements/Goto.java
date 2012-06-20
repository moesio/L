package br.com.seimos.l.statements;

public class Goto extends If {

	public static String format = "\\s*goto\\s*([a-zA-Z]+)\\s*";

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
