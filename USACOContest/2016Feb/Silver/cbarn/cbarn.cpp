#include <cstdio>
#include <cstring>
char PROG[] = "cbarn";

#include <algorithm>
using namespace std;
int n;
long long x, c[1000];
long long squaresum(long long begin, long long end) {
	return (end * (end + 1) * (2 * end + 1) -
			(begin - 1) * begin * (2 * begin - 1)) / 6;
}
long long endat(int i) {
	return x + c[i] - 1;
}
void update(int i) {
	x = max(0ll, endat(i));
}
void init() {
	scanf("%d", &n);
	x = 0ll;
	for (int i = 0; i < n; i++) {
		scanf("%lld", &c[i]);
		update(i);
	}
}
void pickstart() {
	for (int i = 0;; i++) {
		if (!x) {
			rotate(c, c + i, c + n);
			break;
		}
		update(i);
	}
}
long long calc() {
	long long ret = 0;
	for (int i = 0; i < n; i++) {
		ret += squaresum(x, endat(i));
		update(i);
	}
	return ret;
}
void actualMain() {
	init();
	pickstart();
	printf("%lld\n", calc());
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
