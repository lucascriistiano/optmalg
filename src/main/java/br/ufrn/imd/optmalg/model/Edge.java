package br.ufrn.imd.optmalg.model;

public class Edge {

	private Node<BasicBlock> origin;
	private Node<BasicBlock> destination;
	private double weight;

	public Edge(Node<BasicBlock> origin, Node<BasicBlock> destination) {
		this(origin, destination, Double.POSITIVE_INFINITY);
	}

	public Edge(Node<BasicBlock> origin, Node<BasicBlock> destination, double weight) {
		this.origin = origin;
		this.destination = destination;
		this.weight = weight;
	}

	public Node<BasicBlock> getOrigin() {
		return origin;
	}

	public Node<BasicBlock> getDestination() {
		return destination;
	}

	public double getWeight() {
		return weight;
	}

	public String toString() {
		return origin + " ==> " + destination;
	}

}