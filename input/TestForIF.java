package br.ufrn.imd;                                              //00

public class TestFor2 {                                           //01

    public static void main(String[] args) {                      //02
        System.out.println("Starting");                           //03
        for(int i = 0; i < 100; i++) {                            //04
            System.out.println("Running intern " + i + " loop");  //05
            for(int i = 0; i < 100; i++) {                        //06
                System.out.println(i);                            //07
            }
            System.out.println("Finished " + i + " intern loop"); //08
        }
        for(int i = 0; i < 100; i++) {                            //09 [04]
            System.out.println("Running intern " + i + " loop");  //10 [09]
            for(int i = 0; i < 100; i++) {                        //11 [10]
                System.out.println(i);                            //12 [11]
            }
            System.out.println("Finished " + i + " intern loop"); //13 [11]
        }
        if (true) {                                     //14 [09]
            a = b + 1;                                  //15 [14]
        }
    }
}