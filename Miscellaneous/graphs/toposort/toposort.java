import java.io.*;
import java.util.*;
public class toposort {
	private static final String PROG = "toposort";
	
	private static int n;
	private static int[] inDegrees;
	private static Set<Integer>[] outs;
	private static Queue<Integer> result;
	private static boolean init() {
		n = scanner.nextInt();
		if (n == 0) return false;
		inDegrees = new int[n];
		outs = new Set[n];
		for (int u = 0; u < n; u++) outs[u] = new HashSet<>();
		int m = scanner.nextInt();
		for (int edge = 0; edge < m; edge++) {
			int u = scanner.nextInt() - 1;
			int v = scanner.nextInt() - 1;
			outs[u].add(v);
			inDegrees[v]++;
		}
		result = new LinkedList<>();
		return true;
	}
	private static void topologicalSort() {
		Queue<Integer> queue = new LinkedList<>();
		for (int u = 0; u < n; u++)
			if (inDegrees[u] == 0) queue.offer(u);
		while (!queue.isEmpty()) {
			int u = queue.poll();
			result.offer(u);
			for (int v : outs[u])
				if (--inDegrees[v] == 0) queue.offer(v);
		}
	}
	private static void output() {
		if (result.size() == n)
			while (!result.isEmpty()) {
				out.print(result.poll() + 1);
				out.print(result.isEmpty() ? '\n' : ' ');
			}
		else out.println("cycled");
	}
	private static void actualMain() {
		while (init()) {
			topologicalSort();
			output();
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
