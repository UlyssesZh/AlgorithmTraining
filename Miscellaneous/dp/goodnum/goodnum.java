import java.io.*;
import java.util.*;
public class goodnum {
	private static final String PROG = "goodnum";
	
	private static int lenN;
	private static int[][] dp;
	private static int[] digitsN;
	private static int[] digitsM;
	private static boolean flag;
	private static void init() {
		long m = scanner.nextLong();
		if (m > 1) {
			flag = true;
			m--;
		}
		long n = scanner.nextLong();
		lenN = (int) Math.floor(Math.log10(n)) + 1;
		digitsN = new int[lenN + 1];
		digitsM = new int[lenN + 1];
		long t = n;
		int i = 0;
		while (i < lenN) {
			digitsN[++i] = (int) t % 10;
			t /= 10;
		}
		t = m;
		i = 0;
		while (i < lenN) {
			digitsM[++i] = (int) t % 10;
			t /= 10;
		}
		dp = new int[lenN + 1][10];
	}
	private static void calc() {
		dp[0][0] = 1;
		for (int i = 1; i <= lenN; i++)
			for (int j = 0; j < 10; j++)
				for (int k = 0; k < 10; k++)
					if (j != 4 && !(j == 6 && k == 2))
						dp[i][j] += dp[i - 1][k];
	}
	private static long extract(int[] digits) {
		long result = 0;
		for (int i = lenN; i > 0; i--) {
			for (int j = 0; j < digits[i]; j++)
				if (j != 4)
					result += dp[i][j];
			if (digits[i] == 4) {
				result--;
				break;
			}
		}
		return result;
	}
	private static void actualMain() {
		init();
		calc();
		long result = extract(digitsN) - extract(digitsM);
		if (!flag) result++;
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
