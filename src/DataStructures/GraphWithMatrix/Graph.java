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
        clearVisitedState();
        hasEnded = false;
        BFS(0, word, 0);
        System.out.println(results);
    }

    public void BFS(int s, String word, int contextChar) {
        if (s >= nodeList.size()) {
            return;
        }
        if (isCorrectWord(word, currentCharacterSequence)) {
            results.add(stackToString(currentCharacterSequence));
            currentCharacterSequence.clear();
            hasEnded = true;
            return;
        }
        if (!hasEnded) {
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.offer(s);
            boolean stackHaschanged = false;
            while (!queue.isEmpty() && !hasEnded) {
                int u = queue.poll();
                for (int i = 0; i < nodeList.size() && !hasEnded; i++) { // Procurar caractere no contexto atual!
                    if (adjacencyMatrix[u][i] && !nodeList.get(i).getVisited()) {
                        queue.offer(i);
                        if (word.charAt(contextChar) == nodeList.get(i).getLetter()) {
                            nodeList.get(i).setVisited(true);
                            stackHaschanged = true;
                            currentCharacterSequence.add(word.charAt(contextChar));
                            BFS(i, word, contextChar + 1); // mudança para o proximo contexto
                        }
                    }
                }
                if (currentCharacterSequence.isEmpty()) {
                    
                    if (hasEnded) {
                        return;
                    }
                    if (s < nodeList.size() && !stackHaschanged) {
                        int nextChar = findNextUnvisitedNode(s, word.charAt(contextChar));
                        
                        if(nextChar!=-1){
                            nodeList.get(nextChar).setVisited(true);
                            BFS(nextChar, word, contextChar+1);
                        }
                        return;
                    }
                }
                if (!stackHaschanged) {
                    if (hasEnded) {
                        return;
                    }
                    if(!currentCharacterSequence.isEmpty()){
                        currentCharacterSequence.pop();
                    }
                    return;
                }
            }

        }

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
        Stack<Character> copy = (Stack<Character>) charSequence.clone();
        StringBuilder targetWord = new StringBuilder();
        for (int i = 0; i < charSequence.size(); i++) {
            if (!charSequence.isEmpty())
                targetWord.append(copy.pop());
        }
        targetWord = targetWord.reverse();
        String result = targetWord.toString();
        return word.equals(result);

    }

    public String stackToString(Stack<Character> charSequence) {
        Stack<Character> copy = (Stack<Character>) charSequence.clone();
        StringBuilder targetWord = new StringBuilder();
        for (int i = 0; i < charSequence.size(); i++) {
            if (!charSequence.isEmpty())
                targetWord.append(copy.pop());
        }
        targetWord = targetWord.reverse();
        String result = targetWord.toString();
        return result;
    }

    public void clearVisitedState() {
        for (int i = 0; i < nodeList.size(); i++) {
            nodeList.get(i).setVisited(false);
        }
    }

    public int findNextUnvisitedNode(int s, Character c){
        for (int i = s; i < nodeList.size(); i++) {
            if(!nodeList.get(i).getVisited() && nodeList.get(i).getLetter()==c){
                return i;
            }
        }
        return -1;
    }

}
