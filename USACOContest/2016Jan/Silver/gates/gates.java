import java.io.*;
import java.util.*;
public class gates {
	private static final String PROG = "gates";
	
	private static class Edge {
		private Vertex begin, end;
		private Edge(Vertex begin, Vertex end) {
			this.begin = begin;
			this.end = end;
		}
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Edge edge = (Edge) o;
			return Objects.equals(begin, edge.begin) &&
					       Objects.equals(end, edge.end);
		}
		@Override
		public int hashCode() {
			return Objects.hash(begin, end);
		}
	}
	private static class Vertex {
		int x, y;
		private Vertex(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Vertex vertex = (Vertex) o;
			return x == vertex.x &&
					       y == vertex.y;
		}
		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
		private Vertex N() {
			return new Vertex(x, y + 1);
		}
		private Vertex S() {
			return new Vertex(x, y - 1);
		}
		private Vertex E() {
			return new Vertex(x + 1, y);
		}
		private Vertex W() {
			return new Vertex(x - 1, y);
		}
	}
	private static void actualMain() {
		scanner.nextInt();
		Vertex vertex = new Vertex(0, 0);
		Vertex last;
		Edge edge = null;
		Set<Edge> edges = new HashSet<>();
		Set<Vertex> vertices = new HashSet<>();
		vertices.add(vertex);
		for (char c : scanner.next().toCharArray()) {
			last = vertex;
			switch (c) {
				case 'N':
					vertex = last.N();
					edge = new Edge(last, vertex);
					break;
				case 'S':
					vertex = last.S();
					edge = new Edge(vertex, last);
					break;
				case 'E':
					vertex = last.E();
					edge = new Edge(last, vertex);
					break;
				case 'W':
					vertex = last.W();
					edge = new Edge(vertex, last);
					break;
			}
			vertices.add(vertex);
			edges.add(edge);
		}
		out.println(edges.size() - vertices.size() + 1);
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
