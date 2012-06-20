package br.com.seimos.l;

import br.com.seimos.l.struct.Statement;
import br.com.seimos.l.struct.StatementFactory;

public class Main {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		if (args.length == 0) {
			showUsage();
		}

		Program program = new Program(args[0]);
		if (!program.isEmpty()) {
			StringBuilder vars = new StringBuilder();
			for (int i = 1; i < args.length; i++) {
				vars.append(args[i]).append(",");
			}
			Statement statement;
			try {
				statement = StatementFactory.getStatement(vars.toString()
						.replaceAll(",$", ""));
				program.put(0, statement);
				program.run();
			} catch (Exception e) {
				System.err.println("Inicialização das variáveis não é válida");
				showUsage();
			}
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
		System.out
				.println("java -jar l.jar <programa1.l> [x1=n1 x2=n2 ... ]");
		System.out
				.println("Onde x1, x2, ... são variáveis de entrada com seus respectivos valores iniciais n1, n2, ...");
	}

}
