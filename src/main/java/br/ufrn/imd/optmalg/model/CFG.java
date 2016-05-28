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
			BasicBlock basicBlock = node.getBasicBlock();
			int seqIDFirstStmt = basicBlock.firstProgramStatement().getSequenceID();
			int seqIDLastStmt = basicBlock.lastProgramStatement().getSequenceID();
			System.out.print("[" + seqIDFirstStmt + "," + seqIDLastStmt + "] -> ");

			List<Node> childrenNodes = new ArrayList<>();
			for (Edge edge : edges) {
				if (edge.getOrigin().equals(node)) {
					childrenNodes.add(edge.getDestination());
				}
			}

			for (Node child : childrenNodes) {
				BasicBlock childBasicBlock = child.getBasicBlock();
				int seqIDFirstStmtChild = childBasicBlock.firstProgramStatement().getSequenceID();
				int seqIDLastStmtChild = childBasicBlock.lastProgramStatement().getSequenceID();
				System.out.print("[" + seqIDFirstStmtChild + "," + seqIDLastStmtChild + "], ");
			}
			System.out.println();
		}

	}

}
