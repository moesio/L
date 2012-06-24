package br.com.seimos.l.struct;

import br.com.seimos.l.statements.Comment;
import br.com.seimos.l.statements.Decrement;
import br.com.seimos.l.statements.Goto;
import br.com.seimos.l.statements.If;
import br.com.seimos.l.statements.Increment;
import br.com.seimos.l.statements.Init;
import br.com.seimos.l.statements.MacroAssignment;
import br.com.seimos.l.statements.MacroDefinition;

public class StatementFactory {

	public static Statement getStatement(String command) throws Exception {

		if (command.matches(Comment.pattern)) {
			return new Comment(command);
		} else if (command.trim().length() == 0) {
			return new Comment(command);
		} else if (command.matches(Init.pattern)) {
			return new Init(command);
		} else if (command.matches(Increment.pattern)) {
			return new Increment(command);
		} else if (command.matches(Decrement.pattern)) {
			return new Decrement(command);
		} else if (command.matches(If.pattern)) {
			return new If(command);
		} else if (command.matches(Goto.pattern)) {
			return new Goto(command);
		} else if (command.matches(MacroDefinition.pattern)) {
			return new MacroDefinition(command);
		} else if (command.matches(MacroAssignment.pattern)) {
			return new MacroAssignment(command);
		}

		throw new Exception("Comando não reconhecido");
	}

}
