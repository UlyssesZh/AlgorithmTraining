/*
ID: ulysses4
LANG: C++
PROG: wormsort
*/
#include <cstdio>
#include <cstring>
char PROG[] = "wormsort";

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
