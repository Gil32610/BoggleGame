package DataStructures.GraphWithMatrix;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<GraphNode> nodeList = new ArrayList<>();

        char[][] boggleBoard = { { 'M', 'S', 'E' },
                                { 'R', 'A', 'T' },
                                { 'L', 'O', 'N' } };
        int pos = 0;
        for (int i = 0; i < boggleBoard.length; i++) {
            for (int j = 0; j < boggleBoard[0].length; j++) {
                pos = (i * boggleBoard[0].length) + j;
                System.out.println(pos);
                nodeList.add(new GraphNode(boggleBoard[i][j], pos));
            }
        }

        Graph g = new Graph(nodeList);
        g.addUndirectedEdge(0, 1);
        g.addUndirectedEdge(0, 4);
        g.addUndirectedEdge(0, 3);

        g.addUndirectedEdge(1, 3);
        g.addUndirectedEdge(1, 4);
        g.addUndirectedEdge(1, 5);
        g.addUndirectedEdge(1, 2);

        g.addUndirectedEdge(2, 4);
        g.addUndirectedEdge(2, 5);

        g.addUndirectedEdge(3, 4);
        g.addUndirectedEdge(3, 7);
        g.addUndirectedEdge(3, 6);

        g.addUndirectedEdge(4, 5);
        g.addUndirectedEdge(4, 8);
        g.addUndirectedEdge(4, 7);
        g.addUndirectedEdge(4, 6);

        g.addUndirectedEdge(5, 7);
        g.addUndirectedEdge(5, 8);

        g.addUndirectedEdge(6, 3);
        g.addUndirectedEdge(6, 7);

        g.addUndirectedEdge(7, 8);

      


        System.out.println(g);
        g.BFS(0);
        System.out.println(Arrays.deepToString(g.getPrevious()));
        System.out.println(Arrays.deepToString(g.getDistance()));
        System.out.println(Arrays.deepToString(g.getCor()));
    }
}
