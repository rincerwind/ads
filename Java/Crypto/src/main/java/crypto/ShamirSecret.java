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
    private final int shares;
    private final int threshold;

    public ShamirSecret(final int shares, final int threshold) {
        this.hmacSigner = new HmacSigner(HmacSigner.HMAC_SHA256);
        this.shares = shares;
        this.threshold = threshold;
    }

    public SecretShare[] splitSecret(String secret)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

        BigInteger secretInt = new BigInteger(secret.getBytes(HmacSigner.DEFAULT_ENCODING));
        SecretShare[] secretShares = new SecretShare[6];
        BigInteger[] coefs = new BigInteger[shares];

        BigInteger publicKeyInt = generateRandomPrime(secretInt.bitLength());
        String publicKey = new String(publicKeyInt.toByteArray(), HmacSigner.DEFAULT_ENCODING);
        String encryptedSecret = hmacSigner.sign(publicKey, secretInt.toString());

        BigInteger encryptedSecretInt = new BigInteger(encryptedSecret.getBytes(HmacSigner.DEFAULT_ENCODING));
        BigInteger modPrime = generateRandomPrime(encryptedSecretInt.bitLength());

        coefs[0] = encryptedSecretInt;
        for(int i = 1; i < shares; i++) {
            coefs[i] = generateRandomPrime(encryptedSecretInt.bitLength());
        }

        // Compute shares
        for (int share = 0; share < shares; share++) {
            BigInteger secretShare = encodeShare(encryptedSecretInt, coefs, modPrime, share+1);
            secretShares[share] = new SecretShare(share + 1, secretShare, publicKey, modPrime);
            System.out.println(String.format("Share %d: %s", share + 1, secretShare.toString()));
        }

        return secretShares;
    }

    // Reconstruct secret
    public String joinSecret(SecretShare[] shares, String publicKey, BigInteger modPrime)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        BigInteger computedSecret = BigInteger.ZERO;

        for(int share = 0; share < shares.length; share++) {
            BigInteger secretShare = shares[share].getSecretShare();
            BigInteger[] numAndDenom = decodeShare(shares, modPrime, share);
            BigInteger num = numAndDenom[0];
            BigInteger denom = numAndDenom[1];

            final BigInteger tempSum = secretShare.multiply(num).multiply(denom.modInverse(modPrime));
            computedSecret = computedSecret.add(modPrime).add(tempSum).mod(modPrime);
        }

        String computedSecretString = new String(computedSecret.toByteArray(), HmacSigner.DEFAULT_ENCODING);
        String decodedSecret = hmacSigner.verify(publicKey, computedSecretString);

        if (decodedSecret != null) {
            BigInteger decodedSecretInt = new BigInteger(decodedSecret);
            return new String(decodedSecretInt.toByteArray(), HmacSigner.DEFAULT_ENCODING);
        }

        return null;
    }

    private BigInteger encodeShare(BigInteger encodedSecret, BigInteger[] coefs, BigInteger modPrime, int share) {
        BigInteger secretSum = encodedSecret;

        for (int exp = 1; exp < threshold; exp++) {
            final BigInteger shareNum = BigInteger.valueOf(share).modPow(BigInteger.valueOf(exp), modPrime);
            final BigInteger tempProd = coefs[exp].multiply(shareNum).mod(modPrime);
            secretSum = secretSum.add(tempProd).mod(modPrime);
        }

        return secretSum;
    }

    private BigInteger[] decodeShare(SecretShare[] shares, BigInteger modPrime, int share) {
        BigInteger num = BigInteger.ONE;
        BigInteger denom = BigInteger.ONE;
        BigInteger shareNum = BigInteger.valueOf(shares[share].getShareNum());

        for ( int i = 0; i < shares.length; i++ ) {
            if ( share != i ) {
                BigInteger curShareNum = BigInteger.valueOf(shares[i].getShareNum());

                num = num.multiply(curShareNum).mod(modPrime);
                denom = denom.multiply(curShareNum.subtract(shareNum)).mod(modPrime);
            }
        }

        return new BigInteger[]{num, denom};
    }

    private BigInteger generateRandomPrime(int secretBitLen) {
        SecureRandom random = new SecureRandom();
        return new BigInteger(secretBitLen + 1, CERTAINTY, random);
    }
}
