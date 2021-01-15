package com.zivt.observers;

import com.zivt.managers.GameManager;

import java.util.ArrayList;

public class ObserversBuilder {

    private static ObserversBuilder observersBuilder = null;

    private ObserversBuilder() {
    }

    public static ObserversBuilder getInstance(){
        if (observersBuilder == null)
            observersBuilder = new ObserversBuilder();
        return observersBuilder;
    }
    public static ArrayList<IMoveObserver> buildMoveObservers(GameManager gameManager){
        ArrayList<IMoveObserver> moveObservers = new ArrayList<>();
        moveObservers.add(new ParachutistsMove(gameManager));
        return moveObservers;
    }

    public static ArrayList<ITimerObserver> buildTimerObservers(GameManager gameManager){
        ArrayList<ITimerObserver> timerObservers = new ArrayList<>();
        timerObservers.add(new ParachutistsTimer(gameManager));
        return timerObservers;
    }
}
