package BoggleBoard;

import DataStructures.GraphWithMatrix.Graph;
import DataStructures.GraphWithMatrix.GraphNode;

import java.util.ArrayList;

public class BoggleBoardGame {
    private Graph graph;
    private char[][] boggleBoard;
    private ArrayList<String> words;
    private ArrayList<String> results;

    private int[] rowPossibilities = { -1, -1, -1, 0, 0, 1, 1, 1 };
    private int[] colPossibilities = { -1, 0, 1, -1, 1, -1, 0, 1 };

    public BoggleBoardGame(char[][] boggleBoard,Graph graph, ArrayList<String> words) {
        this.boggleBoard = boggleBoard;
        this.graph = graph;
        int boggleRow = boggleBoard.length;
        int boggleCol = boggleBoard[0].length;
        this.words = words;

    }

    public void addEdgeAdjacentPositions(int i, int j,int rowSize,int colSize, ArrayList<GraphNode>nodeList) {
       
    }
    public void completeCellAdjacency(int i, int j, int rowSize,int colSize){
        for (int k = 0; k < colPossibilities.length; k++) {
            if (isValidPosition(i + rowPossibilities[k], j + colPossibilities[k], boggleBoard.length,
                    boggleBoard[0].length)) {
                        graph.addUndirectedEdge(getColumnIndexOnList(i, j, boggleBoard[0].length), getColumnIndexOnList(i+rowPossibilities[k], j+colPossibilities[k], boggleBoard[0].length));
                System.out.println(getColumnIndexOnList(i, j, boggleBoard[0].length)+ " "+ getColumnIndexOnList(i+rowPossibilities[k], j+colPossibilities[k], boggleBoard[0].length));
            }
        }
    }
    public static int getColumnIndexOnList(int i, int j, int colSize) {
        return (i * colSize) + j;
    }

    private boolean isValidPosition(int i, int j, int rowSize, int columnSize) {

        if (i < 0 || i > rowSize - 1 || j < 0 || j > columnSize - 1)
            return false;

        return true;
    }
}
