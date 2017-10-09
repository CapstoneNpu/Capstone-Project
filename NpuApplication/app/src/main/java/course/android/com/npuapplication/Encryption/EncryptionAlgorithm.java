package course.android.com.npuapplication.Encryption;

import android.util.Base64;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Bansari on 10/8/2017.
 */

public class EncryptionAlgorithm {

    String AES = "AES";

    public String encrypt(String userName, String password) throws Exception {
        SecretKeySpec key = generateKey(password);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = cipher.doFinal(userName.getBytes());
        String encryptedValue = Base64.encodeToString(encVal, Base64.NO_WRAP);
        return encryptedValue;
    }

    public String decrypt(String encryptedPassword, String password) throws Exception{
        SecretKeySpec key = generateKey(password);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodeVal = Base64.decode(encryptedPassword, Base64.NO_WRAP);
        byte[] decVal = cipher.doFinal(decodeVal);
        String decryptedValue = new String(decVal);
        return decryptedValue;
    }

    private SecretKeySpec generateKey(String password) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }
}
