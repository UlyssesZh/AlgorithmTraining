import java.io.*;
import java.util.*;
public class mooyomooyo {
	private static final String PROG = "mooyomooyo";
	
	private static int n;
	private static int k;
	private static int[][] grid;
	private static int[][] marks;
	private static List<Integer> count;
	private static int maxId;
	private static class Pos {
		private int i;
		private int j;
		private Pos(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	private static void init() {
		n = scanner.nextInt();
		k = scanner.nextInt();
		grid = new int[n][10];
		for (int i = n - 1; i >= 0; i--) {
			String row = scanner.next();
			for (int j = 0; j < 10; j++)
				grid[i][j] = row.charAt(j) - '0';
		}
	}
	private static void fall() {
		for (int j = 0; j < 10; j++) {
			int zeroAt = -1;
			for (int i = 0; i < n; i++) {
				if (zeroAt == -1) {
					if (grid[i][j] == 0) zeroAt = i;
				} else {
					if (grid[i][j] != 0) {
						grid[zeroAt++][j] = grid[i][j];
						grid[i][j] = 0;
					}
				}
			}
		}
	}
	private static void color() {
		maxId = 0;
		marks = new int[n][10];
		count = new ArrayList<>();
		count.add(0);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 10; j++) {
				if (marks[i][j] > 0 || grid[i][j] == 0) continue;
				maxId++;
				count.add(0);
				Queue<Pos> queue = new LinkedList<>();
				queue.offer(new Pos(i, j));
				while (!queue.isEmpty()) {
					Pos pos = queue.poll();
					int i1 = pos.i;
					int j1 = pos.j;
					if (i1 >= n || j1 >= 10 || i1 < 0 || j1 < 0 ||
							    marks[i1][j1] > 0 || grid[i1][j1] != grid[i][j])
						continue;
					marks[i1][j1] = maxId;
					count.set(maxId, 1 + count.get(maxId));
					queue.offer(new Pos(i1 - 1, j1));
					queue.offer(new Pos(i1 + 1, j1));
					queue.offer(new Pos(i1, j1 - 1));
					queue.offer(new Pos(i1, j1 + 1));
				}
			}
		}
	}
	private static boolean clean() {
		boolean result = false;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < 10; j++)
				if (count.get(marks[i][j]) >= k) {
					grid[i][j] = 0;
					result = true;
				}
		return result;
	}
	private static void output() {
		for (int i = n - 1; i >= 0; i--) {
			for (int j = 0; j < 10; j++)
				out.print(grid[i][j]);
			out.println();
		}
	}
	private static void actualMain() {
		init();
		do {
			fall();
			color();
		} while (clean());
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
