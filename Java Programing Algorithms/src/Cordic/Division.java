/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cordic;

/**
 *
 * @author Aljarhi
 */
public class Division {
    
    public static double divide(int y, int x)
    {
        String bx  = Integer.toBinaryString(x);
        
        while(bx.length() < 32) { bx = '0' + bx;}
        
        int xh = Integer.parseInt(bx.substring(0, 25), 2) * (int)Math.pow(2, 7);
        int xl = Integer.parseInt(bx.substring(25), 2);
        
        
        
        
        double r = ((long)y * (xh - xl)) / (Math.pow(xh, 2));
        
        return r;
    }
    
    public static void main(String[] args)
    {
        int y = 468466481;
        int x = 4687468;
        
        System.out.println("y/x using hung: " + divide(y, x));
        System.out.println("y/x normal: " + y/(double)x);
        
    }
}
