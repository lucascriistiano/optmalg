package br.ufrn.imd;                                              //00 []
public class TestFor2 {                                           //01 [0]
    public static void main(String[] args) {                      //02 [1]
        System.out.println("Starting");                           //03 [2]
        for(int i = 0; i < 100; i++) {                            //04 [3, 6]


            System.out.println("Running intern " + i + " loop");  //05 [4]
            for(int i = 0; i < 100; i++) {                        //06 [5, 7]


                System.out.println(i);                            //07 [6]
            }


            System.out.println("Finished " + i + " intern loop"); //08 [6]
        }


        System.out.println("Finished");                           //09 [4]
    }
}