/*
ID: ulysses4
LANG: C++
PROG: skidesign
*/
#include <cstdio>
#include <cstring>
#include <algorithm>
char PROG[] = "skidesign";

using namespace std;
int N, h[1000], o[1000];
int cost1(int p) {
	int ret = 0, target = h[N - 1] - p;
	for (int i = N - 1; i >= 0 && h[i] > target; i--)
		ret += (h[i] - target) * (h[i] - target);
	return ret;
}
int cost2(int p) {
	int ret = 0, target = h[0] + p;
	for (int i = 0; i < N && h[i] < target; i++)
		ret += (target - h[i]) * (target - h[i]);
	return ret;
}
void actualMain() {
	scanf("%d", &N);
	for (int i = 0; i < N; i++) scanf("%d", &h[i]);
	int ret = 0x7fffffff;
	if (N < 2) ret = 0;
	else {
		sort(h, h + N);
		int m = max(0, h[N - 1] - h[0] - 17);
		for (int p = 0; p <= m; p++)
			ret = min(cost1(p) + cost2(m - p), ret);
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
