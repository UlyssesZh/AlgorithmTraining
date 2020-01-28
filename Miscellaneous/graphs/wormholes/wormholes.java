import java.io.*;
import java.util.*;
public class wormholes {
	private static final String PROG = "wormholes";
	
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
	private static void init() {
		n = scanner.nextInt();
		distances = new int[n];
		connections = new Map[n];
		queue = new CounterSetQueue();
		for (int u = 0; u < n; u++) {
			distances[u] = INFINITY;
			connections[u] = new HashMap<>();
		}
		int m = scanner.nextInt();
		int w = scanner.nextInt();
		for (int edge = 0; edge < m; edge++) {
			int u = scanner.nextInt() - 1;
			int v = scanner.nextInt() - 1;
			int time = scanner.nextInt();
			connections[u].put(v, time);
			connections[v].put(u, time);
		}
		for (int edge = 0; edge < w; edge++) {
			connections[scanner.nextInt() - 1].put(
					scanner.nextInt() - 1, -scanner.nextInt()
			);
		}
		distances[0] = 0;
		queue.offer(0);
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
		for (int f = scanner.nextInt(); f > 0; f--) {
			init();
			out.println(findNegativeCircuit() ? "YES" : "NO");
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
