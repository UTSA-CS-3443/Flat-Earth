package client;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

import communicators.clientToServer.KeyboardState;

/**
 * This implements and event based interface (libgdx's). instantiated in Game(). 
 * All you do is set what happens for different events(button down, button up, etc)
 * @author mauricio
 *
 */

/**
 * @author mauricio
 *
 */
public class GameInput implements InputProcessor {

	/**
	 * State of the keyboad, updated here, sent to server elsewhere
	 */
	private KeyboardState ks;
	
	/**
	 * @param ks
	 */
	public GameInput(KeyboardState ks) {
		this.ks = ks;
	}
	
	// on key down, write to the Keyboardstate
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#keyDown(int)
	 */
	@Override
	public boolean keyDown(int keycode) {
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
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#keyUp(int)
	 */
	@Override
	public boolean keyUp(int keycode) {
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
		case Keys.D:
			Game.debug = !Game.debug;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#keyTyped(char)
	 */
	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#touchDown(int, int, int, int)
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#touchUp(int, int, int, int)
	 */
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#touchDragged(int, int, int)
	 */
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#mouseMoved(int, int)
	 */
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.InputProcessor#scrolled(int)
	 */
	@Override
	public boolean scrolled(int amount) {
		Game.cam.zoom += amount;
		return false;
	}

}
