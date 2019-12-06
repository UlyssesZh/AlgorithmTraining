/*
ID: ulysses4
LANG: C++
PROG: milk3
*/
#include <cstdio>
#include <cstring>
char PROG[] = "milk3";

int A, B, C, at, bt, ct;
bool states[25][25][25], ret[25];
void solve(int a, int b, int c);
void move(int from, int to, int capacity, int* fromt, int* tot,
		int* a, int* b, int* c) {
	while (from > 0 && to < capacity) {
		from--;
		to++;
	}
	*fromt = from;
	*tot = to;
	solve(*a, *b, *c);
}
void solve(int a, int b, int c) {
	if (states[a][b][c]) return;
	states[a][b][c] = true;
	if (!a) ret[c] = true;
	move(c, a, A, &ct, &at, &at, &b, &ct);
	move(c, b, B, &ct, &bt, &a, &bt, &ct);
	move(a, b, B, &at, &bt, &at, &bt, &c);
	move(a, c, C, &at, &ct, &at, &b, &ct);
	move(b, a, A, &bt, &at, &at, &bt, &c);
	move(b, c, C, &bt, &ct, &a, &bt, &ct);
}
void actualMain() {
	scanf("%d%d%d", &A, &B, &C);
	solve(0, 0, C);
	for (int i = 0, c = 1; i < 25; i++)
		if (ret[i]) {
			if (c > 1) printf(" ");
			printf("%d", i);
			c++;
		}
	printf("\n");
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
