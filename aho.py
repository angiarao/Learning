
class Trie_Unit():

	def __init__(data, state, failure_func, output_func):
		super(Trie_Unit, self).__init__()
		self.data = data
		self.state = state
		self.failure_func = failure_func
		self.output_func = output_func
		self.child_nodes = []

	def add_adjacent_child(trie_unit):
		self.child_nodes.append(trie_unit)




class Trie():

	def __init__():
		super(Trie, self).__init__()
		self.trie = {}
		self.root = Trie_Unit(None, 0, None, None))
		self.last_added_state = 0

	def add_word(word):
		lastwrd = word[-1]
		parent = self.root
		for letter in word[:-1]:
			parent, output_func = add_key(parent, letter)
		output_func = lambda x: 
		add_key(parent, letter, )

	def add_key(parent, key, output_func=None):
		matching_node = None
		for node in parent.child_nodes:
			if node.data == key:
				matching_node = node
				return matching_node	
		if matching_node==None:
			self.last_added_state += 1
			Trie_Unit(key, self.last_added_state, None, output_func)

	def add_node(parent, data):

	def add_unit(trie_unit):
		pass


def search_pattern(patterns, text_to_be_searched):
	Trie trieObj = Trie()
	for pattern in patterns:
		for letter in pattern:
			trieObj.add_keyword(letter)







