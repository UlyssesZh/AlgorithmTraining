/*
ID: ulysses4
LANG: C++
PROG: wormhole
*/
#include <cstdio>
#include <cstring>
char PROG[] = "wormhole";

int N, x[12], y[12], next[12], partner[12];
int count() {
	int i = 0, tot = 0;
	while (partner[i] >= 0 && i < N) i++;
	if (i >= N) {
		for (int k = 0; k < N; k++) {
			int pos = k;
			for (int j = 0; j < N; j++) {
				pos = next[partner[pos]];
				if (pos < 0) break;
		 	}
	 		if (pos >= 0) return 1;
		}
		return 0;
	}
	for (int j = i + 1; j < N; j++)
		if (partner[j] < 0) {
			partner[i] = j;
			partner[j] = i;
			tot += count();
			partner[i] = partner[j] = -1;
		}
	return tot;
}
void actualMain() {
	scanf("%d", &N);
	for (int i = 0; i < N; i++) {
		scanf("%d%d", &x[i], &y[i]);
		next[i] = partner[i] = -1;
	}
	for (int i = 0; i < N; i++)
	for (int j = 0; j < N; j++) {
		int dx = x[j] - x[i];
		if (dx > 0 && y[i] == y[j] &&
				(next[i] < 0 || dx < x[next[i]] - x[i]))
			next[i] = j;
	}
	printf("%d\n", count());
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
