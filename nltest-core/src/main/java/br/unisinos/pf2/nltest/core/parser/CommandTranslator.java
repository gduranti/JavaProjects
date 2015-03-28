package br.unisinos.pf2.nltest.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import br.unisinos.pf2.nltest.core.model.Command;
import br.unisinos.pf2.nltest.core.model.Parseable;
import br.unisinos.pf2.nltest.core.model.commands.UnknownCommand;

/**
 * @see CommandTranslatorTest
 */
public class CommandTranslator {

	private List<CommandMap> mapOfCommands;

	public CommandTranslator() {
		mapOfCommands = CommandMap.loadAll();
	}

	public Parseable interpret(String lineCommand) {

		for (CommandMap commandMap : mapOfCommands) {

			Matcher matcher = commandMap.matcher(lineCommand);

			if (matcher.matches()) {
				String[] args = extractArgs(matcher);
				return commandMap.instantiate(args);

			} else if (matcher.reset().find()) {
				List<String> args = new ArrayList<>();
				args.add(matcher.group(1));
				while (matcher.find()) {
					args.add(matcher.group(1));
				}
				return commandMap.instantiate(args.toArray(new String[] {}));
			}
		}

		Command unknownCommand = new UnknownCommand();
		unknownCommand.init(">> Comando desconhecido <<", new String[] { lineCommand });
		return unknownCommand;
	}

	private String[] extractArgs(Matcher matcher) {
		String[] args = new String[matcher.groupCount()];
		for (int i = 0; i < matcher.groupCount(); i++) {
			args[i] = matcher.group(i + 1);
		}
		return args;
	}

}
