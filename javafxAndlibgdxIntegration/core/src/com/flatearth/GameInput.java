package com.flatearth;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;

import utilities.KeyboardState;

// probably have this write to whatever buffer for sending

//either that, or, have some sort of state object that stores the current state of the players
//keyboard presses/clicks, like say:
// 		up arrow down
//		left arrow down
//		(whatever for attacking) down
// and that is set in key down. Key up just removes that from the list. And just send that state
// object to the server, that way the server never misses a button click (cause they will probably get that
// object atleast twice or something, since it processes and send quickly). Might make it hard to make an
// option that changes key bindings though (so you can have custom key bindings or whatever)

public class GameInput implements InputProcessor {

	private KeyboardState ks;
	
	public GameInput(KeyboardState ks) {
		this.ks = ks;
	}
	
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
		}
		
		return false;
	}

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
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		if(character == 'q')
			Game.debug = !Game.debug;
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
