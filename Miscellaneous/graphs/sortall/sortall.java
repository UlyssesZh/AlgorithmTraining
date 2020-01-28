import java.io.*;
import java.util.*;
public class sortall {
	private static final String PROG = "sortall";
	
	private static int n;
	private static int m;
	private static int[] inDegrees;
	private static Set<Integer>[] outs;
	private static int determined;
	private static int parsedRelations;
	private static Queue<Integer> result;
	private static Set<Integer> considered;
	private static boolean init() {
		n = scanner.nextInt();
		if (n == 0) return false;
		inDegrees = new int[n];
		outs = new Set[n];
		for (int u = 0; u < n; u++) outs[u] = new HashSet<>();
		determined = 0;
		parsedRelations = 0;
		considered = new HashSet<>(n);
		m = scanner.nextInt();
		return true;
	}
	private static void topologicalSort() {
		result = new LinkedList<>();
		Queue<Integer> queue = new LinkedList<>();
		int[] temp = new int[n];
		System.arraycopy(inDegrees, 0, temp, 0, n);
		for (int u : considered)
			if (temp[u] == 0) queue.offer(u);
		while (!queue.isEmpty()) {
			int u = queue.poll();
			result.offer(u);
			for (int v : outs[u])
				if (--temp[v] == 0) queue.offer(v);
		}
	}
	private static void output() {
		switch (determined) {
			case 1:
				StringBuilder builder = new StringBuilder();
				while (!result.isEmpty())
					builder.append((char) ('A' + result.poll()));
				out.printf("Sorted sequence determined after %d relations: %s.\n",
						parsedRelations, builder.toString());
				break;
			case -1:
				out.printf("Inconsistency found after %d relations.\n",
						parsedRelations);
				break;
			case 0:
				out.println("Sorted sequence cannot be determined.");
				break;
		}
	}
	private static void actualMain() {
		while (init()) {
			for (int edge = 0; edge < m; edge++) {
				String string = scanner.next();
				int u = string.charAt(0) - 'A';
				int v = string.charAt(2) - 'A';
				considered.add(u);
				considered.add(v);
				outs[u].add(v);
				inDegrees[v]++;
				if (determined == 0) {
					topologicalSort();
					if (result.size() == n) {
						determined = 1;
						parsedRelations = edge + 1;
					} else if (result.size() < considered.size()) {
						determined = -1;
						parsedRelations = edge + 1;
					}
				}
			}
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
