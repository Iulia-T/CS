package LaboratoryWork1;

public class VigenereCipher {
    
    public static void vigenere() {

        String key = "coffee";
        String message = "encryptthismessage";

        String encryptedText = encrypt(message, key);
        System.out.println("Encrypted:");
        System.out.println(encryptedText);

        System.out.println("Decrypted:");
        System.out.println(decrypt(encryptedText, key));
        System.out.println();
    }

    public static String encrypt (String message, String key){
        
        message = message.toLowerCase();   
        String encryptedString = "";   
          
        for (int i = 0, j = 0; i < message.length(); i++)   
        {    
            char letter = message.charAt(i);
            encryptedString += (char) (((letter - 97) + (key.charAt(j) - 97)) % 26 + 97);
            j = ++j % key.length();  
        }   

        return encryptedString;   
    }

    public static String decrypt (String encryptedText, String key) {

        encryptedText = encryptedText.toLowerCase();   
            String decryptedString = "";   
    
            for (int i = 0, j = 0; i < encryptedText.length(); i++)   
            {   
    
                char letter = encryptedText.charAt(i);
            decryptedString += (char) ((letter - key.charAt(j) + 26) % 26 + 97);
            j = ++j % key.length();

            }   
    
            return decryptedString;  
    }

}
