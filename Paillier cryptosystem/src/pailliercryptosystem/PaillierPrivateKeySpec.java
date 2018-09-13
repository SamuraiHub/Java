/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pailliercryptosystem;

import java.math.BigInteger;
import java.security.spec.KeySpec;

/**
 *
 * @author Aljarhi
 */
public class PaillierPrivateKeySpec implements KeySpec, PaillierPrivateKey
{

    private BigInteger modulus;
    private BigInteger privateExponent;


    public PaillierPrivateKeySpec(BigInteger modulus, BigInteger privateExponent)
    {
        this.modulus = modulus;
        this.privateExponent = privateExponent;
    }

    public BigInteger getModulus()
    {
       return modulus;
    }

    public BigInteger getPrivateExponent()
    {
        return privateExponent;
    }

    public String getAlgorithm()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getFormat()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public byte[] getEncoded()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
