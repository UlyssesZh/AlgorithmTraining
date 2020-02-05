/*
ID: ulysses4
LANG: JAVA
PROG: nocows
*/
import java.io.*;
import java.util.*;
public class nocows {
	private static final String PROG = "nocows";
	
	private static void actualMain() {
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		int[][] dp = new int[n + 1][k + 1];
		dp[1][1] = 1;
		for (int i = 3; i <= n; i += 2) {
			for (int j = 1; j <= i - 2; j += 2) {
				for (int l = 1; l < k; l++) {
					for (int m = 1; m <= l; m++) {
						dp[i][l + 1] += 2 * dp[j][l] * dp[i - j - 1][m];
					}
				}
			}
		}
		out.println(dp[n][k]);
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
