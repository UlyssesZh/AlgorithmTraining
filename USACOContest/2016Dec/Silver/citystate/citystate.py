PROG = 'citystate'
fin = open(PROG + '.in', 'r')
fout = open(PROG + '.out', 'w')

import collections
def main():
	counter = collections.defaultdict(lambda: 0)
	for i in range(int(fin.readline())):
		cs = fin.readline().split()
		str1, str2 = cs[0][0:2], cs[1]
		if str1 != str2:
			counter[str1 + str2] += 1
	result = 0
	for key in counter:
		result += counter[key] * counter.get(key[2:4] + key[0:2], 0)
	fout.write(str(result // 2) + '\n')

main()
fin.close()
fout.close()
