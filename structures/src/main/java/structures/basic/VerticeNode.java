package structures.basic;


import structures.ResizableArraySimpleList;
import structures.SimpleList;


public class VerticeNode<D> {
    private SimpleList<Edge> adjacent;

    private D data;

    // traversal vars
    private int cost;
    private boolean visited;
    private VerticeNode<D> previous;


    public VerticeNode(D data) {
        this.data = data;
        this.adjacent = new ResizableArraySimpleList<>();
        resetTraversal();
    }

    public void linkToNode(VerticeNode<D> target) {
        if (target == null) {
            throw new NullPointerException("Null target not allowed");
        }

        this.adjacent.add(new Edge(target));
    }

    public void markAsVisited(int cost, VerticeNode<D> previous) {
        this.visited = true;
        this.cost = cost;
        this.previous = previous;
    }

    public void resetTraversal() {
        this.visited = false;
        this.cost = 0;
        this.previous = null;
    }

    public SimpleList<Edge> adjacent() {
        return adjacent;
    }

    public D data() {
        return this.data;
    }

    public int cost() {
        return cost;
    }

    public VerticeNode<D> previous() {
        return previous;
    }

    public boolean isVisited() {
        return visited;
    }
}
