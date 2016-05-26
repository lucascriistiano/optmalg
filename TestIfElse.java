package br.ufrn.imd;

public class TestSimple {

	public static void main(String[] args) {
		int a = 3;
		int b = 2;
		
		if (a > b) {
			a = b + 1;
		} else if (a < b) {
			b = a + 1;
		} else {
			b = a + b + 1;
		}
		System.out.println("a: " + a + ", b: " + b);
	}

}