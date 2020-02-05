import java.io.*;
import java.util.*;
public class largestpalin {
	private static final String PROG = "largestpalin";
	
	private static void actualMain() {
		String s = scanner.next();
		int len = s.length();
		int resultI = 0;
		int resultJ = 0;
		int result = 1;
		boolean[][] dp = new boolean[len][len];
		for (int i = 0; i < len; i++)
			dp[i][i] = true;
		for (int l = 2; l <= len; l++)
			for (int i = len - l, j = len - 1; i >= 0; i--, j--)
				if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
					dp[i][j] = true;
					result = l;
					resultI = i;
					resultJ = j;
				}
		out.println(result);
		out.println(s.substring(resultI, resultJ + 1));
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
