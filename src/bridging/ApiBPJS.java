package bridging;

import fungsi.config;
import fungsi.lzString;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Properties;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;

public class ApiBPJS {        
    private static final Properties prop = new Properties();
    private long GetUTCdatetimeAsString;
    private String salt;
    private String generateHmacSHA256Signature;
    private byte[] hmacData;
    private Mac mac;
    private long millis;
    private SSLContext sslContext;
    private SSLSocketFactory sslFactory;
    private SecretKeySpec secretKey;
    private Scheme scheme;
    private HttpComponentsClientHttpRequestFactory factory;
    public static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    
//    String lzString = decrypt(src);
    public String lzDecrypt(String lzstring){
        String res = null;
        try {
            res = lzString.decompressFromEncodedURIComponent(lzstring);
        } catch (Exception e) {
            System.out.println("Error LzString : "+e);
            e.printStackTrace();
        }
        return res;
    }

    public HttpHeaders header(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("X-Cons-ID",config.ApiConsBPJS());
        headers.add("X-Timestamp",String.valueOf(GetUTCdatetimeAsString()));
        headers.add("X-Signature",getHmac());
        return headers;
    }
    
    public String getHmac() {        
        GetUTCdatetimeAsString = GetUTCdatetimeAsString();        
        salt = config.ApiConsBPJS() +"&"+String.valueOf(GetUTCdatetimeAsString);
	generateHmacSHA256Signature = null;
	try {
	    generateHmacSHA256Signature = generateHmacSHA256Signature(salt,config.ApiKeyBPJS());
	} catch (GeneralSecurityException e) {
	    // TODO Auto-generated catch block
            System.out.println("Error Signature : "+e);
	    e.printStackTrace();
	}
	return generateHmacSHA256Signature;
    }

    public String generateHmacSHA256Signature(String data, String key)throws GeneralSecurityException {
        hmacData = null;
	try {
            secretKey = new SecretKeySpec(key.getBytes("UTF-8"),"HmacSHA256");
	    mac = Mac.getInstance("HmacSHA256");
	    mac.init(secretKey);
	    hmacData = mac.doFinal(data.getBytes("UTF-8"));
	    return new String(Base64.encode(hmacData), "UTF-8");
	} catch (UnsupportedEncodingException e) {
            System.out.println("Error Generate HMac: e");
	    throw new GeneralSecurityException(e);
	}
    }
        
    public long GetUTCdatetimeAsString(){    
        millis = System.currentTimeMillis();   
        return millis/1000;
    }
    
    public String saLt(){
        String sat = config.ApiConsBPJS()+config.ApiKeyBPJS()+String.valueOf(GetUTCdatetimeAsString);
        return sat;
    }
    
    public String decrypt(String src) throws NoSuchPaddingException, NoSuchAlgorithmException,
		        InvalidAlgorithmParameterException, InvalidKeyException,
		        BadPaddingException, IllegalBlockSizeException {
        String decrypted = "";
        String sat = saLt();
        try {
                Cipher cipher = Cipher.getInstance(ALGORITHM);
                cipher.init(Cipher.DECRYPT_MODE, makeKey(sat), makeIv(sat));
                byte[] srec = src.getBytes();
                decrypted = new String(cipher.doFinal(Base64.decode(srec)));
        } catch (InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
                System.out.println("Error di decrpyt versi 2");
                throw new RuntimeException(e);
        }
        return decrypted;
    }
    
    public AlgorithmParameterSpec makeIv(String keyBpjs) throws NoSuchAlgorithmException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] key = md.digest(keyBpjs.getBytes("UTF-8"));
            byte[] _hashIv = new byte[16];
            for (int i = 0; i < 16; i++) {
                _hashIv[i] = key[i];
            }
            IvParameterSpec _iv = new IvParameterSpec(_hashIv);
            return _iv;
        } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
        }
        return null;
    }

    
    static Key makeKey(String keyBpjs) {
        try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] key = md.digest(keyBpjs.getBytes("UTF-8"));
                return new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
                System.out.println("Error di key");
                e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
                System.out.println("Error di key 2");
                e.printStackTrace();
        }

        return null;
    }
    
    public RestTemplate getRest() throws NoSuchAlgorithmException, KeyManagementException {
        sslContext = SSLContext.getInstance("SSL");
        TrustManager[] trustManagers= {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {return null;}
                public void checkServerTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
                public void checkClientTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
            }
        };
        sslContext.init(null,trustManagers , new SecureRandom());
        sslFactory=new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        scheme=new Scheme("https",443,sslFactory);
        factory=new HttpComponentsClientHttpRequestFactory();
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        return new RestTemplate(factory);
    }

}
