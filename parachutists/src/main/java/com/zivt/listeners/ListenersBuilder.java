package com.zivt.listeners;

import com.zivt.managers.GameManager;

import java.util.ArrayList;
import java.util.EventListener;

public class ListenersBuilder {
    private static ListenersBuilder listenersBuilder = null;

    private ListenersBuilder() {
    }

    public static ListenersBuilder getInstance(){
        if (listenersBuilder== null)
            listenersBuilder= new ListenersBuilder();
        return listenersBuilder;
    }

    public static ArrayList<EventListener> getListeners(GameManager gameManager){
        ArrayList<EventListener> eventListeners = new ArrayList<>();
        GameKeyListener gameKeyListener = new GameKeyListener(gameManager);
        eventListeners.add(gameKeyListener);
        return eventListeners;
    }
}
