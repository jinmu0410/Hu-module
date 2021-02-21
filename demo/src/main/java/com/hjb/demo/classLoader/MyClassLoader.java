package com.hjb.demo.classLoader;

public class MyClassLoader extends ClassLoader{

    //遵循双亲委派
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    //打破双亲委派
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
    }
}
