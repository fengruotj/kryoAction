package com.basic.kryo.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * locate com.basic.dubbo.model
 * Created by mastertj on 2018/3/28.
 */
public class Sample implements Serializable{
    private String name;
    private int age;
    private HashMap<String,Integer> map;
    public Sample(){

    }

    public Sample(String name, int age, HashMap<String, Integer> map) {
        this.name = name;
        this.age = age;
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public HashMap<String, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Sample{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", map=" + map +
                '}';
    }
}
