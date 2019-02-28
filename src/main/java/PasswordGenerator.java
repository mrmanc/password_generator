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

        for (int currentAmountOfNumbers = 0; currentAmountOfNumbers < amountOfNumbers; currentAmountOfNumbers++) {
            password.insert(random.nextInt(password.length() - 1), random.nextInt(10));
        }

        for (int currentAmountOfSpecialChars = 0; currentAmountOfSpecialChars < amountOfSpecialChars; currentAmountOfSpecialChars++) {
            password.insert(random.nextInt(password.length() - 1), specialChars.get(random.nextInt(specialChars.size())));
        }


        return password.toString();
    }

    public String buildPassword(String encodedPassword, int length, int amountOfSpecialChars, int amountOfNumbers) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        List<Character> specialChars = Arrays.asList('£', '$', '!', '#', '@', '+', '-', '/', '*');
        List<Character> alphabet = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');
        int number = 0;

        StringBuilder password;

        for (int i = 0; i < alphabet.size(); i++) {
            password = new StringBuilder();
            password.append(alphabet.get(i));

            for (int j = 0; j < specialChars.size(); j++) {
                password.append(specialChars.get(j));
                password.append(number);
            }

            if (bCryptPasswordEncoder.matches(password, encodedPassword)) {
                return password.toString();
            }
        }


        throw new RuntimeException("Password could not be cracked");

    }

    public String passwordCracker(String encodedPassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String guess;

        do {
            guess = buildPassword(encodedPassword,3,1,1);
            System.out.println(guess);
        }
        while (!bCryptPasswordEncoder.matches(guess, encodedPassword));

        return guess;
    }

    public static void main(String[] args) {
        new PasswordGenerator().generatePassword();
    }
}
