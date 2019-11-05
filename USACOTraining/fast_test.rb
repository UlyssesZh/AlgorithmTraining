require 'open3'

class SearchCompleted < Exception
	attr_accessor :dir_path
	def initialize dir_path
		@dir_path = dir_path
	end
end

def search_file filename, dir_path = Dir.pwd
	Dir.each_child dir_path do |filename_in_dir|
		file_path = File.join dir_path, filename_in_dir
		if filename_in_dir == filename
			raise SearchCompleted.new dir_path
		elsif Dir.exist? file_path
			search_file filename, file_path
		end
	end
end

def copy_io in_io, out_io
	input = nil
	out_io.print input while input = in_io.gets
end

def exit_if_exec_has_err cmd
	out, err, stat = Open3.capture3 "\\time -v #{cmd}"
	time_cmd_head = "\tCommand being timed: \"#{cmd}\"\n"
	unless err[0, time_cmd_head.size] == time_cmd_head
		err.lines.each do |line|
			break if line == time_cmd_head
			print line
		end
		exit
	end
	['User time \(seconds\)', 'Maximum resident set size \(kbytes\)'].map do |string|
		/^\t#{string}: (?<result>[\d\.]+)$/.match(err)[:result]
	end
end

def puts_cutting
	puts ?- * 64
end

class_name = ARGV.first
filename = "#{class_name}.java"

begin
	search_file filename
	puts "Could not find #{class_name}."
rescue SearchCompleted => search_result
	Dir.chdir search_result.dir_path
	
	puts 'Compiling...'
	exit_if_exec_has_err "javac #{filename}"
	puts 'Compile: OK'
	
	puts_cutting
	
	puts 'Input (^D: EOF):'
	open("#{class_name}.in", 'w+') { |in_file| copy_io $stdin, in_file }
	
	puts_cutting
	
	puts 'Executing...'
	time, memory = exit_if_exec_has_err "java #{class_name}"
	puts 'Execute: OK'
	
	puts_cutting
	
	puts 'Output:'
	open("#{class_name}.out") { |out_file| copy_io out_file, $stdout }
	
	puts_cutting
	
	puts "#{time} secs, #{memory} KB"
end

