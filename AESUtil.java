/**
 * Created by fellipe on 06/06/15.
 */

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import android.util.Base64;

public class AESUtil {

   private static final String ALGORITHM      = "AES";
   private static final String TRANSFORMATION = "AES/CBC/PKCS7Padding";
   private static final String SECRET_KEY     = "764164673344344D55624A6C486E4168";
   private static final String SECRET_IV      = "4957414F6B524147";

   public static String encrypt(String text) throws Exception {
      return Base64.encodeToString(getCipher(Cipher.ENCRYPT_MODE).doFinal(padString(text).getBytes()), Base64.NO_PADDING);
   }

   public static String decrypt(String code) throws Exception {
      return new String(getCipher(Cipher.DECRYPT_MODE).doFinal(Base64.decode(code, Base64.NO_PADDING)));
   }

   private static Cipher getCipher(int ENCRYPT_MODE) throws Exception {

      Cipher cipher = Cipher.getInstance(TRANSFORMATION);
      cipher.init(ENCRYPT_MODE, new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM), new IvParameterSpec(SECRET_IV.getBytes()));

      return cipher;
   }

   private static String padString(String source) {
      int size = 16;
      int padLength = size - (source.length() % size);

      for (int i = 0; i < padLength; i++)
         source += ' ';

      return source;
   }
}
