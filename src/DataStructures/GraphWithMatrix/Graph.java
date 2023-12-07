package DataStructures.GraphWithMatrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {
private ArrayList<GraphNode> nodeList;
private boolean[][] adjacencyMatrix;

private Integer[]cor;

private Integer[]previous;
private Integer[] distance;

private Queue<Integer> fila;

private Integer time;

private Integer start[];

private Integer end[];

    public Graph(ArrayList<GraphNode> nodeList) {
        this.nodeList = nodeList;
        this.adjacencyMatrix = new boolean[nodeList.size()][nodeList.size()];
        fila = new LinkedList<>();
        cor = new Integer[nodeList.size()];
        previous = new Integer[nodeList.size()];
        distance = new Integer[nodeList.size()];
        start=new Integer[nodeList.size()];
        end = new Integer[nodeList.size()];
    }

    public void addUndirectedEdge(int i, int j){
        adjacencyMatrix[i][j] = true;
        adjacencyMatrix[j][i] = true;
    }



    @Override
    public String toString(){
        StringBuilder s= new StringBuilder();

        s.append("   ");

        for (int i = 0; i < nodeList.size(); i++) {
            s.append(nodeList.get(i).getLetter() + " ");
        }
        s.append("\n");
        for (int i = 0; i < nodeList.size() ; i++) {
            s.append(nodeList.get(i).getLetter()+": ");
            for (int j = 0; j <adjacencyMatrix[i].length ; j++) {
                s.append(flag(adjacencyMatrix[i][j])+ " ");

            }
            s.append("\n");
        }
        return s.toString();

    }
    

    public int flag(boolean status){
        return status?1:0;
    }

    public void startBFS() {
        for (int j = 0; j < nodeList.size(); j++) {
            
            cor[j] = GraphNode.BRANCO;
            distance[j] = -1;
            previous[j] = null;
        }
    }

    public boolean BFS(int s, String palavra, ArrayList<Character> listachar){ //adiciona retorno booleana e parametro de palavra
        int cont = 1; //posição da palavra para procurar
        Stack<Integer> charsIncluidos = new Stack<>();
        LinkedList<Integer> fila = new LinkedList<>();
        boolean foundp = true; //define se a palavra toda existe

        for (int j = 0; j < nodeList.size(); j++) {
            cor[j] = GraphNode.BRANCO;
            distance[j] = -1;
            previous[j] = null;
        }
        cor[s] = GraphNode.CINZA;
        distance[s] = 0;
        fila.offer(s);
        charsIncluidos.push(s);

        while(!fila.isEmpty()){
            int u = fila.poll(); //tira da fila pra substituir pelos adjacentes (verificar se um dos adjacentes é o proximo char da palavra)
            boolean foundl = false; //define se a letra existe entre a lista de adjacentes, e é reiniciado a cada nova letra
            
            for (int i = 0; i < nodeList.size(); i++) {

                if(cont < palavra.length() && adjacencyMatrix[u][i] && cor[i]==GraphNode.BRANCO && palavra.charAt(cont) == listachar.get(i)){
                    distance[i]= distance[u]+1;
                    previous[i] = u;
                    foundl = true; 
                    fila.offer(i);
                    charsIncluidos.add(i);
                    cor[i] = GraphNode.CINZA;
                    cont++;
                    if (cont >= palavra.length()) {
                        return foundp;
                    }
                    break;
                }
            }
            
            if (!foundl) { //se depois de passar por todos os nodes adjacentes não encontrar a letra seguinte, a palavra nao existe
                charsIncluidos.pop(); //volta pro node anterior e repete a busca
                if (charsIncluidos.isEmpty()) {
                    //procurar outra primeira palavra, se não achar é falso
                    for (int i = s+1; i < listachar.size(); i++) {
                        if (listachar.get(i) == listachar.get(s)) {
                            fila.offer(i);
                            charsIncluidos.push(i);
                            s = i;
                            break;
                        }
                    }
                    if (fila.isEmpty()){
                        foundp = false;
                        return foundp;
                    }
                    
                }
                else {
                    cont--;
                    fila.offer(charsIncluidos.peek());
                }
                
            }
            cor[u] = GraphNode.PRETO;

        }
        return foundp; //

    }

    public void startDFS(int s){

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
    
}
