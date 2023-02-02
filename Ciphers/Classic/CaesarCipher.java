package LaboratoryWork1;

public class CaesarCipher{

    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public static void caesar() {

        int key = 5;
        String message = "encryptthismessage";

        String encryptedText = encrypt(message, key);
        System.out.println("Encrypted:");
        System.out.println(encryptedText);

        System.out.println("Decrypted:");
        System.out.println(decrypt(encryptedText, key));
        System.out.println();
    }

    public static String encrypt (String message, int key) {

        message = message.toLowerCase();   
        String encryptedString = "";   
          
        for (int i = 0; i < message.length(); i++)   
        {    
            int pos = alphabet.indexOf(message.charAt(i));   
            int encryptPos = (key + pos) % 26;   
            char encryptChar = alphabet.charAt(encryptPos);   
              
            encryptedString += encryptChar;   
        }   

        return encryptedString;   
    }

    public static String decrypt (String encryptedText, int key){

        encryptedText = encryptedText.toLowerCase();   
            String decryptedString = "";   
    
            for (int i = 0; i < encryptedText.length(); i++)   
            {   
    
                int pos = alphabet.indexOf(encryptedText.charAt(i));   
      
                int decryptPos = (pos - key) % 26;   
    
                if (decryptPos < 0){   
                    decryptPos = alphabet.length() + decryptPos;   
                }   
                char decryptChar = alphabet.charAt(decryptPos);   
    
                decryptedString += decryptChar;   
            }   
    
            return decryptedString;   
    }
    
}
