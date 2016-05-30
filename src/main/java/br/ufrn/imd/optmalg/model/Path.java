package br.ufrn.imd.optmalg.model;

import java.util.ArrayList;
import java.util.List;

public class Path {

	private List<Node> nodes;

	public Path(List<Node> path) {
		this.nodes = new ArrayList<>(path);
	}
	
	public List<Node> getNodes() {
		return this.nodes;
	}

	public void print() {
		for (int i = 0; i < this.nodes.size(); i++) {
			System.out.print(this.nodes.get(i));
			if (i < this.nodes.size() - 1) {
				System.out.print(" --> ");
			}
		}
		System.out.println();
	}

}
