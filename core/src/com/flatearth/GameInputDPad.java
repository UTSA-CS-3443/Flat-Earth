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
 * @author digWallace
 */
public class GameInputDPad implements InputProcessor, ControllerListener
{
    private KeyboardState ks;

    public GameInputDPad(KeyboardState ks)
    {
        Controllers.addListener(this);
        Gdx.input.setInputProcessor(this);

        this.ks = ks;
    }

    @Override
    public boolean buttonDown(Controller controller, int buttonIndex)
    {
        switch (buttonIndex)
        {
            /* Obviously these can be changed to our liking when we have them implemented. */
            case Settings.BUTTON_A:
                ks.defendPressed();
                break;
            case Settings.BUTTON_B:
                ks.attack1Pressed();
                break;
            case Settings.BUTTON_X:
                ks.attack2Pressed();
                break;
            case Settings.BUTTON_Y:
                // TODO
                break;
            case Settings.BUTTON_LB:
                // TODO
                break;
            case Settings.BUTTON_RB:
                // TODO
                break;
            case Settings.BUTTON_LS:
                // TODO
                break;
            case Settings.BUTTON_RS:
                // TODO
                break;
            case Settings.BUTTON_BACK:
                // TODO
                break;
            case Settings.BUTTON_START:
                // TODO
                break;
        }
        return false;
    }

    @Override
    public boolean buttonUp(Controller controller, int buttonIndex)
    {
        switch (buttonIndex)
        {
            case Settings.BUTTON_A:
                ks.defendReleased();
                break;
            case Settings.BUTTON_B:
                ks.attack1Released();
                break;
            case Settings.BUTTON_X:
                ks.attack2Released();
                break;
            case Settings.BUTTON_Y:
                // TODO
                break;
            case Settings.BUTTON_LB:
                // TODO
                break;
            case Settings.BUTTON_RB:
                // TODO
                break;
            case Settings.BUTTON_LS:
                // TODO
                break;
            case Settings.BUTTON_RS:
                // TODO
                break;
            case Settings.BUTTON_BACK:
                // TODO
                break;
            case Settings.BUTTON_START:
                // TODO
                break;
        }
        return false;
    }

    @Override
    public boolean axisMoved(Controller controller, int axisIndex, float value)
    {
        return false;
    }

    @Override
    public boolean povMoved(Controller controller, int povIndex, PovDirection value)
    {
        System.out.println("pov " + povIndex + ": " + value);
        if (value == Settings.BUTTON_DPAD_UP)
            ks.UpPress();
        if (value == Settings.BUTTON_DPAD_DOWN)
            ks.DownPress();
        if (value == Settings.BUTTON_DPAD_LEFT)
            ks.LeftPress();
        if (value == Settings.BUTTON_DPAD_RIGHT)
            ks.RightPress();
        if (value == Settings.BUTTON_DPAD_CENTER)
        {
            ks.UpRelease();
            ks.DownRelease();
            ks.LeftRelease();
            ks.RightRelease();
        }
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

    public boolean keyDown(int keycode)
    {
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
