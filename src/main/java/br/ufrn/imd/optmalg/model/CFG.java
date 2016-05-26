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
		if(!this.edges.contains(newEdge)) {
			this.edges.add(newEdge);
		}
	}

}
