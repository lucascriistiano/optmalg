package br.ufrn.imd;                                    //00 []
public class TestIfElse {                               //01 [0]
    public static void main(String[] args) {            //02 [1]
        int a = 3;                                      //03 [2]
        int b = 2;                                      //04 [3]
        if (a > b) {                                    //05 [4]


            a = b + 1;                                  //06 [5]


        } else if (a < b) {                             //07 [5]


            b = a + 1;                                  //08 [7]


        } else {                                        //09 [7]
            b = a + b + 1;                              //10 [9]
        }


        System.out.println("a: " + a + ", b: " + b);    //11 [6,8,10]
    }
}

//ENTRY -> 0,5
//0,5 -> 6
//0,5 -> 7
//7 -> 8
//7 -> 9
//6 -> 11
//8 -> 11
//10 -> 11
//11 -> EXIT