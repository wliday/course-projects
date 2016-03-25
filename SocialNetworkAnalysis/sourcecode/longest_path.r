sg = induced_subgraph(mygraph, which(components(mygraph)$membership == 1))

V(sg)$degree = degree(sg)

result = dfs(sg, root = 1, dist = TRUE)$dist