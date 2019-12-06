/*
ID: ulysses4
LANG: C
PROG: template
*/
#include <stdio.h>
#include <string.h>
char PROG[] = "template";

void actualMain() {
	// write programs here
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
