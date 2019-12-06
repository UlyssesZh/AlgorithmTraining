require 'open3'

##
# author: Ulysses

HELP_DOC = <<HELP_DOC
Fast test 1.0 by Ulysses
Usage: ruby fast_test.rb [options] <name>

A Program for running other programs which read from .in files and write to \
.out files. It reads your input in stdin and writes it into a .in file, \
and reads the output in a .out file and puts it to stdout. It is useful when \
you want to quickly test a program because you do not need to open the .in \
file beforehand and open the .out file afterward.
Currently, it can only support Java, C++, C, Python 2, Python 3, Pascal. The \
directories of some related command-line tools should be included in PATH.

<name> is the name of the program. It is not the program's filename, so the \
filename extension should not be included.
Available options are:
-c, --compilers: Prints the version info of compilers. You may use '-l' to \
specify languages.
-e, --error-flexible: Makes it go ahead even if there is something printed to \
stderr.
-f, --folder <folder>: Makes it search for the program file in the specified \
directory instead of the current directory.
-h, --help: Prints this text.
-i, --in-bind: Makes it not take over writing .in file. This option is rather \
useful when you want to test a program repeatedly without changing the input.
-l, --language <language(s)>: 
-o, --out-bind: Makes it not take over printing .out file. This option is \
rather useful when the output of the program is probably very long.
-v, --version: Prints the version info.
HELP_DOC

OPT_NAMES = %i[
	compilers
	error-flexible
	folder
	help
	in-bind
	language
	out-bind
	version
]
LANGS = %i[
	java
	c++
	c
	python3
	python
	pascal
]
EXT = {
	java: 'java',
	'c++': 'cpp',
	c: 'c',
	python3: 'py',
	python: 'py',
	pascal: 'pas'
}
COMPILE = {
	java: 'javac %1$s.java',
	'c++': 'g++ -o %1$s %1$s.cpp',
	c: 'gcc -o %1$s %1$s.c',
	python3: '',
	python: '',
	pascal: 'fpc %1$s.pas'
}
EXECUTE = {
	java: 'java %s',
	'c++': './%s',
	c: './%s',
	python3: 'python3 %s.py',
	python: 'python %s.py',
	pascal: './%s'
}
VERSION = {
	java: ['javac -version', 'java -version'],
	'c++': ['g++ --version'],
	'c': ['gcc --version'],
	python3: ['python3 -V'],
	python: ['python -V'],
	pascal: ['fpc -iW -iD']
}

String.define_method :opt? do |o = nil|
	if o
		["--#{o}", "-#{o.to_s[0]}"].any? &method(:==)
	else
		OPT_NAMES.any? &method(:opt?)
	end
end
NAME = ARGV.last
ARGV.pop unless NAME.opt?
puts 'Name of the program should be provided.' unless NAME
OPTS = {
	compilers: 0,
	'error-flexible': 0,
	folder: ['.'],
	help: 0,
	'in-bind': 0,
	language: LANGS,
	'out-bind': 0,
	version: 0
}
Enumerator.new do |yielder|
	ary = []
	opt = nil
	while token = ARGV.shift
		push_ary = OPT_NAMES.each do |o|
			if token.opt? o
				if opt
					yielder.yield opt.to_sym, ary
					ary = []
				end
				opt = o
				break false
			end
		end
		ary.push token if push_ary
	end
	yielder.yield opt, ary if opt
end.to_a.to_h.then &OPTS.method(:merge!)
OPTS[:language] = OPTS[:language].map &:to_sym

##
# Prints all the items from +in_io+ to +out_io+e
def copy_io in_io, out_io
	input = nil
	out_io.print input while input = in_io.gets
