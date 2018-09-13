/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
/**
 * Algorithm to find the pairs making the K complementary in O(n) complexity
 * 
 * @author Muaz Aljarhi
 * @see KComplementaryTest.java - a JUnit test for this class
 */

public class KComplementary {
    
    private Set pairs;
    
    public static void main(String[] args) {
        KComplementary kcomp = new KComplementary();
        int[] numbers = new int[]{7, 1, 5, 6, 9, 3, 11, -1};
        int[][] pairs = kcomp.getKComplementaryPairs(10, numbers);
        
        for (int[] thePairs : pairs) {
            System.out.println(" Pairs are "+thePairs[0] + " and " + thePairs[1]);
        }
    }
    
    public KComplementary() {
        this.pairs = new HashSet();
    }
    
    /**
     * An algorithm to find the pairs from the given array that would sum up the given K
     * 
     * Algorithm takes o(n). First it will add all numbers to the set (pairs) for fast searching then 
     * it will loop through all the numbers searching for the other pair in the set and (if found) add it to the output  
     * @param K The sum of the complementary pairs
     * @param Numbers An array containing the numbers from which the complementary pairs would be extracted.
     * @return A double 2 sided array that contains all of the different complementary pairs form the input Numbers
     * that would sum up to K (including duplicates and reverses of the same pairs). 
     */
    public int[][] getKComplementaryPairs(int K, int[] Numbers) {
        
        
         //An arraylist is used with maximin capacity the length of the Numbers so, adding an item to the list will always cost O(1). 
         
        List<int[]> complementaryPairs = new ArrayList<>(Numbers.length);
        
        //First fill up the pairs with the complementary numbers
        for (int number : Numbers) { //O(n) complexity
            this.pairs.add(number);
        }
        
        //then filter out the pairs that don't have corresponding complementary number

        for (int number : Numbers) { //O(n) complexity 
            int complementary = K - number;
            //check if this key exists in the pairs
            if ( this.pairs.contains(complementary) ) 
            {
                int[] pair = new int[2]; 
                pair[0] = number;
                pair[1] = complementary;
                complementaryPairs.add(pair);
            }
        }
        
    
        return complementaryPairs.toArray(new int[0][0]);
    }
}
