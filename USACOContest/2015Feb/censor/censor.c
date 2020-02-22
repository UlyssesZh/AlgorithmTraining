#include <stdio.h>
#include <string.h>
char PROG[] = "censor";

const int p = 26;
const int mod = 1000000007;
char S[1000001], T[1000001], R[1000001];
int thash, rhash, si, ri, mp, tsize;
int hash(char * begin, char * end) {
	int ret = 0;
	for (char * ptr = begin; ptr != end; ptr++)
		ret = (ret * p + *ptr) % mod;
	return ret;
}
int strhash(char * str) {
	int ret = 0;
	for (char * ptr = str; *ptr; ptr++)
		ret = (ret * p + *ptr - 'a') % mod;
	return ret;
}
int ppow(int exp) {
	int ret = 1;
	for (int base = p; exp; base = (base * base) % mod, exp >>= 1)
		if (exp & 1) ret = (ret * base) % mod;
	return ret;
}
void actualMain() {
	scanf("%s%s", S, T);
	rhash = thash = strhash(T);
	for (si = 0; T[si]; si++) R[si] = S[si];
	tsize = ri = si;
	mp = ppow(p, si - 1);
	while (S[si]) {
		R[ri] = S[si++];
		rhash = (rhash + mod - R[ri - tsize] * mp) % mod * p % mod
		if (rhash == thash) {
			ri -= tsize
			rhash = hash(R + ri - tsize + 1, R + ri)
		}
		ri++;
	}
	printf("%s\n", S);
	printf("%d\n", thash);
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
