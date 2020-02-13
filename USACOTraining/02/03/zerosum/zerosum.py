"""
ID: ulysses4
PROG: zerosum
LANG: PYTHON3
"""
PROG = 'zerosum'
fin = open(PROG + '.in', 'r')
fout = open(PROG + '.out', 'w')

opr = ['', '+', '-']
def next_answer(answer, i):
	if i < 0:
		raise
	answer[i] += 1
	if answer[i] == 3:
		answer[i] = 0
		next_answer(answer, i - 1)
def output(answer, n):
	str = ''
	for i in range(1, n):
		str += repr(i)
		if answer[i - 1] == 0:
			str += ' '
		else:
			str += opr[answer[i - 1]]
	str += repr(n) + '\n'
	fout.write(str)
def check(answer, n):
	str = ''
	for i in range(1, n):
		str += repr(i)
		str += opr[answer[i - 1]]
	str += repr(n)
	if eval(str) == 0:
		output(answer, n)
def main():
	n = int(fin.readline())
	answer = [0] * (n - 1)
	try:
		while (True):
			next_answer(answer, n - 2)
			check(answer, n)
	except:
		pass

main()
fin.close()
fout.close()
