package com.book.util.Log;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Hashtable;

@SuppressWarnings("unchecked")
public class UDPSocketManager {
    Hashtable<String, InetAddress> Addrs;
    long counter;
    static final int UDPMAX = 54000;
    int sockseq = 10000;

    public UDPSocketManager()
    {
        this.Addrs = new Hashtable(10);
        this.counter = 0L;
    }

    public InetAddress getAddr(String IP) {
        InetAddress ad = null;
        ad = (InetAddress)this.Addrs.get(IP);
        if (ad == null) {
            try {
                ad = InetAddress.getByName(IP);
            } catch (Exception E) {
                E.printStackTrace();
                return null;
            }
            this.Addrs.put(IP, ad);
        }
        return ad;
    }

    public DatagramSocket getSock()
    {
        DatagramSocket ds = null;
        this.sockseq += 1;

        if (this.sockseq > 30000) {
            this.sockseq = 10000;
        }

        if (ds == null)
            try {
                DatagramSocket r = new DatagramSocket();
                r.setSoTimeout(4100);
                return r;
            } catch (Exception E) {
                try {
                    DatagramSocket r = new DatagramSocket();
                    r.setSoTimeout(4100);
                    return r;
                } catch (Exception Ex) {
                    Ex.printStackTrace();

                    E.printStackTrace();
                }
            }
        return ds;
    }

    public DatagramPacket getInPackage() {
        this.counter += 1L;
        try {
            byte[] inBuff = new byte[54000];
            return new DatagramPacket(inBuff, 54000);
        } catch (Exception E) {
            E.printStackTrace();
        }return null;
    }

    public void putSock(DatagramSocket ds)
    {
        ds.close();
    }

    public void putInPackage(DatagramPacket dp)
    {
    }
}
