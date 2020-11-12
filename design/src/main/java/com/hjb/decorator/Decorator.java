package com.hjb.decorator;

public class Decorator implements Compent{

    private Compent compent;

    public Decorator(Compent compent){
        this.compent = compent;
    }

    @Override
    public void sell() {
        compent.sell();
    }
}
