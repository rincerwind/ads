package runners;

import crypto.ShamirSecret;
import utils.HmacSigner;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class ShamirSecretRunner {

    private static final String SECRET = "This is a secret";

    private ShamirSecret shamirSecret;

    public ShamirSecretRunner() throws UnsupportedEncodingException {
        this.shamirSecret = new ShamirSecret();
    }

    public static void main(String[] args) {
        ShamirSecretRunner shamirSecretRunner;
        try {
            shamirSecretRunner = new ShamirSecretRunner();
            String[] secrets = shamirSecretRunner.splitSecret(SECRET, 6, 3);
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
}
