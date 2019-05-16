package com.book.util.Log;


public class LogServ {
    public static int log(String mod, String str, String serverIP)
    {
        return log(mod, str, serverIP, (short)10000);
    }

    public static int log(String mod, String str, String serverIP, short serverPort)
    {
        byte[] MODB = mod.getBytes();
        byte[] STRB = str.getBytes();
        if (MODB.length > 32)
            return 0;
        byte[] outBuff = new byte[38 + STRB.length + 1];

        byte[] inBuff = new byte[100];
        try
        {
            UDPClient.memcpy(outBuff, 0, MODB, 0, MODB.length);
            UDPClient.memLongcpy(outBuff, 32, 0L, 4);
            UDPClient.memLongcpy(outBuff, 36, STRB.length, 2);
            UDPClient.memcpy(outBuff, 38, STRB, 0, STRB.length);
            outBuff[(STRB.length + 38)] = 0;

            return
                    UDPClient.UDPIO(serverIP, serverPort, (short)0, (short)1,
                            (short)0, (short)1, outBuff, inBuff);
        }
        catch (Exception E)
        {
            System.err.println("LogServ.log() to " + serverIP + " ex:" + E.getMessage());
        }return 0;
    }
}
