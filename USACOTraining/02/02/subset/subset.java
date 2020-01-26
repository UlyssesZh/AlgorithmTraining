/*
ID: ulysses4
LANG: JAVA
PROG: subset
*/
import java.io.*;
import java.util.*;
public class subset {
	private static final String PROG = "subset";
	
	private static void actualMain() {
		int n = scanner.nextInt();
		int sum = n * (n + 1) / 2;
		if (sum % 2 == 1) {
			out.println(0);
			return;
		}
		long[] dp = new long[sum + 1];
		dp[0] = 1;
		for (int i = 1; i <= n; i++)
			for (int j = sum - i; j >= 0; j--)
				dp[i + j] += dp[j];
		out.println(dp[sum / 2] / 2);
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
