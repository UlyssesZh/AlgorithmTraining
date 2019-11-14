require 'open3'

##
# Prints all the items from +in_io+ to +out_io+
def copy_io in_io, out_io
	input = nil
	out_io.print input while input = in_io.gets
end
##
# Runs +cmd+, return time and memory used. If something is printed to stderr,
# print it to stdout and exit.
def test_exec cmd
	out, err, stat = Open3.capture3 "\\time -v #{cmd}"
	regexp = /^\tCommand being timed: \"#{cmd}\"\n.*/
	errs = err.lines.take_while { |line| !(regexp =~ line) }
	print *errs or exit unless errs.empty?
	['User time \(seconds\)',
	 'Maximum resident set size \(kbytes\)'].map do |head|
		/^\t#{head}: (?<r>[\d\.]+)$/.match(err)[:r]
	end
end
##
# Print a series of '-' to stdout.
def puts_cutting
	puts ?- * 64
end

puts 'Finding...'
class_name = ARGV.first
filename = "#{class_name}.java"
if found = Dir.glob(File.join '**', filename).first
	Dir.chdir File.split(found).first
	puts "#{class_name} is found: ./#{found}"
else
	puts "#{class_name} is not found"
	exit
end

puts_cutting

puts 'Compiling...'
test_exec "javac #{filename}"
puts 'Compile: OK'

puts_cutting

puts 'Input (^D: EOF):'
open("#{class_name}.in", 'w+') { |in_file| copy_io $stdin, in_file }

puts_cutting

puts 'Executing...'
time, memory = test_exec "java #{class_name}"
puts 'Execute: OK'

puts_cutting

puts 'Output:'
open("#{class_name}.out") { |out_file| copy_io out_file, $stdout }

puts_cutting

puts "#{time} secs, #{memory} KB"
