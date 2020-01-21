/*
ID: ulysses4
LANG: JAVA
PROG: meetings
*/
import java.io.*;
import java.util.*;
public class meetings {
	private static final String PROG = "meetings";
	
	private static int N;
	private static int L;
	private static int[] w;
	private static double[] x;
	private static int[] d;
	private static Integer[] indices;
	private static int totalWeight;
	private static int meetings;
	private static int weight;
	private static double dt;
	private static List<int[]> willCollide;
	private static int start;
	private static int end;
	private static void init() {
		N = scanner.nextInt();
		L = scanner.nextInt();
		w = new int[N]; // weight
		x = new double[N]; // position
		d = new int[N]; // speed
		indices = new Integer[N];
		totalWeight = 0;
		for (int i = 0; i < N; i++) {
			w[i] = scanner.nextInt();
			x[i] = scanner.nextInt();
			d[i] = scanner.nextInt();
			indices[i] = i;
			totalWeight += w[i];
		}
		totalWeight = (totalWeight - 1) / 2;
		Arrays.sort(indices, Comparator.comparingDouble(i -> x[i]));
		meetings = 0;
		weight = 0;
		willCollide = new ArrayList<>();
		start = 0;
		end = N;
	}
	private static void move() {
		int dStart = 0;
		int dEnd = 0;
		for (int j = start; j < end; j++) {
			int i = indices[j];
			x[i] += dt * d[i];
			if (x[i] <= 0) {
				x[i] = 0;
				dStart++;
				weight += w[i];
			} else if (x[i] >= L) {
				x[i] = L;
				dEnd++;
				weight += w[i];
			}
		}
		start += dStart;
		end -= dEnd;
		for (int[] pair : willCollide) {
			d[pair[0]] *= -1;
			d[pair[1]] *= -1;
		}
		meetings += willCollide.size();
		willCollide.clear();
	}
	private static void calc() {
		int left = -1;
		double min = Math.min(x[indices[start]], L - x[indices[end - 1]]);
		for (int j = start; j < end; j++) {
			int i = indices[j];
			if (left >= 0) {
				if (d[i] < 0) {
					double dist = (x[i] - x[left]) / 2;
					if (dist < min) {
						willCollide.clear();
						min = dist;
					}
					if (dist <= min) willCollide.add(new int[] {left, i});
				}
				left = -1;
			}
			if (d[i] > 0) left = i;
		}
		dt = min;
	}
	private static void printStatus() {
		out.print("x = ");
		for (int i = 0; i < N; i++) {
			out.print(x[i]);
			out.print(i == N - 1 ? '\n' : ", ");
		}
		out.print("d = ");
		for (int i = 0; i < N; i++) {
			out.print(d[i]);
			out.print(i == N - 1 ? '\n' : ", ");
		}
		/*out.print("dt = ");
		out.println(dt);
		out.print("weight = ");
		out.println(weight);
		out.print("start = ");
		out.println(start);
		out.print("end = ");
		out.println(end);*/
		out.println();
	}
	private static void actualMain() {
		init();
		// printStatus();
		while (weight <= totalWeight) {
			calc();
			move();
			// printStatus();
		}
		out.println(meetings);
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
