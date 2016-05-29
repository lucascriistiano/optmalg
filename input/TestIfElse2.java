package br.ufrn.imd;                                         //00 []
public class TestIfElse2 {                                   //01 [0]
    public static void main(String[] args) {                 //02 [1]
        boolean result = true;                               //03 [2]
        if (result) {                                        //04 [3]


            if (true) {                                      //05 [4]


                if (true) {                                  //06 [5]


                    if(true) {                               //07 [6]


                        System.out.println("True");          //08 [7]
                    }
                }


            } else {                                         //09 [5]
                if(false) {                                  //10 [9]


                    if(false) {                              //11 [10]


                        System.out.println("False");         //12 [11]
                    }
                }
            }
        }


        System.out.println("Finished");                      //13 [4, 6, 7, 8, 10, 11, 12]
    }

}