package structures;


import com.sun.tools.javac.util.Pair;
import structures.basic.Edge;
import structures.basic.VerticeNode;

public class SimpleGraph<D> {
    SimpleMap<D, VerticeNode<D>> vertices;


    public SimpleGraph() {
        this.vertices = new OpenBucketSimpleMap<>();
    }

    public VerticeNode<D> addVertice(D data) {
        VerticeNode<D> vertice = new VerticeNode<D>(data);
        vertices.put(data, vertice);

        return vertice;
    }

    public void connectVertices(D start, D end) {
        Pair<VerticeNode<D>, VerticeNode<D>> coordinate = pathCoordinate(start, end);
        coordinate.fst.linkToNode(coordinate.snd);
    }

    public void printShortestPath(D start, D end) {
        Pair<VerticeNode<D>, VerticeNode<D>> coordinate = pathCoordinate(start, end);
        VerticeNode<D> startPoint = coordinate.fst;
        VerticeNode<D> endPoint = coordinate.snd;

        setupSearchPaths(startPoint);
        printPath(endPoint);
    }

    private void printPath(VerticeNode<D> endPoint) {
        if (endPoint.previous() != null) {
            printPath(endPoint.previous());
            System.out.print(" --> ");
        }
        System.out.print(endPoint.data().toString());
    }

    private Pair<VerticeNode<D>, VerticeNode<D>> pathCoordinate(D start, D end) {
        VerticeNode<D> startNode = vertices.get(start);
        VerticeNode<D> endNode = vertices.get(end);

        if (start == null || end == null) {
            throw new IllegalArgumentException("Could not find either the starting or ending vertice");
        }

        return Pair.of(startNode, endNode);
    }

    private void setupSearchPaths(VerticeNode<D> startPoint) {
        // Setup start point
        SimpleQueue<VerticeNode<D>> toVisit = new LinkedListSimpleQueue<>();

        startPoint.markAsVisited(0, null);
        toVisit.enqueue(startPoint);

        while (!toVisit.empty()) {
            VerticeNode<D> current = toVisit.dequeue();
            for (Edge adj : current.adjacent()) {
                VerticeNode<D> node = adj.getEndpoint();

                if (node.isVisited()) continue;

                node.markAsVisited(current.cost() + 1, current);
                toVisit.enqueue(node);
            }
        }
    }

    public static void main(String[] args) {
        SimpleGraph<Integer> graph = new SimpleGraph<>();

        graph.addVertice(10);
        graph.addVertice(20);
        graph.addVertice(30);
        graph.addVertice(40);
        graph.addVertice(50);
        graph.addVertice(60);

        graph.connectVertices(10, 20);
        graph.connectVertices(20, 10);
        graph.connectVertices(10, 30);

        graph.connectVertices(20, 40);
        graph.connectVertices(40, 50);
        graph.connectVertices(40, 60);
        graph.connectVertices(50, 60);

        graph.connectVertices(30, 20);

        graph.printShortestPath(10, 60);
    }
}
