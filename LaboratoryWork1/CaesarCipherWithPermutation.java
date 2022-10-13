package LaboratoryWork1;

public class CaesarCipherWithPermutation {

    public static final String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public static void caesar() {

        int key = 5;
        String message = "encryptthismessage";
        String alphabet1 = createAlphabet();

        String encryptedText = encrypt(message, key, alphabet1);
        System.out.println("Encrypted:");
        System.out.println(encryptedText);

        System.out.println("Decrypted:");
        System.out.println(decrypt(encryptedText, key, alphabet1));
        System.out.println();
    }

    public static String createAlphabet () {

        String keyword = "coffee";
        String alphabet1 = "";

        for (int i = 0; i < keyword.length(); i++){
            alphabet1 = addLetters(alphabet1, keyword.charAt(i));
        }

        for (int i = 0; i < alphabet.length(); i++){
            alphabet1 = addLetters(alphabet1, alphabet.charAt(i));
        }
        
        return alphabet1;
    }

    public static String addLetters (String alphabet1, char letter) {
        
        if (!alphabet1.contains(Character.toString(letter)))
             alphabet1 += Character.toString(letter);
        
             return alphabet1;
    }

    public static String encrypt (String message, int key, String alphabet1) {

        message = message.toLowerCase();   
        String encryptedString = "";   
          
        for (int i = 0; i < message.length(); i++){    
            int pos = alphabet1.indexOf(message.charAt(i));   
            int encryptPos = (key + pos) % 26;   
            char encryptChar = alphabet1.charAt(encryptPos);   
              
            encryptedString += encryptChar;   
        }   

        return encryptedString;   
    }

    public static String decrypt (String encryptedText, int key, String alphabet1){

        encryptedText = encryptedText.toLowerCase();   
            String decryptedString = "";   
    
            for (int i = 0; i < encryptedText.length(); i++)   
            {   
    
                int pos = alphabet1.indexOf(encryptedText.charAt(i));   
      
                int decryptPos = (pos - key) % 26;   
    
                if (decryptPos < 0){   
                    decryptPos = alphabet1.length() + decryptPos;   
                }   
                char decryptChar = alphabet1.charAt(decryptPos);   
    
                decryptedString += decryptChar;   
            }   
    
            return decryptedString;   
    }
}
