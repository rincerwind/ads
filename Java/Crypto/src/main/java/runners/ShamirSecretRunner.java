package runners;

import crypto.ShamirSecret;
import utils.HmacSigner;
import utils.SecretShare;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class ShamirSecretRunner {

    private static final String SECRET = "This is a secret";

    private ShamirSecret shamirSecret;

    public ShamirSecretRunner(final int shares, final int threshold) throws UnsupportedEncodingException {
        this.shamirSecret = new ShamirSecret(shares, threshold);
    }

    public static void main(String[] args) {
        int threshold = 3;
        int numShares = 6;
        ShamirSecretRunner shamirSecretRunner;
        try {
            shamirSecretRunner = new ShamirSecretRunner(numShares, threshold);
            ShamirSecret shamirSecret = shamirSecretRunner.getShamirSecret();
            SecretShare[] shares = shamirSecret.splitSecret(SECRET);

            String publicKey = shares[0].getPublicKey();
            BigInteger modPrime = shares[0].getModPrime();
            SecretShare[] randomShares = getRandomShares(shares, threshold);
            String computedSecret = shamirSecret.joinSecret(randomShares, publicKey, modPrime);

            System.out.println();
            System.out.println("Actual secret: " + SECRET);
            System.out.println("Computed secret: " + computedSecret);
        } catch (UnsupportedEncodingException exp) {
            // TODO: log error
        } catch (NoSuchAlgorithmException e) {
            // TODO: log error
        } catch (InvalidKeyException e) {
            // TODO: log error
        }
    }

    public ShamirSecret getShamirSecret() {
        return shamirSecret;
    }

    // Obtains 3 consecutive shares, starting at a random position. Used for testing.
    private static SecretShare[] getRandomShares(SecretShare[] shares, int threshold) {
        SecretShare[] randomShares = new SecretShare[threshold];

        int i = (int)(Math.random() * (shares.length - 1));
        for(int count = 0; count < threshold; count++) {
            randomShares[count] = shares[i];
            i = (i + 1) % shares.length;
        }

        return randomShares;
    }
}
