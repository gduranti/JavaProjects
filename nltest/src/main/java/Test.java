import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {

		String regex = "\\|\\s*(\\S+)\\s*";

		// CharSequence input = "Abrir a página http://www.gmail.com";
		CharSequence input = "| email@dasas.com       |";

		Matcher matcher = Pattern.compile(regex).matcher(input);

		while (matcher.find()) {
			System.out.println(matcher.group(1));
		}

		System.out.println("end");

	}
}
