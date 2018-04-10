package com.basic.kryo;


import com.basic.kryo.model.Sample;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;

/**
 * locate com.basic.dubbo.serialize
 * Created by mastertj on 2018/3/28.
 * Java序列化方法性能测试
 * java setSerializableObject writeObject time: 5226 ms
 * java getSerializableObject readObjcet time: 4286 ms
 * Size : 6923KB
 */
public class MyJavaSer {
    private String filePath="data/java_serialzie.dat";

    @Test
    public void test() throws IOException, ClassNotFoundException {
        File file=new File(filePath);
        if(!file.exists())
            file.createNewFile();

        long start=System.currentTimeMillis();
        setSerializableObject();
        System.out.println("java setSerializableObject writeObject time: "+(System.currentTimeMillis()-start)+" ms");
        start=System.currentTimeMillis();
        getSerializableObject();
        System.out.println("java getSerializableObject readObjcet time: "+(System.currentTimeMillis()-start)+" ms");
    }

    public void setSerializableObject() throws IOException {
        ObjectOutputStream outputStream=new ObjectOutputStream(new FileOutputStream(filePath));

        for(int i=0;i<100000;i++){
            HashMap<String,Integer> map=new HashMap<>(2);
            map.put("zhangfan0",23);
            map.put("zhangfan1",28);
            outputStream.writeObject(new Sample(("zhangfan"+i),(i+1), map));
        }
        outputStream.flush();
        outputStream.close();
    }

    public void getSerializableObject() throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream=new ObjectInputStream(new FileInputStream(filePath));

        Sample sample=null;
        for(int i=0;i<100000;i++){
            sample= (Sample) inputStream.readObject();
        }
        inputStream.close();
    }
}
