package br.ufrn.imd.optmalg.model.dtree;

public class DTree {

	private DTreeNode root;
	
	public DTree() {
	}
	
	public void setRoot(DTreeNode root) {
		this.root = root;
	}
	
	public DTreeNode getRoot() {
		return root;
	}
	
	private void printNode(int level, DTreeNode node) {
		System.out.print(level + " |");
		for(int i = 0; i < level; i++) {
			System.out.print("----");
		}
		System.out.println(node.getCfgNode());
		for(DTreeNode childNode : node.getChildren()) {
			printNode(level+1, childNode);
		}
	}
	
	public void print() {
		printNode(0, root);
	}
	
}
