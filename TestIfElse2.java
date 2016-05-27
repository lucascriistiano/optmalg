package br.ufrn.imd;

public class TestSimple {

	public static void main(String[] args) {
		boolean result = true;
		if (result) {
			if (true) {
				if (true) {
					if(true) {
						System.out.println("True");
					}
				}
			} else {
				if(false) {
					if(false) {
						System.out.println("False");
					}
				}
			}
		}
		System.out.println("Finished");
	}

}