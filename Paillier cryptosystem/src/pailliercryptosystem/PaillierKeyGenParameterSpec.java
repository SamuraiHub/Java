/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pailliercryptosystem;

import java.math.BigInteger;
import java.security.spec.AlgorithmParameterSpec;

/**
 *
 * @author Aljarhi
 */
public class PaillierKeyGenParameterSpec implements AlgorithmParameterSpec
{
    private int keysize;
    private BigInteger publicKeyExponent;
    public static final BigInteger F0 = BigInteger.valueOf(3);
    public static final BigInteger F4 = BigInteger.valueOf(65537);

    public PaillierKeyGenParameterSpec(int keysize, BigInteger publicKeyExponent)
    {
        this.keysize = keysize;
        this.publicKeyExponent = publicKeyExponent;
    }

    public int getKeysize()
    {
       return keysize;
    }

    public BigInteger getPublicKeyExponent()
    {
        return publicKeyExponent;
    }
}
