package com.book.util.encoders;

/**
 * Created by samson on 2017/2/28.
 */
public class UrlBase64Encoder extends Base64Encoder {
    public UrlBase64Encoder() {
        this.encodingTable[this.encodingTable.length - 2] = 45;
        this.encodingTable[this.encodingTable.length - 1] = 95;
        this.padding = 46;
        this.initialiseDecodingTable();
    }
}
