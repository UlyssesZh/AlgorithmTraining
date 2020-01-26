import java.io.*;
import java.util.*;
public class dijkstra {
	private static final String PROG = "dijkstra";
	
	private static final int INFINITY = Integer.MAX_VALUE / 2;
	private static int n;
	private static int[][] edges;
	private static Set<Integer> unjudged;
	private static int[] distances;
	private static int[] previous;
	private static void init() {
		n = scanner.nextInt();
		edges = new int[n][n];
		unjudged = new HashSet<>(n);
		distances = new int[n];
		previous = new int[n];
		for (int u = 0; u < n; u++) {
			for (int v = u + 1; v < n; v++)
				edges[u][v] = edges[v][u] = INFINITY;
			distances[u] = INFINITY;
			previous[u] = -1;
			unjudged.add(u);
		}
		int m = scanner.nextInt();
		for (int edge = 0; edge < m; edge++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			edges[u][v] = scanner.nextInt();
		}
		distances[0] = 0;
	}
	private static int nearest() {
		int result = -1;
		int min = INFINITY;
		for (int u : unjudged)
			if (distances[u] < min) {
				result = u;
				min = distances[u];
			}
		return result;
	}
	private static void findShortestPath() {
		while (!unjudged.isEmpty()) {
			int u = nearest();
			if (u == -1) break;
			unjudged.remove(u);
			for (int v = 0; v < n; v++) {
				if (!unjudged.contains(v)) continue;
				int newDistance = distances[u] + edges[u][v];
				if (newDistance < distances[v]) {
					distances[v] = newDistance;
					previous[v] = u;
				}
			}
		}
	}
	private static void output() {
		for (int u = 0; u < n; u++)
			if (unjudged.contains(u))
				out.println("inf");
			else {
				out.print(distances[u]);
				Stack<Integer> path = new Stack<>();
				for (int v = u; v >= 0; v = previous[v])
					path.add(v);
				while (!path.empty()) {
					out.print(' ');
					out.print(path.pop());
				}
				out.println();
			}
	}
	private static void actualMain() {
		init();
		findShortestPath();
		output();
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
