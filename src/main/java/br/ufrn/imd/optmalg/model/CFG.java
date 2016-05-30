package br.ufrn.imd.optmalg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.SerializationUtils;

public class CFG implements Serializable, Cloneable {

	private static final long serialVersionUID = 8327870543374459624L;
	
	private List<Node> nodes;
	private List<Edge> edges;
	
	private Node entryNode;
	private Node exitNode;

	public CFG() {
		this.nodes = new ArrayList<>();
		this.edges = new ArrayList<>();
	}

	public boolean isEmpty(){
		return nodes.isEmpty();
	}

	public void addNode(Node node) {
		this.nodes.add(node);
	}
	
	public void setEntryNode(Node entryNode) {
		this.addNode(entryNode);
		this.entryNode = entryNode;
	}
	
	public void setExitNode(Node exitNode) {
		this.addNode(exitNode);
		this.exitNode = exitNode;
	}
	
	public void createEdge(Node inNode, Node outNode) {
		Edge newEdge = new Edge(inNode, outNode);
		if (!this.edges.contains(newEdge)) {
			inNode.addChild(outNode);
			this.edges.add(newEdge);
		}
	}

	public List<Node> getNodes() {
		return this.nodes;
	}

	public List<Edge> getEdges() {
		return this.edges;
	}
	
	public List<Edge> getBackEdges() {
		List<Edge> backEdges = new ArrayList<>();
		for(Edge edge : this.edges) {
			if(edge.isBackEdge()) {
				backEdges.add(edge);
			}
		}
		return backEdges;
	}
	
	public Node getEntryNode() {
		return entryNode;
	}
	
	public Node getExitNode() {
		return exitNode;
	}

	public List<Node> getImediatePredecessors(Node node) {
		List<Node> imediatePredecessors = new ArrayList<>();
		for(Node cfgNode : nodes) {
			if(!cfgNode.equals(node) && cfgNode.getChildren().contains(node)) {
				imediatePredecessors.add(cfgNode);
			}
		}
		return imediatePredecessors;
	}

	/**
	 * Prints a generated CFG. Has complexity of O(n * m)
	 */
	public void print() {
		for (Node node : nodes) {
			System.out.print(node.toString() + " -> ");
			for (Node child : node.getChildren()) {
				System.out.print(child.toString() + " ");
			}
			System.out.println();
		}
	}
	
	@Override
	public CFG clone() {
		return SerializationUtils.clone(this);
	}

}
