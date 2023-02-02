package Asymmetrical;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

public class rsa {
    
    public static void main(String[] args) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.generateKeyPair();
        PublicKey publicKey = kp.getPublic();
        PrivateKey privateKey = kp.getPrivate();
    
        String message = "596F7572214C6970";
        Cipher cipher = Cipher.getInstance("RSA");
    
        // encryption
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        BigInteger encryptedMessage = new BigInteger(encryptedBytes);
        System.out.println("Encrypted message: " + encryptedMessage);
    
        // decryption
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedMessage.toByteArray());
        String decryptedMessage = new String(decryptedBytes);
        System.out.println("Decrypted message: " + decryptedMessage);
      }
      
}
