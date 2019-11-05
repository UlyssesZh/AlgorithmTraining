#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
int main() {
	vector<int> digits {1, 2, 3, 4, 5, 6, 7, 8, 9};
	do {
		int a = 100*digits[0] + 10*digits[1] + digits[2];
		int b = 100*digits[3] + 10*digits[4] + digits[5];
		int c = 100*digits[6] + 10*digits[7] + digits[8];
		if (3 * a == c && 2 * a == b) {
			for (int i = 0; i < 9; i++) {
				cout << digits[i];
				if (i % 3 == 2) cout << " ";
			}
			cout << endl;
		}
	} while (next_permutation(digits.begin(), digits.end()));
	return 0;
}
