import java.io.*;
import java.util.*;
public class spfa {
	private static final String PROG = "spfa";
	
	private static final int INFINITY = Integer.MAX_VALUE / 2;
	private static int n;
	private static int[] distances;
	private static int[] previous;
	private static List<int[]>[] connections;
	private static SetQueue queue;
	private static class SetQueue {
		private Set<Integer> set;
		private Queue<Integer> queue;
		private SetQueue() {
			set = new HashSet<>();
			queue = new LinkedList<>();
		}
		private void offer(int e) {
			set.add(e);
			queue.offer(e);
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
	}
	private static void init() {
		n = scanner.nextInt();
		distances = new int[n];
		previous = new int[n];
		connections = new List[n];
		queue = new SetQueue();
		for (int u = 0; u < n; u++) {
			distances[u] = INFINITY;
			previous[u] = -1;
			connections[u] = new ArrayList<>();
		}
		int m = scanner.nextInt();
		for (int edge = 0; edge < m; edge++)
			connections[scanner.nextInt()].add(
					new int[] {scanner.nextInt(), scanner.nextInt()});
		distances[0] = 0;
		queue.offer(0);
	}
	private static void findShortestPath() {
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (int[] edge : connections[u]) {
				int newDistance = distances[u] + edge[1];
				int v = edge[0];
				if (newDistance < distances[v]) {
					distances[v] = newDistance;
					previous[v] = u;
					if (!queue.contains(v)) queue.offer(v);
				}
			}
		}
	}
	private static void output() {
		for (int u = 0; u < n; u++) {
			out.print(distances[u]);
			Stack<Integer> path = new Stack<>();
			for (int v = u; v >= 0; v = previous[v])
				path.add(v);
			while (!path.empty()) {
				out.print(' ');
				out.print(path.pop());
			}
			out.println();
		}
	}
	private static void actualMain() {
		init();
		findShortestPath();
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
