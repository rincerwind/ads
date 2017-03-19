package utils;

import java.math.BigInteger;

public final class SecretShare {
    private final int shareNum;
    private final BigInteger secretShare;
    private final String publicKey;
    private final BigInteger modPrime;

    public SecretShare(final int shareNum, final BigInteger secretShare, final String publicKey,
                       final BigInteger modPrime) {
        this.shareNum = shareNum;
        this.secretShare = secretShare;
        this.publicKey = publicKey;
        this.modPrime = modPrime;
    }

    public int getShareNum() {
        return shareNum;
    }

    public BigInteger getSecretShare() {
        return secretShare;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public BigInteger getModPrime() {
        return modPrime;
    }

    @Override
    public String toString() {
        return "SecretShare{"
                + "shareNum=" + shareNum
                + ", secretShare='" + secretShare + '\''
                + ", publicKey='" + publicKey + '\''
                + ", modPrime=" + modPrime
                + '}';
    }
}
