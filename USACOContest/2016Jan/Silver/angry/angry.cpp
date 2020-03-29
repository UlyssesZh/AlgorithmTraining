#include <cstdio>
#include <cstring>
char PROG[] = "angry";

#include <algorithm>
using namespace std;
int n, k, x[50000];
void init() {
	scanf("%d%d", &n, &k);
	for (int i = 0; i < n; i++) scanf("%d", &x[i]);
	sort(x, x + n);
}
bool ok(int r) {
	r <<= 1;
	for (int used = 0, i = 0; used < k; used++) {
		int side = x[i] + r;
		while (i < n && x[i] <= side) i++;
		if (i == n) return true;
	}
	return false;
}
int binsearch(int inf, int sup) {
	if (sup - inf < 2) return sup;
	int mid = (inf + sup) / 2;
	return ok(mid) ? binsearch(inf, mid) : binsearch(mid, sup);
}
void actualMain() {
	init();
	printf("%d\n", binsearch(1, 1000000000));
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
