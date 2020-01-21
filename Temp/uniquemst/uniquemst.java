import java.io.*;
import java.util.*;
public class uniquemst {
	private static final String PROG = "uniquemst";
	
	private static int[] parents;
	private static int n;
	private static int m;
	private static boolean first;
	private static class Edge implements Comparable<Edge> {
		private int u;
		private int v;
		private int w;
		private boolean equal = false;
		private boolean used = false;
		private boolean deleted = false;
		private Edge() {
			u = scanner.nextInt() - 1;
			v = scanner.nextInt() - 1;
			w = scanner.nextInt();
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(w, o.w);
		}
	}
	private static Edge[] edges;
	private static int find(int u) {
		int s = u;
		while (parents[s] >= 0) s = parents[s];
		while (s != u) {
			int t = parents[u];
			parents[u] = s;
			u = t;
		}
		return s;
	}
	private static void union(Edge edge) {
		int su = find(edge.u);
		int sv = find(edge.v);
		int newParent = parents[su] + parents[sv];
		if (parents[su] < parents[sv]) {
			int t = su;
			su = sv;
			sv = t;
		}
		parents[su] = sv;
		parents[sv] = newParent;
	}
	private static int kruskal() {
		int result = 0;
		int addedN = 0;
		for (int i = 0; i < n; i++) parents[i] = -1;
		for (Edge edge : edges) {
			if (edge.deleted) continue;
			if (find(edge.u) != find(edge.v)) {
				result += edge.w;
				addedN++;
				union(edge);
				if (first) edge.used = true;
			}
			if (addedN >= n - 1) break;
		}
		return result;
	}
	private static void init() {
		n = scanner.nextInt();
		m = scanner.nextInt();
		parents = new int[n];
		edges = new Edge[m];
		for (int i = 0; i < m; i++) edges[i] = new Edge();
		for (int i = 0; i < m; i++) for (int j = i + 1; j < m; j++)
			if (edges[i].compareTo(edges[j]) == 0) {
				edges[i].equal = edges[j].equal = true;
				break;
			}
		Arrays.sort(edges);
		first = true;
	}
	private static void actualMain() {
		int t = scanner.nextInt();
		tests:
		while (t-- > 0) {
			init();
			int firstResult = kruskal();
			for (Edge edge : edges)
				if (edge.used && edge.equal) {
					edge.deleted = true;
					if (firstResult == kruskal()) {
						out.println("Not Unique!");
						continue tests;
					}
					edge.deleted = false;
				}
			out.println(firstResult);
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
