package br.unisinos.pf2.nltest.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.unisinos.pf2.nltest.exception.ParseException;
import br.unisinos.pf2.nltest.model.Command;
import br.unisinos.pf2.nltest.model.Parseable;
import br.unisinos.pf2.nltest.model.commands.UnknownCommand;

public class CommandTranslator {

	private Properties map;

	public CommandTranslator() {
		map = new Properties();
		try {
			map.load(getClass().getResourceAsStream("command-map.properties"));
		} catch (IOException e) {
			throw new ParseException("Ocorreu erro ao carregar o mapa de comandos: " + e.getMessage(), e);
		}
	}

	public Parseable interpret(String lineCommand) {
		for (Entry<Object, Object> entry : map.entrySet()) {

			String baseScript = entry.getValue().toString();

			Matcher matcher = Pattern.compile(baseScript).matcher(lineCommand.trim());

			if (matcher.matches()) {
				String[] args = extractArgs(matcher);
				String parseableName = entry.getKey().toString();
				return createInstance(baseScript, args, parseableName);

			} else {

				matcher.reset();

				if (matcher.find()) {
					String parseableName = entry.getKey().toString();
					List<String> args = new ArrayList<>();
					args.add(matcher.group(1));
					while (matcher.find()) {
						args.add(matcher.group(1));
					}
					return createInstance(baseScript, args.toArray(new String[] {}), parseableName);
				}
			}
		}

		Command unknownCommand = new UnknownCommand();
		unknownCommand.init(null, new String[] { lineCommand });
		return unknownCommand;
	}

	private Parseable createInstance(String baseScript, String[] args, String parseableName) {
		try {

			Parseable parseableInstance = (Parseable) Class.forName(parseableName).newInstance();
			parseableInstance.init(baseScript, args);
			return parseableInstance;

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private String[] extractArgs(Matcher matcher) {
		String[] args = new String[matcher.groupCount()];
		for (int i = 0; i < matcher.groupCount(); i++) {
			args[i] = matcher.group(i + 1);
		}
		return args;
	}

}
