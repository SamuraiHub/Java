/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muaz Aljarhi
 */
public class Palindrome {
    
    //removes all punctuation (except letters and numbers) of string text tehen checks of it is a palindrome.
    // takes O((string length)/2) which is equal to O(string length)
    public static boolean isTextPalindrome(String text) {
    if (text == null) {
        return false;
    }
    String chars = text.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
    int left = 0;
    int right = chars.length() - 1;
    while (left < right) {
        if (chars.charAt(left++) != chars.charAt(right--)) {
            return false;
        }
    }
    return true;
}  

private static String[] words = new String[] { "civic.", "hello", "hann!ah", "rotator$", "decaffeinated#", "Tar Rat", "dish", "madam1", "level", "fish","1211","MooN","a","lol","","RoaR","1221"};

    public static void main(String... args) {
        
        for (String word : words){
            System.out.printf("%s : %b \n", word, isTextPalindrome(word));
         }
    }
    
}
