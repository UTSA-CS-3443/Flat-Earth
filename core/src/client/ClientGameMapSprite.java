package client;

import client.entities.Drawable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import utilities.ParseMap.MapDetails;

import static client.Game.cam;

public class ClientGameMapSprite extends Drawable implements Comparable<Drawable>
{
    private Sprite sprite;

    private static Vector3 unprojector = new Vector3();

    public ClientGameMapSprite(String png, float x, float y)
    {
        this.sprite = new Sprite(new Texture(png.replaceAll("\"", "")));
        unprojector.set((x - (this.sprite.getWidth() / 2)) * MapDetails.SCALE, y * MapDetails.SCALE, 0);
        this.sprite.setScale(MapDetails.SCALE);
//        cam.unproject(unprojector);
        System.out.println(x + ", " + unprojector.x + "... " + y + ", " + unprojector.y);
        this.sprite.setPosition(unprojector.x, unprojector.y);
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
