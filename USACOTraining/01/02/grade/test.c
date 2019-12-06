/*
ID: ulysses4
LANG: C
PROG: test
*/
#include <stdio.h>
#include <string.h>
char PROG[] = "test";

void actualMain() {
	int a, b;
	scanf("%d%d", &a, &b);
	printf("%d\n", a + b);
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
