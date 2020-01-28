import java.io.*;
import java.util.*;
public class idiomgame {
	private static final String PROG = "idiomgame";
	
	private static final int INFINITY = Integer.MAX_VALUE / 2;
	private static int n;
	private static int[][] edges;
	private static Set<Integer> unjudged;
	private static int[] time;
	private static class Idiom {
		private static final int CHARACTER_LENGTH = 4;
		private String head;
		private String tail;
		private int t;
		private Idiom() {
			t = scanner.nextInt();
			String idiom = scanner.next();
			head = idiom.substring(0, CHARACTER_LENGTH);
			tail = idiom.substring(idiom.length() - CHARACTER_LENGTH);
		}
		private boolean canBeFollowed(Idiom next) {
			return tail.compareTo(next.head) == 0;
		}
	}
	private static Idiom[] dictionary;
	private static void connect(int u, int v) {
		if (dictionary[u].canBeFollowed(dictionary[v]))
			edges[u][v] = dictionary[u].t;
	}
	private static boolean init() {
		n = scanner.nextInt();
		if (n == 0) return false;
		edges = new int[n][n];
		dictionary = new Idiom[n];
		unjudged = new HashSet<>(n);
		time = new int[n];
		for (int u = 0; u < n; u++) {
			time[u] = INFINITY;
			unjudged.add(u);
			dictionary[u] = new Idiom();
			for (int v = u + 1; v < n; v++)
				edges[u][v] = edges[v][u] = INFINITY;
		}
		for (int u = 0; u < n; u++) for (int v = u + 1; v < n; v++) {
			connect(u, v);
			connect(v, u);
		}
		time[0] = 0;
		return true;
	}
	private static int nearest() {
		int result = -1;
		int min = INFINITY;
		for (int u : unjudged) if (time[u] < min) {
			result = u;
			min = time[u];
		}
		return result;
	}
	private static void findShortestPath() {
		while (!unjudged.isEmpty()) {
			int u = nearest();
			if (u == -1) break;
			unjudged.remove(u);
			for (int v = 0; v < n; v++) if (unjudged.contains(v))
				time[v] = Math.min(time[u] + edges[u][v], time[v]);
		}
	}
	private static void actualMain() {
		while (init()) {
			findShortestPath();
			out.println(time[n - 1] == INFINITY ? -1 : time[n - 1]);
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
