import java.io.*;
import java.util.*;
public class windowpains {
	private static final String PROG = "windowpains";
	
	private static Set<Integer>[] possibilities = new Set[] {
		new HashSet<>(Arrays.asList(0)),
		new HashSet<>(Arrays.asList(0, 1)),
		new HashSet<>(Arrays.asList(1, 2)),
		new HashSet<>(Arrays.asList(2)),
		new HashSet<>(Arrays.asList(0, 3)),
		new HashSet<>(Arrays.asList(0, 1, 3, 4)),
		new HashSet<>(Arrays.asList(1, 2, 4, 5)),
		new HashSet<>(Arrays.asList(2, 5)),
		new HashSet<>(Arrays.asList(3, 6)),
		new HashSet<>(Arrays.asList(3, 4, 6, 7)),
		new HashSet<>(Arrays.asList(4, 5, 7, 8)),
		new HashSet<>(Arrays.asList(5, 8)),
		new HashSet<>(Arrays.asList(6)),
		new HashSet<>(Arrays.asList(6, 7)),
		new HashSet<>(Arrays.asList(7, 8)),
		new HashSet<>(Arrays.asList(8)),
	};
	private static int[] inDegrees;
	private static Set<Integer>[] outs;
	private static boolean init() {
		if (scanner.next().equals("ENDOFINPUT")) return false;
		inDegrees = new int[9];
		outs = new Set[9];
		for (int u = 0; u < 9; u++) outs[u] = new HashSet<>();
		for (Set<Integer> possibility : possibilities) {
			int prior = scanner.nextInt() - 1;
			for (int window : possibility)
				if (window != prior && !outs[prior].contains(window)) {
					inDegrees[window]++;
					outs[prior].add(window);
				}
		}
		scanner.next();
		return true;
	}
	private static boolean topologicalSort() {
		Queue<Integer> queue = new LinkedList<>();
		int result = 0;
		for (int u = 0; u < 9; u++)
			if (inDegrees[u] == 0) queue.offer(u);
		while (!queue.isEmpty()) {
			result++;
			for (int v : outs[queue.poll()])
				if (--inDegrees[v] == 0) queue.offer(v);
		}
		return result == 9;
	}
	private static void actualMain() {
		while (init())
			out.println(topologicalSort() ? "CLEAN" : "BROKEN");
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
