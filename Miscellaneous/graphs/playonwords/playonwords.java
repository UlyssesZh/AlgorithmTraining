import java.io.*;
import java.util.*;
public class playonwords {
	private static final String PROG = "playonwords";
	
	private static int[] inDegrees = new int[26];
	private static int[] outDegrees = new int[26];
	private static Set<Integer> involved = new HashSet<>();
	private static int[] parents = new int[26];
	private static int find(int u) {
		int root = u;
		while (parents[root] >= 0) root = parents[root];
		while (root != u) {
			int t = parents[u];
			parents[u] = root;
			u = t;
		}
		return root;
	}
	private static void union(int u, int v) {
		if (u == v) return;
		int rootU = find(u);
		int rootV = find(v);
		if (rootU == rootV) return;
		int newParent = parents[rootU] + parents[rootV];
		if (parents[rootU] > parents[rootV]) {
			parents[rootU] = rootV;
			parents[rootV] = newParent;
		} else {
			parents[rootV] = rootU;
			parents[rootU] = newParent;
		}
	}
	private static void init() {
		int n = scanner.nextInt();
		Arrays.fill(inDegrees, 0);
		Arrays.fill(outDegrees, 0);
		Arrays.fill(parents, -1);
		involved.clear();
		for (int i = 0; i < n; i++) {
			String word = scanner.next();
			int u = word.charAt(0) - 'a';
			int v = word.charAt(word.length() - 1) - 'a';
			involved.add(u);
			involved.add(v);
			outDegrees[u]++;
			inDegrees[v]++;
			union(u, v);
		}
	}
	private static boolean connected() {
		int first = -1;
		for (int u = 0; u < 26; u++) {
			if (!involved.contains(u)) continue;
			if (first == -1) first = u;
			else if (find(u) != find(first)) return false;
		}
		return true;
	}
	private static boolean euler() {
		if (!connected()) return false;
		boolean positive = false;
		boolean negative = false;
		for (int u = 0; u < 26; u++) {
			if (!involved.contains(u)) continue;
			int inOutDifference = inDegrees[u] - outDegrees[u];
			if (inOutDifference == 1) {
				if (positive) return false;
				positive = true;
			} else if (inOutDifference == -1) {
				if (negative) return false;
				negative = true;
			} else if (inOutDifference != 0) return false;
		}
		return positive == negative;
	}
	private static void actualMain() {
		for (int t = scanner.nextInt(); t > 0; t--) {
			init();
			out.println(euler() ? "YES" : "NO");
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
