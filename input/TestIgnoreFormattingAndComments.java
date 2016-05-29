package br.ufrn.imd;                            //00 []
public class TestIgnoreFormattingAndComments {  //01 [0]
	public static void main(String[] args) {    //02 [1]
		for(int i = 0; i < 100; i++) {          //03 [2, 6]


			if(i % 2 == 0) {                    //04 [3]


				continue;                       //05 [4]
			}


			System.out.println(i);              //06 [4]
		}


		boolean/*que coisa*/bool = false;       //07 [3]
		if(bool) {                              //08 [7]


			System.out.println("True");         //09 [8]


		} else {                                //10 [8]
			System.out.println("False");        //11 [10]
		}
		//AEWWW


		int value = 1;                          //12 [9, 11]
		switch(value) {                         //13 [12]


			case 1:                             //14 [13]
				System.out.println("One");      //15 [14]
				break;                          //16 [15]


			case 2:                             //17 [14]
				System.out.println("Two");      //18 [17]
				break;                          //19 [18]


			case 3:                             //20 [17]
				System.out.println("Three");    //21 [20]
				break;                          //22 [21]


			default:                            //23 [20]
				System.out.println("Unknown");  //24 [23]
				break;                          //25 [24]
		}
	}
}