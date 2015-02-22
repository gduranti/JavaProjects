import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {

		String regex = "\\|(\\w*)";

		CharSequence input = "|123asd12|asdasd|ssadasdas|";

		Matcher m = Pattern.compile(regex).matcher(input);

		while (m.find()) {
			for (int i = 0; i <= m.groupCount(); i++) {
				System.out.println(i + " - " + m.group(i));
			}
		}

	}

}
