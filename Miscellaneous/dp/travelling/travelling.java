import java.io.*;
import java.util.*;
public class travelling {
	private static final String PROG = "travelling";
	
	private static final int INFINITY = Integer.MAX_VALUE / 2;
	private static int n;
	private static int size;
	private static int[] bits;
	private static int[][] status;
	private static int[][] dp;
	private static int[][] edges;
	private static void init() {
		n = scanner.nextInt();
		bits = new int[n];
		size = 1;
		for (int u = 0; u < n; size *= 3) {
			bits[u++] = size;
		}
		status = new int[size][n];
		dp = new int[n][size];
		edges = new int[n][n];
		for (int s = 0; s < size; s++) {
			int t = s;
			for (int i = 0; t > 0; t /= 3)
				status[s][i++] = t % 3;
		}
		for (int u = 0; u < n; u++) {
			Arrays.fill(edges[u], INFINITY);
			Arrays.fill(dp[u], INFINITY);
			dp[u][bits[u]] = 0;
		}
		int m = scanner.nextInt();
		for (int edge = 0; edge < m; edge++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			int w = scanner.nextInt();
			if (w < edges[u][v])
				edges[u][v] = edges[v][u] = w;
		}
	}
	private static int calc() {
		int result = INFINITY;
		for (int s = 0; s < size; s++) {
			boolean flag = true;
			for (int u = 0; u < n; u++) {
				if (status[s][u] == 0) {
					flag = false;
					continue;
				}
				//if (s == u) continue;
				for (int v = 0; v < n; v++) {
					int t = s - bits[u];
					if (status[s][v] == 0) continue;
					dp[u][s] = Math.min(dp[u][s], dp[v][t] + edges[v][u]);
				}
			}
			if (flag)
				for (int u = 0; u < n; u++)
					result = Math.min(result, dp[u][s]);
		}
		return result == INFINITY ? -1 : result;
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
