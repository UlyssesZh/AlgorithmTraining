/*
ID: ulysses4
LANG: JAVA
PROG: money
*/
import java.io.*;
import java.util.*;
public class money {
	private static final String PROG = "money";
	
	private static void actualMain() {
		int v = scanner.nextInt();
		int[] money = new int[v];
		int n = scanner.nextInt();
		long[] dp = new long[n + 1];
		for (int j = 0; j < v; j++) money[j] = scanner.nextInt();
		dp[0] = 1;
		for (int m : money)
			for (int i = m; i <= n; i++)
					dp[i] += dp[i - m];
		out.println(dp[n]);
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
