import java.io.File;
import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Enumeration;

import sun.misc.BASE64Encoder;

public class Test {
    // This is used to get Keystore alias name and key
    public static void main(String[] args) {
        try {
            File file = new File("path", "keystore name");
            FileInputStream is = new FileInputStream(file);
            KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
            String password = "password";
            keystore.load(is, password.toCharArray());

            Enumeration enumeration = keystore.aliases();
            while (enumeration.hasMoreElements()) {
                String alias = (String) enumeration.nextElement();
                System.out.println("alias name: " + alias);
                
                Key key = keystore.getKey(alias, password.toCharArray());
                String encodedKey = new BASE64Encoder().encode(key.getEncoded());
                System.out.println("key ? " + encodedKey);
                
                Certificate certificate = keystore.getCertificate(alias);
                System.out.println(certificate.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}
