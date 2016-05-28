package br.ufrn.imd.optmalg.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Code adapted from http://learn.yancyparedes.net/2012/03/my-graph-implementation-in-java/
 *
 */
public class Node {

	private String label;
	private BasicBlock basicBlock;
	private List<Node> children;

	protected boolean visited;
	public Integer index = null;
	public Integer lowlink = null;
	public double distance = Double.POSITIVE_INFINITY;
	public Node predecessor = null;

	public Node(String label) {
		this(label, new BasicBlock());
	}

	public Node(String label, BasicBlock basicBlock) {
		this.label = label;
		this.basicBlock = basicBlock;
		this.children = new ArrayList<>();
	}

	public void addChild(Node node) {
		this.children.add(node);
	}

	public List<Node> getChildren() {
		return this.children;
	}

	public boolean isVisited() {
		return visited;
	}

	public void visit() {
		visited = true;
	}

	public void unvisit() {
		visited = false;
	}

	public BasicBlock getBasicBlock() {
		return this.basicBlock;
	}

	@Override
	public String toString() {
		return this.label;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Node))
			return false;
		if (obj == this)
			return true;

		Node node = (Node) obj;
		return this.basicBlock.equals(node.getBasicBlock());
	}

}