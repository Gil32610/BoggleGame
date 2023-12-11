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
        BFS(0, word);
        System.out.println(results);
    }

    public void BFS(int s, String word) {
        if (currentCharacterSequence.size() >= word.length()) {
            results.add(stackToString(currentCharacterSequence));
            hasEnded = true;
            currentCharacterSequence.clear();
            return;
        }
        if (s >= nodeList.size()) {
            return;
        }
        boolean stackHaschanged = false;
        int u = s;
        for (int i = 0; i < nodeList.size() && !hasEnded; i++) { // Procurar caractere no contexto atual!
            if (adjacencyMatrix[u][i] && !nodeList.get(i).getVisited()) {
                if (word.charAt(currentCharacterSequence.size()) == nodeList.get(i).getLetter()) {
                    nodeList.get(i).setVisited(true);
                    stackHaschanged = true;
                    currentCharacterSequence.add(word.charAt(currentCharacterSequence.size()));
                    BFS(i, word); // mudança para o proximo contexto
                    stackHaschanged = false;
                }
            }
        }
        // Não houveram caracteres adicionados
        if (currentCharacterSequence.isEmpty()) {
            handleEmptyStack(word, s);
            return;
        }
        // Caractere atual não encontrado
        if (!stackHaschanged) {
            currentCharacterSequence.pop();
            if (hasEnded) {
                return;
            }
            // Após a remoção pilha pode estar vazia
            if (currentCharacterSequence.isEmpty()) {

                int nextLetter = findNextUnvisitedNode(s, word.charAt(currentCharacterSequence.size()));
                if (nextLetter != -1) {
                    nodeList.get(s).setVisited(false);
                    nodeList.get(nextLetter).setVisited(true);
                    currentCharacterSequence.add(nodeList.get(nextLetter).getLetter());
                    BFS(nextLetter, word);
                }
                hasEnded = true;
                return;
            }
            // Retorna para o vértice anterior
            nodeList.get(s).setVisited(false);
            return;

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

    public int findNextUnvisitedNode(int s, Character c) {

        for (int i = s; i < nodeList.size(); i++) {
            if (!nodeList.get(i).getVisited() && nodeList.get(i).getLetter() == c) {
                return i;
            }
        }
        return -1;
    }

    public void handleEmptyStack(String word, int start) {
        if (hasEnded) {
            return;
        }
        int nextChar = findNextUnvisitedNode(start, word.charAt(currentCharacterSequence.size()));
        if (nextChar != -1) {
            nodeList.get(nextChar).setVisited(true);
            currentCharacterSequence.add(nodeList.get(nextChar).getLetter());
            BFS(nextChar, word);
        }

    }

    public void handleUnchangedStack(String word) {

    }

}
