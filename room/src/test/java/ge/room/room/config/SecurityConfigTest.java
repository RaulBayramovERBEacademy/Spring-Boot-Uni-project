package ge.room.room.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SecurityConfigTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void passwordEncoder_isBCrypt() {
        assertThat(passwordEncoder).isInstanceOf(BCryptPasswordEncoder.class);
    }

    @Test
    void passwordEncoder_encodesAndMatches() {
        String encoded = passwordEncoder.encode("secret");

        assertThat(encoded).isNotEqualTo("secret");
        assertThat(passwordEncoder.matches("secret", encoded)).isTrue();
        assertThat(passwordEncoder.matches("wrong", encoded)).isFalse();
    }
}
