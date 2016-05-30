package br.ufrn.imd;                            //00 []
public class TestIgnoreFormattingAndComments {  //01 [0]
	public static void main(String[] args) {    //02 [1]
		for(int i = 0; i < 100; i++) {          //03 [2, 6]


			if(i % 2 == 0) {                    //04 [3]


				System.out.println("Teste");    //05 [4]
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
	}
}

//ENTRY -> 0,3
//0,3 -> 4
//4 -> 5
//4 -> 6
//5 -> 6
//6 -> 0,3
//0,3 -> 7,8
//7,8 -> 9
//7,8 -> 10,11
//9 -> EXIT
//10,11 -> EXIT