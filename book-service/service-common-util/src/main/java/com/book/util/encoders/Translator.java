package com.book.util.encoders;

/**
 * Created by samson on 2017/2/28.
 */
public interface Translator {
    int getEncodedBlockSize();

    int encode(byte[] var1, int var2, int var3, byte[] var4, int var5);

    int getDecodedBlockSize();

    int decode(byte[] var1, int var2, int var3, byte[] var4, int var5);
}
