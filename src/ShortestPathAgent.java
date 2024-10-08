import java.util.Scanner;
class ShortestPathAgent{
    public static void main(String[] args) {
        //initialize vertices
        String[] vertices = {"Menethil Harbor", "Ironforge", "Thelsemar", "Kargath", "Blackrock Mountain",
                "Stormwind", "Lakeshire", "Sentinel Hill", "Darkshore", "Karazan", "Stonard", "Booty Bay",
                "Zul-Gurub", "Nethergarde Keep"};

        //initialize edges
        int[][] edges = {
                {0, 1, 7}, {0, 2, 20},
                {1, 0, 7}, {1, 2, 22}, {1, 4, 29}, {1, 5, 15},
                {2, 0, 20}, {2, 1, 22}, {2, 3, 25}, {2, 4, 14},
                {3, 2, 25}, {3, 4, 29},
                {4, 1, 29}, {4, 2, 14}, {4, 3, 29}, {4, 5, 30}, {4, 6, 22},
                {5, 1, 15}, {5, 4, 30}, {5, 7, 14}, {5, 8, 13},
                {6, 4, 22}, {6, 8, 32},
                {7, 5, 14}, {7, 8, 13}, {7, 11, 19},
                {8, 5, 13}, {8, 6, 32}, {8, 7, 13}, {8, 9, 20}, {8, 11, 20},
                {9, 8, 20}, {9, 10, 21}, {9, 12, 16},
                {10, 9, 21}, {10, 13, 17},
                {11, 7, 19}, {11, 8, 20},
                {12, 9, 16}, {12, 11, 28}, {12, 13, 31},
                {13, 10, 17}, {13, 12, 31}
        };

        //initialize graph
        WeightedGraph graph = new WeightedGraph (vertices, edges);

        //user input
        Scanner scan = new Scanner(System.in);
        System.out.println("Where are you?");
        String source = scan.nextLine();
        System.out.println("Where are you trying to go?");
        String goal = scan.nextLine();

        //create tree of shortest paths to specific goal
        WeightedGraph.ShortestPathTree tree = graph.getShortestPath(graph.getIndex(goal));

        //Print shortest path from source to goal
        System.out.println("The shortest path from " + source + " to " + goal + " is:");
        java.util.List<String> path = tree.getPath(graph.getIndex(source));
        for (String s: path){
            System.out.print(s + " ");
        }
    }
}
