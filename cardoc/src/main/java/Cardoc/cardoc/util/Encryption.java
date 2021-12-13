package Cardoc.cardoc.util;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class Encryption {
    public static String getHashedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
