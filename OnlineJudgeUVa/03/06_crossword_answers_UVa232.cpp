#include <cstdio>
#include <vector>
#include <algorithm>
#include <bits/stdc++.h>
using namespace std;
int main() {
	int row, col;
	scanf("%d%d", &row, &col);
	char s[row][col + 1];
	vector<int[2]> blanks, starts;
	for (int i = 0; i < row; i++) {
		for (int j = 0; j <= col; j++) {
			switch (char c = (char) getchar()) {
				case '\n':
					s[i][j] = '\0';
					break;
				case '*': {
					int pos[2] = {i, j};
					blanks.push_back(pos);
				} default:
					s[i][j] = c;
			}
		}
	}
	for (int i = 0; i < row; i++) {
		int pos[2] = {i, 0};
		if (find(blanks.begin(), blanks.end(), pos) == blanks.end())
			starts.push_back(pos);
	}
	for (int i = 0; i < blanks.size(); i++) {
		int blank[2] = {blanks[i][0], blanks[i][1]};
		if (blank[0] < row - 1) {
			int pos[2] = {blank[0] + 1, blank[1]};
			starts.push_back(pos);
		}
		if (blank[1] < col - 1) {
			int pos[2] = {blank[0], blank[1] + 1};
			starts.push_back(pos);
		}
	}
	return 0;
}
