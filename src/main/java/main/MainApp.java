/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import object.thrift.User;
import org.apache.thrift.TException;
import serializer.KryoSerializer;
import serializer.ThriftSerializer;

/**
 *
 * @author sangdn
 */
public class MainApp {

    public static void main(String[] args) throws TException, IOException {
        int TOTAL = 10;

        long totalSerialize = 0;
        long totalDeserialize = 0;
        long tmp;
        for (int i = 0; i < TOTAL; ++i) {
            object.thrift.User randTUser = randTUser();
            tmp = System.currentTimeMillis();
            byte[] tdata = ThriftSerializer.INS.serialize(randTUser);
            totalSerialize += System.currentTimeMillis() - tmp;
            tmp = System.currentTimeMillis();
            object.thrift.User tUser = new User();
            ThriftSerializer.INS.deserialize(tUser, tdata);
            totalDeserialize += System.currentTimeMillis() - tmp;
            assert randTUser.equals(tUser);
        }
        print("Thrift Serialize", totalSerialize, TOTAL);
        print("Thrift DeSerialize", totalDeserialize, TOTAL);

        totalDeserialize = 0;
        totalSerialize = 0;

        KryoSerializer.INS.register(object.java.User.class);

        for (int i = 0; i < TOTAL; ++i) {
            object.java.User randUser = randUser();
            tmp = System.currentTimeMillis();
            byte[] tdata = KryoSerializer.INS.serialize(randUser);
            totalSerialize += System.currentTimeMillis() - tmp;
            tmp = System.currentTimeMillis();
            object.java.User user = KryoSerializer.INS.deserialize(object.java.User.class, tdata);
            totalDeserialize += System.currentTimeMillis() - tmp;
            assert randUser.equals(user);
        }

        print("Kryo Serialize", totalSerialize, TOTAL);
        print("Kryo DeSerialize", totalDeserialize, TOTAL);
    }

    static void print(String type, long totalTimeInMs, int num) {
        assert num > 0;
        System.out.println("--------------------------------------");
        System.out.println(type);
        System.out.println("Total Time: " + totalTimeInMs + " (ms)");
        System.out.println("Avg Time: " + ((double) totalTimeInMs / num) + " (ms)");
        System.out.println("--------------------------------------");
    }

    static object.thrift.User randTUser() {
        object.thrift.User user = new object.thrift.User();
        user.setId(UUID.randomUUID().toString());
        user.setName(UUID.randomUUID().toString());
        user.setAge(ThreadLocalRandom.current().nextInt());
        user.setDob(ThreadLocalRandom.current().nextLong());
        return user;
    }

    static object.java.User randUser() {
        object.java.User user = new object.java.User();
        user.setId(UUID.randomUUID().toString());
        user.setName(UUID.randomUUID().toString());
        user.setAge(ThreadLocalRandom.current().nextInt());
        user.setDob(ThreadLocalRandom.current().nextLong());
        return user;
    }

}
