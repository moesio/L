package br.com.seimos.l.statements;

import java.util.LinkedHashMap;

import br.com.seimos.l.struct.AbstractStatement;

public class Comment extends AbstractStatement {

	public Comment() {
	}

	public Comment(String command) {
		super(command);
	}

	@Override
	public String execute(LinkedHashMap<String, String> variables) {
		return null;
	}

	@Override
	public String getVariable() {
		return null;
	}

}
