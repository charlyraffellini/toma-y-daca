package conf;

import com.google.inject.Inject;
import ninja.utils.Crypto;
import ninja.utils.NinjaProperties;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.spec.KeySpec;

/**
 * Created by charly on 9/13/14.
 */
public class EncryptedSession extends ninja.session.SessionImpl{
    @Inject
    public EncryptedSession(Crypto crypto, NinjaProperties ninjaProperties) {
        super(crypto, ninjaProperties);
    }


    @Override
    public String get(String key) {
        String encryptedKey = this.encrypt(key);
        String encryptedValue = super.get(encryptedKey);
        return this.decrypt(encryptedValue);
    }

    @Override
    public void put(String key, String value) {
        if(value == null)
            super.put(this.encrypt(key), value);
        else
            super.put(this.encrypt(key), this.encrypt(value));
    }

    private String encrypt(String value){
        try {
            return new DESedeEncryption().encrypt(value);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private String decrypt(String value){
        try {
            return new DESedeEncryption().decrypt(value);
        }
        catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    class DESedeEncryption {

        private static final String UNICODE_FORMAT = "UTF8";
        public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
        private KeySpec myKeySpec;
        private SecretKeyFactory mySecretKeyFactory;
        private Cipher cipher;
        byte[] keyAsBytes;
        private String myEncryptionKey;
        private String myEncryptionScheme;
        SecretKey key;

        public DESedeEncryption() throws Exception {
            myEncryptionKey = "ThisIsSecretEncryptionYEK"; //TODO: Esto es de prueba en el mundo real no se puede poner acá
            myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
            keyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
            myKeySpec = new DESedeKeySpec(keyAsBytes);
            mySecretKeyFactory = SecretKeyFactory.getInstance(myEncryptionScheme);
            cipher = Cipher.getInstance(myEncryptionScheme);
            key = mySecretKeyFactory.generateSecret(myKeySpec);
        }

        /**
         * Method To Encrypt The String
         */
        public String encrypt(String unencryptedString) {
            String encryptedString = null;
            try {
                cipher.init(Cipher.ENCRYPT_MODE, key);
                byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
                byte[] encryptedText = cipher.doFinal(plainText);
                BASE64Encoder base64encoder = new BASE64Encoder();
                encryptedString = base64encoder.encode(encryptedText);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return encryptedString;
        }

        /**
         * Method To Decrypt An Ecrypted String
         */
        public String decrypt(String encryptedString) {
            String decryptedText = null;
            try {
                cipher.init(Cipher.DECRYPT_MODE, key);
                BASE64Decoder base64decoder = new BASE64Decoder();
                byte[] encryptedText = base64decoder.decodeBuffer(encryptedString);
                byte[] plainText = cipher.doFinal(encryptedText);
                decryptedText = bytes2String(plainText);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return decryptedText;
        }

        /**
         * Returns String From An Array Of Bytes
         */
        private String bytes2String(byte[] bytes) {
            return new String(bytes);
        }
    }
}



