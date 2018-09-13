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
public class PaillierPublicKeySpec implements KeySpec, PaillierPublicKey
{

    private BigInteger modulus;
    private BigInteger publicExponent;


    public PaillierPublicKeySpec(BigInteger modulus, BigInteger publicExponent)
    {
        this.modulus = modulus;
        this.publicExponent = publicExponent;
    }

    public BigInteger getModulus()
    {
       return modulus;
    }

    public BigInteger getPublicExponent()
    {
        return publicExponent;
    }

    public String getAlgorithm() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String getFormat() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public byte[] getEncoded() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
