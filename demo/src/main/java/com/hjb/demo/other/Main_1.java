package com.hjb.demo.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 在一个字符中，找到第一个只出现一次的字符，并返回他的位置，没有返回-1；
 */
public class Main_1 {

    public static int firstIndex(String str){
        char[] chars = str.toCharArray();
        Map<String,Integer> hashMap = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            String key = String.valueOf(chars[i]);
            if (!hashMap.containsKey(key)) {
                hashMap.put(key, i);
            }else {
                hashMap.remove(key);
            }
        }
        int b = 0;
        for (Integer key : hashMap.values()) {
            b=key;
            break;
        }
        return hashMap.isEmpty() ? -1 : b;
    }

    public static void main(String[] args) {
        System.out.println(firstIndex("safaccfsdd"));
    }
}
