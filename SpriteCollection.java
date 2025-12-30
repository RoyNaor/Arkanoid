// ID - 324946102
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

public class SpriteCollection {

    private List<Sprite> sprites = new ArrayList<>();

    public void addSprite(Sprite s){
        this.sprites.add(s) ;
    }

    public void removeSprite(Sprite s) {
        this.sprites.remove(s); }

    // call timePassed() on all sprites.
    public void notifyAllTimePassed(){
        for (int i= 0 ; i< sprites.size(); i ++){
            sprites.get(i).timePassed();
        }
    }

    // call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d){
        for (int i= 0 ; i< sprites.size(); i ++){
            sprites.get(i).drawOn(d);
        }
    }

}
