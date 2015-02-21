package br.unisinos.pf2.nltest.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.unisinos.pf2.nltest.model.Command;
import br.unisinos.pf2.nltest.model.Executable;

public class CommandTranslator {

	private Properties map;

	public CommandTranslator() {
		map = new Properties();
		try {
			// TODO extrair caminho do arquivo
			map.load(new FileInputStream("E:\\Java\\GitHub\\Unisinos\\nltest\\src\\main\\resources\\command-map.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Executable translate(String strCommand) {

		for (Entry<Object, Object> entry : map.entrySet()) {

			Matcher matcher = Pattern.compile(entry.getValue().toString()).matcher(strCommand.trim());
			if (matcher.matches()) {
				String executableName = entry.getKey().toString();
				try {

					Executable executableInstance = (Executable) Class.forName(executableName).newInstance();

					String[] args = new String[matcher.groupCount()];
					for (int i = 0; i < matcher.groupCount(); i++) {
						args[i] = matcher.group(i + 1);
					}

					executableInstance.init(args);

					return executableInstance;

				} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		// TODO tratar quando nao encotra
		return null;
	}

	public Command translateCommand(String strCommand) {
		// TODO
		return null;
	}

}
