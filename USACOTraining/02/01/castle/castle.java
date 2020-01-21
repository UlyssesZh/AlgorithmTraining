/*
ID: ulysses4
LANG: JAVA
PROG: castle
*/
import java.io.*;
import java.util.*;
public class castle {
	private static final String PROG = "castle";
	
	private static class Pos {
		int y, x;
		Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	private static class Edge {
		int y, x;
		char dir;
		Edge(int y, int x, char dir) {
			this.y = y;
			this.x = x;
			this.dir = dir;
		}
	}
	private static int width, height;
	private static boolean[][] verticalWalls, horizontalWalls;
	private static int[][] rooms;
	private static int roomsCount;
	private static boolean[][] marks;
	private static List<Integer> roomSizes;
	private static Edge[][] edges;
	private static Edge maxEdge;
	private static int largest;
	private static void handleInput() {
		width = scanner.nextInt();
		height = scanner.nextInt();
		verticalWalls = new boolean[height][width + 1];
		horizontalWalls = new boolean[height + 1][width];
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++) {
				int walls = scanner.nextInt();
				if ((walls & 1) > 0) // west
					verticalWalls[y][x] = true;
				if ((walls & 2) > 0) // north
					horizontalWalls[y][x] = true;
				if ((walls & 4) > 0) // east
					verticalWalls[y][x + 1] = true;
				if ((walls & 8) > 0) // south
					horizontalWalls[y + 1][x] = true;
			}
	}
	private static void distributeRooms() {
		rooms = new int[height][width];
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				rooms[y][x] = -1;
		roomsCount = 0;
		largest = 0;
		roomSizes = new ArrayList<>();
		while (true) {
			Pos pos = nextEmpty();
			if (pos == null) return;
			roomSizes.add(0);
			marks = new boolean[height][width];
			travelFrom(pos.y, pos.x);
			int size = roomSizes.get(roomsCount);
			if (size > largest) largest = size;
			roomsCount++;
		}
	}
	private static void buildEdges() {
		edges = new Edge[roomsCount][roomsCount];
		largest = 0;
		int south = height - 1;
		for (int x = 0; x < width; x++) {
			if (x > 0)
				for (int y = south; y >= 0; y--)
					if (verticalWalls[y][x])
						buildEdge(rooms[y][x - 1], rooms[y][x], y, x, 'E');
			for (int y = south; y > 0; y--)
				if (horizontalWalls[y][x])
					buildEdge(rooms[y - 1][x], rooms[y][x], y, x + 1, 'N');
		}
	}
	private static Pos nextEmpty() {
		for (int y = 0; y < height; y++)
			for (int x = 0; x < width; x++)
				if (rooms[y][x] < 0)
					return new Pos(y, x);
		return null;
	}
	private static void travelFrom(int y, int x) {
		rooms[y][x] = roomsCount;
		if (marks[y][x]) return;
		marks[y][x] = true;
		roomSizes.set(roomsCount, roomSizes.get(roomsCount) + 1);
		if (!verticalWalls[y][x]) travelFrom(y, x - 1);
		if (!horizontalWalls[y][x]) travelFrom(y - 1, x);
		if (!verticalWalls[y][x + 1]) travelFrom(y, x + 1);
		if (!horizontalWalls[y + 1][x]) travelFrom(y + 1, x);
	}
	private static void buildEdge(int room1, int room2, int y, int x, char dir) {
		if (room1 != room2 && edges[room1][room2] == null) {
			Edge edge = new Edge(y + 1, x, dir);
			int roomSize = roomSizes.get(room1) + roomSizes.get(room2);
			if (roomSize > largest) {
				largest = roomSize;
				maxEdge = edge;
			}
			edges[room1][room2] = edges[room2][room1] = edge;
		}
	}
	private static void printWalls() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				out.print(horizontalWalls[y][x] ? " ---" : "    ");
			}
			out.print("\n");
			for (int x = 0; x < width; x++) {
				out.printf(verticalWalls[y][x] ? "|%2d " : " %2d ", rooms[y][x]);
			}
			out.print("|\n");
		}
		for (int x = 0; x < width; x++) {
			out.print(" ---");
		}
		out.print("\n");
	}
	private static void actualMain() {
		handleInput();
		distributeRooms();
		out.println(roomsCount);
		out.println(largest);
		//printWalls();
		buildEdges();
		out.println(largest);
		out.printf("%d %d %c\n", maxEdge.y, maxEdge.x, maxEdge.dir);
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
