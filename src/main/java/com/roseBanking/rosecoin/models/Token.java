package com.roseBanking.rosecoin.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

public class Token {

    final byte[] code;
    private final transient String urlencoded;
    private final transient String string;

    private final static Random random = new Random(12);
    private final static Base64.Encoder encoder = Base64.getUrlEncoder();
    private final static Base64.Decoder decoder = Base64.getUrlDecoder();


    public static Token makeRandom(int size) {
        byte[] byteCode = new byte[size];
        random.nextBytes(byteCode);
        return new Token(byteCode);
    }

    public final Token fromString(String value) {
        return new Token(decoder.decode(value));
    }
    private Token(byte[] byteCode) {
        this.code = byteCode;
        this.urlencoded = encoder.encodeToString(byteCode);
        string = "Token(" + urlencoded + ")";
    }

    public String getCode() {
        return urlencoded;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(code);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (! (obj instanceof  Token))
            return false;
        Token other = (Token) obj;
        return Arrays.equals(this.code, other.code);
    }

    @Override
    public String toString() {
        return string;
    }
}
