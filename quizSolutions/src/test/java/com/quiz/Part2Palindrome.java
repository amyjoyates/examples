package com.quiz;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashSet;

public class Part2Palindrome {

    @Test
    public void VerifyingPalindromes()
    {
        /*
         *  References researched when writing this class and methods
         *  A. https://en.wiktionary.org/wiki/Appendix:English_palindromes
         *  B. StackOverflow - had some issues with loop within a loop
         *  C. https://www.quora.com/What-is-the-most-efficient-algorithm-to-find-the-longest-palindrome-in-a-string
         *  D. http://docs.aws.amazon.com/machine-learning/latest/dg/when-to-use-machine-learning.html
        */

        /* 
         * 1) Write a program that takes a string and returns true if it’s a palindrome 
         *    (do not use string reverse function).
         */
        System.out.println("--Verifying Palindromes--");

        Assert.assertTrue(checkToSeeIfPalindrome("abba"));
        Assert.assertTrue(checkToSeeIfPalindrome("tattarrattat"));

        System.out.println("--End Verifying Palindromes--");

        /* 
         * 2) Provide all test cases for this palindrome use case
         *    Standard Test Cases:
         *    Happy Path
         *    Negative Tests
         *    Boundaries
         *
         *    Machine Learning Approach:
         *    If we were looking at this with machine learning in mind, the following testcases would be considered labeled.
         *       Labeled Data = data that we know the target answer
         *    You need to collect the right kind of positive and negative data to help teach the ML
         *    Once you have that, and any other attributes that could help the ML learn, then you can start figuring
         *       out the algorithm you are going to need in order to find all of the possible test cases (happy path)
         *    
         */
        System.out.println("--Various test cases being executed, will fail if the assert is not correct--");
        Assert.assertTrue(checkToSeeIfPalindrome(""));
        Assert.assertTrue(checkToSeeIfPalindrome("tattarrattat"));
        Assert.assertFalse(checkToSeeIfPalindrome("amy")); // Verify that is not a palindrome
        Assert.assertTrue(checkToSeeIfPalindrome("racecar")); // Verify simple palindrome
        Assert.assertFalse(checkToSeeIfPalindrome("Don't nod")); // Verify using punctuation that doesn't match - ' makes this sentence false since there isn't a corresponding one in the pattern
        Assert.assertTrue(checkToSeeIfPalindrome("A's s'A")); // Verify using punctuation that does match
        Assert.assertFalse(checkToSeeIfPalindrome("Was it a cat I saw")); // Verify spacing that doesn't match
        Assert.assertTrue(checkToSeeIfPalindrome("Step on no pets")); // Verify spacing that does match
        Assert.assertTrue(checkToSeeIfPalindrome("Repaper")); // Verify palindrome method not case sensitive
        Assert.assertFalse(checkToSeeIfPalindrome("-1232323baby")); // Verify method accepts numbers, but this case is not true
        Assert.assertTrue(checkToSeeIfPalindrome("-11-")); // Verify numbers and dashes when placed in the correct order is a palindrome
        Assert.assertFalse(checkToSeeIfPalindrome("No 'x' in 'Nixon'")); // This should fail because of the placement of the ' and the spaces
        Assert.assertFalse(checkToSeeIfPalindrome("No x in Nixon")); // This should fail because of the spacing
        Assert.assertTrue(checkToSeeIfPalindrome("No devil lived on")); // This will pass because spacing and letters all match

        System.out.println("--End Various test cases being executed--");

        /*
         * 3) For a width w, how many palindromes are expected to be generated?
         *    NOTE: Single chars/spaces are being considered palindromes 
         *    NOTE: This does not rearrange the letters to make new palindromes - goes from left to right
         * 
         * Using a standard approach, looping through each char that is part of the string, and comparing it
         *    to the opposite end, gives us the various combinations possible for a given string.
         *    
         * However, if we want to find all of the strings and their possible combinations, it would be very hard and performance heavy.
         * 
         * But with machine learning, you can setup an algorithm to help you solving this problem.  You again have target
         *     data and known outcomes to use when finding the patterns
        */

        System.out.println("--Verify how many palindromes are expected to be generated--");

        Assert.assertEquals(findPalindromeCombinations("abba"), 4);
        Assert.assertEquals(findPalindromeCombinations("abcbaaabbaa"), 11);
        Assert.assertEquals(findPalindromeCombinations("sdsdddss"), 8);
        Assert.assertEquals(findPalindromeCombinations("abcdefghijklmnopqrstuvwxyz"), 26); // single chars are considered palindromes in this case
        Assert.assertEquals(findPalindromeCombinations("No devil lived on"), 17); // this takes the spaces into consideration and it counts as 1

        System.out.println("--End Verify how many palindromes are expected to be generated--");
    }

    static boolean checkToSeeIfPalindrome(String palindrome) {
        String palindromeLowerCase = palindrome.toLowerCase();
        // The loop length is half of the total string - if "abba", then length=2
        for (int i=0; i < (palindrome.length()/2); i++) {
            // 1) "abba".charAt(0) != "abba".charAt(3) = false, they are equal
            // 2) it's equal, so keep checking (if stmt is only true when the values don't match
            // 3) "abba".charAt(1) != "anna".charAt(2) = false, they are equal
            // 4) Get out of the loop, because we have hit 2 and therefore "abba" is a palindrome
            if (palindromeLowerCase.charAt(i) != palindromeLowerCase.charAt(palindrome.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    static int findPalindromeCombinations(String stringValues) {
        System.out.println("**Starting to find palindromes for: " + stringValues + " ***");
        // When you use a HashSet to store values, it will automatically have uniqueness
        HashSet<Object> listOfUniquepalindromes = new HashSet<>();

        // Looping through the length of the string
        for(int i = 0; i <= stringValues.length(); i++) {
            // 1) "abba" i=0, length = 4
            for(int j = i; j < stringValues.length(); j++) {
                // 2) "abba" j=0 (because i=0), length=4
                // We need to make sure that the combination is an actual palindrome, if not, keep going
                // 3) "abba".substring(0, 1) = "a" = true
                // 5) "abba".substring(0, 2) = "ab" = false
                // 6) "abba".substring(0, 3) = "abb" = false
                // 7) "abba".substring(0, 4) = "abba" = true
                // 8) "abba".substring(1, 2) = "b" = true
                // 9) "abba".substring(1, 3) = "bb" = true
                // 10) "abba".substring(1, 4) = "bba" = false
                // 12) "abba".substring(2, 3) = "b" = true (not unique)
                // 13) "abba".substring(2, 4) = "ba" = false
                // 14) "abba".substring(3, 4) = "a" = true (not unique)
                System.out.println("*:" + stringValues.substring(i, j + 1));
                if(checkToSeeIfPalindrome(stringValues.substring(i, j + 1))) {
                    // 4) Yes, "a" is a palindrome, ETC
                    listOfUniquepalindromes.add(stringValues.substring(i, j + 1));
                }
            }
        }

        System.out.println(listOfUniquepalindromes);
        System.out.println("**End**");

        return listOfUniquepalindromes.size();
    }
}
