package utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HmacSigner {
    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final String HMAC_SHA256 = "HmacSHA256";
    public static final String HMAC_SHA1 = "HmacSHA1";
    private static final String HMAC_SHA256_DELIMITER = ".";
    private static final String HMAC_SHA1_DELIMITER = "|";

    private String algorithm;
    private String encoding;
    private Base64 base64;

    public HmacSigner() {
        this(HMAC_SHA1);
    }

    public HmacSigner(String algorithm) {
        this(algorithm, DEFAULT_ENCODING);
    }

    public HmacSigner(String algorithm, String encoding) {
        this.algorithm = algorithm;
        this.encoding = encoding;
        this.base64 = new Base64(true);
    }

    public String sign(String key, String payload) throws UnsupportedEncodingException, InvalidKeyException,
            NoSuchAlgorithmException {
        String signature = generateHmac(key, payload.getBytes(DEFAULT_ENCODING));

        if (HMAC_SHA256.equals(algorithm)) {
            return String.format("%s.%s", base64.encodeAsString(signature.getBytes(encoding)),
                    base64.encodeAsString(payload.getBytes(encoding)));
        } else {
            // TODO: Implement
        }

        return null;
    }

    public String verify(String key, String signedPayload) throws UnsupportedEncodingException, InvalidKeyException,
            NoSuchAlgorithmException {

        if (HMAC_SHA256.equals(key)) {
            return verifySHA256(key, signedPayload);
        } else {
            // TODO: Implement
        }

        return null;
    }

    private String verifySHA256(String key, String signedPayload) throws UnsupportedEncodingException,
            InvalidKeyException, NoSuchAlgorithmException {
        String[] splitSignedData = signedPayload.split(HMAC_SHA256_DELIMITER);
        if (splitSignedData.length < 2) {
            return null;
        }
        String decodedSignature = new String(Base64.decodeBase64(splitSignedData[0].getBytes(DEFAULT_ENCODING)),
                DEFAULT_ENCODING);
        String decodedPayload = new String(Base64.decodeBase64(splitSignedData[1].getBytes(DEFAULT_ENCODING)),
                DEFAULT_ENCODING);
        String computedSignature = generateHmac(key, decodedPayload.getBytes(DEFAULT_ENCODING));

        return decodedSignature.equals(computedSignature) ? decodedPayload : null;
    }

    private String generateHmac(String key, byte[] payload) throws UnsupportedEncodingException,
            NoSuchAlgorithmException, InvalidKeyException {
        Mac hmacSha256 = Mac.getInstance(algorithm);
        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(DEFAULT_ENCODING), algorithm);
        hmacSha256.init(secret_key);

        return Hex.encodeHexString(hmacSha256.doFinal(payload));
    }
}
