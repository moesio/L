package br.com.seimos.l.struct;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class AbstractStatement implements Statement {

	private String label;
	private String command;
	
	public AbstractStatement(){}
	
	public AbstractStatement(String command) {
		this.command = command.toLowerCase();
		Pattern pattern = Pattern.compile("(\\[[a-zA-Z]+\\])");
		Matcher matcher = pattern.matcher(this.command);
		if (matcher.find()) {
			label = matcher.group().replace("[", "").replace("]", "");
		}
	}

	@Override
	public String getLabel() {
		return label;
	}
	
	@Override
	public String toString() {
		return command;
	}

	@Override
	public String getCommand() {
		return command;
	}

}
