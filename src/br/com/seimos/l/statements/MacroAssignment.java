package br.com.seimos.l.statements;

import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.seimos.l.Program;
import br.com.seimos.l.struct.AbstractStatement;
import br.com.seimos.l.struct.StatementFactory;

public class MacroAssignment extends AbstractStatement {

	private String variableList;
	private String macro;

	public MacroAssignment(String command) {
		super(command);
		Pattern pattern = Pattern.compile("([a-zA-Z_][a-zA-Z0-9_]*)\\(((([xzXZ][0-9]*)|[yY])(,([xzXZ][0-9]*)|[yY])*)\\)");
		Matcher matcher = pattern.matcher(command);
		if (matcher.find()) {
			macro = matcher.group(1);
			variableList = matcher.group(2);
		}
	}

	@Override
	public String execute(LinkedHashMap<String, String> variables) {

		try {
			Program program = new Program(variables.get(macro + ":"));
			String[] vars = variableList.split(",");
			StringBuilder macroVariables = new StringBuilder();
			for (String var : vars) {
				String varValue = variables.get(var);
				macroVariables.append(var).append("=").append(varValue==null?0:varValue).append(",");
			}
			String initVars = macroVariables.toString();
			String command = initVars.substring(0, initVars.lastIndexOf(","));
			Init initStatement = (Init) StatementFactory.getStatement(command);
			program.put(0, initStatement);
			System.out.println();
			program.run();
			
			variables.put(getVariable(), program.getVariables().get("y"));
		} catch (Exception e) {
			System.out.println("Macro não encontrada: " + macro);
		}

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
