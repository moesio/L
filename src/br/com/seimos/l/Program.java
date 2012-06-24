package br.com.seimos.l;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import br.com.seimos.l.statements.Comment;
import br.com.seimos.l.statements.Init;
import br.com.seimos.l.statements.MacroAssignment;
import br.com.seimos.l.statements.MacroDefinition;
import br.com.seimos.l.struct.Statement;
import br.com.seimos.l.struct.StatementFactory;

public class Program extends LinkedHashMap<Object, Statement> {

	private LinkedHashMap<String, String> variables = new LinkedHashMap<String, String>();
	private HashMap<String, Integer> labels = new HashMap<String, Integer>();
	private ArrayList<String> errors = new ArrayList<String>();
	private String filePath;

	public Program(String filePath) throws Exception {
		this.filePath = filePath;
		readFile();
	}

	/**
	 * Ler arquivo com o caminho especificado e empilhar cada linha do arquivo
	 * num ArrayList As linhas do arquivos deverão ser validadas dentro dos
	 * formatos aceitos para a linguagem L
	 * 
	 * [label] v = v + 1 
	 * [label] v = v - 1 
	 * [label] if v != 0 goto label
	 * 
	 * Onde o label inicial é opcional
	 * 
	 * @throws Formato de linha inválido
	 */
	private void readFile() throws Exception {
		//		System.out.println("Lendo arquivo " + filePath);
		String str = null;
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			int linha = 0;
			boolean assignmentAllowed = true;
			while ((str = in.readLine()) != null) {
				Statement statement = null;
				try {
					linha++;
					statement = StatementFactory.getStatement(str);
					if (statement.getClass() == Init.class) {
						if (!assignmentAllowed) {
							errors.add(new StringBuilder()
									.append("Linha ")
									.append(linha)
									.append(": [Inicialização de variáveis só é aceita na primeira linha] ")
									.append(str).toString());
						}
					}
					if (statement.getClass() != Comment.class) {
						assignmentAllowed = false;
					}
					if (statement.getClass() == MacroDefinition.class) {
						((MacroDefinition) statement).setRoot(filePath);
					}

					put(linha, statement);
					String label = statement.getLabel();
					if (label != null) {
						if (labels.containsKey(label)) {
							errors.add(new StringBuffer().append(linha).append(": label ")
									.append(label).append(" já existe").toString());
						}
						labels.put(label, linha);
					}
				} catch (Exception e) {
					errors.add(new StringBuffer().append("Linha ").append(linha).append(": [")
							.append(e.getMessage()).append("] ").append(str).toString());
				}
			}
			in.close();
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo: " + e.getLocalizedMessage());
		}
	}

	public void run() throws Exception {
		if (errors.size() > 0) {
			for (String erro : errors) {
				System.out.println(erro);
			}
			throw new Exception("Program halted");
		} else if (size() > 0) {
			System.out.println(filePath);
			Statement statement;
			for (int currentLine = 0; currentLine <= size();) {
				statement = get(currentLine);
				if (statement != null && statement.getClass() != Comment.class) { // it's possible when macro is called. Its line 0 is an artificial Init command
					String next = statement.execute(variables);
					if (statement.getClass() == MacroAssignment.class) {
						System.out.println(filePath);
					}
					if (currentLine != 0) {
						System.out.print(new StringBuilder()
								.append("Line ")
								.append(currentLine)
								.append(statement.getLabel() == null ? "" : " [".concat(statement.getLabel()).concat("]")).append(":").append(" "));
						showVariablesValue();
					}
					if (next == null) {
						currentLine++;
						continue;
					} else {
						Integer lineForLabel = labels.get(next);
						if (lineForLabel == null) {
							break;
						}
						currentLine = lineForLabel;
						continue;
					}
				} else {
					currentLine++;
				}
			}
			if (variables.get("y") == null) {
				variables.put("y", "0");
			}
			System.out.println("y = ".concat(variables.get("y")));
			System.out.println();
		} else {
			System.out.println("Nada a fazer");
		}
	}

	private void showVariablesValue() {
		System.out.println(variables.toString().replaceAll(",?\\s[a-zA-Z0-9_]+=[a-zA-Z_/\\.]+", ""));
	}

	public LinkedHashMap<String, String> getVariables() {
		return variables;
	}
}
