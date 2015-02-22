package br.unisinos.pf2.nltest.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.unisinos.pf2.nltest.exception.ParseException;
import br.unisinos.pf2.nltest.model.Parseable;

public class CommandTranslator {

	private Properties map;

	public CommandTranslator() {
		map = new Properties();
		try {
			// TODO extrair caminho do arquivo
			map.load(new FileInputStream("src\\main\\resources\\command-map.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Parseable interpret(String strCommand) {
		for (Entry<Object, Object> entry : map.entrySet()) {
			Matcher matcher = Pattern.compile(entry.getValue().toString()).matcher(strCommand.trim());

			if (matcher.matches()) {
				String[] args = extractArgs(matcher);
				String parseableName = entry.getKey().toString();
				return createInstance(args, parseableName);

			} else {

				matcher.reset();

				if (matcher.find()) {
					String parseableName = entry.getKey().toString();
					List<String> args = new ArrayList<>();
					args.add(matcher.group(1));
					while (matcher.find()) {
						args.add(matcher.group(1));
					}
					return createInstance(args.toArray(new String[] {}), parseableName);
				}
			}

		}

		throw new ParseException("Command not mapped: " + strCommand);
	}

	private Parseable createInstance(String[] args, String parseableName) {
		try {

			Parseable parseableInstance = (Parseable) Class.forName(parseableName).newInstance();
			parseableInstance.init(args);
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
