package com.basic.kryo;

import com.basic.kryo.model.User;
import org.junit.Test;
/**
 * locate com.basic.kryo
 * Created by mastertj on 2018/4/10.
 */
public class KryoUtilsTest {
    @Test
    public void writeToByteArray() throws Exception {
        User user =new User(1,"tanjie",23);
        System.out.println(user.hashCode());
        byte[] bytes = KryoUtils.writeToByteArray(user);
        System.out.println(new String(bytes,"UTF-8"));
        User user1 =(User) KryoUtils.readFromByteArray(bytes);
        System.out.println(user1==user);
        System.out.println(user1.hashCode());
    }

    @Test
    public void writeToString() throws Exception {
        User user =new User(1,"tanjie",23);
        String str = KryoUtils.writeToString(user);
        User user1 =(User) KryoUtils.readFromString(str);
        System.out.println(user1);
    }

    @Test
    public void writeObjectToByteArray() throws Exception {
        User user =new User(1,"tanjie",23);
        byte[] bytes = KryoUtils.writeObjectToByteArray(user);
        System.out.println(user.hashCode());
        System.out.println(new String(bytes,"UTF-8"));
        User user1 =(User) KryoUtils.readObjectFromByteArray(bytes,User.class);
        System.out.println(user1);
        System.out.println(user1.hashCode());
}

    @Test
    public void writeObjectToString() throws Exception {
        User user =new User(1,"tanjie",23);
        String str = KryoUtils.writeObjectToString(user);
        User user1 =(User) KryoUtils.readObjectFromString(str,User.class);
        System.out.println(user1);
    }

}
