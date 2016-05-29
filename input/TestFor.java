package br.ufrn.imd;                           //00 []
public class TestFor {                         //01 [0]
    public static void main(String[] args) {   //02 [1]
        System.out.println("Starting");        //03 [2]
        for(int i = 0; i < 100; i++) {         //04 [3, 7]


            System.out.println("In loop");     //05 [4]
            System.out.println(i);             //06 [5]
        }


        System.out.println("Finished");        //07 [4]
    }
}