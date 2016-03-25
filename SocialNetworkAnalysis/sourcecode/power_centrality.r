result = power_centrality(mygraph, rescale = TRUE) # Failure

new_graph = delete.vertices(mygraph, which(degree(newgraph) < 300))
result = power_centrality(new_graph, rescale = TRUE)
sort(result) # result 1

new_graph = delete.vertices(mygraph, which(degree(newgraph) < 200))
result = power_centrality(new_graph, rescale = TRUE)
sort(result) # result 2

new_graph = delete.vertices(mygraph, which(degree(newgraph) < 100))
result = power_centrality(new_graph, rescale = TRUE)
sort(result) #Failure

new_graph = delete.vertices(mygraph, which(degree(newgraph) < 150))
result = power_centrality(new_graph, rescale = TRUE)
sort(result) #result 3

new_graph = delete.vertices(mygraph, which(degree(newgraph) < 125))
result = power_centrality(new_graph, rescale = TRUE)
sort(result) #result 4

new_graph = delete.vertices(mygraph, which(degree(newgraph) < 112))
result = power_centrality(new_graph, rescale = TRUE)
sort(result) #result 5

new_graph = delete.vertices(mygraph, which(degree(newgraph) < 106))
result = power_centrality(new_graph, rescale = TRUE)
sort(result) #result 6 