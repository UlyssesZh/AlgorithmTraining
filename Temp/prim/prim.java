import java.io.*;
import java.util.*;
public class prim {
	private static final String PROG = "prim";
	
	private static final int INFINITY = Integer.MAX_VALUE / 2;
	private static int n;
	private static int[][] edges;
	private static int[] distances;
	private static int[] neighbours;
	private static void init() {
		n = scanner.nextInt();
		edges = new int[n][n];
		for (int u = 0; u < n; u++) for (int v = u + 1; v < n; v++)
			edges[u][v] = edges[v][u] = INFINITY;
		int m = scanner.nextInt();
		for (int edge = 0; edge < m; edge++) {
			int u = scanner.nextInt() - 1;
			int v = scanner.nextInt() - 1;
			edges[u][v] = edges[v][u] = scanner.nextInt();
		}
		distances = new int[n];
		neighbours = new int[n];
		for (int u = 0; u < n; u++) {
			distances[u] = edges[0][u];
			neighbours[u] = 0;
		}
		neighbours[0] = -1;
	}
	private static int findNearest() {
		int min = INFINITY;
		int result = -1;
		for (int v = 0; v < n; v++) {
			if (neighbours[v] >= 0 && distances[v] < min) {
				result = v;
				min = distances[v];
			}
		}
		return result;
	}
	private static void addVertex(int u) {
		neighbours[u] = -1;
		for (int v = 0; v < n; v++)
			if (neighbours[v] >= 0 && edges[u][v] < distances[v]) {
				distances[v] = edges[u][v];
				neighbours[v] = u;
			}
	}
	private static void output(int addedVertex) {
		int u = addedVertex;
		int v = neighbours[addedVertex];
		if (u > v) {
			int t = u;
			u = v;
			v = t;
		}
		out.print(u + 1);
		out.print(" ");
		out.print(v + 1);
		out.print(" ");
		out.println(distances[addedVertex]);
	}
	private static void actualMain() {
		init();
		int result = 0;
		for (int u = 0; u < n; u++) {
			int nearest = findNearest();
			if (nearest >= 0) {
				output(nearest);
				neighbours[nearest] = -1;
				result += distances[nearest];
				addVertex(nearest);
			}
		}
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
