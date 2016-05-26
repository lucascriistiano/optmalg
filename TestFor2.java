package br.ufrn.imd;

public class TestFor2 {

	public static void main(String[] args) {
		System.out.println("Starting");
		for(int i = 0; i < 100; i++) {
			System.out.println("Running intern " + i + " loop");
			for(int i = 0; i < 100; i++) {
				System.out.println(i);
			}
			System.out.println("Finished " + i + " intern loop");
		}
		System.out.println("Finished");
	}

}