#include <iostream>
#include <cstring>
using namespace std;
int main() {
	int times;
	cin >> times;
	for (int i = 0; i < times; i++) {
		char s[81];
		cin >> s;
		int p, len = strlen(s);
		for (p = 1; p <= len; p++) {
			if (len % p) continue;
			if (p == len) break;
			bool should_break = true;
			for (int j = p; j < len; j++) {
				if (s[j] != s[j % p]) {
					should_break = false;
					break;
				}
			}
			if (should_break) break;
		}
		cout << p << endl;
	}
	return 0;
}
