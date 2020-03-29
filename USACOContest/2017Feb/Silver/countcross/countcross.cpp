#include <cstdio>
#include <cstring>
char PROG[] = "countcross";

#include <stack>
using namespace std;
int N, K, R, marks[100][100], r[100], c[100], mark = 0;
bool roads[100][100][100][100];
void actualMain() {
	scanf("%d%d%d", &N, &K, &R);
	for (int i = 0; i < R; i++) {
		int r1, c1, r2, c2;
		scanf("%d%d%d%d", &r1, &c1, &r2, &c2);
		r1--, c1--, r2--, c2--;
		roads[r1][c1][r2][c2] = roads[r2][c2][r1][c1] = true;
	}
	for (int i = 0; i < K; i++) {
		scanf("%d%d", &r[i], &c[i]);
		r[i]--, c[i]--;
	}
	for (int i = 0; i < N; i++) for (int j = 0; j < N; j++)
		if (!marks[i][j]) {
			mark++;
			stack<pair<int, int>> path;
			path.push({i, j});
			while (!path.empty()) {
				int x = path.top().first, y = path.top().second;
				marks[x][y] = mark;
				if (x < N-1 && !roads[x][y][x+1][y] && !marks[x+1][y])
					path.push({x+1, y});
				else if (y > 0 && !roads[x][y][x][y-1] && !marks[x][y-1])
					path.push({x, y-1});
				else if (x > 0 && !roads[x][y][x-1][y] && !marks[x-1][y])
					path.push({x-1, y});
				else if (y < N-1 && !roads[x][y][x][y+1] && !marks[x][y+1])
					path.push({x, y+1});
				else path.pop();
			}
		}
	int ret = 0;
	for (int i = 0; i < K; i++) for (int j = i + 1; j < K; j++)
		if (marks[r[i]][c[i]] != marks[r[j]][c[j]]) ret++;
	printf("%d\n", ret);
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
