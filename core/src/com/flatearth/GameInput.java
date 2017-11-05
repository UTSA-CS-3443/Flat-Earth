package com.flatearth;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;

import utilities.KeyboardState;
import utilities.Settings;

/**
 * This implements and event based interface (libgdx's). instantiated in Game(). 
 * All you do is set what happens for different events(button down, button up, etc)
 * @author mauricio
 *
 */

public class GameInput implements InputProcessor, ControllerListener {

	private KeyboardState ks;
	//boolean hasControllers = true;
	
	public GameInput(KeyboardState ks)
	{
		Controllers.addListener(this);
		Gdx.input.setInputProcessor(this);
		this.ks = ks;
	}

	public void connected(Controller controller)
	{
		/* Currently only works with "Android". */
	}

	public void disconnected(Controller controller)
	{
		/* Currently only works with "Android" */
	}

	// on key down, write to the Keyboardstate
	@Override
	public boolean keyDown(int keycode)
	{
		switch(keycode) {
		case Keys.UP:
			ks.UpPress(); break;
		case Keys.DOWN:
			ks.DownPress(); break;
		case Keys.LEFT:
			ks.LeftPress(); break;
		case Keys.RIGHT:
			ks.RightPress(); break;
		case Keys.Q:
			ks.defendPressed(); break;
		case Keys.W:
			ks.attack1Pressed(); break;
		case Keys.E:
			ks.attack2Pressed(); break;
		}
		
		return false;
	}

	// On button up, write to the keyboardState
	@Override
	public boolean keyUp(int keycode)
	{
		switch(keycode) {
		case Keys.UP:
			ks.UpRelease(); break;
		case Keys.DOWN:
			ks.DownRelease(); break;
		case Keys.LEFT:
			ks.LeftRelease(); break;
		case Keys.RIGHT:
			ks.RightRelease(); break;
		case Keys.Q:
			ks.defendReleased(); break;
		case Keys.W:
			ks.attack1Released(); break;
		case Keys.E:
			ks.attack2Released(); break;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character)
	{
		if(character == 'q')
			Game.debug = !Game.debug;
		return false;
	}

	@Override
	public boolean buttonDown(Controller controller, int buttonIndex) {

//		switch(controller){
//			case Settings.BUTTON_DPAD_DOWN:
//				ks.DownPress(); break;
//		}

		switch (buttonIndex) {
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
		switch (buttonIndex) {
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

		System.out.println("Axis value = " + value);
		if (value >= 0.1) {
			if (axisIndex == Settings.AXIS_LX) {
				ks.RightPress();
			}
			if (axisIndex == Settings.AXIS_LY) {
				ks.DownPress();
			}
		}
		else if ( value <= -0.1) {
			if (axisIndex == Settings.AXIS_LX) {
				ks.LeftPress();
			}
			if (axisIndex == Settings.AXIS_LY) {
				ks.UpPress();
			}
		} else {
			ks.RightRelease();
			ks.LeftRelease();
			ks.DownRelease();
			ks.UpRelease();
		}
		return false;
	}

	@Override
	public boolean povMoved(Controller controller, int povIndex, PovDirection value)
	{
		//System.out.println("pov " + povIndex + ": " + value);
		if (value == Settings.BUTTON_DPAD_UP)
			ks.UpPress();
		if (value == Settings.BUTTON_DPAD_DOWN)
			ks.DownPress();
		if (value == Settings.BUTTON_DPAD_LEFT)
			ks.LeftPress();
		if (value == Settings.BUTTON_DPAD_RIGHT)
			ks.RightPress();
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

	@Override
	public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value)
	{
		return false;
	}
}
