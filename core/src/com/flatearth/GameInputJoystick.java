package com.flatearth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import utilities.KeyboardState;
import utilities.Settings;

/**
 * This creates a unique joystick gamepad setup for a player.
 * @author digWallace
 */
public class GameInputJoystick implements InputProcessor, ControllerListener
{
    private KeyboardState ks;
    private static float trackY[];
    private static float trackX[];
    private static int i = 4;
    private static boolean mRight, mLeft, mUp, mDown;

    public GameInputJoystick(KeyboardState ks)
    {
        trackX = new float[this.i];
        trackY = new float[this.i];
        this.mRight = false;
        this.mLeft = false;
        this.mUp = false;
        this.mDown = false;
        Controllers.addListener(this);
        Gdx.input.setInputProcessor(this);
        this.i = 0;

        this.ks = ks;
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonIndex)
    {
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonIndex)
    {
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisIndex, float value)
    {
        this.i++;
        //System.out.println("axisIndex = " + axisIndex + ", value = " + value + "\n");

        if (this.i > this.trackY.length - 1)
            this.i = 0;

        System.out.println("\naxisIndex = " + axisIndex);

        if (axisIndex == Settings.AXIS_LY)
            this.trackY[this.i] = value;
        else if (axisIndex == Settings.AXIS_LX)
            this.trackX[this.i] = value;
        else
            return false;

        float sum = 0;

        for (float d : trackY)
            sum += d;
        float avgY = sum / (this.trackY.length - 1f);

        sum = 0;

        for (float d : trackX)
            sum += d;
        float avgX = sum / (this.trackX.length - 1f);

        System.out.println("avgX = " + avgX + ", avgY = " + avgY);
        System.out.println("Xa[0] = " + trackX[0] + ", sum = " + sum);

        if (avgX >= 0.2)
            this.mRight = true;
        else if (avgX <= -0.2)
            this.mLeft = true;
        else
            this.mRight = this.mLeft = false;

        if (avgY >= 0.2)
            this.mDown = true;
        else if (avgY <= -0.2)
            this.mUp = true;
        else
            this.mDown = this.mUp = false;

        if (mRight == true)
            ks.RightPress();
        else
            ks.RightRelease();

        if (mLeft == true)
            ks.LeftPress();
        else
            ks.LeftRelease();

        if (mDown == true)
            ks.DownPress();
        else
            ks.DownRelease();

        if (mUp == true)
            ks.UpPress();
        else
            ks.UpRelease();

        System.out.println("i = " + this.i);
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povIndex, PovDirection value)
    {
        return false;
    }

    @Override
    public boolean xSliderMoved(Controller controller, int sliderCode, boolean value)
    {
        return false;
    }

    @Override
    public boolean ySliderMoved(Controller controller, int sliderCode, boolean value)
    {
        return false;
    }

    @Override
    public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value)
    {
        return false;
    }

    public void connected(Controller controller)
    {
		/* Currently only works with "Android". */
    }

    public void disconnected(Controller controller)
    {
		/* Currently only works with "Android" */
    }

    public boolean keyDown(int keycode){
        return false;
    }

    public boolean keyUp(int keycode)
    {
        return false;
    }

    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button)
    {
        return true;
    }
    @Override
    public boolean touchUp(int x, int y, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchDragged(int x, int y, int pointer)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }
}