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
                pos = getColumnIndexOnList(i, j, boggleBoard[0].length);
                nodeList.add(new GraphNode(boggleBoard[i][j], pos));
            }
        }
        for (int i = 0; i < boggleBoard.length; i++) {
            for (int j = 0; j < boggleBoard[0].length; j++) {
                int value = getColumnIndexOnList(i, j, boggleBoard[0].length);
                System.out.println("Index on list: " + value);
            }
        }

        int[] rowPossibilities = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] colPossibilities = { -1, 0, 1, -1, 1, -1, 0, 1 };

        for (int i = 0; i < boggleBoard.length; i++) {
            for (int j = 0; j < boggleBoard[0].length; j++) {
                for (int k = 0; k < colPossibilities.length; k++) {
                    if (isValidPosition(i + rowPossibilities[k], j + colPossibilities[k], boggleBoard.length,
                            boggleBoard[0].length)) {
                        System.out.println("Adjacency with: " + boggleBoard[i][j] + " AND "
                                + boggleBoard[i + rowPossibilities[k]][j + colPossibilities[k]]);
                                int rowMove = i + rowPossibilities[k];
                                int colMove = j + colPossibilities[k];
                        System.out.println(getColumnIndexOnList(i, j, boggleBoard[0].length)+ " "+ getColumnIndexOnList(i, j, i));
                    }
                }

            }
        }

        // Graph g = new Graph(nodeList);
        // g.addUndirectedEdge(0, 1);
        // g.addUndirectedEdge(0, 4);
        // g.addUndirectedEdge(0, 3);
        //
        // g.addUndirectedEdge(1, 3);
        // g.addUndirectedEdge(1, 4);
        // g.addUndirectedEdge(1, 5);
        // g.addUndirectedEdge(1, 2);
        //
        // g.addUndirectedEdge(2, 4);
        // g.addUndirectedEdge(2, 5);
        //
        // g.addUndirectedEdge(3, 4);
        // g.addUndirectedEdge(3, 7);
        // g.addUndirectedEdge(3, 6);
        //
        // g.addUndirectedEdge(4, 5);
        // g.addUndirectedEdge(4, 8);
        // g.addUndirectedEdge(4, 7);
        // g.addUndirectedEdge(4, 6);
        //
        // g.addUndirectedEdge(5, 7);
        // g.addUndirectedEdge(5, 8);
        //
        // g.addUndirectedEdge(6, 3);
        // g.addUndirectedEdge(6, 7);
        //
        // g.addUndirectedEdge(7, 8);
        //
        //
        //
        //
        // System.out.println(g);
        // g.BFS(0);
        // System.out.println(Arrays.deepToString(g.getPrevious()));
        // System.out.println(Arrays.deepToString(g.getDistance()));
        // System.out.println(Arrays.deepToString(g.getCor()));
    }

    public static int getColumnIndexOnList(int i, int j, int colSize) {
        return (i * colSize) + j;
    }

    public static int getRowIndexOnList(int i, int j, int rowSize) {
        return (i * rowSize) + j;
    }

    public static boolean isValidPosition(int i, int j, int rowSize, int columnSize) {

        if (i < 0 || i > rowSize - 1 || j < 0 || j > columnSize - 1)
            return false;

        return true;
    }
}
