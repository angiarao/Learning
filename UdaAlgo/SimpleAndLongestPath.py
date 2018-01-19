from IPython.core.debugger import Tracer

#
# Modify long_and_simple_path 
# to build and return the path
# 

# Find me that path!
def long_and_simple_path(G,u,v,l):
    """
    G: Graph
    u: starting node
    v: ending node
    l: minimum length of path
    """
    if not long_and_simple_decision(G,u,v,l):
       return False
    #Otherwise, build and return the path

    simple_paths = [] # to get all the simple paths
    nodes_left = [] #stack for processing
    nodes_processed = []
    bad_path = []
    nodes_left.append(u)
    current_path=[]
    while len(nodes_left)>0:
        current_node = nodes_left.pop()
        print("current_node -- ",current_node)
        print("Nodes_Left -- ",nodes_left)
        print("current_path -- ",current_path)
        print("Bad_path -- ",bad_path)
        print("Simple_path -- ",simple_paths)
        print("----------------------------------")
        Tracer()()

        if current_node in current_path:
            continue

        if len(current_path)>0 and current_node not in G[current_path[-1]].keys():
            # this means the current node is not connected to the current path last node directly
            break;

        if (current_path + [current_node] in bad_path) or current_node in current_path:
            bad_path.append(list(current_path))
            current_path.pop()
            if current_path[-1] not in G[current_node].keys():
                continue

        if current_node is v:
            simple_path = current_path + [current_node]
            bad_path.append(list(simple_path))
            if len(simple_path)>l:
                simple_paths.append(list(simple_path))
            continue

        all_neigbours = G[current_node].keys()
        no_new_node = True
        for neigbour in all_neigbours:
            next_path = current_path + [current_node, neigbour]
            if neigbour not in current_path and next_path not in bad_path and next_path not in simple_paths:
                no_new_node = False
                nodes_left.append(neigbour)

        if no_new_node:
            bad_path.append(list(current_path))
            node_to_check = current_path.pop()
            while len(nodes_left)>0 and nodes_left[-1] not in G[node_to_check].keys() and len(nodes_left)>0:
                node_to_check = current_path.pop()
        else:
            current_path.append(current_node)

    return simple_paths


#############

def make_link(G, node1, node2):
    if node1 not in G:
        G[node1] = {}
    (G[node1])[node2] = 1
    if node2 not in G:
        G[node2] = {}
    (G[node2])[node1] = 1
    return G

def break_link(G, node1, node2):
    if node1 not in G:
        print("error: breaking link in a non-existent node")
        return
    if node2 not in G:
        print("error: breaking link in a non-existent node")
        return
    if node2 not in G[node1]:
        print("error: breaking non-existent link")
        return
    if node1 not in G[node2]:
        print("error: breaking non-existent link")
        return
    del G[node1][node2]
    del G[node2][node1]
    return G

def all_perms(seq):
    if len(seq) == 0: return [[]]
    if len(seq) == 1: return [seq, []]
    seq = list(seq)
    most = all_perms(seq[1:])
    first = seq[0]
    rest = []
    for perm in most:
        for i in range(len(perm)+1):
            rest.append(perm[0:i] + [first] + perm[i:])
    return most + rest

def check_path(G,path):
    for i in range(len(path)-1):
        if path[i+1] not in G[path[i]]: return False
    return True
    
def long_and_simple_decision(G,u,v,l):
    if l == 0:
        return False
    n = len(G)
    perms = all_perms(G.keys())
    for perm in perms:
        # check path
        if (len(perm) >= l and check_path(G,perm) and perm[0] == u 
            and perm[len(perm)-1] == v):    
            return True
    return False


flights = [(1,2),(1,3),(2,3),(2,6),(2,4),(2,5),(3,6),(4,5)]
G = {}
for (x,y) in flights: make_link(G,x,y)

if __name__ == "__main__":
    print(long_and_simple_path(G,1,5,2))

