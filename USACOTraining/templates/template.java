/*
ID: ulysses4
LANG: JAVA
PROG: template
*/
import java.io.*;
import java.util.*;
public class template {
	private static final String PROG = "template";
	
	private static void actualMain() {
		// write programs here
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
