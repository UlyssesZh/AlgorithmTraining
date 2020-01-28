import java.io.*;
import java.util.*;
public class floyd {
	private static final String PROG = "floyd";
	
	private static final int INFINITY = Integer.MAX_VALUE / 2;
	private static int n;
	private static int[][] distances;
	private static int[][] transits;
	private static void init() {
		n = scanner.nextInt();
		distances = new int[n][n];
		transits = new int[n][n];
		for (int u = 0; u < n; u++) for (int v = 0; v < u; v++) {
			distances[u][v] = distances[v][u] = INFINITY;
			transits[u][v] = transits[v][u] = -1;
		}
		int m = scanner.nextInt();
		for (int edge = 0; edge < m; edge++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			distances[u][v] = scanner.nextInt();
			transits[u][v] = u;
		}
	}
	private static void findShortestPaths() {
		for (int k = 0; k < n; k++) for (int u = 0; u < n; u++) for (int v = 0; v < n; v++) {
			int newDistance = distances[u][k] + distances[k][v];
			if (newDistance < distances[u][v]) {
				distances[u][v] = newDistance;
				transits[u][v] = transits[k][v];
			}
		}
	}
	private static void output() {
		for (int u = 0; u < n; u++) for (int v = 0; v < n; v++) {
			out.print(distances[u][v]);
			Stack<Integer> path = new Stack<>();
			for (int k = v; k != u; k = transits[u][k]) path.push(k);
			path.push(u);
			while (!path.empty()) {
				out.print(' ');
				out.print(path.pop());
			}
			out.println();
		}
	}
	private static void actualMain() {
		init();
		findShortestPaths();
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
