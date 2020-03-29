import java.io.*;
import java.util.*;
public class moocast {
	private static final String PROG = "moocast";
	
	private static int n;
	private static Set<Integer>[] neighbors;
	private static Set<Integer>[] reachable;
	private static void init() {
		n = scanner.nextInt();
		int[] x = new int[n];
		int[] y = new int[n];
		int[] p = new int[n];
		neighbors = new Set[n];
		reachable = new Set[n];
		for (int u = 0; u < n; u++) {
			x[u] = scanner.nextInt();
			y[u] = scanner.nextInt();
			p[u] = scanner.nextInt();
			p[u] *= p[u];
			neighbors[u] = new HashSet<>();
		}
		for (int u = 0; u < n; u++) for (int v = 0; v < u; v++) {
			int dx = x[u] - x[v], dy = y[u] - y[v];
			int d = dx * dx + dy * dy;
			if (d <= p[u]) neighbors[u].add(v);
			if (d <= p[v]) neighbors[v].add(u);
		}
	}
	private static Set<Integer> dfs(int u) {
		if (reachable[u] != null) return reachable[u];
		reachable[u] = new HashSet<>();
		reachable[u].add(u);
		for (int v : neighbors[u])
			reachable[u].addAll(dfs(v));
		return reachable[u];
	}
	private static void actualMain() {
		init();
		for (int u = 0; u < n; u++) dfs(u);
		int result = 0;
		for (Set<Integer> set : reachable)
			result = Math.max(set.size(), result);
		out.println(result);
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
