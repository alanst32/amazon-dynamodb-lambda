package com.rocketozzy.utils;

import java.util.HashMap;

public class QueryUtils {


    public static HashMap<String, String> createAttrHash(String key, String value){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put(key, value);

        return hashMap;
    }

    public static HashMap<String, Object> createValueHash(String key, String value){
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(key, value);

        return hashMap;
    }
}
