package com.book.util.encoders;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by samson on 2017/2/28.
 */
public class Base64Encoder implements Encoder {
    protected final byte[] encodingTable = new byte[]{(byte)65, (byte)66, (byte)67, (byte)68, (byte)69, (byte)70, (byte)71, (byte)72, (byte)73, (byte)74, (byte)75, (byte)76, (byte)77, (byte)78, (byte)79, (byte)80, (byte)81, (byte)82, (byte)83, (byte)84, (byte)85, (byte)86, (byte)87, (byte)88, (byte)89, (byte)90, (byte)97, (byte)98, (byte)99, (byte)100, (byte)101, (byte)102, (byte)103, (byte)104, (byte)105, (byte)106, (byte)107, (byte)108, (byte)109, (byte)110, (byte)111, (byte)112, (byte)113, (byte)114, (byte)115, (byte)116, (byte)117, (byte)118, (byte)119, (byte)120, (byte)121, (byte)122, (byte)48, (byte)49, (byte)50, (byte)51, (byte)52, (byte)53, (byte)54, (byte)55, (byte)56, (byte)57, (byte)43, (byte)47};
    protected byte padding = 61;
    protected final byte[] decodingTable = new byte[128];

    protected void initialiseDecodingTable() {
        int i;
        for(i = 0; i < this.decodingTable.length; ++i) {
            this.decodingTable[i] = -1;
        }
        for(i = 0; i < this.encodingTable.length; ++i) {
            this.decodingTable[this.encodingTable[i]] = (byte)i;
        }

    }

    public Base64Encoder() {
        this.initialiseDecodingTable();
    }

    public int encode(byte[] data, int off, int length, OutputStream out) throws IOException {
        int modulus = length % 3;
        int dataLength = length - modulus;

        int b1;
        for(b1 = off; b1 < off + dataLength; b1 += 3) {
            int a1 = data[b1] & 255;
            int a2 = data[b1 + 1] & 255;
            int a3 = data[b1 + 2] & 255;
            out.write(this.encodingTable[a1 >>> 2 & 63]);
            out.write(this.encodingTable[(a1 << 4 | a2 >>> 4) & 63]);
            out.write(this.encodingTable[(a2 << 2 | a3 >>> 6) & 63]);
            out.write(this.encodingTable[a3 & 63]);
        }

        int b2;
        int d1;
        switch(modulus) {
            case 0:
            default:
                break;
            case 1:
                d1 = data[off + dataLength] & 255;
                b1 = d1 >>> 2 & 63;
                b2 = d1 << 4 & 63;
                out.write(this.encodingTable[b1]);
                out.write(this.encodingTable[b2]);
                out.write(this.padding);
                out.write(this.padding);
                break;
            case 2:
                d1 = data[off + dataLength] & 255;
                int d2 = data[off + dataLength + 1] & 255;
                b1 = d1 >>> 2 & 63;
                b2 = (d1 << 4 | d2 >>> 4) & 63;
                int b3 = d2 << 2 & 63;
                out.write(this.encodingTable[b1]);
                out.write(this.encodingTable[b2]);
                out.write(this.encodingTable[b3]);
                out.write(this.padding);
        }

        return dataLength / 3 * 4 + (modulus == 0?0:4);
    }

    private boolean ignore(char c) {
        return c == 10 || c == 13 || c == 9 || c == 32;
    }

    public int decode(byte[] data, int off, int length, OutputStream out) throws IOException {
        int outLen = 0;

        int end;
        for(end = off + length; end > off && this.ignore((char)data[end - 1]); --end) {
            ;
        }

        int finish = end - 4;

        for(int i = this.nextI(data, off, finish); i < finish; i = this.nextI(data, i, finish)) {
            byte b1 = this.decodingTable[data[i++]];
            i = this.nextI(data, i, finish);
            byte b2 = this.decodingTable[data[i++]];
            i = this.nextI(data, i, finish);
            byte b3 = this.decodingTable[data[i++]];
            i = this.nextI(data, i, finish);
            byte b4 = this.decodingTable[data[i++]];
            if((b1 | b2 | b3 | b4) < 0) {
                throw new IOException("invalid characters encountered in base64 data");
            }

            out.write(b1 << 2 | b2 >> 4);
            out.write(b2 << 4 | b3 >> 2);
            out.write(b3 << 6 | b4);
            outLen += 3;
        }

        outLen += this.decodeLastBlock(out, (char)data[end - 4], (char)data[end - 3], (char)data[end - 2], (char)data[end - 1]);
        return outLen;
    }

    private int nextI(byte[] data, int i, int finish) {
        while(i < finish && this.ignore((char)data[i])) {
            ++i;
        }

        return i;
    }

    public int decode(String data, OutputStream out) throws IOException {
        int length = 0;

        int end;
        for(end = data.length(); end > 0 && this.ignore(data.charAt(end - 1)); --end) {
            ;
        }

        byte i = 0;
        int finish = end - 4;

        for(int var11 = this.nextI((String)data, i, finish); var11 < finish; var11 = this.nextI(data, var11, finish)) {
            byte b1 = this.decodingTable[data.charAt(var11++)];
            var11 = this.nextI(data, var11, finish);
            byte b2 = this.decodingTable[data.charAt(var11++)];
            var11 = this.nextI(data, var11, finish);
            byte b3 = this.decodingTable[data.charAt(var11++)];
            var11 = this.nextI(data, var11, finish);
            byte b4 = this.decodingTable[data.charAt(var11++)];
            if((b1 | b2 | b3 | b4) < 0) {
                throw new IOException("invalid characters encountered in base64 data");
            }

            out.write(b1 << 2 | b2 >> 4);
            out.write(b2 << 4 | b3 >> 2);
            out.write(b3 << 6 | b4);
            length += 3;
        }

        length += this.decodeLastBlock(out, data.charAt(end - 4), data.charAt(end - 3), data.charAt(end - 2), data.charAt(end - 1));
        return length;
    }

    private int decodeLastBlock(OutputStream out, char c1, char c2, char c3, char c4) throws IOException {
        byte b1;
        byte b2;
        if(c3 == this.padding) {
            b1 = this.decodingTable[c1];
            b2 = this.decodingTable[c2];
            if((b1 | b2) < 0) {
                throw new IOException("invalid characters encountered at end of base64 data");
            } else {
                out.write(b1 << 2 | b2 >> 4);
                return 1;
            }
        } else {
            byte b3;
            if(c4 == this.padding) {
                b1 = this.decodingTable[c1];
                b2 = this.decodingTable[c2];
                b3 = this.decodingTable[c3];
                if((b1 | b2 | b3) < 0) {
                    throw new IOException("invalid characters encountered at end of base64 data");
                } else {
                    out.write(b1 << 2 | b2 >> 4);
                    out.write(b2 << 4 | b3 >> 2);
                    return 2;
                }
            } else {
                b1 = this.decodingTable[c1];
                b2 = this.decodingTable[c2];
                b3 = this.decodingTable[c3];
                byte b4 = this.decodingTable[c4];
                if((b1 | b2 | b3 | b4) < 0) {
                    throw new IOException("invalid characters encountered at end of base64 data");
                } else {
                    out.write(b1 << 2 | b2 >> 4);
                    out.write(b2 << 4 | b3 >> 2);
                    out.write(b3 << 6 | b4);
                    return 3;
                }
            }
        }
    }

    private int nextI(String data, int i, int finish) {
        while(i < finish && this.ignore(data.charAt(i))) {
            ++i;
        }

        return i;
    }
}
