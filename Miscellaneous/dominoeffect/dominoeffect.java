import java.io.*;
import java.util.*;
public class dominoeffect {
	private static final String PROG = "dominoeffect";
	
	private static final int INFINITY = Integer.MAX_VALUE / 2;
	private static int n;
	private static int[][] edges;
	private static Set<Integer> unjudged;
	private static int[] time;
	private static int maxTimeVertex;
	private static int lastVertex;
	private static double maxTimeEdge;
	private static int lastEdgeU;
	private static int lastEdgeV;
	private static boolean init() {
		n = scanner.nextInt();
		if (n == 0) return false;
		maxTimeVertex = -1;
		maxTimeEdge = -1;
		edges = new int[n][n];
		unjudged = new HashSet<>(n);
		time = new int[n];
		for (int u = 0; u < n; u++) {
			time[u] = INFINITY;
			unjudged.add(u);
			for (int v = u + 1; v < n; v++)
				edges[u][v] = edges[v][u] = INFINITY;
		}
		int m = scanner.nextInt();
		for (int edge = 0; edge < m; edge++) {
			int u = scanner.nextInt() - 1;
			int v = scanner.nextInt() - 1;
			edges[u][v] = edges[v][u] = scanner.nextInt();
		}
		time[0] = 0;
		return true;
	}
	private static int nearest() {
		int result = -1;
		int min = INFINITY;
		for (int u : unjudged) if (time[u] < min) {
			result = u;
			min = time[u];
		}
		return result;
	}
	private static void findShortestPath() {
		while (!unjudged.isEmpty()) {
			int u = nearest();
			if (time[u] > maxTimeVertex) {
				maxTimeVertex = time[u];
				lastVertex = u;
			}
			unjudged.remove(u);
			for (int v = 0; v < n; v++) if (unjudged.contains(v))
				time[v] = Math.min(time[u] + edges[u][v], time[v]);
		}
	}
	private static void findMaxTimeEdge() {
		for (int u = 0; u < n; u++)
			for (int v = u + 1; v < n; v++) {
				double t = (time[u] + time[v] + edges[u][v]) / 2.0;
				if (edges[u][v] < INFINITY && t > maxTimeEdge) {
					maxTimeEdge = t;
					lastEdgeU = u;
					lastEdgeV = v;
				}
			}
	}
	private static void output() {
		if (maxTimeEdge > maxTimeVertex) {
			out.print("at ");
			out.print(maxTimeEdge);
			out.print(" between ");
			out.print(lastEdgeU + 1);
			out.print(" and ");
			out.println(lastEdgeV + 1);
		} else {
			out.print("at ");
			out.print(maxTimeVertex);
			out.print(" at ");
			out.println(lastVertex + 1);
		}
	}
	private static void actualMain() {
		while (init()) {
			findShortestPath();
			findMaxTimeEdge();
			output();
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
