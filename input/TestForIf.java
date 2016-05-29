package br.ufrn.imd;                                              //00 []
public class TestForIf {                                          //01 [0]
    public static void main(String[] args) {                      //02 [1]
        System.out.println("Starting");                           //03 [2]
        for(int i = 0; i < 100; i++) {                            //04 [3, 8]


            System.out.println("Running intern " + i + " loop");  //05 [4]
            for(int i = 0; i < 100; i++) {                        //06 [5, 7]


                System.out.println(i);                            //07 [6]
            }


            System.out.println("Finished " + i + " intern loop"); //08 [6]
        }


        for(int i = 0; i < 100; i++) {                            //09 [4, 13]


            System.out.println("Running intern " + i + " loop");  //10 [9]
            for(int i = 0; i < 100; i++) {                        //11 [10, 12]
            
            
                System.out.println(i);                            //12 [11]
            }
            
            
            System.out.println("Finished " + i + " intern loop"); //13 [11]
        }


        if (true) {                                               //14 [9]


            a = b + 1;                                            //15 [14]
        }


        System.out.println("Finished");                           //16 [14, 15]
    }
}