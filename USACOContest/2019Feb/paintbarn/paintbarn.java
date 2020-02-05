import java.io.*;
import java.util.*;
public class paintbarn {
	private static final String PROG = "paintbarn";
	
	private static void actualMain() {
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		int[][] dp = new int[1000][1000];
		while(n-- > 0) {
			int x1 = scanner.nextInt();
			int y1 = scanner.nextInt();
			int x2 = scanner.nextInt();
			int y2 = scanner.nextInt();
			dp[x1][y1]++;
			dp[x1][y2]--;
			dp[x2][y1]--;
			dp[x2][y2]++;
		}
		int result = 0;
		for (int i = 0; i < 1000; i++)
			for (int j = 0; j < 1000; j++) {
				if (i > 0) dp[i][j] += dp[i - 1][j];
				if (j > 0) dp[i][j] += dp[i][j - 1];
				if (i > 0 && j > 0) dp[i][j] -= dp[i - 1][j - 1];
				if (dp[i][j] == k) result++;
			}
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
