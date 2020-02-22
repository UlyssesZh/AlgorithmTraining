import java.io.*;
import java.nio.charset.IllegalCharsetNameException;
import java.util.*;
public class meeting {
	private static final String PROG = "meeting";
	
	private static final int INFINITY = Integer.MAX_VALUE / 2;
	private static int n;
	private static Set<Integer>[] neighbors;
	private static int[][] bessieEdge;
	private static int[][] elsieEdge;
	private static Set<Integer>[] bessieDis;
	private static Set<Integer>[] elsieDis;
	private static void init() {
		n = scanner.nextInt();
		neighbors = new Set[n];
		bessieDis = new Set[n];
		elsieDis = new Set[n];
		for (int u = 0; u < n; u++) {
			neighbors[u] = new HashSet<>();
			bessieDis[u] = new HashSet<>();
			elsieDis[u] = new HashSet<>();
		}
		bessieEdge = new int[n][n];
		elsieEdge = new int[n][n];
		for (int m = scanner.nextInt(); m > 0; m--) {
			int u = scanner.nextInt() - 1;
			int v = scanner.nextInt() - 1;
			neighbors[u].add(v);
			bessieEdge[u][v] = scanner.nextInt();
			elsieEdge[u][v] = scanner.nextInt();
		}
	}
	private static void dfs(int u, int distance, Set<Integer>[] buffer, int[][] edge) {
		if (buffer[u].contains(distance)) return;
		buffer[u].add(distance);
		for (int v : neighbors[u])
			dfs(v, distance + edge[u][v], buffer, edge);
	}
	private static void actualMain() {
		init();
		dfs(0, 0, bessieDis, bessieEdge);
		dfs(0, 0, elsieDis, elsieEdge);
		int result = INFINITY;
		for (int dis : bessieDis[n - 1])
			if (elsieDis[n - 1].contains(dis) && dis < result)
				result = dis;
		out.println(result == INFINITY ? "IMPOSSIBLE" : result);
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
