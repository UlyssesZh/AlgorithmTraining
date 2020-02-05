/*
ID: ulysses4
LANG: JAVA
PROG: prefix
*/
import java.io.*;
import java.util.*;
public class prefix {
	private static final String PROG = "prefix";
	
	private static void actualMain() {
		List<String> primitives = new ArrayList<>();
		while (true) {
			String next = scanner.next();
			if (next.equals(".")) break;
			primitives.add(next);
		}
		StringBuilder sequenceBuilder = new StringBuilder();
		while (scanner.hasNext())
			sequenceBuilder.append(scanner.next());
		String sequence = sequenceBuilder.toString();
		boolean[] dp = new boolean[sequence.length() + 1];
		dp[0] = true;
		for (int i = 0; i < sequence.length(); i++) if (dp[i])
			for (String primitive : primitives) {
				int end = i + primitive.length();
				if (end > sequence.length()) continue;
				if (primitive.equals(sequence.substring(i, end)))
					dp[end] = true;
			}
		for (int result = sequence.length(); result >= 0; result--)
			if (dp[result]) {
				out.println(result);
				break;
			}
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
