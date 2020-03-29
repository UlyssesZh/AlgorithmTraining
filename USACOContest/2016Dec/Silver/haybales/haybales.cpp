#include <cstdio>
#include <cstring>
char PROG[] = "haybales";

#include <algorithm>
using namespace std;
int haybales[100000], n, q;
void init() {
	scanf("%d%d", &n, &q);
	for (int i = 0; i < n; i++)
		scanf("%d", &haybales[i]);
	sort(haybales, haybales + n);
}
int count(int bound) {
	if (haybales[0] > bound) return 0;
	int inf = 0, sup = n - 1, mid;
	while (inf != sup) {
		mid = (inf + sup + 1) / 2;
		if (haybales[mid] <= bound)
			inf = mid;
		else
			sup = mid - 1;
	}
	return inf + 1;
}
void parsequery(int a, int b) {
	printf("%d\n", count(b) - count(a - 1));
}
void actualMain() {
	init();
	for (int a, b; q; q--) {
		scanf("%d%d", &a, &b);
		parsequery(a, b);
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
