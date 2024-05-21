import java.util.*;

public class WeightedGraph {
    protected List<String> vertices = new ArrayList<>(); //store vertices
    protected List<List<WeightedEdge>> neighbors = new ArrayList<>(); //adjacency lists

    //construct WeightedGraph from vertices and edges in arrays
    public WeightedGraph(String[] vertices, int[][] edges){
        createWeightedGraph(List.of(vertices), edges);
    }

    //create adjacency lists from edge arrays
    private void createWeightedGraph(List<String> vertices, int[][] edges){
        this.vertices = vertices;

        //create vertices list
        for(int i = 0; i < vertices.size(); i++){
            neighbors.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            neighbors.get(edge[0]).add(new WeightedEdge(edge[0], edge[1], edge[2]));
        }
    }

    //return the number of vertices in the graph
    public int getSize(){
        return vertices.size();
    }

    //return the index for the specified vertex object
    public int getIndex(String s){
        return vertices.indexOf(s);
    }

    //find single source the shortest paths
    public ShortestPathTree getShortestPath(int sourceVertex){ //dijkstras
        //cost[v] stores the cost of the path from v to the source
        double[] cost = new double[getSize()];
        //initial cost set to infinity
        Arrays.fill(cost, Double.POSITIVE_INFINITY);
        cost[sourceVertex] = 0; //cost of source is 0

        //parent[v] stores previous vertex of v in the path
        int[] parent = new int[getSize()];
        parent[sourceVertex] = -1; //parent of source is set to -1

        //T stores the vertices whose path found so far
        List<Integer> T = new ArrayList<>();

        //expand T
        while(T.size() < getSize()){
            //find the smallest cost v in V - T
            int u = -1; //vertex to be determined
            double currentMinCost = Double.POSITIVE_INFINITY;
            for(int i = 0; i < getSize(); i++){
                if(!T.contains(i) && cost[i] < currentMinCost){
                    currentMinCost = cost[i];
                    u = i;
                }
            }

            T.add(u); //add a new vertex to T

            //adjust cost[v] for v that is adjacent to u and v in V - T
            for(WeightedEdge e: neighbors.get(u)){
                if(!T.contains(e.v) && cost[e.v] > cost[u] + e.weight){
                    cost[e.v] = cost[u] = e.weight;
                    parent[e.v] = u;
                }
            }
        }//end of while

        //create ShortestPathTree
        return new ShortestPathTree(sourceVertex, parent, T, cost);
    }

    //inner class WeightedEdge
    public static class WeightedEdge{
        public int u; //starting vertex of the edge
        public int v; //ending vertex of the edge

        int weight;

        //construct an edge for (u, v)
        public WeightedEdge(int u, int v, int weight){
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        public boolean equals(Object o){
            return u == ((WeightedEdge)o).u && v == ((WeightedEdge)o).v;
        }
    }

    //Inner class ShortestPathTree
    public class ShortestPathTree {
        private int source; // root of the tree
        private int[] parent; //parent of each vertex
        private List<Integer> searchOrder; //store the search order
        private double[] cost; //cost[v] is the cost from v to source

        public ShortestPathTree(int source, int[] parent, List<Integer> searchOrder, double[] cost){
            this.source = source;
            this.parent = parent;
            this.searchOrder = searchOrder;
            this.cost = cost;
        }

        //return path of vertices from a vertex to the root
        public List<String> getPath(int index){
            ArrayList<String> path = new ArrayList<>();

            do{
                path.add(vertices.get(index));
                index = parent[index];
            }
            while(index != -1);

            return path;
        }
    }
}

