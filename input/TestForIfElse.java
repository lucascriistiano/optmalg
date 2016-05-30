package br.ufrn.imd;                           //00 []
public class TestForIfElse {                   //01 [0]
    public static void main(String[] args) {   //02 [1]
        System.out.println("Starting");        //03 [2]
        for(int i = 0; i < 100; i++) {         //04 [3, 8, 11]


            System.out.println("In loop");     //05 [4]
            if(a == 2) {                       //06 [5]


                System.out.println("Dois");    //07 [6]
                System.out.println("Dois");    //08 [7]


            } else {                           //09 [6]
                System.out.println("Outro");   //10 [9]
                System.out.println("Outro");   //11 [10]
            }
        }


        System.out.println("Finished");        //12 [4]
    }
}