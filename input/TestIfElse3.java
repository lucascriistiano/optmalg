package br.ufrn.imd;                           //00 []

public class TestIfElse3 {                     //01 [0]

    public static void main(String[] args) {   //02 [1]
        System.out.println("Starting");        //03 [2]
        int a, b;                              //04 [3]
        if(a == 1) {                           //05 [4]
            System.out.println("Um");          //06 [5]
            System.out.println("Um");          //07 [6]
        }

        if(a == 2) {                           //08 [5,7]
            System.out.println("Dois");        //09 [8]
            System.out.println("Dois");        //10 [9]
        } else {                               //11 [8]
            System.out.println("Outro");       //12 [11]
            System.out.println("Outro");       //13 [12]
        }

        if(b == 3) {                           //14 [10, 13]
            System.out.println("Tres");        //15 [14]
            System.out.println("Tres");        //16 [15]
        } else if(b == 4) {                    //17 [14]
            System.out.println("Quatro");      //18 [17]
            System.out.println("Quatro");      //19 [18]
        } else if(b == 5) {                    //20 [17]
            System.out.println("Cinco");       //21 [20]
            System.out.println("Cinco");       //22 [21]
        } else {                               //23 [20]
            System.out.println("Outro");       //24 [23]
            System.out.println("Outro");       //25 [24]
        }

        System.out.println("Finished");        //26 [16, 19, 22, 25]
    }

}