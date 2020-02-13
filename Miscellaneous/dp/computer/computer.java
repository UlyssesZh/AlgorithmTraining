import java.io.*;
import java.util.*;
public class computer {
	private static final String PROG = "computer";
	
	private static int n;
	private static int[][] dp;
	private static Set<int[]>[] children;
	private static void init() {
		n = scanner.nextInt();
		children = new Set[n];
		dp = new int[n][3];
		for (int u = 0; u < n; u++) children[u] = new HashSet<>();
		for (int u = 1; u < n; u++)
			children[scanner.nextInt() - 1].add(new int[] {
					scanner.nextInt(), u});
	}
	private static void dfs1(int parent) {
		int one = 0;
		int two = 0;
		for (int[] child : children[parent]) {
			dfs1(child[1]);
			int cost = child[0] + dp[child[1]][0];
			if (cost >= one) {
				two = one;
				one = cost;
			}
			if (cost < one && cost > two) two = cost;
		}
		dp[parent][0] = one;
		dp[parent][1] = two;
	}
	private static void dfs2(int parent) {
		for (int[] child : children[parent]) {
			int update = dp[parent][dp[child[1]][0] + child[0] == dp[parent][0] ? 1 : 0];
			dp[child[1]][2] = Math.max(dp[parent][2], update) + child[0];
			dfs2(child[1]);
		}
	}
	private static void actualMain() {
		init();
		dfs1(0);
		dp[0][2] = 0;
		dfs2(0);
		for (int u = 0; u < n; u++)
			out.println(Math.max(dp[u][0], dp[u][2]));
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
