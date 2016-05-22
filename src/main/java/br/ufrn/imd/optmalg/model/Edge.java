package br.ufrn.imd.optmalg.model;

public class Edge {

	private Node origin;
	private Node destination;
	private double weight;

	public Edge(Node origin, Node destination) {
		this(origin, destination, Double.POSITIVE_INFINITY);
	}

	public Edge(Node origin, Node destination, double weight) {
		this.origin = origin;
		this.destination = destination;
		this.weight = weight;
	}

	public Node getOrigin() {
		return origin;
	}

	public Node getDestination() {
		return destination;
	}

	public double getWeight() {
		return weight;
	}

	@Override
	public String toString() {
		return origin + " ==> " + destination;
	}
	
	@Override
    public boolean equals(Object obj) {
       if (!(obj instanceof Edge))
            return false;
        if (obj == this)
            return true;

        Edge edge = (Edge) obj;
		return this.origin.equals(edge.getOrigin()) && this.destination.equals(edge.getDestination());
    }

}