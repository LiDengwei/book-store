package com.book.util.redis;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.*;

public class Serializer {

    public static String serialize(Object object) {
        String value = null;
        if(object != null) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                byte[] bytes = baos.toByteArray();
                value = Base64.encode(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public static Object deserialize(String value) {
        Object object = null;
        if(value != null) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(value)));
                object = ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return object;
    }
}
