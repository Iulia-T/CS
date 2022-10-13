package LaboratoryWork1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("1) Caesar cipher");
            System.out.println("2) Vigenere cipher");
            System.out.println("3) Playfair cipher");
            System.out.println("4) Caesar cipher with permutation");
            System.out.println("5) exit\n");
            System.out.println("Enter Choice:");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    CaesarCipher.caesar();
                    break;
                case 2:
                    VigenereCipher.vigenere();
                    break;
                case 3:
                    PlayfairCipher.playfair();
                    break;
                case 4:
                    CaesarCipherWithPermutation.caesar();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
        run();
    }
}
