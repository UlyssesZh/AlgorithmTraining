import java.io.*;
import java.util.*;
public class anparty {
	private static final String PROG = "anparty";
	
	private static int n;
	private static int[] values;
	private static Set<Integer>[] children;
	private static int[] parents;
	private static int[][] dp;
	private static void init() {
		n = scanner.nextInt();
		values = new int[n];
		children = new Set[n];
		parents = new int[n];
		dp = new int[n][2];
		for (int u = 0; u < n; u++) {
			values[u] = scanner.nextInt();
			children[u] = new HashSet<>();
			parents[u] = -1;
		}
		while (true) {
			int u = scanner.nextInt() - 1;
			int v = scanner.nextInt() - 1;
			if (u < 0 && v < 0) break;
			children[v].add(u);
			parents[u] = v;
		}
	}
	private static int dfs(int u) {
		dp[u][0] = 0;
		dp[u][1] = values[u];
		for (int v : children[u]) {
			dp[u][0] += dfs(v);
			dp[u][1] += dp[v][0];
		}
		return Math.max(dp[u][0], dp[u][1]);
	}
	private static int findRoot() {
		int result = 0;
		while (parents[result] >= 0) result = parents[result];
		return result;
	}
	private static void actualMain() {
		init();
		out.println(dfs(findRoot()));
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
