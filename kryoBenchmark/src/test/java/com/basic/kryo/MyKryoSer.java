package com.basic.kryo;


import com.basic.kryo.model.Sample;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.junit.Test;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * locate com.basic.dubbo.serialize
 * Created by mastertj on 2018/3/28.
 * Kryo序列化方法性能测试
 * Kryo setSerializableObject writeObject time: 504 ms
 * Kryo getSerializableObject readObjcet time: 282 ms
 * size: 5648KB
 */
public class MyKryoSer {
    private String filePath="data/kryo_serialzie.dat";

    @Test
    public void test() throws IOException, ClassNotFoundException {
        File file=new File(filePath);
        if(!file.exists())
            file.createNewFile();

        long start = System.currentTimeMillis();
        setSerializableObject();
        System.out.println("Kryo setSerializableObject writeObject time: " + (System.currentTimeMillis() - start) + " ms");
        start = System.currentTimeMillis();
        getSerializableObject();
        System.out.println("Kryo getSerializableObject readObjcet time: " + (System.currentTimeMillis() - start) + " ms");
    }

    private void setSerializableObject() throws IOException {
        Output output=new Output(new FileOutputStream(filePath));

        Kryo kryo=new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(false);
        kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
        kryo.register(Sample.class);

        for(int i=0;i<100000;i++){
            HashMap<String,Integer> map=new HashMap<>(2);
            map.put("zhangfan0",23);
            map.put("zhangfan1",28);
            kryo.writeObject(output,new Sample(("zhangfan"+i),(i+1), map));
        }
        output.flush();;
        output.close();
    }

    public void getSerializableObject() {
        Kryo kryo=new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(false);
        kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
        Input input;
        try {
            input=new Input(new FileInputStream(filePath));
            Sample sample=null;
            while (!input.eof()){
                kryo.readObject(input,Sample.class);
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
