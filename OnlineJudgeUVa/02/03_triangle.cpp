#include <iostream>
using namespace std;
int main() {
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < i; j++) cout << " ";
		int j_max = 2 * (n - i) - 1;
		for (int j = 0; j < j_max; j++) cout << "#";
		cout << endl;
	}
	return 0;
}
