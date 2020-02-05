import java.io.*;
import java.util.*;
public class stonemerge {
	private static final String PROG = "stonemerge";
	
	private static void output(int[][] splits, int i, int j, boolean space) {
		if (i == j) return;
		if (space) out.print(' ');
		int k = splits[i][j];
		out.print(k);
		output(splits, i, k, true);
		output(splits, k + 1, j, true);
	}
	private static void actualMain() {
		int n = scanner.nextInt();
		int[][] dp = new int[n + 1][n + 1];
		int[] sums = new int[n + 1];
		int[][] splits = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++)
			sums[i] = sums[i - 1] + scanner.nextInt();
		for (int i = 1; i < n; i++) {
			dp[i][i + 1] = sums[i + 1] - sums[i - 1];
			splits[i][i + 1] = i;
		}
		for (int l = 2; l < n; l++)
			for (int i = 1; i <= n - l; i++) {
				int j = i + l;
				dp[i][j] = Integer.MAX_VALUE / 2;
				for (int k = splits[i][j - 1]; k <= splits[i + 1][j]; k++) {
					int update = dp[i][k] + dp[k + 1][j] + sums[j] - sums[i - 1];
					if (update < dp[i][j]) {
						dp[i][j] = update;
						splits[i][j] = k;
					}
				}
			}
		out.println(dp[1][n]);
		output(splits, 1, n, false);
		out.println();
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
