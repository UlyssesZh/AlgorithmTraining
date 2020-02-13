import java.io.*;
import java.util.*;
public class goodnum2 {
	private static final String PROG = "goodnum2";
	
	private static int lenN;
	private static int lenM;
	private static int[] digitsN;
	private static int[] digitsM;
	private static long[] dp;
	private static boolean flag;
	private static void init() {
		long m = scanner.nextLong();
		if (m > 1) {
			flag = true;
			m--;
		}
		long n = scanner.nextLong();
		lenN = (int) Math.floor(Math.log10(n)) + 1;
		lenM = (int) Math.floor(Math.log10(m)) + 1;
		digitsN = new int[lenN + 1];
		digitsM = new int[lenM + 1];
		long t = n;
		int i = 0;
		while (i < lenN) {
			digitsN[++i] = (int) t % 10;
			t /= 10;
		}
		t = m;
		i = 0;
		while (i < lenM) {
			digitsM[++i] = (int) t % 10;
			t /= 10;
		}
		dp = new long[lenN + 1];
	}
	private static long dfs(int len, int[] digits, boolean isMax) {
		long result = 0;
		if (len == 0) return 1;
		if (!isMax && dp[len] != -1) return dp[len];
		int maxDigit = isMax ? digits[len] : 9;
		for (int i = 0; i <= maxDigit; i++) if (i != 4)
			result += dfs(len - 1, digits, isMax && i == maxDigit);
		if (!isMax) dp[len] = result;
		return result;
	}
	private static void actualMain() {
		init();
		for (int i = 0; i <= lenN; i++) dp[i] = -1;
		long result = dfs(lenN, digitsN, true);
		for (int i = 0; i <= lenN; i++) dp[i] = -1;
		result -= dfs(lenM, digitsM, true);
		out.println(flag ? result : result + 1);
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
