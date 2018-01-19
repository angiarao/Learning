# Bridge Edges v4
#
# Find the bridge edges in a graph given the
# algorithm in lecture.
# Complete the intermediate steps
#  - create_rooted_spanning_tree
#  - post_order
#  - number_of_descendants
#  - lowest_post_order
#  - highest_post_order
#
# And then combine them together in
# `bridge_edges`

# So far, we've represented graphs 
# as a dictionary where G[n1][n2] == 1
# meant there was an edge between n1 and n2
# 
# In order to represent a spanning tree
# we need to create two classes of edges
# we'll refer to them as "green" and "red"
# for the green and red edges as specified in lecture
#
# So, for example, the graph given in lecture
# G = {'a': {'c': 1, 'b': 1}, 
#      'b': {'a': 1, 'd': 1}, 
#      'c': {'a': 1, 'd': 1}, 
#      'd': {'c': 1, 'b': 1, 'e': 1}, 
#      'e': {'d': 1, 'g': 1, 'f': 1}, 
#      'f': {'e': 1, 'g': 1},
#      'g': {'e': 1, 'f': 1} 
#      }
# would be written as a spanning tree
# S = {'a': {'c': 'green', 'b': 'green'}, 
#      'b': {'a': 'green', 'd': 'red'}, 
#      'c': {'a': 'green', 'd': 'green'}, 
#      'd': {'c': 'green', 'b': 'red', 'e': 'green'}, 
#      'e': {'d': 'green', 'g': 'green', 'f': 'green'}, 
#      'f': {'e': 'green', 'g': 'red'},
#      'g': {'e': 'green', 'f': 'red'} 
#      }
#       
def create_rooted_spanning_tree(G, root):
    S = {}
    nodes_left = []
    visited_nodes = []
    node_total_len = len(G.keys())
    nodes_left.append(root)
    while len(nodes_left)!=0 or len(visited_nodes)!=node_total_len:
        current_node = nodes_left[0]
        visited_nodes.append(current_node)
        nodes_left.remove(current_node)
        if current_node not in S:
            S[current_node] = {}
        for sub_node in G[current_node].keys():
            if sub_node not in S:
                S[current_node][sub_node]='green'
                S[sub_node] = {}
                S[sub_node][current_node] = 'green'
            else:
                if sub_node not in S[current_node]:
                    S[current_node][sub_node]='red'
                    S[sub_node][current_node]='red'
            if sub_node not in visited_nodes and sub_node not in nodes_left: nodes_left.append(sub_node)
    return S

# This is just one possible solution
# There are other ways to create a 
# spanning tree, and the grader will
# accept any valid result
# feel free to edit the test to
# match the solution your program produces
def test_create_rooted_spanning_tree():
    G = {'a': {'c': 1, 'b': 1}, 
         'b': {'a': 1, 'd': 1}, 
         'c': {'a': 1, 'd': 1}, 
         'd': {'c': 1, 'b': 1, 'e': 1}, 
         'e': {'d': 1, 'g': 1, 'f': 1}, 
         'f': {'e': 1, 'g': 1},
         'g': {'e': 1, 'f': 1} 
         }
    S = create_rooted_spanning_tree(G, "a")
    print(str(S))
    assert S == {'a': {'c': 'green', 'b': 'green'}, 
                 'b': {'a': 'green', 'd': 'red'}, 
                 'c': {'a': 'green', 'd': 'green'}, 
                 'd': {'c': 'green', 'b': 'red', 'e': 'green'}, 
                 'e': {'d': 'green', 'g': 'green', 'f': 'green'}, 
                 'f': {'e': 'green', 'g': 'red'},
                 'g': {'e': 'green', 'f': 'red'} 
                 }

###########


