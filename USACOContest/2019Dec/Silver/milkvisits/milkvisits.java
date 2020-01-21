/*
ID: ulysses4
LANG: JAVA
PROG: milkvisits
*/
import java.io.*;
import java.util.*;
public class milkvisits {
	private static final String PROG = "milkvisits";
	
	private static <T> T peek(List<T> list) {
		return list.get(list.size() - 1);
	}
	private static <T> void pop(List<T> list) {
		list.remove(list.size() - 1);
	}
	private static void actualMain() {
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		String cowsString = scanner.next();
		char[] cows = new char[N];
		cowsString.getChars(0, N, cows, 0);
		ArrayList<Integer>[] neighbors = new ArrayList[N];
		for (int i = 0; i < N; i++) neighbors[i] = new ArrayList<>();
		for (int i = 0; i < N - 1; i++) {
			int X = scanner.nextInt() - 1;
			int Y = scanner.nextInt() - 1;
			neighbors[X].add(Y);
			neighbors[Y].add(X);
		}
		boolean[][] G = new boolean[N][N];
		boolean[][] H = new boolean[N][N];
		Set<Integer> travelled = new HashSet<>(N);
		travelled.add(0);
		List<Integer> path = new ArrayList<>();
		path.add(0);
		G[0][0] = cows[0] == 'G';
		H[0][0] = cows[0] == 'H';
		addPoint:
		while (travelled.size() < N) {
			Integer peek = peek(path);
			for (int next : neighbors[peek])
				if (!travelled.contains(next)) {
					path.add(next);
					travelled.add(next);
					boolean gFlag = false;
					boolean hFlag = false;
					for (int i = path.size() - 1; i >= 0; i--) {
						Integer point = path.get(i);
						if (cows[point] == 'G') gFlag = true;
						if (cows[point] == 'H') hFlag = true;
						G[point][next] = G[next][point] = gFlag;
						H[point][next] = H[next][point] = hFlag;
					}
					continue addPoint;
				}
			pop(path);
		}
		for (int i = 0; i < M; i++) {
			int A = scanner.nextInt() - 1;
			int B = scanner.nextInt() - 1;
			out.print((scanner.next().charAt(0) == 'G' ? G : H)[A][B] ? '1' : '0');
		}
		out.println();
/*ArrayList<Integer>[][] paths = new ArrayList[N][N];
		friends:
		for (int i = 0; i < M; i++) {
			int A = scanner.nextInt() - 1;
			int B = scanner.nextInt() - 1;
			char C = scanner.next().charAt(0);
			ArrayList<Integer> path;
			if (paths[A][B] != null) {
				path = paths[A][B];
			} else {
				path = new ArrayList<>();
				boolean[] marks = new boolean[N];
				marks[A] = true;
				path.add(A);
				addPath:
				while (true) {
					Integer peek = peek(path);
					if (peek == B) break;
					for (int next : neighbors[peek])
						if (!marks[next]) {
							path.add(next);
							marks[next] = true;
							paths[A][next] = paths[next][A] = new ArrayList<>(path);
							ArrayList<Integer> newPath = new ArrayList<>();
							newPath.add(peek);
							for (int j = path.size() - 2; j >= 0; j--) {
								Integer point = path.get(j);
								newPath.add(point);
								paths[point][peek] = paths[peek][point] = new ArrayList<>(newPath);
							}
							if (paths[next][B] != null) {
								boolean flag = true;
								for (int farm : paths[next][B]) {
									if (flag) {
										flag = false;
										continue;
									}
									path.add(farm);
									paths[A][farm] = paths[farm][A] = new ArrayList<>(path);
								}
								break addPath;
							}
							continue addPath;
						}
					pop(path);
				}
			}
			// out.println(path.toString());
			for (int farm : path)
				if (cows[farm] == C) {
					out.print('1');
					continue friends;
				}
			out.print('0');
		}
		out.print('\n');*/
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
