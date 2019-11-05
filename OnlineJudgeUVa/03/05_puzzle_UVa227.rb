class Puzzle
	SIZE, IllegalMoving, StopMoving = 5, *Array.new(2) { Class.new Exception }
	def initialize array
		@array, @s_pos = array, array.flatten.find_index(?\s).divmod(SIZE).reverse
	end
	def self.define_move sym, x, y
		define_method sym do
			new_pos = @s_pos[0] + x, @s_pos[1] + y
			raise IllegalMoving if new_pos.any? { |x| x < 0 || x >= SIZE }
			self[@s_pos], self[new_pos], @s_pos = self[new_pos], ?\s, new_pos
		end
	end
	[[:A, 0, -1], [:B, 0, 1], [:L, -1, 0], [:R, 1, 0]].each { |m| define_move *m }
	define_method(?0) { raise StopMoving }
	define_method(?\n) { }
	define_method(:[]) { |pos| @array[pos[1]][pos[0]] }
	define_method(:[]=) { |pos, value| @array[pos[1]][pos[0]] = value }
	define_method(:to_s) { @array.map { |a| a.join ?\s }.join ?\n }
	(1..).each do |i|
		arr = Array.new(SIZE) { (a = gets.chars.take SIZE).include?(?Z) ? exit : a }
		puzzle = Puzzle.new arr
		ret = loop do
			illegal = false
			gets.each_char do |cstr|
				puzzle.send cstr
			rescue IllegalMoving, NoMethodError
				illegal = true
				next
			end
		rescue StopMoving
			break illegal ? 'This puzzle has no final configuration.' : puzzle
		end
		puts "Puzzle ##{i}:", ret, ''
	end
end

