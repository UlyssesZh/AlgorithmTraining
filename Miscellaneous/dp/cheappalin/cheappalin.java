import java.io.*;
import java.util.*;
public class cheappalin {
	private static final String PROG = "cheappalin";
	
	private static void actualMain() {
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		String string = scanner.next();
		int[] cost = new int[n];
		for (int i = 0; i < n; i++)
			cost[scanner.next().charAt(0) - 'a'] =
					Math.min(scanner.nextInt(), scanner.nextInt());
		int[][] dp = new int[m][m];
		for (int i = m - 1; i >= 0; i--)
			for (int j = i + 1; j < m; j++)
				if (string.charAt(i) == string.charAt(j))
					dp[i][j] = dp[i + 1][j - 1];
				else
					dp[i][j] = Math.min(dp[i + 1][j] + cost[string.charAt(i) - 'a'],
							dp[i][j - 1] + cost[string.charAt(j) - 'a']);
		out.println(dp[0][m - 1]);
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
