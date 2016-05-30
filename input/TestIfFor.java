package br.ufrn.imd;                           		//00 []
public class TestIfFor {                         	//01 [0]
    public static void main(String[] args) {   		//02 [1]
        System.out.println("Starting");        		//03 [2]
        if(true){									//04 [3]


	        for(int i = 0; i < 100; i++) {         	//05 [4, 7]


	            System.out.println("In loop");     	//06 [5]
	            System.out.println(i);             	//07 [6]
	        }
        }

        System.out.println("Finished");        		//08 [4, 5]
    }
}