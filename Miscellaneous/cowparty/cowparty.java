import java.io.*;
import java.util.*;
public class cowparty {
	private static final String PROG = "cowparty";
	
	private static final int INFINITY = Integer.MAX_VALUE / 2;
	private static int n;
	private static int[][] edges;
	private static int center;
	private static void init() {
		n = scanner.nextInt();
		edges = new int[n][n];
		for (int u = 0; u < n; u++) {
			for (int v = u + 1; v < n; v++)
				edges[u][v] = edges[v][u] = INFINITY;
		}
		int m = scanner.nextInt();
		center = scanner.nextInt();
		for (int edge = 0; edge < m; edge++) {
			int u = scanner.nextInt() - 1;
			int v = scanner.nextInt() - 1;
			edges[u][v] = scanner.nextInt();
		}
	}
	private static int nearest(Set<Integer> range, int[] time) {
		int result = -1;
		double min = INFINITY;
		for (int u : range) if (time[u] < min) {
			result = u;
			min = time[u];
		}
		return result;
	}
	private static int[] dijkstra(boolean transpose) {
		Set<Integer> unjudged = new HashSet<>(n);
		int[] distances = new int[n];
		for (int u = 0; u < n; u++) {
			unjudged.add(u);
			distances[u] = INFINITY;
		}
		distances[center] = 0;
		while (!unjudged.isEmpty()) {
			int u = nearest(unjudged, distances);
			unjudged.remove(u);
			for (int v = 0; v < n; v++) {
				if (!unjudged.contains(v)) continue;
				distances[v] = Math.min(distances[v],
						distances[u] + (transpose ? edges[v][u] : edges[u][v]));
			}
		}
		return distances;
	}
	private static void actualMain() {
		init();
		int result = 0;
		int[] goTime = dijkstra(false);
		int[] comeTime = dijkstra(true);
		for (int u = 0; u < n; u++)
			result = Math.max(result, goTime[u] + comeTime[u]);
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
