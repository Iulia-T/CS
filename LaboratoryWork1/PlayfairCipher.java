package LaboratoryWork1;

public class PlayfairCipher{

    private static char[][] charTable;
    private static int[][] positions;

    public static void playfair() {

        String message = "encryptthismessage";
        String key = "marius";

        createCipherTable(key);
        String encode = insertX(message.replace ('j', 'i'));

        System.out.println("Encrypted:");
        System.out.println(encode);

        System.out.println("Decrypted:");
        System.out.println(decrypt(encode));
        System.out.println();
    }

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

    private static String insertX(String message) {
        StringBuilder sb = new StringBuilder(message);

        for (int i = 0; i < sb.length(); i += 2) {

            if (i == sb.length() - 1)
                sb.append(sb.length() % 2 == 1 ? 'X' : "");

            else if (sb.charAt(i) == sb.charAt(i + 1))
                sb.insert(i + 1, 'X');
        }
        return encrypt(sb, 1);
    }

    private static String decrypt(String message) {
        return encrypt(new StringBuilder(message), 4);
    }

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