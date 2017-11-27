package client.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Drawable implements Comparable<Drawable>
{
    public abstract Sprite getSprite();
}
