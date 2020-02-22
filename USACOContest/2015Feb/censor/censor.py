PROG = 'censor'
fin = open(PROG + '.in', 'r')
fout = open(PROG + '.out', 'w')

def main():
	s = fin.readline()
	s = s[:len(s) - 1]
	t = fin.readline()
	t = t[:len(t) - 1]
	while True:
		i = s.find(t)
		if i == -1:
			break
		s = s[0:i] + s[i + len(t):]
	fout.write(s + '\n')

main()
fin.close()
fout.close()
