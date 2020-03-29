#include <cstdio>
#include <cstring>
char PROG[] = "swap";

#include <algorithm>
using namespace std;
int n, m, k, trans[100001], cows[100001], tmp[100001], l, r;
void dotrans(int *in, int *out) {
	if (in != out) {
		for (int i = 1; i <= n; i++)
			out[i] = in[trans[i]];
	} else {
		dotrans(in, tmp);
		for (int i = 1; i <= n; i++)
			out[i] = tmp[i];
	}
}
void actualMain() {
	scanf("%d%d%d", &n, &m, &k);
	for (int i = 1; i <= n; i++) {
		cows[i] = i;
		trans[i] = i;
	}
	for (int i = 0; i < m; i++) {
		scanf("%d%d", &l, &r);
		reverse(trans + l, trans + r + 1);
	}
	while (k) {
		if (k % 2) {
			k--;
			dotrans(cows, cows);
		}
		k /= 2;
		dotrans(trans, trans);
	}
	for (int i = 1; i <= n; i++) {
		printf("%d\n", cows[i]);
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
