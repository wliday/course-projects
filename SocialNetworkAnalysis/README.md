# Social Network Analysis
### Data Set:
**astrocollab.RData**Co-authorship network between scientists posting preprints on the Astrophysics E-Print archive.### DescriptionThe collaboration network of scientists posting preprints on the astrophysics archive at http://www.arxiv.org, 1995-1999, as compiled by M. Newman. The network is weighted, with weights assigned as described in the original papers. Please cite, as appropriate, one or more of:M. E. J. Newman, The structure of scientific collaboration networks, Proc. Natl. Acad. Sci. USA 98, 404-409 (2001).M. E. J. Newman, Scientific collaboration networks: I. Network construction and fundamental results, Pys. Rev. E 64, 016131 (2001).M. E. J. Newman, Scientific collaboration networks: II. Shortest paths, weighted networks, and centrality, Phys. Rev. E 64, 016132 (2001).### MetadataEdge attribute 'weight'Edge weights, based on the number of common papers and the number of authors of these papers. See the publication(s) for the definition. Vertex attribute 'name'Author name. 

### Problem
Determine the 
1. central person(s) in the graph
2. longest path
3. largest clique
4. ego
5. power centrality. 

### Solutions
We use "igraph" package to handle graph.
##### 1. Central person is the one with most degrees, so we just find out the person with largest degree.
```r
mygraph = upgrade_graph("astrocollab")

degreenumber = degree(mygraph, mode = "total")

sort(degreenumber)
```

##### 2.The longest path must be in the largest connected component. Use DFS to travel from one vertex, the largest distance will be the result.
```r
sg = induced_subgraph(mygraph, which(components(mygraph)$membership == 1))

V(sg)$degree = degree(sg)

result = dfs(sg, root = 1, dist = TRUE)$dist
```

##### 3. Assign attribute degree to each vertex. In the largest clique, each vertex has the same minimum degree; we try to find out the minimum degree with a spliting graph, sg. And verify max clique in sg. We can use binary search to close to the result.

```r
V(mygraph)$degree = degree(mygraph)
sg = induced.subgraph(mygraph, which(V(mygraph)$degree > 50))
vcount(sg)
max_cliques(sg)
```

##### 4. Since edges in the graph have weight, so the ego centrality need to find out vertex with the largest sum of edge weights.

First, we try to reduce the graph, delete all the vertices with degree less than 50. And try to find out the vertex with largest adjacent edges weight sum.
The result is 60.75835. The actual weight sum is 80.

Second, we reduce the graph by deleting edges with weight less than 1. The result is 74.38169; the real weight sum is 109.

Third, verify all the results with weight sum larger than 40 in step 2. We got the same result.

```r
V(mygraph)$degree = degree(mygraph)
mynewgraph = delete.edges(mygraph, which(E(mygraph)$weight <= 1))
ecount(mynewgraph)
for (v in V(mynewgraph)) {
  edges = incident(mynewgraph, v);
  vertex_attr(mynewgraph, "weight", index = c(v)) <- sum(edges$weight);
}
max_weight = max(V(mynewgraph)$weight)
max_weight
target_single_vertex_graph = induced.subgraph(mynewgraph, which(V(mynewgraph)$weight == max_weight))
V(target_single_vertex_graph)
```

##### 5. We can't calculate power centrality directly due to the large scale of the graph.

- Assume 1, the node with power centrality is in the biggest connected component.

Continually delete the node with degree one, but fail to find out the result.

- Assume 2: the person with a larger number of degrees will be more possible to have greater power of centrality.

At first, we try these people who have degree larger than 300, calculate the power of centrality.

Second, try nodes with degree greater than 200, we got a result.

Then we use binary search method to try degree, 100, 150, 125, 112, 106.

At last, we find 106 is the critical value. We obtain an approximate result due to the limitation of computational power.

```r
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

```

### Instruction
1. Path length based on edges' weight or just the number of edges?
based on the number of edges between vertex a and vertex b remembering that there should be no circuits or loops.

2. what is power centrality?
Power centrality encompasses the idea that an individual's power is the sum of the power of its neighbors - which may be positive or negative. This concept is applied recursively and computed for every node.
See bonpow in the contributed extensions directory for a routine that computes power centrality.

3. ego: find biggest immediate neighborhood (of a single vertex)
Ego-centrality is similar to or equal to betweenness centrality depending on the reference you consult.
Betweenness centrality is a measure of the number of indegree edges to a vertex, usually without regard to any weighting.
Ego-centrality has sometimes been defined as the sum of the weights associated with the indegree edges suitably normalized (such as to whole numbers). The latter must be done with the same scaling factor for all edges in the graph.

4. central person is the person with most connections (largest degree)
The central person could be defined as the person with the most edges = sum of indegree and outdegree.
It could also be defined as the person(s) with the highest betweenness centrality.
Note: for complex graphs, there may be many such people with the highest betweenness centrality. Then, other rules and measures must be applied to decide who is mot central.







