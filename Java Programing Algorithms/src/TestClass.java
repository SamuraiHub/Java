
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Muaz Aljarhi
 */
public class TestClass {
    
    public static void main(String[] args)
    {
        String s1 = " qwert asdfg zxcvb ";
       
        long t1 = System.nanoTime();
        for(int i = 0; i < 10000; i++)
        {  
            s1.trim();
        }
        long t2 = System.nanoTime();
        
        System.out.println(s1.trim());
        System.out.println("T1: "+((t2-t1)/1000.0));
        
        List<Integer> at = new ArrayList<>();
        for(int i = 0; i < 5000; i++)
        {
            at.add(i);
        }
        
        t1 = System.nanoTime();
        for(int i = 0; i < 10000; i++)
        {
            s1.substring(1, s1.length()-1);
        }
        t2 = System.nanoTime();
        
        System.out.println(s1.substring(1, s1.length()-1));
        System.out.println("T2: "+((t2-t1)/1000.0));
    }
}
