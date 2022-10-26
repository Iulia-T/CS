# Laboratory work №1. Intro to Cryptography. Classical ciphers. 

### Course: Cryptography & Security
### Author: Iulia Tarus, FAF-202

## Objectives:

1. Get familiar with the basics of cryptography and classical ciphers.

2. Implement 4 types of the classical ciphers:
    - Caesar cipher with one key used for substitution,
    - Caesar cipher with two keys used for substitution,
    - Vigenere cipher,
    - Playfair cipher,


3. Structure the project in methods/classes/packages as neeeded.


## Implementation description

Each cipher was inplemented in a dedicated class, which contains ```encrypt``` and ```decrypt``` functions.


#### Caesar cipher
This is the simplest cipher to implement. Each letter of a given text is replaced by a letter with a fixed number of positions down the alphabet.
It is based on 2 methods: ```encrypted```  and ```decrypted``` , which take as argument a plain text and a key for permutation. It traverses input string one character at a time. Depending on the encryption or decryption process, each character is transformed as per the rule.
```
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
```

#### Caesar cipher with keyword
This cipher is a modification of classical Caesar Cipher, where a keyword is used as the key, and it determines the letter matchings of the cipher alphabet to the plain alphabet. Repeats of letters in the word are removed, then the cipher alphabet is generated with the keyword matching to A, B, C, etc. until the keyword is used up, whereupon the rest of the ciphertext letters are used in alphabetical order, excluding those already used in the key. After obtaining a new alphabet, each letter of a given text is replaced by a letter with a fixed number of positions down the alphabet. 
It is based on 2 methods: ```encrypted```  and ```decrypted``` , which take as argument a plain text and a key for permutation and the new alphabet.
```
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
```

#### Vigene cipher
Uses a simple form of polyalphabetic substitution. The encryption of the original text is done using the Vigenère square. The table consists of the alphabets written out 26 times in different rows, each alphabet shifted cyclically to the left compared to the previous alphabet, corresponding to the 26 possible Caesar Ciphers. At different points in the encryption process, the cipher uses a different alphabet from one of the rows. The alphabet used at each point depends on a repeating keyword.
```
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
```
#### Playfair cipher
The Playfair cipher was the first practical digraph substitution cipher. Here the algorithm consists of 2 steps: 
- generate the key Square(5×5): 
The key square is a 5×5 grid of alphabets that acts as the key for encrypting the plaintext. Each of the 25 alphabets must be unique and one letter of the alphabet (usually J) is omitted from the table (as the table can hold only 25 alphabets). If the plaintext contains J, then it is replaced by I. 
```
    private static void createCipherTable(String key) {
        String concatKeyWithMessage = key + "abcdefghijklmnopqrstuvwxyz";
        concatKeyWithMessage.replace(Character.toString('j'), "");
        charTable = new char[5][5];
        positions = new int[5][5];

        int length = concatKeyWithMessage.length();
        for (int i = 0, count = 0; i < length; i++) {
            char letter = concatKeyWithMessage.charAt(i);
            int firstOccurance = concatKeyWithMessage.indexOf(letter);

            if (firstOccurance == i) {
                charTable[count/5][count%5] = letter;
                positions[count/5][count%5] = letter;
                count++;
            }
        }

        System.out.println();

        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                System.out.print(charTable[row][col] + "  ");
            }
            System.out.println();
        }
    }
```
- Algorithm to encrypt the plain text: 
The plaintext is split into pairs of two letters (digraphs). If there is an odd number of letters, a Z is added to the last letter. 

```
    private static String encrypt(StringBuilder message, int direction) {
        int firstLetterRow = 0;
        int secondLetterRow = 0;
        int firstLetterCol = 0;
        int secondLetterCol = 0;

        int length = message.length();
        for (int i = 0; i < length; i += 2) {
            char firstLetter = message.charAt(i);
            char secondLetter = message.charAt(i + 1);

            for (int row = 0; row < positions.length; row++) {
                for (int col = 0; col < positions.length; col++) {
                    if (firstLetter == positions[row][col]) {
                        firstLetterRow = row;
                        firstLetterCol = col;
                    } else if (secondLetter == positions[row][col]) {
                        secondLetterRow = row;
                        secondLetterCol = col;
                    }
                }
            }

            if (firstLetterRow == secondLetterRow) {
                firstLetterCol = (firstLetterCol + direction) % 5;
                secondLetterCol = (secondLetterCol + direction) % 5;

            } else if (firstLetterCol == secondLetterCol) {
                firstLetterRow = (firstLetterRow + direction) % 5;
                secondLetterRow = (secondLetterRow + direction) % 5;

            } else {
                int temp = firstLetterCol;
                firstLetterCol = secondLetterCol;
                secondLetterCol = temp;
            }

            message.setCharAt(i, charTable[firstLetterRow][firstLetterCol]);
            message.setCharAt(i + 1, charTable[secondLetterRow][secondLetterCol]);
        }
        return message.toString();
    }
}
```

## Conclusions
After performing this laboratory work, I got familiar with the classical ciphers (Caesar (with one and two keys), Vigenere and Playfair) and their implementation in Java. Although there are a lot of more efficient and complex ciphers used nowadays to encrypt information, it is useful to get to know how the basic ciphers perform, in order to understand better how more conmplex ones perform.