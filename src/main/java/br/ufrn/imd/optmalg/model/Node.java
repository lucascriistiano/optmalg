package br.ufrn.imd.optmalg.model;

/**
 * Adaptado de http://learn.yancyparedes.net/2012/03/my-graph-implementation-in-java/
 *
 */
public class Node {

	protected BasicBlock basicBlock;
	
	protected boolean visited;
	public Integer index = null;
	public Integer lowlink = null;
	public double distance = Double.POSITIVE_INFINITY;
	public Node predecessor = null;

	public Node() { }
	
	public Node(BasicBlock basicBlock) {
		this.basicBlock = basicBlock;
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
		return this.basicBlock.toString();
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