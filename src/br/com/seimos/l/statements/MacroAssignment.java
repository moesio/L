package br.com.seimos.l.statements;

import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.seimos.l.Program;
import br.com.seimos.l.struct.AbstractStatement;
import br.com.seimos.l.struct.StatementFactory;

public class MacroAssignment extends AbstractStatement {

	public static final String pattern = "\\s*(\\[[a-zA-Z_][a-zA-Z0-9_]*\\])?\\s*(([zZ][0-9]*)|([yY]))\\s*=\\s*(([a-zA-Z_][a-zA-Z0-9_]*))\\(((([xzXZ][0-9]*)|([yY]))(,(([xzXZ])|[yY])[0-9]*)*)\\)\\s*";
	private String variableList;
	private String macro;

	public MacroAssignment(String command) {
		super(command);
		Pattern pattern = Pattern.compile(MacroAssignment.pattern);
		Matcher matcher = pattern.matcher(command);
		if (matcher.find()) {
			macro = matcher.group(5);
			variableList = matcher.group(7);
		}
	}

	@Override
	public String execute(LinkedHashMap<String, String> variables) throws Exception {

			Program program = new Program(variables.get(macro));
			String[] vars = variableList.split(",");
			StringBuilder macroVariables = new StringBuilder();
			int i = 1;
			for (String var : vars) {
				String varValue = variables.get(var);
				macroVariables.append("x"+i++).append("=").append(varValue==null?0:varValue).append(",");
			}
			String initVars = macroVariables.toString();
			String command = initVars.substring(0, initVars.lastIndexOf(","));
			Init initStatement = (Init) StatementFactory.getStatement(command);
			program.put(0, initStatement);
			System.out.println();
			program.run();
			
			variables.put(getVariable(), program.getVariables().get("y"));

		return null;
	}

	@Override
	public String getVariable() {
		Pattern pattern = Pattern.compile("\\s*(\\[[a-zA-Z]+\\])?\\s*(([xzXZ][0-9]*)|[yY])\\s*=");
		Matcher matcher = pattern.matcher(getCommand());
		String label = null;
		if (matcher.find()) {
			label = matcher.group(2);
		}
		return label;
	}

}
