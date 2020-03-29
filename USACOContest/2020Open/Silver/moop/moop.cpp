#include <cstdio>
#include <cstring>
char PROG[] = "moop";

#include <algorithm>
using namespace std;
int n, moos[100005][2], p[100005], i, j, findRet, t, su, sv;
inline int find(int u) {
	findRet = u;
	while (p[findRet] >= 0) findRet = p[findRet];
	while (findRet != u) t = p[u], p[u] = findRet, u = t;
	return findRet;
}
inline void uni() {
	su = find(i), sv = find(j);
	if (su == sv) return;
	if (p[su] < p[sv]) swap(su, sv);
	p[sv] += p[su], p[su] = sv;
}
inline int calc() {
	findRet = 0;
	for (i = 0; i < n; i++) if (p[i] < 0) findRet++;
	return findRet;
}
inline bool canInteract() {
	return moos[i][0] <= moos[j][0] && moos[i][1] <= moos[j][1] ||
			moos[j][0] <= moos[i][0] && moos[j][1] <= moos[i][1];
}
int actualMain() {
	scanf("%d", &n);
	for (i = 0; i < n; i++) p[i] = -1;
	for (i = 0; i < n; i++) {
		scanf("%d%d", &moos[i][0], &moos[i][1]);
		for (j = 0; j < i; j++)
			if (canInteract()) uni();
	}
	printf("%d\n", calc());
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