end
##
# Runs +cmd+, return time and memory used. If something is printed to stderr,
# prints it to stdout and exits.
def test_exec cmd
	puts 'There is nothing to be run.' or return if cmd.empty?
	out, err, stat = Open3.capture3 "\\time -v #{cmd}"
	print out
	msg = /^Command exited with non-zero status .*$/
	err.each_line do |line|
		if msg =~ line
			OPTS[:'error-flexible'] == 0 ? exit : break
		else
			print line
		end
	end if stat.exitstatus != 0
	['User time \(seconds\)',
	 'Maximum resident set size \(kbytes\)'].map do |head|
		/^\t#{head}: (?<r>[\d\.]+)$/.match(err)[:r]
	end
end
##
# Prints a series of '-' to stdout.
def puts_cutting
	puts ?- * 64
end

just_printed = false
if OPTS[:compilers] != 0
	puts_cutting if just_printed
	if OPTS[:language].empty?
		puts 'Failed to print compilers info.'
		puts 'At least 1 language should be specified.'
		just_printed = true
	end
	OPTS[:language].each do |lang|
		puts_cutting if just_printed
		VERSION[lang].each do |cmd|
			puts ">> #{cmd}"
			system cmd
		end
		just_printed = true
	end
end
if OPTS[:help] != 0
	puts_cutting if just_printed
	puts <<HELP_DOC
Fast test 1.0 by Ulysses
Usage: ruby fast_test.rb [options] <name>

A Program for running other programs which read from .in files and write to \
.out files. It reads your input in stdin and writes it into a .in file, \
and reads the output in a .out file and puts it to stdout. It is useful when \
you want to quickly test a program because you do not need to open the .in \
file beforehand and open the .out file afterward.
Currently, it can only support Java, C++, C, Python 2, Python 3, Pascal. The \
directories of some related command-line tools should be included in PATH.

<name> is the name of the program. It is not the program's filename, so the \
filename extension should not be included.
Available options are:
-c, --compilers: Prints the version info of compilers. You may use '-l' to \
specify languages.
-e, --error-flexible: Makes it go ahead even if there is something printed to \
stderr.
-f, --folder <folder>: Makes it search for the program file in the specified \
directory instead of the current directory.
-h, --help: Prints this text.
-i, --in-bind: Makes it not take over writing .in file. This option is rather \
useful when you want to test a program repeatedly without changing the input.
-l, --language <language(s)>: 
-o, --out-bind: Makes it not take over printing .out file. This option is \
rather useful when the output of the program is probably very long.
-v, --version: Prints the version info.
HELP_DOC
	just_printed = true
end
if OPTS[:version] != 0
	puts_cutting if just_printed
	puts 'Fast test 1.0 by Ulysses'
	just_printed = true
end

exit if NAME.opt?
puts_cutting if just_printed
puts 'Finding...'

found, langs = [], []
OPTS[:folder].each do |folder|
	OPTS[:language].each do |lang|
		filename = File.join folder, '**', "#{NAME}.#{EXT[lang.to_sym]}"
		Dir.glob filename do |path|
			unless found.include? path
				found.push path
				langs.push lang
			end
		end
	end
end
case found.size
when 0
	puts "'#{NAME}' has not been found"
	exit
when 1
	found = found.first
	lang = langs.first
	puts "'#{NAME}' has been found: #{found}"
else
	puts "Multiple '#{NAME}' have been found:"
	found.each_with_index { |path, i| printf "%4d: %s\n", i, path }
	print 'Select one [default 0]: '
	found = found[i = gets.to_i]
	lang = langs[i]
end
Dir.chdir File.split(found).first

puts_cutting
puts 'Compiling...'
test_exec sprintf COMPILE[lang], NAME
puts 'Compile: OK'

if OPTS[:'in-bind'] == 0
	puts_cutting
	puts 'Input (^D: EOF):'
	open("#{NAME}.in", 'w+') { |f| copy_io $stdin, f }
end

puts_cutting
puts 'Executing...'
time, memory = test_exec sprintf EXECUTE[lang], NAME
puts 'Execute: OK'

if OPTS[:'out-bind'] == 0
	puts_cutting
	puts 'Output:'
	open("#{NAME}.out") { |f| copy_io f, $stdout }
end

puts_cutting
puts "#{time} secs, #{memory} KB"
