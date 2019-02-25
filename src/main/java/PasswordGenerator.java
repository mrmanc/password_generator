import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class PasswordGenerator {

    List<Character> specialCharacter = new ArrayList<>();
    List<Character> letters = new ArrayList<>();
    List<Character> numbersList = new ArrayList<>();

    PasswordGenerator() {
        addSpecialCharacters();
        addNumbers();
        addLetters();
    }

    String generatePassword(int passwordLength, int specialCharacters, int numbers) {

        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < specialCharacters; i++) {
            password.append(specialCharacter.get(random.nextInt(specialCharacter.size())));
            passwordLength--;
        }

        for (int i = 0; i < numbers; i++) {
            password.append(numbersList.get(random.nextInt(numbersList.size())));
            passwordLength--;
        }

        for (int i = 0; i < passwordLength; i++) {
            password.append(letters.get(random.nextInt(letters.size())));
        }

        password = new StringBuilder(mixUpPassword(password.toString()));
        return password.toString();
    }

    private void addSpecialCharacters() {
        for (char c = '!'; c <= '/'; c++) {
            specialCharacter.add(c);
        }
        specialCharacter.add('£');
        specialCharacter.add('@');
    }

    private void addNumbers() {
        for (char c = '0'; c <= '9'; c++) {
            numbersList.add(c);
        }
    }

    private void addLetters() {
//        for (char uc = 'A'; uc <= 'Z'; uc++) {
//            letters.add(uc);
//        }
        for (char lc = 'a'; lc <= 'z'; lc++) {
            letters.add(lc);
        }
    }

    private String mixUpPassword(String orderedPassword) {
        char[] passwordArray = orderedPassword.toCharArray();
        List<Character> currentPassword = new ArrayList<>();

        for (char character : passwordArray) {
            currentPassword.add(character);
        }

        Collections.shuffle(currentPassword);

        StringBuilder newWord = new StringBuilder();
        for (char characters : currentPassword) {
            newWord.append(characters);
        }
        return newWord.toString();
    }
}
