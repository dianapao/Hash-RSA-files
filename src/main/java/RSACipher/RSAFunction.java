package RSACipher;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


/**
 *
 * @author Diana Paola
 */
public class RSAFunction {
    //private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCfBSzj5UaxbwgsG4WIda7VqEBPMKW0Auwovk50dNdqrB4mnNcgwAF5tJpeVLAM0m6CVezF0F7uzLqpf/hOBmpVTJJtohWoKhgQCZ3qqASygCleQqJUcpwkUra9BAe0FMa9Abwz3LsgsfG5Ryiy/yhBmLZ86Ump68Uit+wxiTNvcwIDAQAB";
    //private static String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ8FLOPlRrFvCCwbhYh1rtWoQE8wpbQC7Ci+TnR012qsHiac1yDAAXm0ml5UsAzSboJV7MXQXu7Muql/+E4GalVMkm2iFagqGBAJneqoBLKAKV5ColRynCRStr0EB7QUxr0BvDPcuyCx8blHKLL/KEGYtnzpSanrxSK37DGJM29zAgMBAAECgYABdUOpIu2p7BBScVRk94ljphJvtJ+wzcOV7ZTHz+6Rmbx462YdsOfUcKUb8PYzOU8T6fps8rqasjopOJciheWJKBRfXaWGIUgQTmRyVxKPmDy6DIPL7Lec+n/UFPAPlLC0qlPl1fU3cRqiUgTNsxkg8JxxWiJ/ItxqLyD3LiPnRQJBALNJVl0d7a5IpfDK996aKCH3U4Cgo0nQEScFMHfSJEXt95F6nBLRZVP7jKJQegmyeIOuLIjf7xrHOoRbDY88BlUCQQDjD+ow4QThvy0ByVOZB6ue7R421t2CdUWBexjFIoIeVxW3YxdRU8c78q+HlZ7OijsQwnZlqjTU0RsN+dWugBanAkAFM1Ji/xQ+4vKVSn3oIhaZ7Z+pcn9qC7QDqr3eK+VLdfyhZqoSTaqfxgMtTiJMF7YxM6MtETnRUAgdgLqi9f3FAkEApkn1fS6OYLWdxH0pGB2h9pY5VngtaWlxKc546YbIPGgSo2eSoCD2/FRTcLyGvURG1ZNVGkd4wZMSeT3K3C06aQJAPSXKqOMmQNForIgLbz0l2H30olRGtZ7Zj8z+hHTOXWZUs15jlNSJWyajDLz6ahuiLbtMJN+4bfCCcSEMyPkKlw==";
    private String publicKey;
    private String privateKey;
    
    public void setPublicKey(String publicKey){
        this.publicKey = publicKey;
    }
    
    public void setPrivateKey(String privateKey){
        this.privateKey = privateKey;
    }
    
    public PublicKey getPublicKey(String base64PublicKey) throws InvalidKeySpecException{
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(java.util.Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public PrivateKey getPrivateKey(String base64PrivateKey) throws InvalidKeySpecException{
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(java.util.Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    public byte[] encrypt(String data, String privateKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPrivateKey(privateKey));
        return cipher.doFinal(data.getBytes());
    }

    public String decrypt(byte[] data, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return new String(cipher.doFinal(data));
    }

    public String decrypt(String data, String base64PublicKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {
        return decrypt(java.util.Base64.getDecoder().decode(data.getBytes()), getPublicKey(base64PublicKey));
    }

    /*public static void main(String[] args) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException, InvalidKeySpecException {
        try {
            String mensaje = "ESTA es una Prueba";
            String encryptedString = java.util.Base64.getEncoder().encodeToString(encrypt(mensaje, publicKey));
            System.out.println("msg cifrado: " + encryptedString);
            String decryptedString = decrypt(encryptedString, privateKey);
            System.out.println("msg descifrado: " + decryptedString);
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        }

    }*/
}
