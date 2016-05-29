package br.ufrn.imd.optmalg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Code adapted from http://learn.yancyparedes.net/2012/03/my-graph-implementation-in-java/
 *
 */
public class Node implements Serializable {

	private static final long serialVersionUID = 2615190811533950656L;
	
	private String label;
	private BasicBlock basicBlock;
	private List<Node> children;
	private List<Node> dominators;

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
		this.dominators = new ArrayList<>();
	}

	public void addChild(Node node) {
		this.children.add(node);
	}
	
	public void setDominators(List<Node> dominators) {
		this.dominators = dominators;
	}
	
	public void addDominator(Node node) {
		this.dominators.add(node);
	}
	
	public void removeDominator(Node node) {
		this.dominators.remove(node);
	}

	public String getLabel() {
		return this.label;
	}
	
	public BasicBlock getBasicBlock() {
		return this.basicBlock;
	}
	
	public List<Node> getChildren() {
		return this.children;
	}
	
	public List<Node> getDominators() {
		return dominators;
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

	@Override
	public String toString() {
		return "(" + this.label + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((basicBlock == null) ? 0 : basicBlock.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (basicBlock == null) {
			if (other.basicBlock != null)
				return false;
		} else if (!basicBlock.equals(other.basicBlock))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}

}