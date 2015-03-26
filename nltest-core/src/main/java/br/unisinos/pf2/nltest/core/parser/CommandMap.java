package br.unisinos.pf2.nltest.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import br.unisinos.pf2.nltest.core.exception.ParseException;
import br.unisinos.pf2.nltest.core.model.Parseable;

public class CommandMap {

	private String template;
	private String regex;
	private Class<? extends Parseable> clazz;
	private boolean executable;

	public boolean isExecutable() {
		return executable;
	}

	public Matcher matcher(String input) {
		return Pattern.compile(regex).matcher(input.trim());
	}

	public Parseable instantiate(String[] args) {
		try {
			Parseable newInstance = clazz.newInstance();
			newInstance.init(regex, args);
			return newInstance;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<CommandMap> loadAll() {
		List<CommandMap> commands = new ArrayList<>();

		Scanner scanner = null;
		try {
			scanner = new Scanner(CommandMap.class.getResourceAsStream("command-map.txt"));
			while (scanner.hasNext()) {
				String line = scanner.nextLine().trim();
				if (!line.isEmpty() && !line.startsWith("#")) {
					commands.add(read(line));
				}
			}
		} catch (ClassNotFoundException e) {
			throw new ParseException("Erro ao carregar mapa de comandos.", e);
		} finally {
			if (scanner != null) {
				IOUtils.closeQuietly(scanner);
			}
		}

		return commands;
	}

	@SuppressWarnings({ "unchecked" })
	private static CommandMap read(String line) throws ClassNotFoundException {
		String[] split = line.split(";");
		CommandMap mappedCommands = new CommandMap();
		mappedCommands.clazz = (Class<? extends Parseable>) Class.forName(split[0]);
		mappedCommands.template = split[1];
		mappedCommands.regex = formatRegex(split[1]);
		mappedCommands.executable = Boolean.valueOf(split[2]);
		return mappedCommands;
	}

	private static String formatRegex(String template) {
		return template.replaceAll("\\{\\w*\\}", "(.*)");
	}

	@Override
	public String toString() {
		return template;
	}
}
