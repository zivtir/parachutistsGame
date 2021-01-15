package com.zivt.observers;

import com.zivt.gameGraphics.IImage;
import com.zivt.managers.Consts;
import com.zivt.managers.GameManager;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.Map;

public class ParachutistsMove implements IMoveObserver {
    private ArrayList<String> imagesToRemove = new ArrayList<>();
    private GameManager gameManager;

    public ParachutistsMove(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    @Override
    public void fired(Map.Entry<String, IImage> imageEntry) {
        parachutistState(imageEntry.getKey(),imageEntry.getValue());
    }

    @Override
    public void postMove() {
        imagesToRemove.forEach(i-> gameManager.removeImage(i));
    }

    private void parachutistState(String id, IImage parachutist) {
        if (NumberUtils.isCreatable(id)){
            if (boatHitsParachutist(parachutist)){
                imagesToRemove.add(id);
                gameManager.getMainPanel().accumulateScore(gameManager.getScorePerHit());
            }else if (parachutistsSinkInWater(parachutist)){
                imagesToRemove.add(id);
                subtractLives();
            }
        }
    }

    private void subtractLives() {
        int currentLives = gameManager.getMainPanel().getLives();
        if (currentLives == 1){
            gameManager.getMainPanel().repaintGameOver();
            gameManager.stop();
        }
        gameManager.getMainPanel().setLives(--currentLives);
    }

    private boolean parachutistsSinkInWater(IImage parachutist) {
        return parachutist.getY()  >= gameManager.seaLevel() ;
    }

    private boolean boatHitsParachutist(IImage image){
        IImage boat = gameManager.getImages().get(Consts.BOAT);
        return image.getY()+image.getImage().getHeight(null) >= boat.getY()
                && checkX(image, boat);
    }

    private boolean checkX(IImage image, IImage boat) {
        int boatWidth = boat.getImage().getWidth(null);
        int imageWidth = image.getImage().getWidth(null);
        return image.getX() >= boat.getX()-imageWidth && image.getX() <=  boat.getX()+ boatWidth;
    }
}
