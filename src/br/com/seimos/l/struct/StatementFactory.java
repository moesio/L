package br.com.seimos.l.struct;

import br.com.seimos.l.statements.Comment;
import br.com.seimos.l.statements.Decrement;
import br.com.seimos.l.statements.Division;
import br.com.seimos.l.statements.Goto;
import br.com.seimos.l.statements.If;
import br.com.seimos.l.statements.Increment;
import br.com.seimos.l.statements.Init;
import br.com.seimos.l.statements.MacroAssignment;
import br.com.seimos.l.statements.MacroDefinition;
import br.com.seimos.l.statements.Multiplication;
import br.com.seimos.l.statements.Subtraction;
import br.com.seimos.l.statements.Sum;

public class StatementFactory {

	public static Statement getStatement(String command) throws Exception {

		if (command.matches("(\\s*#.*)|(\\s*//.*)")) {
			return new Comment(command);
		} else if (command.trim().length() == 0) {
			return new Comment(command);
		} else if (command.matches("((\\s*([xzXZ][0-9]*+)\\s*=\\s*[0-9]+\\s*,)+(\\s*([xzXZ][0-9]*+)\\s*=\\s*[0-9]+\\s*)$)|(\\s*([xzXZ][0-9]*+)\\s*=\\s*[0-9]+\\s*$)")) {
			return new Init(command);
		} else if (command.matches("\\s*(\\[[a-zA-Z]+\\])?\\s*(([xzXZ][0-9]*)|([yY])+)\\s*=\\s*\\2\\s*\\+\\s*1\\s*")) {
			return new Increment(command);
		} else if (command.matches("\\s*(\\[[a-zA-Z]+\\])?\\s*(([xzXZ][0-9]*)|([yY])+)\\s*=\\s*\\2\\s*\\-\\s*1\\s*")) {
			return new Decrement(command);
		} else if (command.matches("\\s*(\\[[a-zA-Z]+\\])?\\s*if\\s*(([xzXZ][0-9]*)|([yY])+)\\s*!=\\s*0\\s*,?\\s*goto\\s*([a-zA-Z]+)\\s*")) {
			return new If(command);
		} else if (command.matches("\\s*(\\[[a-zA-Z]+\\])?\\s*goto\\s*([a-zA-Z]+)\\s*")) {
			return new Goto(command);
		} else if (command.matches("\\s*(\\[[a-zA-Z]+\\])?\\s*(([xzXZ][0-9]*)|([yY])+)\\s*=\\s*\\2\\s*\\+\\s*([0-9]+)\\s*")) {
			return new Sum(command);
		} else if (command.matches("\\s*(\\[[a-zA-Z]+\\])?\\s*(([xzXZ][0-9]*)|([yY])+)\\s*=\\s*\\2\\s*\\*\\s*([0-9]+)\\s*")) {
			return new Multiplication(command);
		} else if (command.matches("\\s*(\\[[a-zA-Z]+\\])?\\s*(([xzXZ][0-9]*)|([yY])+)\\s*=\\s*\\2\\s*\\/\\s*([0-9]+)\\s*")) {
			return new Division(command);
		} else if (command.matches("\\s*(\\[[a-zA-Z]+\\])?\\s*(([xzXZ][0-9]*)|([yY])+)\\s*=\\s*\\2\\s*\\-\\s*([0-9]+)\\s*")) {
			return new Subtraction(command);
		} else if (command.matches("\\s*(\\[[a-zA-Z]+\\])?\\s*macro\\s*([a-zA-Z_][a-zA-Z0-9_]*):\\s*(['\"])([a-zA-Z0-9_/]+(\\.[lL])?)\\3\\s*")) {
			return new MacroDefinition(command);
		} else if (command.matches("\\s*(\\[[a-zA-Z]+\\])?\\s*(([xzXZ][0-9]*)|([yY])+)\\s*=\\s*(([a-zA-Z_][a-zA-Z0-9_]*))\\([xzXZ][0-9]*(,[xzXZ][0-9]*)*\\)\\s*")) {
			return new MacroAssignment(command);
		}

		throw new Exception("Comando não reconhecido");
	}

}
