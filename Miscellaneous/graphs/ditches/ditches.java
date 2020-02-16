import java.io.*;
import java.util.*;
public class ditches {
	private static final String PROG = "ditches";
	
	private static final int INFINITY = Integer.MAX_VALUE / 2;
	private static class Arc {
		private int capacity;
		private int flow;
		private Arc() {
			this(0, 0);
		}
		private Arc(int capacity, int flow) {
			this.capacity = capacity;
			this.flow = flow;
		}
	}
	private static int n;
	private static Arc[][] edges;
	private static int[] flags;
	private static int[] previous;
	private static int[] delta;
	private static Queue<Integer> queue;
	private static void init() {
		int m = scanner.nextInt();
		n = scanner.nextInt();
		edges = new Arc[n][n];
		for (int u = 0; u < n; u++) for (int v = 0; v < n; v++)
			edges[u][v] = new Arc();
		flags = new int[n];
		previous = new int[n];
		delta = new int[n];
		queue = new LinkedList<>();
		while (m-- > 0) {
			int u = scanner.nextInt() - 1;
			int v = scanner.nextInt() - 1;
			edges[v][u] = new Arc(scanner.nextInt(), 0);
		}
	}
	private static void prepare() {
		Arrays.fill(flags, -1);
		Arrays.fill(previous, -1);
		Arrays.fill(delta, -1);
		flags[0] = 0;
		previous[0] = 0;
		delta[0] = INFINITY;
		queue.clear();
		queue.offer(0);
	}
	private static void parsePath() {
		int v = queue.poll();
		for (int u = 0; u < n; u++) {
			if (flags[u] != -1) continue;
			if (edges[v][u].flow < edges[v][u].capacity) {
				previous[u] = v;
				delta[u] = Math.min(delta[v], edges[v][u].capacity - edges[v][u].flow);
			} else if (edges[u][v].capacity > 0 && edges[u][v].flow > 0) {
				previous[u] = -v;
				delta[u] = Math.min(delta[v], edges[u][v].flow);
			} else continue;
			flags[u] = 0;
			queue.offer(u);
		}
		flags[v] = 1;
	}
	private static void update() {
		int d = delta[n - 1];
		for (int u = n - 1, v = -1; v != 0; u = v) {
			v = Math.abs(previous[u]);
			if (edges[v][u].flow > 0)
				edges[v][u].flow += d;
			else edges[u][v].flow -= d;
		}
	}
	private static void calc() {
		while (true) {
			prepare();
			while (!queue.isEmpty() && flags[n - 1] == -1)
				parsePath();
			if (flags[n - 1] == -1 || delta[n - 1] == 0) break;
			update();
		}
	}
	private static void output() {
		int result = 0;
		for (int u = 0; u < n; u++)
			result += edges[0][u].flow;
		out.println(result);
	}
	private static void actualMain() {
		init();
		calc();
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
