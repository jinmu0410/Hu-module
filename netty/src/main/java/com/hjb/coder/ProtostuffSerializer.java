package com.hjb.coder;

import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import org.springframework.cglib.core.internal.LoadingCache;

import java.io.Serializable;

import static com.dyuproject.protostuff.runtime.RuntimeSchema.getSchema;

public class ProtostuffSerializer implements Serializer {


    @Override public byte[] encode(Object msg) {
        RuntimeSchema<?> runtimeSchema = RuntimeSchema.createFrom(msg.getClass());

        Schema schema = getSchema(msg.getClass());
        return new byte[0];
    }

    @Override public <T> T decode(byte[] buf, Class<T> type) {
        return null;
    }

}
