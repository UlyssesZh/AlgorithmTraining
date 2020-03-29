#include <cstdio>
#include <cstring>
char PROG[] = "triangles";

#define x first
#define y second
#define MOD 1000000007
#include <algorithm>
using namespace std;
typedef pair<int, int> point;
int n;
point p[100000];
void actualMain() {
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d%d", &p[i].x, &p[i].y);
	}
	sort(p, p + n);
	int ret = 0;
	for (int i = 0; i < n - 1; i++) {
		for (int j = i + 1; p[i].x == p[j].x && j < n; j++) {
			int dy = p[j].y - p[i].y;
			for (int k = 0; k < n; k++) {
				if (k != i && p[k].y == p[i].y) {
					ret = (ret + abs(p[k].x - p[i].x) * dy) % MOD;
				} else if (k != j && p[k].y == p[j].y) {
					ret = (ret + abs(p[k].x - p[j].x) * dy) % MOD;
				}
			}
		}
	}
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
