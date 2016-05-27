package br.ufrn.imd;                           //00

public class TestFor {                         //01

    public static void main(String[] args) {   //02
        System.out.println("Starting");        //03

        for(int i = 0; i < 100; i++) {         //04
            System.out.println("In loop");     //05
            System.out.println(i);             //06
        }

        System.out.println("Finished");        //07
    }
}