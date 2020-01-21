/*
ID: ulysses4
LANG: JAVA
PROG: milk2
*/
import java.io.*;
import java.util.*;
public class milk2 {
	private static final String PROG = "milk2";
	
	private static void actualMain() {
		int N = scanner.nextInt();
		int N1 = N + 1;
		int[] starts = new int[N1];
		int[] ends = new int[N1];
		Integer[] startIndices = new Integer[N1];
		Integer[] endIndices = new Integer[N1];
		for (int i = 0; i < N; i++) {
			starts[i] = scanner.nextInt();
			ends[i] = scanner.nextInt();
			startIndices[i] = i;
			endIndices[i] = i;
		}
		starts[N] = Integer.MAX_VALUE;
		ends[N] = Integer.MAX_VALUE;
		startIndices[N] = N;
		endIndices[N] = N;
		Arrays.sort(startIndices, (o1, o2) -> Integer.compare(starts[o1], starts[o2]));
		Arrays.sort(endIndices, (o1, o2) -> Integer.compare(ends[o1], ends[o2]));
		int result1 = 0;
		int result2 = 0;
		int milking = 0;
		int lastMilking = 0;
		int lastTime = Integer.min(starts[startIndices[0]], ends[endIndices[0]]);
		for (int i = 0, j = 0; i <= N && j <= N;) {
			int currentTime;
			int time1 = starts[startIndices[i]];
			int time2 = ends[endIndices[j]];
			switch (Integer.compare(time1, time2)) {
				case -1:
					currentTime = time1;
					i++;
					milking++;
					break;
				case 1:
					currentTime = time2;
					j++;
					milking--;
					break;
				default:
					currentTime = time1;
					i++;
					j++;
					break;
			}
			if (milking != 0 && lastMilking == 0) {
				int newResult = currentTime - lastTime;
				if (newResult > result2) result2 = newResult;
				lastTime = currentTime;
				lastMilking = milking;
			} else if (milking == 0 && lastMilking != 0) {
				int newResult = currentTime - lastTime;
				if (newResult > result1) result1 = newResult;
				lastTime = currentTime;
				lastMilking = milking;
			}
		}
		out.printf("%s %s\n", result1, result2);
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
