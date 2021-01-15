package com.zivt.observers;

import com.zivt.gameGraphics.IImage;

import java.util.Map;

public interface IMoveObserver {
    void fired(Map.Entry<String, IImage> imageEntry);
    void postMove();
}
