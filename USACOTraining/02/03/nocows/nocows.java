/*
ID: ulysses4
LANG: JAVA
PROG: nocows
*/
import java.io.*;
import java.util.*;
public class nocows {
	private static final String PROG = "nocows";
	
	private static void actualMain() {
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		// int[][] dp = new int[maxI][k];
		// dp[i][j] means ans at 2i+1 nodes and j+1 layers
		// max layer num is k, max j is k-1
		// so max node num is 2**(k+1)-1
		// so max i is 2**k-1
		int[][] dp = new int[n][k];
		dp[0][0] = 1;
		// dp[i1+i2+1][max(j1,j2)+1] += dp[i1][j1] * dp[i2][j2]
		/*for (int i1 = 0; i1 < n / 2; i1++)
			for (int i2 = 0; i2 < n / 2; i2++)
				for (int j1 = 0; j1 < k - 1; j1++)
					for (int j2 = 0; j2 < k - 1; j2++) {
						int i = i1 + i2 + 1;
						int j = Math.max(j1, j2) + 1;
						dp[i][j] += dp[i1][j1] * dp[i2][j2];
						out.printf("dp[%d][%d] = %d\n", 2*i+1, j+1, dp[i][j]);
					}*/
		for (int j = 1; j < k; j++) { // start from 2nd layer
			int j1 = j - 1; // j = max(j1, j2) + 1
			int maxI = Math.min(1 << j, n / 2 + 1);
			for (int i = j; i <= maxI; i++) { // start from 2*(j+1)-1 nodes
				for (int i1 = 0; i1 < i; i1++) {
					int i2 = i - i1 - 1; // i = i1 + i2 + 1
					for (int j2 = 0; j2 < j; j2++) {
						dp[i][j] += dp[i1][j1] * dp[i2][j2];
						if (i1 != i2 || j1 != j2)
							dp[i][j] += dp[i1][j2] * dp[i2][j1];
						dp[i][j] %= 9901;
					}
				}
			}
		}
		out.println(dp[n / 2][k - 1]);
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
