package com.quiz;

import org.testng.annotations.Test;
import org.testng.Assert;

public class Part10and1 {

    @Test
    public void testApp()
    {
        // References researched when writing this class and methods
        // A. StackOverflow - had to look up how recursion worked as I figured I might need it
        
        // Write a program to generate all possible strings using ‘0’ and ‘1’ for a given width w

        // 1) Provide test cases to validate that this function is correct.
        //    Generate strings takes in the width and returns the number of combinations
        
        // Verify that generateStrings can take in a width(number of 0s and 1s)
        // -- This can be verified by doing the math, if passing in 3, then it is 2^3 = 8
        // -- 2^5 = 32 (2*2*2*2*2) = 2*2=4*2=8*2=16*2=32
        Assert.assertEquals(generateStrings(3), 8);
        Assert.assertEquals(generateStrings(5), 32);

        // 2) You realize that the number of strings to be very large as w increases and you are asked to write test cases that are fast and reasonably validate without testing for each and every string. What would you do?
        // Testing Various Scenarios - some won't compile in this program, so commented them out
        // A. Verify 0 width
        // B. Verify Negative width
        // C. Verify Empty
        // D. Verify Max Width - Cannot run this with the actual max width, as it would either run out of memory, or take too long to run, so for this I used a smaller number
        //    For D), we could also test a formula being created, we could write code to print out what the formula would be and compare it to the known formula
        // E. Verify Min Width
        // F. Verify Happy Path
        // G. Verify some negative values

        Assert.assertEquals(generateStrings(0), 1);     //A. This returns 1 - probably not right
        //Assert.assertEquals(generateStrings(-1), 1);  //B: Will throw an error
        //Assert.assertEquals(generateStrings(), 1);    //C: Can't compile
        Assert.assertEquals(generateStrings(10), 1024); //D: Not the max width, but the max that can be run given the performance (Max int in Java 2147483647)
        Assert.assertEquals(generateStrings(1), 2);     // E: Min Width is 2
        Assert.assertEquals(generateStrings(3), 8);     // F: Happy Path 2^3 = 8
        // Assert.assertEquals(generateStrings(""), 1); // G.  Verify that negative tests
        // Assert.assertEquals(generateStrings(''), 1); // G.  Verify that negative tests
        Assert.assertNotEquals(generateStrings(3), 9);  // G. Verify 2^3 is NOT 9

        // 3) For a width w, how many strings are expected to be generated?
        //    Using a Math equation where x = 2^w (Java Math.pow(x, n)) you can figure out how many combinations you should get
        //      Here we have a method that prints out the number of combinations both in int and as a double - to show where int has a max value
        possibleCombinations(1);
        possibleCombinations(8);
        possibleCombinations(16);
        possibleCombinations(32);
        possibleCombinations(256);
        possibleCombinations(1000);
    }

    static int generateStrings (int widthOfString) {
        // Instantiate the object with the width of the string (number of 0s, 1s)
        GenerateStringWithBits stringBits = new GenerateStringWithBits(widthOfString);
        // Return the number of combinations that should have been printed out
        return stringBits.numberOfBits(widthOfString);
    }
    
    static double possibleCombinations(int widthOfString) {
        // Find all possible combinations of a width for Os and 1s - Java has a method to use - pow
        // Printing out result as an int or double just to show the limitations of ints
        double numberOfCombos = Math.pow(2, widthOfString);
        System.out.println("For 0 and 1 with a width of " + widthOfString + " as an integer: " + (int)numberOfCombos);
        System.out.println("For 0 and 1 with a width of " + widthOfString + " as an double: " + numberOfCombos);
        return numberOfCombos;
    }

}