def post_order(S, root):
    # return mapping between nodes of S and the post-order value
    # of that node 
    nodes_left = []
    nodes_left.append(root)
    visited_nodes = []
    node = root
    pordernum=0
    po = {}
    while len(nodes_left)>0:
        node = nodes_left.pop()
        visited_nodes.append(node)
        children = [a for a in S[node].keys() if S[node][a]=='green' and a not in visited_nodes]
        if len(children)>0:
            nodes_left.append(node)
            nodes_left.extend(children)
        else:
            pordernum=pordernum+1
            po[node] = pordernum 
    return po

# This is just one possible solution
# There are other ways to create a 
# spanning tree, and the grader will
# accept any valid result.
# feel free to edit the test to
# match the solution your program produces
def test_post_order():
    S = {'a': {'c': 'green', 'b': 'green'}, 
         'b': {'a': 'green', 'd': 'red'}, 
         'c': {'a': 'green', 'd': 'green'}, 
         'd': {'c': 'green', 'b': 'red', 'e': 'green'}, 
         'e': {'d': 'green', 'g': 'green', 'f': 'green'}, 
         'f': {'e': 'green', 'g': 'red'},
         'g': {'e': 'green', 'f': 'red'} 
         }
    po = post_order(S, 'a')
    print(str(po))
    assert po == {'a':7, 'b':1, 'c':6, 'd':5, 'e':4, 'f':2, 'g':3}
    

##############


def _numofdescendants(S,root,visited_nodes,nd):
    visited_nodes.append(root)
    num_of_des = 1
    childrens = [children for children in S[root].keys() if S[root][children]=='green' and children not in visited_nodes]
    if len(childrens)!=0:
        for child in childrens:
            if child not in nd:
                nd = _numofdescendants(S,child,visited_nodes,nd)
            num_of_des += nd[child]
        nd[root] = num_of_des
    else:
        nd[root]=1
    return nd

def number_of_descendants(S, root):
    # return mapping between nodes of S and the number of descendants
    # of that node
    nd = _numofdescendants(S,root,[],{})
    print(str(nd))
    return nd

def test_number_of_descendants():
    S =  {'a': {'c': 'green', 'b': 'green'}, 
          'b': {'a': 'green', 'd': 'red'}, 
          'c': {'a': 'green', 'd': 'green'}, 
          'd': {'c': 'green', 'b': 'red', 'e': 'green'}, 
          'e': {'d': 'green', 'g': 'green', 'f': 'green'}, 
          'f': {'e': 'green', 'g': 'red'},
          'g': {'e': 'green', 'f': 'red'} 
          }
    nd = number_of_descendants(S, 'a')
    assert nd == {'a':7, 'b':1, 'c':5, 'd':4, 'e':3, 'f':1, 'g':1}

###############

def lowest_post_order(S, root, po):
    # return a mapping of the nodes in S
    # to the lowest post order value
    # below that node
    # (and you're allowed to follow 1 red edge)
    lpo = {}
    visited_nodes=[]
    nodes_left=[root]
    while len(nodes_left)>0:
        node = nodes_left.pop()
        visited_nodes.append(node)
        
        #setting lpo for post verification
        lpo[node]=po[node] if node not in lpo else lpo[node]
        
        #getting childrens
        green_children = [child for child in S[node].keys() if S[node][child]=='green' and child not in visited_nodes]
        
        #for adding red child before .. for comparison
        red_children = [child for child in S[node].keys() if S[node][child]=='red']
        for child in red_children:
            lpo[child]=po[child] if child not in lpo else lpo[child]
        
        if len(green_children)>0:
            nodes_left.append(node)
            green_children.reverse()
            nodes_left.extend(green_children)
        else:
            gchild = [child for child in S[node].keys() if S[node][child]=='green' and child not in nodes_left]
            lpo[node] = lpo[min(gchild + red_children+[node],key=lambda p:lpo[p])]
        
    return lpo

