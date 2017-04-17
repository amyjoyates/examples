package com.quiz;

import java.util.Arrays;

public class GenerateStringWithBits {
    // Make some variables to store stuff
    int[] arrayOfBits;
    int numberOfCombos = 0;

    // Make a constructor with an array of ints
    public GenerateStringWithBits(int n) {
        arrayOfBits = new int[n];
    }

    // Method that we will us recursively
    public int numberOfBits(int numBits) {
        //numbBits is the number of bits = width
        //if it is <= 0, then we are done processing the combination 
        if (numBits <= 0) {
            // 7) Since numBits = 0
            numberOfCombos = numberOfCombos + 1;
            System.out.println(Arrays.toString(arrayOfBits));
        } else {
            // 1) numBits=3, array[2]=0
            // 3) numBits=2, array[1]=0
            // 5) numBits=1, array[0]=0
            arrayOfBits[numBits - 1] = 0;
            
            // 2) recursively calls using numBits(3)-1 which is 2
            // 4) recursively calls using numBits(2)-1 which is 1
            // 6) recursively calls using numBits(1)-1 which is 0
            numberOfBits(numBits - 1);
            
            // 8) we come out of the recursion and we are back to numBits=3, array[2]=1 and start a new set of recursion until the end
            arrayOfBits[numBits - 1] = 1;
            numberOfBits(numBits - 1);
        }
        return numberOfCombos;
    }

}