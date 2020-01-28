import java.io.*;
import java.util.*;
public class linkedcamp {
	private static final String PROG = "linkedcamp";
	
	private static final int INFINITY = Integer.MAX_VALUE / 2;
	private static int n;
	private static int[] distances;
	private static Map<Integer, Integer>[] connections;
	private static CounterSetQueue queue;
	private static class CounterSetQueue {
		private Set<Integer> set;
		private Queue<Integer> queue;
		private Map<Integer, Integer> counter;
		private CounterSetQueue() {
			set = new HashSet<>();
			queue = new LinkedList<>();
			counter = new HashMap<>();
		}
		private boolean offer(int e) {
			set.add(e);
			queue.offer(e);
			addKey(e);
			counter.put(e, 1 + counter.get(e));
			return counter.get(e) > n;
		}
		private int poll() {
			int result = queue.poll();
			set.remove(result);
			return result;
		}
		private boolean contains(int e) {
			return set.contains(e);
		}
		private boolean isEmpty() {
			return set.isEmpty();
		}
		private void addKey(int e) {
			if (!counter.containsKey(e)) counter.put(e, 0);
		}
	}
	private static void addEdge(int u, int v, int w) {
		connections[u].put(v, w);
	}
	private static boolean init() {
		if (!scanner.hasNext()) return false;
		n = scanner.nextInt() + 1;
		distances = new int[n];
		connections = new Map[n];
		queue = new CounterSetQueue();
		for (int u = 0; u < n; u++) {
			distances[u] = INFINITY;
			connections[u] = new HashMap<>();
		}
		int m = scanner.nextInt();
		for (int u = 1; u < n; u++) {
			addEdge(u - 1, u, scanner.nextInt());
			addEdge(u, u - 1, 0);
		}
		for (int edge = 0; edge < m; edge++) {
			int i = scanner.nextInt();
			int j = scanner.nextInt();
			addEdge(j, i - 1, -scanner.nextInt());
		}
		distances[n - 1] = 0;
		queue.offer(n - 1);
		return true;
	}
	private static boolean findNegativeCircuit() {
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (int v: connections[u].keySet()) {
				int newDistance = distances[u] + connections[u].get(v);
				if (newDistance < distances[v]) {
					distances[v] = newDistance;
					if (!queue.contains(v) && queue.offer(v))
						return true;
				}
			}
		}
		return false;
	}
	private static void actualMain() {
		while (init())
			out.println(findNegativeCircuit() ? "Bad Estimations" : -distances[0]);
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
