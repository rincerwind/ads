package crypto;

import utils.HmacSigner;
import utils.SecretShare;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class ShamirSecret {

    private static final int CERTAINTY = 32;
    private HmacSigner hmacSigner;

    public ShamirSecret() {
        hmacSigner = new HmacSigner(HmacSigner.HMAC_SHA256);
    }

    public String[] splitSecret(String secret, int shares, int threshold)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        /*String publicKey = generatePublicKey();

        String encryptedSecret = hmacSigner.sign(publicKey, secret);
        byte[] secretBytes = encryptedSecret.getBytes(HmacSigner.DEFAULT_ENCODING);*/

        BigInteger[] coefs = {new BigInteger("1234"), new BigInteger("166"), new BigInteger("94")};
        BigInteger randomPrime = generateRandomPrimer(coefs[0].bitLength());
        SecretShare[] secretShares = new SecretShare[6];

        // Compute shares
        for (int i = 0; i < shares; i++) {
            BigInteger secretSum = coefs[0];

            for (int exp = 1; exp < threshold; exp++) {
                final BigInteger xCoord = BigInteger.valueOf(i+1).modPow(BigInteger.valueOf(exp), randomPrime);
                final BigInteger tempProd = coefs[exp].multiply(xCoord).mod(randomPrime);
                secretSum = secretSum.add(tempProd).mod(randomPrime);
            }
            secretShares[i] = new SecretShare(i+1, secretSum);
            System.out.println(String.format("Share %d: %s", i+1, secretSum.toString()));
        }

        // Reconstruct secret
        BigInteger computedSecret = BigInteger.ZERO;
        for(int i = 0; i < threshold; i++) {
            BigInteger yCoord = secretShares[i].getyCoord();
            BigInteger num = BigInteger.ONE;
            BigInteger denom = BigInteger.ONE;

            for ( int xInd = 0; xInd < threshold; xInd++ ) {
                if ( i != xInd ) {
                    BigInteger xIndCoord = BigInteger.valueOf(secretShares[xInd].getxCoord());
                    BigInteger xICoord = BigInteger.valueOf(secretShares[i].getxCoord());
                    num = num.multiply(xIndCoord).mod(randomPrime);
                    denom = denom.multiply(xIndCoord.subtract(xICoord)).mod(randomPrime);
                }
            }

            final BigInteger tempSum = yCoord.multiply(num).multiply(denom.modInverse(randomPrime));
            computedSecret = computedSecret.add(randomPrime).add(tempSum).mod(randomPrime);
        }
        System.out.print("Computed secret: " + computedSecret);

        return null;
    }

    private byte[] computeShare() {
        return null;
    }

    private BigInteger generateRandomPrimer(int secretBitLen) {
        SecureRandom random = new SecureRandom();
        return new BigInteger(secretBitLen + 1, CERTAINTY, random);
    }

    private String generatePublicKey() throws UnsupportedEncodingException {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);

        return new String(bytes, HmacSigner.DEFAULT_ENCODING);
    }
}
