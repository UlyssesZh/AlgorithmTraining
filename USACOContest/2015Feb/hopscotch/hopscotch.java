import java.io.*;
import java.util.*;
public class hopscotch {
	private static final String PROG = "hopscotch";
	
	private static void actualMain() {
		int r = scanner.nextInt();
		int c = scanner.nextInt();
		int k = scanner.nextInt();
		int[][] labels = new int[r][c];
		for (int y = 0; y < r; y++) {
			for (int x = 0; x < c; x++) {
				labels[y][x] = scanner.nextInt();
			}
		}
		int[][] dp = new int[r][c];
		dp[0][0] = 1;
		for (int y = 1; y < r; y++) {
			for (int x = 1; x < c; x++) {
				for (int i = 0; i < y; i++) {
					for (int j = 0; j < x; j++) {
						if (labels[i][j] != labels[y][x]) {
							dp[y][x] += dp[i][j];
							dp[y][x] %= 1000000007;
						}
					}
				}
			}
		}
		out.println(dp[r - 1][c - 1]);
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
