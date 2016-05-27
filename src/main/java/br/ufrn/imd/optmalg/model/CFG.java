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
			BasicBlock el = node.getBasicBlock();
			int i = el.firstProgramStatement().getSequenceID();
			int j = el.lastProgramStatement().getSequenceID();
			System.out.print("[" + i + "," + j + "] -> ");

			List<Node> nodeChildren = new ArrayList<>();
			for (Edge edge : edges) {
				if (edge.getOrigin().equals(node)) {
					nodeChildren.add(edge.getDestination());
				}
			}

			for (Node child : nodeChildren) {
				BasicBlock elChild = child.getBasicBlock();
				int ii = elChild.firstProgramStatement().getSequenceID();
				int jj = elChild.lastProgramStatement().getSequenceID();
				System.out.print("[" + ii + "," + jj + "], ");
			}
			System.out.println();
		}

	}

}
