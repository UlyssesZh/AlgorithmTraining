import java.io.*;
import java.util.*;
public class moop {
	private static final String PROG = "moop";
	
	private static int n;
	private static Set<Integer>[] neighbors;
	private static Set<Integer> unremoved;
	private static boolean interactable(int[][] moos, int u, int v) {
		return moos[v][0] <= moos[u][0] &&
				       moos[v][1] <= moos[u][1] ||
		       moos[u][0] <= moos[v][0] &&
				       moos[u][1] <= moos[v][1];
	}
	private static void init() {
		n = scanner.nextInt();
		neighbors = new Set[n];
		unremoved = new HashSet<>(n);
		for (int u = 0; u < n; u++) {
			neighbors[u] = new HashSet<>();
			unremoved.add(u);
		}
		int[][] moos = new int[n][2];
		for (int u = 0; u < n; u++) {
			moos[u] = new int[] {
					scanner.nextInt(),
					scanner.nextInt()
			};
			for (int v = 0; v < u; v++) {
				if (interactable(moos, u, v)) {
					neighbors[u].add(v);
					neighbors[v].add(u);
				}
			}
		}
	}
	private static int minDegree() {
		int result = -1;
		for (int u : unremoved) {
			if (neighbors[u].size() > 0 &&
					    (result < 0 || neighbors[u].size() < neighbors[result].size())) {
				result = u;
			}
		}
		return result;
	}
	private static void remove(int u) {
		for (int v : neighbors[u])
			neighbors[v].remove(u);
		unremoved.remove(u);
	}
	private static void actualMain() {
		init();
		while (true) {
			int u = minDegree();
			if (u < 0) break;
			remove(u);
		}
		out.println(unremoved.size());
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
