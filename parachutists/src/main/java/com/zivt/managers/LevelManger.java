package com.zivt.managers;

import java.util.Random;

public class LevelManger {

    public Boolean isToCreateParachutist(int x,int low, int high){
        Random r = new Random();
        int result = r.nextInt(high-low) + low;
        return x%(result*10) == 0;
    }
}
