package br.ufrn.imd;                            //00 []
public class TestIgnoreFormattingAndComments {  //01 [0]
	public static void main(String[] args) {    //02 [1]
		int value = 1;                          //03 [2]
		switch(value) {                         //04 [3]


			case 1:                             //05 [4]
				System.out.println("One");      //06 [5]
				break;                          //07 [6]


			case 2:                             //08 [5]
				System.out.println("Two");      //09 [8]
				break;                          //10 [9]


			case 3:                             //11 [8]
				System.out.println("Three");    //12 [11]
				break;                          //13 [12]


			default:                            //14 [11]
				System.out.println("Unknown");  //15 [14]
				break;                          //16 [15]
		}
		
		
		System.out.println("iiiiiw!");          //17 [7,10,13,16]
	}
}