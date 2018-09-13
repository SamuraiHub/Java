/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pailliercryptosystem;

import java.math.BigInteger;
import java.security.PrivateKey;

/**
 *
 * @author Aljarhi
 */
public interface PaillierPrivateKey extends PrivateKey, PaillierKey
{

        public abstract BigInteger getPrivateExponent();


}
