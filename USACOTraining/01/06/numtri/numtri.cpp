/*
ID: ulysses4
LANG: C++
PROG: numtri
*/
#include <cstdio>
#include <cstring>
char PROG[] = "numtri";

int R, s[1000], n[1000];
void actualMain() {
	scanf("%d", &R);
	for (int i = 0, i1 = i - 1, i2 = i - 2; i < R; i++, i1++, i2++) {
		for (int j = 0; j <= i; j++) scanf("%d", &n[j]);
		if (i > 0) s[i] = s[i1] + n[i];
		for (int j = i1, j1 = i2; j > 0; j--, j1--)
			s[j] = (s[j] > s[j1] ? s[j] : s[j1]) + n[j];
		s[0] += n[0];
	}
	int r = 0;
	for (int i = 0; i < R; i++) if (s[i] > r) r = s[i];
	printf("%d\n", r);
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
