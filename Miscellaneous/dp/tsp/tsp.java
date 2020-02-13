import java.io.*;
import java.util.*;
public class tsp {
	private static final String PROG = "tsp";
	
	private static final int INFINITY = Integer.MAX_VALUE / 2;
	private static int n;
	private static int[][] dp;
	private static Set<int[]>[] neighbors;
	private static void init() {
		n = scanner.nextInt();
		dp = new int[n][1 << n];
		neighbors = new Set[n];
		for (int u = 0; u < n; u++) {
			Arrays.fill(dp[u], INFINITY);
			neighbors[u] = new HashSet<>();
		}
		int m = scanner.nextInt();
		for (int edge = 0; edge < m; edge++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			int w = scanner.nextInt();
			neighbors[v].add(new int[] {u, w});
		}
	}
	private static int calc() {
		dp[0][(1 << n) - 1] = 0;
		for (int s = (1 << n) - 2; s >= 0; s--)
			for (int v = 0; v < n; v++)
				for (int[] uw : neighbors[v]) {
					int u = uw[0];
					int w = uw[1];
					if ((s >> u & 1) == 0)
						dp[v][s] = Math.min(dp[v][s], dp[u][s | 1 << u] + w);
				}
		return dp[0][0];
	}
	private static void actualMain() {
		init();
		out.println(calc());
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
