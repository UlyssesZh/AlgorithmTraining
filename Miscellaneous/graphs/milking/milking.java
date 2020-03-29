import java.io.*;
import java.util.*;
public class milking {
	private static final String PROG = "milking";
	
	private static int k;
	private static int c;
	private static int m;
	private static int n;
	private static int[][] distances;
	private static int[][] capacities;
	private static boolean[][] signs;
	private static boolean[] used;
	private static void init() {
		k = scanner.nextInt();
		c = scanner.nextInt();
		m = scanner.nextInt();
		n = k + c;
		int n1 = n + 2;
		distances = new int[n1][n1];
		capacities = new int[n1][n1];
		signs = new boolean[n1][n1];
		used = new boolean[n1];
		for (int u = 1; u <= n; u++) for (int v = 1; v <= n; v++)
			distances[u][v] = scanner.nextInt();
	}
	private static void floyd() {
		for (int u = 1; u <= n; u++) for (int v = 1; v <= n; v++)
			for (int w = 1; w <= n; w++)
				distances[u][v] = Math.min(
						distances[u][w] + distances[w][v], distances[u][v]);
	}
	private static void buildCapacities(int minmax) {
		for (int u = 0; u <= n + 1; u++) for (int v = 0; v <= n + 1; v++)
			capacities[u][v] = 0;
		for (int u = k + 1; u <= n; u++) capacities[0][u] = 1;
		for (int u = 1; u <= k; u++) capacities[u][n + 1] = m;
		for (int u = k + 1; u <= n; u++) for (int v = 1; v <= k; v++)
			if (distances[u][v] <= minmax) capacities[u][v] = 1;
	}
	private static boolean bfs() {
		for (int u = 0; u <= n + 1; u++) used[u] = false;
		for (int u = 0; u <= n + 1; u++) for (int v = 0; v <= n + 1; v++)
			signs[u][v] = false;
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(0);
		used[0] = true;
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (int v = 0; v <= n; v++)
				if (!used[v] && capacities[u][v] > 0) {
					
				}
		}
		return false;
	}
	private static void binarySearch() {
		int l = 0;
		int r = 10_000;
		while (l < r) {
			int mid = (l + r) / 2;
			int result = 0;
			
		}
	}
	private static void actualMain() {
	
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
