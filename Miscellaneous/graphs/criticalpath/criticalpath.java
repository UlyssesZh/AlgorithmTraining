import java.io.*;
import java.util.*;
public class criticalpath {
	private static final String PROG = "criticalpath";
	
	private static class Edge {
		private int start;
		private int target;
		private int length;
		private Edge(int start, int target, int length) {
			this.start = start;
			this.target = target;
			this.length = length;
		}
		private boolean isCritical() {
			return earliest[start] - latest[target] == total - length;
		}
	}
	private static int n;
	private static int m;
	private static Edge[] edges;
	private static Set<Edge>[] outEdges;
	private static Set<Edge>[] inEdges;
	private static int[] outDegrees;
	private static int[] inDegrees;
	private static int[] earliest;
	private static int[] latest;
	private static int total;
	private static void init() {
		n = scanner.nextInt();
		outEdges = new Set[n];
		inEdges = new Set[n];
		for (int u = 0; u < n; u++) {
			outEdges[u] = new HashSet<>();
			inEdges[u] = new HashSet<>();
		}
		outDegrees = new int[n];
		inDegrees = new int[n];
		earliest = new int[n];
		latest = new int[n];
		m = scanner.nextInt();
		edges = new Edge[m];
		for (int i = 0; i < m; i++) {
			int u = scanner.nextInt();
			int v = scanner.nextInt();
			edges[i] = new Edge(u, v, scanner.nextInt());
			outDegrees[u]++;
			inDegrees[v]++;
			outEdges[u].add(edges[i]);
			inEdges[v].add(edges[i]);
		}
	}
	private static void findEarliest() {
		Queue<Integer> queue = new LinkedList<>();
		for (int u = 0; u < n; u++) if (inDegrees[u] == 0) queue.offer(u);
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (Edge edge : outEdges[u]) {
				int v = edge.target;
				if (--inDegrees[v] == 0) queue.offer(v);
				earliest[v] = Math.max(earliest[v], earliest[u] + edge.length);
			}
		}
	}
	private static void findLatest() {
		Queue<Integer> queue = new LinkedList<>();
		for (int u = 0; u < n; u++) if (outDegrees[u] == 0) queue.offer(u);
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (Edge edge : inEdges[u]) {
				int v = edge.start;
				if (--outDegrees[v] == 0) queue.offer(v);
				latest[v] = Math.min(latest[v], latest[u] - edge.length);
			}
		}
	}
	private static void calculateTotal() {
		for (int u = 0; u < n; u++)
			if (earliest[u] > total)
				total = earliest[u];
	}
	private static void output() {
		for (int i = 0; i < m; i++)
			if (edges[i].isCritical()) {
				out.print(i);
				out.print(i == m - 1 ? '\n' : ' ');
			}
	}
	private static void actualMain() {
		init();
		findEarliest();
		calculateTotal();
		findLatest();
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
