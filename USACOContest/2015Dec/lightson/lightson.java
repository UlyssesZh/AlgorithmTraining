import java.io.*;
import java.util.*;
public class lightson {
	private static final String PROG = "lightson";
	
	private static class Pos {
		int x, y;
		private Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		private Pos() {
			this(scanner.nextInt() - 1, scanner.nextInt() - 1);
		}
		private void yieldNeighbors(PosYielder yielder) {
			if (x > 0) yielder.yield(new Pos(x - 1, y));
			if (x + 1 < n) yielder.yield(new Pos(x + 1, y));
			if (y > 0) yielder.yield(new Pos(x, y - 1));
			if (y + 1 < n) yielder.yield(new Pos(x, y + 1));
		}
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Pos pos = (Pos) o;
			return x == pos.x &&
					       y == pos.y;
		}
		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}
	private interface PosYielder {
		void yield(Pos pos);
	}
	private static int n;
	private static Set<Pos>[][] switches;
	private static Set<Pos> reachable;
	private static Set<Pos> visited;
	private static Set<Pos> lit;
	private static void init() {
		n = scanner.nextInt();
		switches = new Set[n][n];
		for (int x = 0; x < n; x++) for (int y = 0; y < n; y++)
			switches[x][y] = new HashSet<>();
		for (int m = scanner.nextInt(); m > 0; m--) {
			switches[scanner.nextInt() - 1][scanner.nextInt() - 1]
					.add(new Pos());
		}
		lit = new HashSet<>();
		reachable = new HashSet<>();
		visited = new HashSet<>();
	}
	private static void visit(Pos pos) {
		if (visited.contains(pos)) return;
		visited.add(pos);
		for (Pos target : switches[pos.x][pos.y]) {
			lit.add(target);
			if (reachable.contains(target)) visit(target);
		}
		pos.yieldNeighbors(neighbor -> {
			reachable.add(neighbor);
			if (lit.contains(neighbor)) visit(neighbor);
		});
	}
	private static void actualMain() {
		init();
		Pos origin = new Pos(0, 0);
		lit.add(origin);
		reachable.add(origin);
		visit(origin);
		out.println(lit.size());
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
