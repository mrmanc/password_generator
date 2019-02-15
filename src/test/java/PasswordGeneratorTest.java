import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordGeneratorTest {
    private PasswordGenerator testSubject;

    @Before
    public void beforeEach() {
        testSubject = new PasswordGenerator();
    }

    @Test
    public void checkSecretPassword() {
        String secretPassword = testSubject.passwordCracker("$2a$10$N43LdqU9b1ZMuZM2KiBnIeaXiPqxYcTEVoMFabb9ZV2jfG82jupD6");

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        assertThat(bCryptPasswordEncoder.matches(secretPassword,
                "$2a$10$N43LdqU9b1ZMuZM2KiBnIeaXiPqxYcTEVoMFabb9ZV2jfG82jupD6"))
                .isTrue();
    }

    @Test
    public void correctMinimumLength() {
        assertThat(testSubject.buildPassword(8, 0, 0).length()).isGreaterThanOrEqualTo(8);
    }

    @Test
    public void correctAmountOfNumbers() {
        String result = testSubject.buildPassword(10, 0, 3);
        assertThat(result.replaceAll("[^\\d.]", "").length()).isEqualTo(3);
    }

    @Test
    public void correctAmountOfSpecialChars() {
        String result = testSubject.buildPassword(10, 3, 0);
        assertThat(result.replaceAll("[A-Za-z]", "").length()).isEqualTo(3);
    }

    @Test
    public void correctEverything() {
        String result = testSubject.buildPassword(20, 5, 6);
        assertThat(result.length()).isGreaterThanOrEqualTo(20);
        assertThat(result.replaceAll("[^\\d.]", "").length()).isEqualTo(6);
        assertThat(result.replaceAll("[A-Za-z0-9]", "").length()).isEqualTo(5);
    }

}
