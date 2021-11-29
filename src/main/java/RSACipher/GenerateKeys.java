package RSACipher;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.*;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class GenerateKeys {
    public static void main(String[] args) throws IOException {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();

            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();
            
            Base64.Encoder encoder = Base64.getEncoder();
            System.out.println("private: " + encoder.encodeToString(privateKey.getEncoded()));
            System.out.println("public: " + encoder.encodeToString(publicKey.getEncoded()));
            
            /*Keys in files: */
            File f = new File("");
            String ruta = f.getAbsolutePath();
            String routeKeys = ruta + "\\publicAndPrivateKeys\\";
            
            /*File fKeys = new File(routeKeys);
            fKeys.mkdirs();
            fKeys.setWritable(true);*/
            
            FileWriter newFile= new FileWriter(ruta + "\\publicAndPrivateKeys\\" + "KEYS" + ".txt");
            newFile.write("________ PUBLIC KEY ________ \n");
            newFile.write(encoder.encodeToString(publicKey.getEncoded()));
            newFile.write("\n \n");
            newFile.write("________ PRIVATE KEY _______ \n");
            newFile.write(encoder.encodeToString(privateKey.getEncoded()));
            newFile.close();
            
            /*byte[] txt = "This is a secret message.".getBytes();            
            Cipher cipher;
            
            try {
                cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                try {
                    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                } catch (InvalidKeyException ex) {
                    Logger.getLogger(GenerateKeys.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    txt = cipher.doFinal(txt);
                    String textCipher = Base64.getEncoder().encodeToString(txt);
                    System.out.println("Encrypted message: " + new String(textCipher));
                } catch (IllegalBlockSizeException ex) {
                    Logger.getLogger(GenerateKeys.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BadPaddingException ex) {
                    Logger.getLogger(GenerateKeys.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(GenerateKeys.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            try {
                cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                try {
                    cipher.init(Cipher.DECRYPT_MODE, privateKey);
                } catch (InvalidKeyException ex) {
                    Logger.getLogger(GenerateKeys.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    txt = cipher.doFinal(txt);
                    System.out.println("Decrypted message: " + new String(txt));
                } catch (IllegalBlockSizeException ex) {
                    Logger.getLogger(GenerateKeys.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BadPaddingException ex) {
                    Logger.getLogger(GenerateKeys.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(GenerateKeys.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            
            
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
