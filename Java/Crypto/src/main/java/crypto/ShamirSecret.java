package crypto;

import utils.HmacSigner;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class ShamirSecret {

    private HmacSigner hmacSigner;

    public ShamirSecret() {
        hmacSigner = new HmacSigner(HmacSigner.HMAC_SHA256);
    }

    public String[] splitSecret(String secret, int shares, int threshold)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        String publicKey = generatePublicKey();

        String encryptedSecret = hmacSigner.sign(publicKey, secret);
        byte[] secretBytes = encryptedSecret.getBytes(HmacSigner.DEFAULT_ENCODING);

        return null;
    }

    private byte[] computeShare() {
        return null;
    }

    private String generatePublicKey() throws UnsupportedEncodingException {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);

        return new String(bytes, HmacSigner.DEFAULT_ENCODING);
    }
}
