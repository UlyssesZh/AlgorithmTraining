import java.io.*;
import java.util.*;
public class numtower {
	private static final String PROG = "numtower";
	
	private static void actualMain() {
		int n = scanner.nextInt();
		int[] dp = new int[n];
		int[][] tower = new int[n][n];
		for (int y = 0; y < n; y++) for (int x = 0; x <= y; x++)
			tower[y][x] = scanner.nextInt();
		System.arraycopy(tower[n - 1], 0, dp, 0, n);
		boolean[][] path = new boolean[n - 1][n - 1];
		for (int y = n - 2; y >= 0; y--) for (int x = 0; x <= y; x++) {
			path[y][x] = dp[x] < dp[x + 1];
			dp[x] = Math.max(dp[x], dp[x + 1]) + tower[y][x];
		}
		out.println(dp[0]);
		for (int y = 0, x = 0;; x += path[y++][x] ? 1 : 0) {
			out.print(tower[y][x]);
			if (y == n - 1) {
				out.println();
				break;
			} else out.print(' ');
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
