#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;
char PROG[] = "convention2";

int n, arrivals[100000], eatTime[100000];
int approaching[100000], startTime[100000];
int now, eating, approachingI, result;
bool waiting[100000];
void init() {
	scanf("%d", &n);
	for (int i = 0; i < n; i++) {
		scanf("%d%d", &arrivals[i], &eatTime[i]);
		approaching[i] = i;
		startTime[i] = -1;
	}
	sort(approaching, approaching + n,
			[](int i1, int i2) { return arrivals[i1] < arrivals[i2]; });
	result = 0;
	approachingI = 0;
	eating = -1;
}
int nextArriveTime() {
	return arrivals[approaching[approachingI]];
}
int nextFinishEatingTime() {
	return eating == -1 ? -1 : startTime[eating] + eatTime[eating];
}
int findQueueTop() {
	for (int i = 0; i < n; i++) if (waiting[i]) return i;
	return -1;
}
void eat() {
	int next = findQueueTop();
	if (next != -1) {
		waiting[next] = false;
		startTime[next] = now;
		eating = next;
		result = max(result, now - arrivals[next]);
	}
}
void emulate() {
	while (approachingI < n) {
		if (eating == -1) eat();
		int arrive = nextArriveTime();
		int finishing = nextFinishEatingTime();
		if (finishing == -1 || arrive < finishing) {
			now = arrive;
			waiting[approaching[approachingI++]] = true;
		} else {
			now = finishing;
			eating = -1;
		}
	}
}
void actualMain() {
	init();
	emulate();
	printf("%d\n", result);
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
