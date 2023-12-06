package DataStructures.GraphWithMatrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
    private ArrayList<GraphNode> nodeList;
    private boolean[][] adjacencyMatrix;

    Stack<Character> currentCharacterSequence = new Stack<Character>();
    ArrayList<String> results = new ArrayList<>();
    boolean hasEnded = false;

    private Integer[] cor;

    private Integer[] previous;
    private Integer[] distance;

    private Integer time;

    private Integer start[];

    private Integer end[];

    public Graph(ArrayList<GraphNode> nodeList) {
        this.nodeList = nodeList;
        this.adjacencyMatrix = new boolean[nodeList.size()][nodeList.size()];

        cor = new Integer[nodeList.size()];
        previous = new Integer[nodeList.size()];
        distance = new Integer[nodeList.size()];
        start = new Integer[nodeList.size()];
        end = new Integer[nodeList.size()];
    }

    public void addUndirectedEdge(int i, int j) {
        adjacencyMatrix[i][j] = true;
        adjacencyMatrix[j][i] = true;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("   ");

        for (int i = 0; i < nodeList.size(); i++) {
            s.append(nodeList.get(i).getLetter() + " ");
        }
        s.append("\n");
        for (int i = 0; i < nodeList.size(); i++) {
            s.append(nodeList.get(i).getLetter() + ": ");
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                s.append(flag(adjacencyMatrix[i][j]) + " ");

            }
            s.append("\n");
        }
        return s.toString();

    }

    public int flag(boolean status) {
        return status ? 1 : 0;
    }

    public void initializeBfsContext(String word) {
        currentCharacterSequence.clear();
        hasEnded = false;
        BFS(0, word, 0);
        System.out.println("Result");
    }

    public void BFS(int s, String word, int contextChar) {
        if (!hasEnded) {
            Queue<Integer> queue = new LinkedList<Integer>();
            if (isCorrectWord(word, currentCharacterSequence)) {
                results.add(stackToString(currentCharacterSequence));
                hasEnded = true;
                return;
            }

            // Iniciando variáveis auxiliares
            for (int j = 0; j < nodeList.size(); j++) {

                cor[j] = GraphNode.BRANCO;
                distance[j] = -1;
                previous[j] = null;
            }

            cor[s] = GraphNode.CINZA;
            distance[s] = 0;
            queue.offer(s);
            int index = 0;
            boolean stackHaschanged = false;
            while (!queue.isEmpty() && !hasEnded) {

                int u = queue.poll();
                for (int i = 0; i < nodeList.size(); i++) {
                    if (adjacencyMatrix[u][i] && cor[i] == GraphNode.BRANCO &&
                        nodeList.get(i).getLetter() == word.charAt(contextChar)) {
                        cor[i] = GraphNode.CINZA;
                        distance[i] = distance[u] + 1;
                        previous[i] = u;
                        queue.offer(i);
                        currentCharacterSequence.add(word.charAt(contextChar));
                        stackHaschanged = true;
                        BFS(i,word,contextChar+1);
                    }
                    if(!stackHaschanged){
                        currentCharacterSequence.pop();
                        return;
                    }
                }
            }
            index++;
            cor[u] = GraphNode.PRETO;
        }

    }return;

    }

    public boolean hasLetterInNodeList(Character target) {
        for (int i = 0; i < nodeList.size(); i++) {
            if (nodeList.get(i).getLetter().equals(target)) {
                return true;
            }
        }
        return false;
    }

    public int getIndexOfNodeLetter(Character target) {
        for (int i = 0; i < nodeList.size(); i++) {
            if (nodeList.get(i).getLetter().equals(target))
                return i;
        }
        return -1;
    }

    public void startDFS(int s) {

    }

    public Integer[] getCor() {
        return cor;
    }

    public Integer[] getPrevious() {
        return previous;
    }

    public Integer[] getDistance() {
        return distance;
    }

    public boolean[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public boolean isCorrectWord(String word, Stack<Character> charSequence) {
        StringBuilder targetWord = new StringBuilder();
        for (int i = 0; i < charSequence.size(); i++) {
            if (!charSequence.isEmpty())
                targetWord.append(charSequence.pop());
        }
        String result = targetWord.toString();
        return word.equals(result);

    }

    public String stackToString(Stack<Character> charSequence) {
        StringBuilder targetWord = new StringBuilder();
        for (int i = 0; i < charSequence.size(); i++) {
            if (!charSequence.isEmpty())
                targetWord.append(charSequence.pop());
        }
        String result = targetWord.toString();
        return result;
    }

}
