package com.book.util.Log;


import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class UDPClient {
    static UDPSocketManager socks = new UDPSocketManager();
    public static long counter = 0L;
    public static int Timeout = 0;
    public static long TotalTime = 0L;
    public static int TimeLimit = 0;
    public static int TimeCount = 0;

    public static void memIntcpy(byte[] buf, int offset, int val, int len) {
        if (len > 4)
            return;
        try {
            for (int x1 = 0; x1 < len; x1++) {
                buf[(offset + x1)] = ((byte)(val % 256));

                val >>= 8;
            }
        }
        catch (Exception localException) {
        }
    }

    public static void memLongcpy(byte[] buf, int offset, long val, int len) {
        if (len > 8)
            return;
        try {
            for (int x1 = 0; x1 < len; x1++) {
                buf[(offset + x1)] = ((byte)(int)(val % 256L));

                val >>= 8;
            }
        }
        catch (Exception localException) {
        }
    }

    public static int intMemcpy(byte[] buf, int offset, int len) {
        int ret = 0;

        if (len > 4)
            return 0;
        try {
            for (int x1 = len - 1; x1 >= 0; x1--)
            {
                ret *= 256;

                ret = ret + (buf[(offset + x1)] >= 0 ? buf[(offset + x1)] :
                        256 + buf[(offset + x1)]);
            }
        } catch (Exception localException) {
        }
        return ret;
    }

    public static int longMemcpy(byte[] buf, int offset, int len) {
        int ret = 0;

        if (len > 8)
            return 0;
        try {
            for (int x1 = len - 1; x1 >= 0; x1--)
            {
                ret *= 256;

                ret = ret + (buf[(offset + x1)] >= 0 ? buf[(offset + x1)] :
                        256 + buf[(offset + x1)]);
            }
        } catch (Exception localException) {
        }
        return ret;
    }

    public static void memcpy(byte[] buf, int offset, byte[] inbuf, int off2, int len) {
        try {
            for (int x1 = 0; x1 < len; x1++)
                buf[(x1 + offset)] = inbuf[(x1 + off2)];
        }
        catch (Exception localException)
        {
        }
    }

    public static int UDPIO(String ServerIP, short method, short function, byte[] L2PkgIn, byte[] L2PkgOut) {
        return UDPIO(ServerIP, (short)10000, method, function, L2PkgIn,
                L2PkgOut);
    }

    public static int UDPIO(String ServerIP, short port, short method, short function, byte[] L2PkgIn, byte[] L2PkgOut)
    {
        return UDPIO(ServerIP, port, method, function, (short)1200,
                (short)(function == 2 ? 1 : 2), L2PkgIn, L2PkgOut);
    }

    public static int UDPIO(String ServerIP, short port, short method, short function, short timeout, short retry, byte[] L2PkgIn, byte[] L2PkgOut)
    {
        boolean isOK = false;
        counter += 1L;
        TimeCount += 1;
        if (TimeCount > TimeLimit) {
            TimeCount = 0;
        }
        byte[] outBuffer = new byte[10 + L2PkgIn.length];
        byte[] inBuffer = (byte[])null;
        long t0;
        short seq = (short)(int)(((t0 = System.currentTimeMillis()) + counter) % 31231L);

        memIntcpy(outBuffer, 0, method, 2);
        memIntcpy(outBuffer, 2, function, 2);
        memIntcpy(outBuffer, 4, seq, 2);
        memIntcpy(outBuffer, 6, L2PkgIn.length + 10, 2);

        memcpy(outBuffer, 10, L2PkgIn, 0, L2PkgIn.length);

        DatagramSocket ds = socks.getSock();
        DatagramPacket dpin = socks.getInPackage();

        if ((ds == null) || (dpin == null)) {
            System.out
                    .println("socks.getSock() || socks.getInPackage() == null");
            return -1;
        }

        DatagramPacket dp = new DatagramPacket(outBuffer, outBuffer.length,
                socks.getAddr(ServerIP), port);

        int never = 0;

        for (int x1 = 0; x1 < retry; x1++) {
            try {
                if (x1 == 1)
                    System.out.println("UDPClient->UDPIO(" + seq + ") To:" +
                            ServerIP + " FUNC=" + function + " try again!");
                never++;
                if (never >= 3)
                    break;
                if (timeout != 0) {
                    ds.setSoTimeout(timeout);
                }
                ds.send(dp);
                System.out.println("x1="+x1);
                if (timeout != 0)
                    ds.receive(dpin);
                else {
                    isOK = true;
                }

            }
            catch (Exception E)
            {
                System.out
                        .println("UDPClient->UDPIO(" + seq +
                                "): Receive timed out:" + ServerIP + " FUNC=" +
                                function + " TM=" + (
                                System.currentTimeMillis() - t0));
                Timeout += 1;
                TimeLimit += 3;
                continue;
            }

//            if (dpin.getLength() < 10) {
//                x1--;
//            }
//            else {
//                inBuffer = dpin.getData();
//
//                if (intMemcpy(inBuffer, 4, 2) == seq) {
//                    isOK = true;
//                    break;
//                }
//                x1--;
//            }
        }
        long t1 = System.currentTimeMillis();

        TotalTime += t1 - t0;

        if (isOK) {
            memcpy(L2PkgOut, 0, dpin.getData(), 10, dpin.getLength());
            socks.putSock(ds);
            socks.putInPackage(dpin);
            if (TimeLimit > 0)
                TimeLimit /= 2;
            return dpin.getLength() - 10;
        }
        System.out.println("Timeout");

        socks.putSock(ds);
        socks.putInPackage(dpin);

        return -1;
    }

    public static int UDPIOtest(String ServerIP, short port, short method, short function, byte[] L2PkgIn, byte[] L2PkgOut)
    {
        boolean isOK = false;
        counter += 1L;
        TimeCount += 1;
        if (TimeCount > TimeLimit) {
            TimeCount = 0;
        }
        byte[] outBuffer = new byte[10 + L2PkgIn.length];
        byte[] inBuffer = (byte[])null;
        long t0;
        short seq = (short)(int)(((t0 = System.currentTimeMillis()) + counter) % 31231L);

        memIntcpy(outBuffer, 0, method, 2);
        memIntcpy(outBuffer, 2, function, 2);
        memIntcpy(outBuffer, 4, seq, 2);
        memIntcpy(outBuffer, 6, L2PkgIn.length + 10, 2);

        memcpy(outBuffer, 10, L2PkgIn, 0, L2PkgIn.length);

        DatagramSocket ds = socks.getSock();
        DatagramPacket dpin = socks.getInPackage();

        if ((ds == null) || (dpin == null)) {
            System.out
                    .println("socks.getSock() || socks.getInPackage() == null");
            return -1;
        }

        DatagramPacket dp = new DatagramPacket(outBuffer, outBuffer.length,
                socks.getAddr(ServerIP), port);

        int never = 0;
        int retry = function == 2 ? 1 : 2;

        for (int x1 = 0; x1 < retry; x1++) {
            try {
                if (x1 == 1)
                    System.out.println("UDPClient->UDPIO(" + seq + ") To:" +
                            ServerIP + " FUNC=" + function + " try again!");
                never++;
                if (never >= 3)
                {
                    break;
                }
                ds.setSoTimeout(4200);

                ds.send(dp);
            }
            catch (Exception E)
            {
                System.out
                        .println("UDPClient->UDPIO(" + seq +
                                "): Receive timed out:" + ServerIP + " FUNC=" +
                                function + " TM=" + (
                                System.currentTimeMillis() - t0));
                Timeout += 1;
                TimeLimit += 3;
                continue;
            }

            if (dpin.getLength() < 10) {
                x1--;
            }
            else {
                inBuffer = dpin.getData();

                if (intMemcpy(inBuffer, 4, 2) == seq) {
                    isOK = true;
                    break;
                }
                x1--;
            }
        }
        long t1 = System.currentTimeMillis();

        TotalTime += t1 - t0;

        if (isOK) {
            memcpy(L2PkgOut, 0, dpin.getData(), 10, dpin.getLength());
            socks.putSock(ds);
            socks.putInPackage(dpin);
            if (TimeLimit > 0)
                TimeLimit /= 2;
            return dpin.getLength() - 10;
        }

        socks.putSock(ds);
        socks.putInPackage(dpin);

        return -1;
    }
}
