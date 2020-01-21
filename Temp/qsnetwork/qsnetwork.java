import java.io.*;
import java.util.*;
public class qsnetwork {
	private static final String PROG = "qsnetwork";
	
	private static int n;
	private static int[][] edges;
	private static int[] distances;
	private static void init() {
		n = scanner.nextInt();
		int[] favourites = new int[n];
		for (int i = 0; i < n; i++) favourites[i] = scanner.nextInt();
		edges = new int[n][n];
		for (int u = 0; u < n; u++) for (int v = 0; v < n; v++) {
			edges[u][v] = edges[v][u] = scanner.nextInt();
			if (u != v) {
				int adapters = favourites[u] + favourites[v];
				edges[u][v] += adapters;
				edges[v][u] += adapters;
			}
		}
		distances = new int[n];
		System.arraycopy(edges[0], 0, distances, 0, n);
	}
	private static int findNearest() {
		int min = Integer.MAX_VALUE;
		int result = -1;
		for (int v = 0; v < n; v++) {
			if (distances[v] >= 0 && distances[v] < min) {
				result = v;
				min = distances[v];
			}
		}
		return result;
	}
	private static void addVertex(int u) {
		distances[u] = -1;
		for (int v = 0; v < n; v++)
			if (edges[u][v] < distances[v]) distances[v] = edges[u][v];
	}
	private static void actualMain() {
		init();
		int result = 0;
		for (int u = 0; u < n; u++) {
			int nearest = findNearest();
			if (nearest >= 0) {
				result += distances[nearest];
				distances[nearest] = -1;
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
