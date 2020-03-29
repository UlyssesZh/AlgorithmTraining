#include <cstdio>
#include <cstring>
char PROG[] = "gates";

int n, mark[2002][2002];
bool vert[2002][2002], hori[2002][2002];
char s[1001];
void init() {
	scanf("%d", &n);
	scanf("%s", s);
	int x = 1001, y = 1001;
	for (char * c = s; *c; c++)
		switch (*c) {
			case 'N':
				vert[x][y++] = true;
				break;
			case 'S':
				vert[x][--y] = true;
				break;
			case 'W':
				hori[--x][y] = true;
				break;
			case 'E':
				hori[x++][y] = true;
				break;
		}
}
void dfs(int x, int y, int m) {
	if (mark[x][y]) return;
	printf("%d %d\n", x, y);
	mark[x][y] = m;
	if (x > 0 && !vert[x][y]) dfs(x - 1, y, m);
	if (x < 2001 && !vert[x + 1][y]) dfs(x + 1, y, m);
	if (y > 0 && !hori[x][y]) dfs(x, y - 1, m);
	if (y < 2001 && !hori[x][y + 1]) dfs(x, y + 1, m);
}
void actualMain() {
	init();
	int m = 0;
	for (int x = 0; x < 2002; x++) {
		for (int y = 0; y < 2002; y++) {
			if (!mark[x][y]) dfs(x, y, ++m);
		}
	}
	printf("%d\n", m - 1);
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
