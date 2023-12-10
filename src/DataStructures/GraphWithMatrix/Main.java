package DataStructures.GraphWithMatrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.SortedSet;

import BoggleBoard.BoggleBoardGame;

public class Main {
    public static void main(String[] args) {
        ArrayList<GraphNode> nodeList = new ArrayList<>();

        char[][] boggleBoard = { { 'D', 'O', 'J', 'O','D' },
                                 { 'A', 'D', 'O', 'E', 'Q' },
                                 { 'R', 'A', 'T', 'R', 'P' },
                                 { 'D', 'R', 'N', 'M', 'A' },
                                 { 'P', 'E', 'X', 'R', 'O' }

        };

        int pos = 0;
        ArrayList<Character> charList = new ArrayList<>();
        for (int i = 0; i < boggleBoard.length; i++) {
            for (int j = 0; j < boggleBoard[0].length; j++) {
                charList.add(boggleBoard[i][j]);
            }
        }

        for (int i = 0; i < boggleBoard.length; i++) {
            for (int j = 0; j < boggleBoard[0].length; j++) {
                pos = getColumnIndexOnList(i, j, boggleBoard[0].length);
                nodeList.add(new GraphNode(boggleBoard[i][j], pos)); // adicionar a palavra com sua respectiva função
            }
        }

        ArrayList<String> words = new ArrayList<>(); // criação da lista de palavras que queremos encontrar
        words.add("PERNA");
        words.add("ADORO");
        words.add("MOTOR");
        words.add("ARADO");

        Graph g = new Graph(nodeList); // criação do grafo

        BoggleBoardGame boggle = new BoggleBoardGame(boggleBoard, g, words); // inicialização do jogo

        System.out.println(g);
        boggle.checkResults();
        // System.out.println(boggle.getGraph()); //mostra a matriz de adjacencia

        // boolean found = false;
        // for (int i = 0; i < words.size(); i++) {
        // String palavra = words.get(i); //palavra do momento
        // char letra = palavra.charAt(0); //pega a primeira letra
        // boolean existe = findWord(letra, boggleBoard); //verifica se a primeira letra
        // existe na matriz
        // int start = findPos(letra, boggleBoard); //procura pela posição da primeira
        // letra

        // if (existe) {
        // found = g.BFS(start, palavra, charList); //além da posicação da primeira
        // letra, passa a palavra e a lista feita a partir da matriz de caracteres
        // }

        // if (found) {
        // System.out.println("Palavra encontrada: " + palavra);
        // }
        // else {
        // System.out.println("Palavra NÃO encontrada: " + palavra);
        // }
        // }

    }

    public static boolean findWord(char letra, char[][] boggleBoard) {
        for (int i = 0; i < boggleBoard.length; i++) { // linhas
            for (int j = 0; j < boggleBoard[i].length; j++) { // colunas
                if (boggleBoard[i][j] == letra) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int findPos(char letra, char[][] boggleBoard) {
        int cont = 0;
        for (int i = 0; i < boggleBoard.length; i++) { // linhas
            for (int j = 0; j < boggleBoard[i].length; j++) { // colunas
                if (boggleBoard[i][j] == letra) {
                    return cont;
                }
                cont++;
            }
        }
        return cont;
    }

    public static int getColumnIndexOnList(int i, int j, int colSize) {
        return (i * colSize) + j;
    }

    public static boolean isValidPosition(int i, int j, int rowSize, int columnSize) {
        if (i < 0 || i > rowSize - 1 || j < 0 || j > columnSize - 1)
            return false;

        return true;
    }
}
