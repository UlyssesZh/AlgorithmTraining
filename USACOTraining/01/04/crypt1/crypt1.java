/*
ID: ulysses4
LANG: JAVA
PROG: crypt1
*/
import java.io.*;
import java.util.*;
public class crypt1 {
	private static final String PROG = "crypt1";
	
	private static int N;
	private static int[] nums;
	private static boolean legal(int n) {
		while (n > 0) {
			if (!contains(n % 10)) return false;
			n /= 10;
		}
		return true;
	}
	private static boolean contains(int num) {
		for (int i = 0; i < nums.length; i++)
			if (nums[i] == num) return true;
		return false;
	}
	private static void actualMain() {
		N = scanner.nextInt();
		nums = new int[N];
		for (int i = 0; i < N; i++)
			nums[i] = scanner.nextInt();
		int result = 0;
		for (int i1 : nums) {
			int n1 = i1 * 100;
			for (int i2 : nums) {
				int n2 = i2 * 10;
				for (int i3 : nums) {
					int n = n1 + n2 + i3;
					for (int i4 : nums) {
						int p1 = n * i4;
						if (p1 >= 1000 || !legal(p1)) continue;
						for (int i5 : nums) {
							int p2 = n * i5;
							if (p2 >= 1000 || !legal(p2)) continue;
							if (legal(p1 * 10 + p2)) result++;
						}
					}
				}
			}
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
