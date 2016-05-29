package br.ufrn.imd.optmalg.model.dtree;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.optmalg.model.Node;

public class DTreeNode {

	private Node cfgNode;
	private List<DTreeNode> children;
	
	public DTreeNode(Node cfgNode) {
		this.cfgNode = cfgNode;
		this.children = new ArrayList<>();
	}
	
	public void addChild(DTreeNode child) {
		this.children.add(child);
	}
	
	public Node getCfgNode() {
		return cfgNode;
	}
	
	public List<DTreeNode> getChildren() {
		return children;
	}
	
	
}
