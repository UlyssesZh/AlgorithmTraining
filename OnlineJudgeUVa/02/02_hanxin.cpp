#include <iostream>
using namespace std;
int main() {
	for (int i = 1;; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		cout << "Case " << i << ": ";
		bool not_found = true;
		for (int n = 10; n <= 100; n++) {
			if ((n - a) % 3 == 0 && (n - b) % 5 == 0 && (n - c) % 7 == 0) {
				cout << n << endl;
				not_found = false;
				break;
			}
		}
		if (not_found)
			cout << "No answer" << endl;
	}
	return 0;
}
