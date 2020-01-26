import java.io.*;
import java.util.*;
public class heavycargo {
	private static final String PROG = "heavycargo";
	
	private static final int INFINITY = Integer.MAX_VALUE / 2;
	private static int n;
	private static int[][] roads;
	private static Set<Integer> unjudged;
	private static int[] capacity;
	private static int target;
	private static Map<String, Integer> citiesIndex;
	private static int parseCity() {
		String city = scanner.next();
		if (citiesIndex.containsKey(city))
			return citiesIndex.get(city);
		else {
			int result = citiesIndex.size();
			citiesIndex.put(city, result);
			return result;
		}
	}
	private static boolean init() {
		n = scanner.nextInt();
		if (n == 0) return false;
		roads = new int[n][n];
		unjudged = new HashSet<>(n);
		capacity = new int[n];
		citiesIndex = new HashMap<>(n);
		for (int u = 0; u < n; u++) {
			roads[u][u] = INFINITY;
			capacity[u] = 0;
			unjudged.add(u);
		}
		int m = scanner.nextInt();
		for (int edge = 0; edge < m; edge++) {
			int u = parseCity();
			int v = parseCity();
			roads[u][v] = roads[v][u] = scanner.nextInt();
		}
		capacity[parseCity()] = INFINITY;
		target = parseCity();
		return true;
	}
	private static int optimal() {
		int result = -1;
		int max = 0;
		for (int u : unjudged) if (capacity[u] > max) {
			result = u;
			max = capacity[u];
		}
		return result;
	}
	private static void dijkstra() {
		while (!unjudged.isEmpty()) {
			int u = optimal();
			unjudged.remove(u);
			if (u == target) break;
			for (int v = 0; v < n; v++) {
				if (!unjudged.contains(v)) continue;
				capacity[v] = Math.max(capacity[v], Math.min(capacity[u], roads[u][v]));
			}
		}
	}
	private static void actualMain() {
		while(init()) {
			dijkstra();
			out.println(capacity[target]);
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
