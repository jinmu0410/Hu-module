package com.hjb.demo.other;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRU<K,V> extends LinkedHashMap<K,V> {

    public Integer size;

    public LRU(Integer maxSize){
        super((int) (Math.ceil(maxSize/0.75) +1),0.75f,true);
        this.size = maxSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size()> size;
    }
}
