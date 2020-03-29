import java.io.*;
import java.util.*;
public class citystate {
	private static final String PROG = "citystate";
	
	private static void actualMain() {
		Map<String, Integer> counter = new HashMap<>();
		for (int n = scanner.nextInt(); n > 0; n--) {
			String str1 = scanner.next().substring(0, 2);
			String str2 = scanner.next();
			if (!str1.equals(str2)) {
				String key = str1 + str2;
				counter.put(key, counter.containsKey(key) ? counter.get(key) + 1 : 1);
			}
		}
		int result = 0;
		for (String key : counter.keySet()) {
			String otherKey = key.substring(2, 4) + key.substring(0, 2);
			if (counter.containsKey(otherKey))
				result += counter.get(key) * counter.get(otherKey);
		}
		out.println(result / 2);
	}
	
	private static InputStream in;
	private static PrintStream out;
	private static Scanner scanner;
	public static void main(String[] args) throws IOException {
		in = new FileInputStream(PROG + ".in");
		out = new PrintStream(PROG + ".out");
		scanner = new Scanner(in);
		actualMain();
		in.close();
		out.close();
		scanner.close();
	}
}
