#include <cstdio>
#include <cstring>
char PROG[] = "leftout";

int n;
bool grid[1000][1000];
char s[1001];

int count(int y1, int x1, int y2, int x2, bool b) {
	int total = 0;
	for (int y = y1; y < y2; y++) for (int x = x1; x < x2; x++)
		if (!(grid[y][x] ^ b)) total++;
	return total;
}
void init() {
	scanf("%d", &n);
	for (int y = 0; y < n; y++) {
		scanf("%s", s);
		for (int x = 0; x < n; x++) grid[y][x] = s[x] == 'L';
	}
}
void firstFlip() {
	if (grid[0][0]) for (int x = 1; x < n; x++) grid[0][x] = !grid[0][x];
	for (int y = 1; y < n; y++) for (int x = 1; x < n; x++)
		grid[y][x] ^= grid[0][x] ^ grid[y][0];
}
void output() {
	if (count(1, 1, n, n, false) == 0)
		printf("1 1\n");
	else if (count(1, 1, n, n, true) == n - 1) {
		for (int x = 1; x < n; x++)
			if (count(1, x, n, x + 1, true) == n - 1) {
				printf("1 %d\n", x + 1);
				return;
			}
		for (int y = 1; y < n; y++)
			if (count(y, 1, y + 1, n, true) == n - 1) {
				printf("%d 1\n", y + 1);
				return;
			}
		printf("-1\n");
	} else if (count(1, 1, n, n, true) != 1)
		printf("-1\n");
	else
		for (int y = 1; y < n; y++) for (int x = 1; x < n; x++)
			if (grid[y][x]) {
				printf("%d %d\n", y + 1, x + 1);
				return;
			}
}
void actualMain() {
	init();
	firstFlip();
	output();
}

int main() {
	int size = sizeof(PROG);
	char in[size + 3], out[size + 4];
	freopen(strcat(strcpy(in, PROG), ".in"), "r", stdin);
	freopen(strcat(strcpy(out, PROG), ".out"), "w", stdout);
	actualMain();
	fclose(stdin);
	fclose(stdout);
	return 0;
}