def test_lowest_post_order():
    S = {'a': {'c': 'green', 'b': 'green'}, 
         'b': {'a': 'green', 'd': 'red'}, 
         'c': {'a': 'green', 'd': 'green'}, 
         'd': {'c': 'green', 'b': 'red', 'e': 'green'}, 
         'e': {'d': 'green', 'g': 'green', 'f': 'green'}, 
         'f': {'e': 'green', 'g': 'red'},
         'g': {'e': 'green', 'f': 'red'} 
         }
    po = post_order(S, 'a')
    l = lowest_post_order(S, 'a', po)
    assert l == {'a':1, 'b':1, 'c':1, 'd':1, 'e':2, 'f':2, 'g':2}

################

def highest_post_order(S, root, po):
    # return a mapping of the nodes in S
    # to the highest post order value
    # below that node
    # (and you're allowed to follow 1 red edge)
    mpo = {}
    visited_nodes=[]
    nodes_left=[root]
    while len(nodes_left)>0:
        node = nodes_left.pop()
        visited_nodes.append(node)
        
        #setting mpo for post verification
        mpo[node]=po[node] if node not in mpo else mpo[node]
        
        #getting childrens
        green_children = [child for child in S[node].keys() if S[node][child]=='green' and child not in visited_nodes]
        
        #for adding red child before .. for comparison
        red_children = [child for child in S[node].keys() if S[node][child]=='red']
        for child in red_children:
            mpo[child]=po[child] if child not in mpo else mpo[child]
        
        if len(green_children)>0:
            nodes_left.append(node)
            green_children.reverse()
            nodes_left.extend(green_children)
        else:
            gchild = [child for child in S[node].keys() if S[node][child]=='green' and child not in nodes_left]
            mpo[node] = mpo[max(gchild + red_children+[node],key=lambda p:mpo[p])]
    print(str(mpo))
    return mpo

def test_highest_post_order():
    S = {'a': {'c': 'green', 'b': 'green'}, 
         'b': {'a': 'green', 'd': 'red'}, 
         'c': {'a': 'green', 'd': 'green'}, 
         'd': {'c': 'green', 'b': 'red', 'e': 'green'}, 
         'e': {'d': 'green', 'g': 'green', 'f': 'green'}, 
         'f': {'e': 'green', 'g': 'red'},
         'g': {'e': 'green', 'f': 'red'} 
         }
    po = post_order(S, 'a')
    h = highest_post_order(S, 'a', po)
    assert h == {'a':7, 'b':5, 'c':6, 'd':5, 'e':4, 'f':3, 'g':3}
    
#################

def bridge_edges(G, root):
    # use the four functions above
    # and then 
    # determine which edges in G are bridge edges
    # return them as a list of tuples ie: [(n1, n2), (n4, n5)]
    S = create_rooted_spanning_tree(G,root)
    po = post_order(S,root)
    nd = number_of_descendants(S,root)
    lpo = lowest_post_order(S,root,po)
    mpo = highest_post_order(S,root,po)
    
    bridges = []
    nodes_left=[root]
    visited_nodes=[]
    rule1 = lambda node: mpo[node]<=po[node]
    rule2 = lambda node: lpo[node]>(po[node]-nd[node])
    while len(nodes_left)>0:
        node = nodes_left.pop()
        visited_nodes.append(node)
        children = [child for child in S[node].keys() if S[node][child]=='green' and child not in visited_nodes]
        if len(children)>0:
            children.reverse()
            for child in children:
                if rule1(child) and rule2(child):
                    bridges.append((node,child))
                nodes_left.append(child)
    
    print(str(bridges))
    return bridges

def test_bridge_edges():
    G = {'a': {'c': 1, 'b': 1}, 
         'b': {'a': 1, 'd': 1}, 
         'c': {'a': 1, 'd': 1}, 
         'd': {'c': 1, 'b': 1, 'e': 1}, 
         'e': {'d': 1, 'g': 1, 'f': 1}, 
         'f': {'e': 1, 'g': 1},
         'g': {'e': 1, 'f': 1} 
         }
    bridges = bridge_edges(G, 'a')
    assert bridges == [('d', 'e')]