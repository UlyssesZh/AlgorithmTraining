/*
ID: ulysses4
LANG: JAVA
PROG: combo
*/
import java.io.*;
import java.util.*;
public class combo {
	private static final String PROG = "combo";
	
	private static int N;
	private static List<Integer[]> solutions = new ArrayList<>();
	private static boolean interved(int dist) {
		return dist >= -2 && dist <= 2;
	}
	private static boolean legal(int i, int key) {
		int dist = i - key;
		return interved(dist) || interved(dist - N) || interved(dist + N);
	}
	private static boolean arrayEqual(Integer[] a1, Integer[] a2) {
		for (int i = 0; i < 3; i++)
			if (a1[i] != a2[i]) return false;
		return true;
	}
	private static void add(Integer[] solution) {
		for (int i = 0; i < solutions.size(); i++)
			if (arrayEqual(solutions.get(i), solution)) return;
		solutions.add(solution);
	}
	private static void actualMain() {
		N = scanner.nextInt();
		int[] farmer = new int[] {scanner.nextInt(),
			scanner.nextInt(), scanner.nextInt()};
		int[] master = new int[] {scanner.nextInt(),
			scanner.nextInt(), scanner.nextInt()};
		for (int i1 = 1; i1 <= N; i1++)
			for (int i2 = 1; i2 <= N; i2++)
				for (int i3 = 1; i3 <= N; i3++)
					if (legal(i1, farmer[0]) && legal(i2, farmer[1]) && legal(i3, farmer[2]) ||
						legal(i1, master[0]) && legal(i2, master[1]) && legal(i3, master[2]))
						add(new Integer[] {i1, i2, i3});
		out.println(solutions.size());
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
