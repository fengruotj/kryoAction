package com.basic.kryo;

import com.basic.kryo.model.User;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * locate com.basic.kryo
 * Created by mastertj on 2018/4/11.
 */
public class SerializationTest {
    private String filePath="data/java_serialzie.dat";
    @Test
    public void test() throws Exception {
        ObjectOutputStream outputStream=new ObjectOutputStream(new FileOutputStream(filePath));
        User user1=new User(1,"tanjie",23);
        outputStream.writeObject(user1);
        System.out.println("user1 hashcode: "+user1.hashCode());
        outputStream.flush();
        outputStream.close();

        ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream(filePath));
        User user2= (User) inputStream.readObject();

        System.out.println("user2 hashcode: "+user2.hashCode());

        System.out.println("user1 == user2 : "+ (user1==user2));
    }
}
