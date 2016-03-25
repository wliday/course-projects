V(mygraph)$degree = degree(mygraph)
sg = induced.subgraph(mygraph, which(V(mygraph)$degree > 50))
vcount(sg)
max_cliques(sg)