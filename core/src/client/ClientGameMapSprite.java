package client;

import client.entities.Drawable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ClientGameMapSprite extends Drawable implements Comparable<Drawable>
{
    private Sprite sprite;

    public ClientGameMapSprite(String png, float x, float y)
    {
        this.sprite = new Sprite(new Texture(png));
        this.sprite.setPosition(x, y);
    }

    @Override
    public Sprite getSprite() {
        return this.sprite;
    }

    @Override
    public int compareTo(Drawable other) {
        if (this.sprite.getY() > other.getSprite().getY())
            return -1;
        else if (this.sprite.getY() < other.getSprite().getY())
            return 1;
        return 0;
    }
}
