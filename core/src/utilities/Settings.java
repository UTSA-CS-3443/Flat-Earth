package utilities;

import com.badlogic.gdx.controllers.PovDirection;

/**
 * some general settings, set by the javafx
 * @author mauricio
 *
 */

public class Settings {

	public int chosenState;
	public boolean controller = true;
	public boolean music = true;

	public static final int BUTTON_X = 0;
	public static final int BUTTON_Y = 1;
	public static final int BUTTON_A = 2;
	public static final int BUTTON_B = 3;
	public static final int BUTTON_LB = 4;
	public static final int BUTTON_RB = 5;
	public static final int BUTTON_BACK = 6;
	public static final int BUTTON_START = 7;
	public static final int BUTTON_LS = 8;
	public static final int BUTTON_RS = 9;

	public static final PovDirection BUTTON_DPAD_UP = PovDirection.north;
	public static final PovDirection BUTTON_DPAD_DOWN = PovDirection.south;
	public static final PovDirection BUTTON_DPAD_RIGHT = PovDirection.east;
	public static final PovDirection BUTTON_DPAD_LEFT = PovDirection.west;

	public static final int AXIS_LX = 0;
	public static final int AXIS_LY = 1;
	// public static final int AXIS_RX = 2;
	// public static final int AXIS_RY = 3;
	// public static final int AXIS_TRIGGER = 4;

	
}
