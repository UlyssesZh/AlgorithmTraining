import java.io.*;
import java.util.*;
public class marathon {
	private static final String PROG = "marathon";
	
	private static int[][] d;
	private static int n;
	private static int k;
	private static int[][] dp;
	private static void init() {
		n = scanner.nextInt();
		k = scanner.nextInt();
		int[] x = new int[n];
		int[] y = new int[n];
		dp = new int[n][k + 1];
		for (int i = 0; i < n; i++) {
			x[i] = scanner.nextInt();
			y[i] = scanner.nextInt();
			Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
		}
		d = new int[n][n];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < i; j++)
				d[i][j] = d[j][i] = Math.abs(x[i] - x[j]) + Math.abs(y[i] - y[j]);
	}
	private static void calc() {
		dp[0][0] = 0;
		for (int end = 0; end < n; end++)
			for (int skip = 0; skip <= Math.min(k, end - 1); skip++)
				for (int lastSkip = 0; lastSkip <= skip; lastSkip++) {
					int lastEnd = end - lastSkip - 1;
					dp[end][skip] = Math.min(dp[end][skip],
							dp[lastEnd][skip - lastSkip] + d[lastEnd][end]);
				}
	}
	private static void actualMain() {
		init();
		calc();
		out.println(dp[n - 1][k]);
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
