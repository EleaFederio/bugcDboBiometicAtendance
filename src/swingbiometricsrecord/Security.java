/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swingbiometricsrecord;

/**
 *
 * @author eleazar
 */
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Security{
    public String encrypt(String hashThis){
        String name = hashThis;
        String secret = "";
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(name.getBytes(), 0, name.length());
            secret = (new BigInteger(1, md.digest()).toString(16));
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return secret;
    }
}
