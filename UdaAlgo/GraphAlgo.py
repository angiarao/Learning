# Find Eulerian Tour
#
# Write a function that takes in a graph
# represented as a list of tuples
# and return a list of nodes that
# you would follow on an Eulerian Tour
#
# For example, if the input graph was
# [(1, 2), (2, 3), (3, 1)]
# A possible Eulerian tour would be [1, 2, 3, 1]

from random import randint
use_harder_tests=True

def pop_random(listVal,exclude=None):
    n = len(listVal)
    random_int = randint(0,n-1)
    i = random_int
    random_val = listVal[i]
    if exclude:
        while random_val in exclude:
            i = (i+1)%n
            random_val = listVal[i]
            if i==random_int:
                return None
    return random_val

def build_tour_iterative(tour,graph_rest,all_edges,start_node):
    total_edges = len(all_edges)
    current_node = start_node
    tour.append(current_node)
    promising_edge = pop_random(graph_rest[current_node]["edges"])
    visited_edges = []
    edges_to_be_popped = []
    
    while total_edges!=len(visited_edges) and len(tour)!=0:
        
        #checks the promising edge is none, if none, then
        #move back one node and check for alternate edge
        if promising_edge is None:
            tour.pop()
            if len(tour)==0: break;
            current_node = tour[len(tour)-1]
            promising_edge = pop_random(graph_rest[current_node]["edges"],exclude=visited_edges+edges_to_be_popped)
            edges_to_be_popped.append(visited_edges.pop())
            continue
        
        if len(edges_to_be_popped)>0:
            edges_to_be_popped=[]
            
        #incase promising edge is some thing
        #check for the probable edges and add the promising edge to visited edge
        #and chck for probable edge
        
        promising_node = promising_edge[0] if promising_edge[0]!=current_node else promising_edge[1]
        probable_edge = pop_random(graph_rest[promising_node]["edges"],exclude=visited_edges + [promising_edge])
        visited_edges.append(promising_edge)
        tour.append(promising_node)
        
        if probable_edge is None:
            #if probable edge is none, then there are two cases
            # either we have visited all the edge...
            if total_edges==len(visited_edges):
                print(visited_edges)
                return tour
            #or there's no paths in this promising node, so setting it to none
            # so we 
            print("Probable edge is none for promising node :" + str(promising_node))
            print("At this time the visited edges are : " + str(visited_edges))
            print("At this time the tour was like this : " + str(tour))
            promising_edge = None
            for edge in graph_rest[promising_node]["edges"]:
                if probable_edge!=edge and edge not in visited_edges:
                    promising_edge = edge
                    break            
            continue
        else:
            current_node = promising_node
            promising_edge = probable_edge
            
            
    print(visited_edges)
    return tour if len(tour)>0 else None

def build_tour(tour,vertices_map,graph,current_node,visited_edges,excluded=[]):
    if len(visited_edges)==len(graph):
        return tour
    new_edge = pop_random(vertices_map[current_node]["edges"],exclude=visited_edges + excluded)
    if new_edge is None:
        if len(tour)==0:
            print("Either wrong start node or no path available")
            return []
        previous_edge = visited_edges.pop()
        current_node = tour.pop()
        return build_tour(tour,vertices_map,graph,current_node,visited_edges,excluded=excluded+[previous_edge])
    else:
        excluded=[]
        
    tour.append(current_node)
    visited_edges.append(new_edge)
    new_node = new_edge[0] if new_edge[0]!=current_node else new_edge[1]
    if len(visited_edges)==len(graph):
        tour.append(new_node)
        return tour
    return build_tour(tour,vertices_map,graph,new_node,visited_edges)

def find_eulerian_tour(graph):
    graph_restructured,odd_vertices_num = build_graph(graph)
    tour = []
    if odd_vertices_num<0:
        return tour
    vertices = list(graph_restructured.keys())

    """
        Need to get the correct node as if all nodes has even degree, can start with any node but
        if some node has odd degree, then need to pick up that node
    """
    if odd_vertices_num>0:
        return None
        #odd_vertices = [k for k,v in graph_restructured.items() if v['degree']%2!=0]
        #start_vertice = odd_vertices[0]
    else:
        start_vertice = pop_random(vertices)
    
    #print("Graph : " + str(graph_restructured))
    
    print("all nodes : " + str(vertices))
    
    print("starting with node : " + str(start_vertice))


    tour = build_tour_iterative([],graph_restructured,graph,start_vertice)
    return tour


def build_graph(graph):
    vertices = {}
    odd_degree_vertices = 0
    for edge in graph:
        edgeVal = edge
        for vertice in edge:
            val = 1 if vertice not in vertices else (vertices[vertice]['degree']+1)
            if val!=1:
                edges = vertices[vertice]['edges']
                edges.append(edgeVal)
            else:
                edges = [edgeVal]

            if val%2==0:
                odd_degree_vertices-=1
            else:
                odd_degree_vertices+=1
            vertices.update({vertice:{'edges':edges,'degree':val}})
            
    if odd_degree_vertices%2!=0:
        return ([],-1)
        
    return (vertices,odd_degree_vertices)


#
# Write centrality_max to return the maximum distance
# from a node to all the other nodes it can reach
#

def centrality_max(G, v):
    distance_from_start = {}
    open_list = set([v])
    distance_from_start[v]=0
    while len(open_list)!=0:
        current_node = open_list.pop()
        for neighbour in G[current_node].keys():
            if neighbour not in distance_from_start:
                distance_from_start[neighbour] = distance_from_start[current_node]+1
                if neighbour not in open_list:
                    open_list.add(neighbour)
    
    print distance_from_start
    return max(distance_from_start.values())


