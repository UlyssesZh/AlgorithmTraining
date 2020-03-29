import java.io.*;
import java.util.*;
public class pails {
	private static final String PROG = "pails";
	
	private static boolean[][] reached;
	private static int x;
	private static int y;
	private static int k;
	private static int m;
	private static int result;
	private static void init() {
		x = scanner.nextInt();
		y = scanner.nextInt();
		k = scanner.nextInt();
		m = scanner.nextInt();
		reached = new boolean[x + 1][y + 1];
		result = Integer.MAX_VALUE / 2;
	}
	private static void dfs(int cX, int cY, int cK) {
		if (reached[cX][cY] || cK > k) return;
		reached[cX][cY] = true;
		result = Math.min(result, Math.abs(cX + cY - m));
		cK++;
		dfs(cX, 0, cK);
		dfs(0, cY, cK);
		dfs(x, cY, cK);
		dfs(cX, y, cK);
		int move = Math.min(cX, y - cY);
		dfs(cX - move, cY + move, cK);
		move = Math.min(x - cX, cY);
		dfs(cX + move, cY - move, cK);
	}
	private static void actualMain() {
		init();
		dfs(0, 0, 0);
		out.println(result);
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
