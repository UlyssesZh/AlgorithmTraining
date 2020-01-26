/*
ID: ulysses4
LANG: C++
PROG: lamps
*/
#include <cstdio>
#include <cstring>
char PROG[] = "lamps";

const int buttons[] = {
	0b111111,
	0b101010,
	0b010101,
	0b100100
};
int nlamp, nswitch, on, known;
bool possible[0b1000000];
void read(bool readon) {
	while (true) {
		int next;
		scanf("%d", &next);
		if (next == -1) break;
		next = 5 - (next - 1) % 6;
		if (readon) on |= 1 << next;
		known |= 1 << next;
	}
}
void search(int lights, int i, int n) {
	if (n-- == 0 && (lights & known) == on) {
		possible[lights] = true;
		return;
	}
	for (; i < 4; i++) search(lights ^ buttons[i], i + 1, n);
}
void output(int lights) {
	for (int i = 0; i < nlamp; i++)
		putchar(lights & (1 << (5 - i % 6)) ? '1' : '0');
	putchar('\n');
}
void actualMain() {
	scanf("%d%d", &nlamp, &nswitch);
	read(true);
	read(false);
	if (nswitch > 4)
		nswitch = nswitch % 2 ? 3 : 4;
	for (; nswitch >= 0; nswitch -= 2)
		search(0b111111, 0, nswitch);
	bool impossible = true;
	for (int i = 0; i < 0b1000000; i++)
		if (possible[i]) {
			output(i);
			impossible = false;
		}
	if (impossible) printf("IMPOSSIBLE\n");
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
