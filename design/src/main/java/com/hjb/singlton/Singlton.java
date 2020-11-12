package com.hjb.singlton;

/**
 * 双层校验
 */
public class Singlton {

    private static volatile Singlton singlton;

    private Singlton(){}

    public static Singlton getInstance(){
        if(singlton == null){
            synchronized (Singlton.class){
                if(singlton == null){
                    singlton = new Singlton();
                }
            }
        }
        return singlton;
    }
}
