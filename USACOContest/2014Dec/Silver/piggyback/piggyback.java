import java.io.*;
import java.util.*;
public class piggyback {
	private static final String PROG = "piggyback";
	
	private static final int INFINITY = Integer.MAX_VALUE / 2;
	private static int n;
	private static Set<Integer>[] neighbors;
	private static int[] dB;
	private static int[] dE;
	private static int[] dP;
	private static void init() {
		n = scanner.nextInt();
		neighbors = new Set[n];
		dB = new int[n];
		dE = new int[n];
		dP = new int[n];
		for (int u = 0; u < n; u++) {
			dB[u] = INFINITY;
			dE[u] = INFINITY;
			dP[u] = INFINITY;
			neighbors[u] = new HashSet<>();
		}
		for (int m = scanner.nextInt(); m > 0; m--) {
			int u = scanner.nextInt() - 1;
			int v = scanner.nextInt() - 1;
			neighbors[u].add(v);
			neighbors[v].add(u);
		}
	}
	/*private static int nearest(int[] distances, Set<Integer> candidates) {
		int result = -1;
		int min = INFINITY;
		for (int u : candidates)
			if (distances[u] < min) {
				result = u;
				min = distances[u];
			}
		return result;
	}
	private static void dijkstra(int[] distances) {
		Set<Integer> unjudged = new HashSet<>(n);
		for (int u = 0; u < n; u++) unjudged.add(u);
		while (!unjudged.isEmpty()) {
			int u = nearest(distances, unjudged);
			if (u == -1) break;
			unjudged.remove(u);
			int newDistance = distances[u] + 1;
			for (int v = 0; v < n; v++)
				if (unjudged.contains(v) && edges[u][v])
					distances[v] = Math.min(newDistance, distances[v]);
		}
	}*/
	private static void bfs(int start, int[] distances) {
		distances[start] = 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(start);
		while (!queue.isEmpty()) {
			int u = queue.poll();
			int newDistance = distances[u] + 1;
			for (int v : neighbors[u]) {
				if (distances[v] > newDistance) {
					distances[v] = newDistance;
					queue.offer(v);
				}
			}
		}
	}
	private static void actualMain() {
		int b = scanner.nextInt();
		int e = scanner.nextInt();
		int p = scanner.nextInt();
		init();
		bfs(0, dB);
		bfs(1, dE);
		bfs(n - 1, dP);
		int result = INFINITY;
		for (int u = 0; u < n; u++)
			result = Math.min(result, b * dB[u] + e * dE[u] + p * dP[u]);
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
