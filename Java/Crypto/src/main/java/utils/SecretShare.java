package utils;

import java.math.BigInteger;

public final class SecretShare {
    private final int xCoord;
    private final BigInteger yCoord;

    public SecretShare(final int xCoord, final BigInteger yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public int getxCoord() {
        return xCoord;
    }

    public BigInteger getyCoord() {
        return yCoord;
    }

    @Override
    public String toString() {
        return "SecretShare{"
                + "xCoord=" + xCoord
                + ", yCoord=" + yCoord
                + '}';
    }
}
