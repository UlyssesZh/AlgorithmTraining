require 'scanf'
module Enumerable
	def each_with_object_index *obj
		return to_enum __method__ unless block_given?
		each_with_index.each_with_object(*obj) { |(x, i), *obj| yield x, *obj, i }
	end
end
class Puzzle
	BLACK = ?*
	@@n = 0
	def initialize row_n, col_n, array
		@row_n, @col_n, @array, @flatten = row_n, col_n, array, array.flatten
		@starts = @flatten.each_with_object_index [[], [], {}] do |c, (blacks, starts, should_starts), i|
			y, x = pos = i.divmod(col_n)
			if c == BLACK
				[blacks.push(pos), starts, should_starts.merge({i + 1 => (x != col_n - 1), i + col_n => true})]
			elsif should_starts[i] || x == 0 || y == 0
				[blacks, starts.push(pos), should_starts]
			else
				[blacks, starts, should_starts]
			end
		end.[] 1
	end
	def across_words
		(0...@row_n).to_a.product((0...@col_n).to_a).each_with_object [{}, 0] do |(y, x), (result, i, last_c, last_i)|
			if (c = @array[y][x]) != BLACK
				i += 1 if (start = last_c == BLACK || x == 0) || y == 0
				result[last_i = i] = '' if start
				result[last_i] += c
			end
			[result, i, (last_c = c), last_i]
		end.first
	end
	def down_words
		t = @row_n, @col_n, @array
		@row_n, @col_n, @array = @col_n, @row_n, @array.transpose
		result = across_words
		@row_n, @col_n, @array = t
		result
	end
	def print_words
		puts "puzzle ##{@@n += 1}:"
		puts 'Across'
		across_words.each { |entry| printf "%3d.%s\n", *entry }
		puts 'Down'
		down_words.each { |entry| printf "%3d.%s\n", *entry }
		puts ''
	end
	loop { Puzzle.new(*(row_n, col_n = scanf '%d%d'), Array.new(row_n) { gets.chars.to_a }).print_words }
end

