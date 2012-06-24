package br.com.seimos.l.statements;

import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.seimos.l.struct.AbstractStatement;

public class MacroDefinition extends AbstractStatement {

	public static final String pattern = "\\s*(\\[[a-zA-Z_][a-zA-Z0-9_]*\\])?\\s*macro\\s*([a-zA-Z_][a-zA-Z0-9_]*):\\s*(['\"])([a-zA-Z0-9_/]+(\\.[lL])?)\\3\\s*";
	private String macro;
	private String filePath;

	public MacroDefinition() {
	}

	public MacroDefinition(String command) {
		super(command);
//		Pattern pattern = Pattern.compile("\\s*(\\[[a-zA-Z]+\\])?\\s*macro\\s*([a-zA-Z_][a-zA-Z0-9_]*:)\\s*(['\"])([a-zA-Z0-9_/]+(\\.[lL])?)\\3\\s*");
		Pattern pattern = Pattern.compile(MacroDefinition.pattern);
		Matcher matcher = pattern.matcher(command);
		if (matcher.find()) {
			macro = matcher.group(2);
			filePath = matcher.group(4);
		}
	}

	@Override
	public String execute(LinkedHashMap<String, String> variables) {
		variables.put(macro, filePath);
		return null;
	}

	@Override
	public String getVariable() {
		return null;
	}

	public void setRoot(String filePath) {
		this.filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1) + this.filePath;
	}
}
