package DataStructures.GraphWithMatrix;

public class GraphNode {
    public static final int BRANCO = 0;
    public static final int CINZA = 1;
    public static final int PRETO = 2;
    private boolean isVisited;
    private Character letter;
    private int index;

    public GraphNode(Character letter, int index) {
        this.letter = letter;
        this.index = index;
        isVisited = false;
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    public int getIndex() {
        return index;
    }

    public void setVisited(boolean visited){
        this.isVisited = visited;
    }
    public boolean getVisited(){
        return isVisited;
    }
}
