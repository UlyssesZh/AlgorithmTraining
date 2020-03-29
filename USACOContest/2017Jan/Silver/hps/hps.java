import java.io.*;
import java.util.*;
public class hps {
	private static final String PROG = "hps";
	
	private static int[] sumH;
	private static int[] sumP;
	private static int[] sumS;
	private static int n;
	private static void init() {
		n = scanner.nextInt();
		sumH = new int[n + 1];
		sumP = new int[n + 1];
		sumS = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			String str = scanner.next();
			sumH[i] = sumH[i - 1] + (str.equals("S") ? 1 : 0);
			sumP[i] = sumP[i - 1] + (str.equals("H") ? 1 : 0);
			sumS[i] = sumS[i - 1] + (str.equals("P") ? 1 : 0);
		}
	}
	private static int calc() {
		int result = 0;
		for (int x = 0; x <= n; x++) {
			result = Math.max(result, sumH[x] + sumP[n] - sumP[x]);
			result = Math.max(result, sumP[x] + sumH[n] - sumH[x]);
			result = Math.max(result, sumS[x] + sumH[n] - sumH[x]);
			result = Math.max(result, sumH[x] + sumS[n] - sumS[x]);
			result = Math.max(result, sumS[x] + sumP[n] - sumP[x]);
			result = Math.max(result, sumP[x] + sumS[n] - sumS[x]);
		}
		return result;
	}
	private static void actualMain() {
		init();
		out.println(calc());
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
