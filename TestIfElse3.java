package br.ufrn.imd;

public class TestIfElse3 {

	public static void main(String[] args) {
		System.out.println("Starting");
		int a, b;
		if(a == 1) {
			System.out.println("Um");
			System.out.println("Um");
		}
		
		if(a == 2) {
			System.out.println("Dois");
			System.out.println("Dois");
		} else {
			System.out.println("Outro");
			System.out.println("Outro");
		}
		
		if(b == 3) {
			System.out.println("Tres");
			System.out.println("Tres");
		} else if(b == 4) {
			System.out.println("Quatro");
			System.out.println("Quatro");
		} else if(b == 5) {
			System.out.println("Cinco");
			System.out.println("Cinco");
		} else {
			System.out.println("Outro");
			System.out.println("Outro");
		}
		
		System.out.println("Finished");
	}

}