package crypto;

import utils.HmacSigner;
import utils.SecretShare;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
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
        /*String publicKey = generatePublicKey();

        String encryptedSecret = hmacSigner.sign(publicKey, secret);
        byte[] secretBytes = encryptedSecret.getBytes(HmacSigner.DEFAULT_ENCODING);*/

        BigInteger[] coefs = {new BigInteger("1234"), new BigInteger("166"), new BigInteger("94")};
        SecretShare[] secretShares = new SecretShare[6];

        // Compute shares
        for (int i = 0; i < shares; i++) {
            BigInteger secretSum = coefs[0];

            for (int coefInd = 1; coefInd < threshold; coefInd++) {
                final BigInteger xCoord = BigInteger.valueOf(i+1).pow(coefInd);
                secretSum = secretSum.add(coefs[coefInd].multiply(xCoord));
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
                    BigInteger xIndCoord = new BigInteger("" + secretShares[xInd].getxCoord());
                    BigInteger xICoord = new BigInteger("" + secretShares[i].getxCoord());
                    num = num.multiply(xIndCoord);
                    denom = denom.multiply(xIndCoord.subtract(xICoord));
                }
            }

            computedSecret = computedSecret.add(yCoord.divide(denom).multiply(num));
        }
        System.out.print("Computed secret: " + computedSecret);

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
