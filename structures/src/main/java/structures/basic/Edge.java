package structures.basic;


public class Edge {
    private static final int DEFAULT_WEIGHT = 1;

    private VerticeNode endpoint;
    private int weight;


    public Edge(VerticeNode endpoint) {
        this(endpoint, DEFAULT_WEIGHT);
    }

    public Edge(VerticeNode endpoint, int weight) {
        this.endpoint = endpoint;
        this.weight = weight;
    }

    public VerticeNode getEndpoint() {
        return endpoint;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
