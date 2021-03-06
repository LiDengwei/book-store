package com.book.util.encoders;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by samson on 2017/2/28.
 */
public class UrlBase64 {
    private static final Encoder encoder = new UrlBase64Encoder();

    public UrlBase64() {
    }

    public static byte[] encode(byte[] data) {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();

        try {
            encoder.encode(data, 0, data.length, bOut);
        } catch (Exception var3) {
            throw new EncoderException("exception encoding URL safe base64 data: " + var3.getMessage(), var3);
        }

        return bOut.toByteArray();
    }

    public static int encode(byte[] data, OutputStream out) throws IOException {
        return encoder.encode(data, 0, data.length, out);
    }

    public static byte[] decode(byte[] data) {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();

        try {
            encoder.decode(data, 0, data.length, bOut);
        } catch (Exception var3) {
            throw new DecoderException("exception decoding URL safe base64 string: " + var3.getMessage(), var3);
        }

        return bOut.toByteArray();
    }

    public static int decode(byte[] data, OutputStream out) throws IOException {
        return encoder.decode(data, 0, data.length, out);
    }

    public static byte[] decode(String data) {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();

        try {
            encoder.decode(data, bOut);
        } catch (Exception var3) {
            throw new DecoderException("exception decoding URL safe base64 string: " + var3.getMessage(), var3);
        }

        return bOut.toByteArray();
    }

    public static int decode(String data, OutputStream out) throws IOException {
        return encoder.decode(data, out);
    }
}
