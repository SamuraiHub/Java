/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pailliercryptosystem;

import java.math.BigInteger;

/**
 *
 * @author Aljarhi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        Paillier p = new Paillier(16);

        p.generateKeys();

        BigInteger b = BigInteger.valueOf(5034);

        System.out.println("b: " + b);

        BigInteger c = p.encrypt(b);

         System.out.println("c: " + c);

        System.out.println("b: " + p.decrypt(c));
    }


}
