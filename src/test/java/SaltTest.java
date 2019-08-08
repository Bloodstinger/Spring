import com.online.store.utils.HashUtil;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import static org.junit.Assert.*;

public class SaltTest {

    @Test
    public void testSalt() throws NoSuchProviderException, NoSuchAlgorithmException {
        byte[] salt = HashUtil.getSalt();
        String password = "test_password";
        String securePassword1 = HashUtil.getSecurePassword(password, salt);
        String checkSecurePassword = HashUtil.getSecurePassword(password, salt);
        assertEquals(securePassword1, checkSecurePassword);
    }
}
