package runners;

import crypto.ShamirSecret;
import utils.HmacSigner;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ShamirSecretRunner {

    private static final String SECRET = "This is a secret";
    private static final String HMAC_TEST_KEY = "This is a key";

    private ShamirSecret shamirSecret;
    private HmacSigner hmacSigner;

    public ShamirSecretRunner() throws UnsupportedEncodingException {
        this.shamirSecret = new ShamirSecret();
        this.hmacSigner = new HmacSigner(HmacSigner.HMAC_SHA256);
    }

    public static void main(String[] args) {
        ShamirSecretRunner shamirSecretRunner = null;
        try {
            shamirSecretRunner = new ShamirSecretRunner();
            String[] secrets = shamirSecretRunner.splitSecret(SECRET, 5, 3);
        } catch (UnsupportedEncodingException exp) {
            // TODO: log error
        } catch (NoSuchAlgorithmException e) {
            // TODO: log error
        } catch (InvalidKeyException e) {
            // TODO: log error
        }
    }

    public String[] splitSecret(String secret, int shares, int threshold)
            throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
        return shamirSecret.splitSecret(secret, shares, threshold);
    }

    public String signPayloadHmacSHA256(String key, String payload) {
        try {
            return hmacSigner.sign(key, payload);
        } catch (UnsupportedEncodingException exp) {
            // TODO: log error
            return null;
        } catch (InvalidKeyException exp) {
            // TODO: log error
            return null;
        } catch (NoSuchAlgorithmException exp) {
            // TODO: log error
            return null;
        }
    }
}
