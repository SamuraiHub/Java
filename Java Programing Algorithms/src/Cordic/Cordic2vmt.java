/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Cordic;

/**
 *
 * @author Aljarhi
 */
public class Cordic2vmt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //1.2074970677630725
        double[] X = new double[2]; 
        double[] Y = new double[2]; 
        double[] z = new double[2];
        X[0] = ((1.0E13)+0.25)/Math.pow(2, 29+14); 
        Y[0] = ((1.0E13)-0.25)/Math.pow(2, 29+14); 
        z[1] = 0;
        X[1] = X[0];//((Math.pow(2, 52)) * X[0]);
        Y[1] = Y[0];//((Math.pow(2, 52)) * Y[0]);
        long mx = (long) ((1.2074970677630725 * Math.pow(2, 52)) * X[0]); 
        long my = (long) ((1.2074970677630725 * Math.pow(2, 52)) * Y[0]);

        long X1 = mx - my;
            //System.out.println(X1.longValue()/(Math.pow(2, 52-(Eref - 1023))));
            int Enew = (Long.numberOfLeadingZeros(X1) - Long.numberOfLeadingZeros(mx)) == 1 ? 1 :
                    (Long.numberOfLeadingZeros(X1) - Long.numberOfLeadingZeros(mx)) - 1;
           
        X1 = X1 << Enew;
        System.out.println(X1); 
        System.out.println("Enew: " + Enew);
        
        X[0] = (1.2074970677630725* ((X[1] + Y[1]) + ((X[1] - Y[1])*Math.pow(2, Enew))));
        Y[0] = (1.2074970677630725* ((Y[1] + X[1]) - ((X[1] - Y[1])*Math.pow(2, Enew))));
        System.out.println("X: "+(X[0] * Math.pow(2, 52)));
        System.out.println(Y[0] * Math.pow(2, 52));    
 
        int i = 1; boolean t = false; int s = Y[0] < 0 ? 1: -1;
        
        while(i < 100)
        {
           X[1] = X[0] + (s*Y[0]*Math.pow(2, -i));
           Y[1] = Y[0] + (s*X[0]*Math.pow(2, -i));
           X[0] = X[1]; Y[0] = Y[1];
           System.out.println("X: "+X[0]*Math.pow(2, 29+14));
           System.out.println(Y[0]*Math.pow(2, 29+14));
           z[1] = z[1] - (s * atanh(Math.pow(2, -i))); 
            
            if((i == 4 || i == 13 || i == 40) && !t)
            {
                t = true;
            }
            else 
            {
               i++; t = false;
            }
            
            s = Y[0] < 0 ? 1: -1;
        }
        
        z[1] = 2*(z[1] + (Enew * Math.log(2)/2));
        X[0] = X[0] * Math.pow(2, (14+29) - ((Enew+2)/2));
        
       System.out.println("X: "+1.0E13);
       System.out.println(X[0]);
       System.out.println(Math.sqrt(1.0E13));
       System.out.println(Math.sqrt(1.0E13) - ((X[0])));
    }
    
    public static double atanh(double a) 
    {  
       return 0.5 * Math.log((1 + a) / (1 - a));
    }
}
