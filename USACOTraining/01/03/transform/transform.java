/*
ID: ulysses4
LANG: JAVA
PROG: transform
*/
import java.io.*;
import java.util.*;
public class transform {
	private static final String PROG = "transform";
	
	private static int N = 0;
	private static char[][] rotate(char[][] pattern) {
		char[][] result = new char[N][N];
		for (int row = 0; row < N; row++)
			for (int col = 0; col < N; col++)
				result[row][col] = pattern[N - col - 1][row];
		return result;
	}
	private static char[][] reflect(char[][] pattern) {
		char[][] result = new char[N][N];
		for (int row = 0; row < N; row++)
			for (int col = 0; col < N; col++)
				result[row][col] = pattern[row][N - col - 1];
		return result;
	}
	private static boolean equal(char[][] pattern1, char[][] pattern2) {
		for (int row = 0; row < N; row++)
			for (int col = 0; col < N; col++)
				if (pattern1[row][col] != pattern2[row][col]) return false;
		return true;
	}
	private static void actualMain() {
		N = scanner.nextInt();
		char[][] pattern = new char[N][N];
		char[][] transformed = new char[N][N];
		for (int i = 0; i < N; i++)
			scanner.next().getChars(0, N, pattern[i], 0);
		for (int i = 0; i < N; i++)
			scanner.next().getChars(0, N, transformed[i], 0);
		int result = 7;
		tag1: {
			char[][] rotated = pattern;
			for (int i = 1; i <= 3; i++) {
				rotated = rotate(rotated);
				if (equal(rotated, transformed)) {
					result = i;
					break tag1;
				}
			}
			rotated = reflect(pattern);
			if (equal(rotated, transformed)) {
				result = 4;
				break tag1;
			}
			for (int i = 1; i <= 3; i++) {
				rotated = rotate(rotated);
				if (equal(rotated, transformed)) {
					result = 5;
					break tag1;
				}
			}
			if (equal(pattern, transformed)) {
				result = 6;
				break tag1;
			}
			result = 7;
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
