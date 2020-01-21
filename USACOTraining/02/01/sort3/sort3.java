/*
ID: ulysses4
LANG: JAVA
PROG: sort3
*/
import java.io.*;
import java.util.*;
public class sort3 {
	private static final String PROG = "sort3";
	
	private static void actualMain() {
		int N = scanner.nextInt();
		int[] a = new int[N];
		for (int i = 0; i < N; i++) a[i] = scanner.nextInt();
		int ret = 0;
		int[] count = new int[4];
		for (int n : a) count[n]++;
		Queue<Integer> bi = new LinkedList<>();
		Queue<Integer> tri = new LinkedList<>();
		for (int i = count[1]; i < N; i++)
			if (a[i] == 1) (i < count[1] + count[2] ? bi : tri).add(i);
		for (int i = 0; i < count[1]; i++)
			if (a[i] != 1) {
				ret++;
				int j = ((a[i] == 2 ? !bi.isEmpty() : tri.isEmpty()) ? bi : tri).poll();
				int t = a[i];
				a[i] = a[j];
				a[j] = t;
			}
		for (int i = count[1]; i < count[1] + count[2]; i++) if (a[i] != 2) ret++;
		out.println(ret);
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
