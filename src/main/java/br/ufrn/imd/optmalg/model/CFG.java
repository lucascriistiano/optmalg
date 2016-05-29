package br.ufrn.imd.optmalg.model;

import java.util.ArrayList;
import java.util.List;

public class CFG {

	private List<Node> nodes;
	private List<Edge> edges;

	public CFG() {
		this.nodes = new ArrayList<>();
		this.edges = new ArrayList<>();
	}

	public void addNode(Node node) {
		this.nodes.add(node);
	}

	public List<Node> getNodes() {
		return this.nodes;
	}

	public List<Edge> getEdges() {
		return this.edges;
	}

	public void createEdge(Node inNode, Node outNode) {
		Edge newEdge = new Edge(inNode, outNode);
		if (!this.edges.contains(newEdge)) {
			inNode.addChild(outNode);
			this.edges.add(newEdge);
		}
	}

	/**
	 * Prints a generated CFG. Has complexity of O(n * (m+n))
	 */
	public void print() {
		for (Node node : nodes) {
			System.out.print("[" + node.getLabel() + "] -> ");
			for (Node child : node.getChildren()) {
				System.out.print("[" + child.getLabel() + "] ");
			}
			System.out.println();
		}

	}

}
