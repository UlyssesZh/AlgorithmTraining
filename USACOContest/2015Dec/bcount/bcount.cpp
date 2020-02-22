/*
ID: ulysses4
LANG: C++
PROG: bcount
*/
#include <cstdio>
#include <cstring>
char PROG[] = "bcount";

int n, q, sum[100001][4];
void actualMain() {
	scanf("%d%d", &n, &q);
	for (int i = 1, breed; i <= n; i++) {
		scanf("%d", &breed);
		for (int b = 1; b <= 3; b++)
			sum[i][b] = sum[i - 1][b] + (b == breed);
	}
	for (int a, b; q; q--) {
		scanf("%d%d", &a, &b);
		printf("%d %d %d\n",
				sum[b][1] - sum[a - 1][1],
				sum[b][2] - sum[a - 1][2],
				sum[b][3] - sum[a - 1][3]);
	}
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
