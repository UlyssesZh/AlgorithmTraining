import java.io.*;
import java.util.*;
public class lcs {
	private static final String PROG = "lcs";
	
	private static void actualMain() {
		String string1 = scanner.next();
		String string2 = scanner.next();
		int l1 = string1.length();
		int l2 = string2.length();
		StringBuilder[][] dp = new StringBuilder[l1 + 1][l2 + 1];
		for (int i = 0; i <= l1; i++)
			dp[i][0] = new StringBuilder();
		for (int j = 0; j <= l2; j++)
			dp[0][j] = new StringBuilder();
		for (int i = 1; i <= l1; i++) for (int j = 1; j <= l2; j++)
			if (string1.charAt(i - 1) == string2.charAt(j - 1)) {
				dp[i][j] = new StringBuilder(dp[i - 1][j - 1]);
				dp[i][j].append(string1.charAt(i - 1));
			} else {
				StringBuilder s1 = dp[i - 1][j];
				StringBuilder s2 = dp[i][j - 1];
				dp[i][j] = new StringBuilder(s1.length() > s2.length() ? s1 : s2);
			}
		out.println(dp[l1][l2]);
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
