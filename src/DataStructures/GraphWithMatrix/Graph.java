package DataStructures.GraphWithMatrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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

    public void BFS(int s){
        for (int j = 0; j < nodeList.size(); j++) {
            cor[j] = GraphNode.BRANCO;
            distance[j] = -1;
            previous[j] = null;
        }
        cor[s] = GraphNode.CINZA;
        distance[s] = 0;
        fila.offer(s);
        while(!fila.isEmpty()){
            int u = fila.poll();
            for (int i = 0; i < nodeList.size(); i++) {

                if(adjacencyMatrix[u][i] && cor[i]==GraphNode.BRANCO){
                    cor[i] = GraphNode.CINZA;
                    distance[i]= distance[u]+1;
                    previous[i] = u;
                    fila.offer(i);
                }
            }
            cor[u] = GraphNode.PRETO;
        }

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
