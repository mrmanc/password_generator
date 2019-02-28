import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    private int minimumLength;
    private int amountOfSpecialChars;
    private int amountOfNumbers;
    Random random = new Random();


    public void generatePassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the minimum length of password you would like?");
        minimumLength = Integer.parseInt(scanner.nextLine());

        System.out.println("How many special characters would you like?");
        amountOfSpecialChars = Integer.parseInt(scanner.nextLine());

        System.out.println("How many numbers would you like in your password?");
        amountOfNumbers = Integer.parseInt(scanner.nextLine());

        System.out.println("Your password is \n" + buildPassword(minimumLength, amountOfSpecialChars, amountOfNumbers));
    }

    public String buildPassword(int minimumLength, int amountOfSpecialChars, int amountOfNumbers) {
        List<Character> specialChars = Arrays.asList('!', '@', '£', '$', '%', '^', '&', '*', '(', ')', '#', '+', '-', '/');
        List<Character> alphabet = Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');
        StringBuilder password = new StringBuilder();

        while (password.length() < minimumLength) {
            password.append(alphabet.get(random.nextInt(alphabet.size())));
        }

        for (int i = 0; i < amountOfNumbers; i++) {
            password.insert(random.nextInt(password.length() - 1), random.nextInt(10));
        }

        for (int i = 0; i< amountOfSpecialChars; i++) {
            password.insert(random.nextInt(password.length() - 1), specialChars.get(random.nextInt(specialChars.size())));
        }


        return password.toString();
    }

    public String buildPassword(int minimumLength, int maximumLength, int amountOfSpecialChars, int amountOfNumbers) {
        List<Character> specialChars = Arrays.asList('£', '$', '!', '#', '@', '+', '-', '/', '*');
        List<Character> alphabet = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
        StringBuilder password = new StringBuilder();
        char number = 0;

        while (password.length() < minimumLength) {
            password.append(alphabet.get(random.nextInt(alphabet.size())));
        }

        for (int i = 0; i < amountOfNumbers; i++) {
            number = String.valueOf(random.nextInt(9)).charAt(0);
            password.setCharAt(random.nextInt(2), number);
        }

        for (int i = 0; i < amountOfSpecialChars; i++) {
            int randomIndex = random.nextInt(2);

            if (password.charAt(randomIndex) != number) {
                password.setCharAt(randomIndex, specialChars.get(random.nextInt(specialChars.size())));
            }



        }

        return password.toString();
    }

    public String passwordCracker(String encodedPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String guess;


        do {
            guess = buildPassword(3,3,1,1);
            System.out.println(guess);
        }
        while (!bCryptPasswordEncoder.matches(guess, encodedPassword));

        return guess;
    }

    public static void main(String[] args) {
        new PasswordGenerator().generatePassword();
    }
}
