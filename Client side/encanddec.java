import javax.crypto.*;
import javax.crypto.spec.*;
import java.io.*;
import java.security.spec.*;
public class encanddec
{
    private static Cipher encrypt;
    private static Cipher decrypt;
    private static byte[] iv ={0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    public static SecretKey fun(String datastring, String encstring, String decstring, SecretKey s) throws Exception
    {
        String data = datastring;
        String encrypted = encstring;
        String decrypted = decstring;
        AlgorithmParameterSpec aps = new IvParameterSpec(iv);
        SecretKey secret = KeyGenerator.getInstance("AES").generateKey();
        if(datastring.equals("null"))
        {
            secret = s;
            System.out.println(secret.toString());
            decrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
            decrypt.init(Cipher.DECRYPT_MODE, secret, aps);
            decryption(new FileInputStream(encrypted), new FileOutputStream(decrypted));
            System.out.println("Decryption of file using AES is successful");
        }
        else
        {
            encrypt = Cipher.getInstance("AES/CBC/PKCS5Padding");
            encrypt.init(Cipher.ENCRYPT_MODE, secret, aps);
            encryption(new FileInputStream(data), new FileOutputStream(encrypted));
            System.out.println("Encryption of file using AES is successful");
        }
       
        return secret;
    }
    public static void encryption(InputStream input, OutputStream output) throws Exception
    {
        output = new CipherOutputStream(output, encrypt);
        writebytes(input,output);
    }
    public static void decryption(InputStream input, OutputStream output) throws Exception
    {
        input = new CipherInputStream(input, decrypt);
        writebytes(input, output);
    }
    public static void writebytes(InputStream input,OutputStream output) throws Exception
    {
        byte[] writebuffer = new byte[512];
        int readbytes = 0;
        while((readbytes=input.read(writebuffer)) >=0 )
        {
            output.write(writebuffer, 0, readbytes);
        }
        input.close();
        output.close();
    }
    public static void copyContent(File a, File b)
        throws Exception
    {
        FileInputStream in = new FileInputStream(a);
        FileOutputStream out = new FileOutputStream(b);
  
        try {
            int n;
            while ((n = in.read()) != -1) {
                out.write(n);
            }
        }
        finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
        System.out.println("File Copied");
    }
}