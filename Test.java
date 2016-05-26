package br.ufrn.imd;

public class Test {

	public static void main(String[] args) {
		for(int i = 0; i < 100; i++) {
			if(i % 2 == 0) {
				continue;
			}
			
			System.out.println(i);
		}
		
		boolean bool = false;
		if(bool) {
			System.out.println("True");
		} else {
			System.out.println("False");
		}
		
		int value = 1;
		switch(value) {
			case 1:
				System.out.println("One");
				break;
				
			case 2:
				System.out.println("Two");
				break;
				
			case 3:
				System.out.println("Three");
				break;
				
			default:
				System.out.println("Unknown value");
				break;
		}
		
	}

}