package br.com.seimos.l;

import java.io.IOException;

import br.com.seimos.l.struct.Statement;
import br.com.seimos.l.struct.StatementFactory;

public class Main {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) {
		
		System.out.println("+-----------------------------------------------+");
		System.out.println("|                  L Simulator                  |");
		System.out.println("+-----------------------------------------------+");
		if (args.length == 0) {
			showUsage();
		}
		
		for (String arg : args) {
			System.out.print(arg.concat(" "));
		}
		System.out.println();
		System.out.println();

		try {
			Program program = new Program(args[0]);
			if (!program.isEmpty()) {
				StringBuilder vars = new StringBuilder();
				for (int i = 1; i < args.length; i++) {
					vars.append("x" + i + "=" + args[i]).append(",");
				}
				Statement statement;
				statement = StatementFactory.getStatement(vars.toString().replaceAll(",$", ""));
				program.put(0, statement);
				program.run();
			}
		} catch (IOException e) {
			System.err.println("Erro na leitura do arquivo " + e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		//		Pattern pattern = Pattern.compile("\\s*(\\[[a-zA-Z]+\\])?\\s*(([xzXZ][0-9]*)|([yY])+)\\s*=\\s*(([a-zA-Z_][a-zA-Z0-9_]*))\\([xzXZ][0-9]*(,[xzXZ][0-9]*)*\\)\\s*");
		//		Matcher matcher = pattern.matcher("[lsls] x1 = f(x2,x3)");
		//		String label = null;
		//		if (matcher.find()) {
		//			label = matcher.group(3);
		//		}
		//		System.out.println(label);
	}

	private static void showUsage() {
		System.out.println("Uso:");
		System.out.println("java -jar l.jar <programa1.l> [n1 n2 n3 ... ]");
		System.out
				.println("Onde n1, n2, n3 ... são respectivos valores das variáveis x1, x2, x3, ...");
	}

}
