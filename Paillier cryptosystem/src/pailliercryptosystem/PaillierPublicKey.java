/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pailliercryptosystem;

import java.math.BigInteger;
import java.security.PublicKey;

/**
 *
 * @author Aljarhi
 */
public interface PaillierPublicKey extends PublicKey, PaillierKey
{
    public abstract BigInteger getPublicExponent();
}
