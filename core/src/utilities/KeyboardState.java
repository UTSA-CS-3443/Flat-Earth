package utilities;

//obviously not optimized, just want to get something working
// maybe just send key codes
/**
 * holds the state of the keyboard
 * 
 * holds key presses as a bit field in a short int.
 * 					byte 2					least significant bit(byte 1)
 * .... (blank)(defend)(attack2)(attack1) | (right)(left)(down)(up)
 * 			0100 1001
 * 
 * 1 means pressed, 0 means not
 * 
 * needs a lot of optimization but it's getting there. Done like this so that
 * the amount we're sending over the connection is smaller
 * 
 * @author mauricio
 *
 */
public class KeyboardState {
	
	// TODO these might be getting serialized as well, don't send these
	// shift amounts for first byte
	private static final int UP_MOD = 0;
	private static final int DOWN_MOD = 1;
	private static final int LEFT_MOD = 2;
	private static final int RIGHT_MOD = 3;
	
	// shift amounts for second byte
	private static final int ATTACK1_MOD = 4;
	private static final int ATTACK2_MOD = 5;
	private static final int DEFEND_MOD = 6;
	
	// short my cause a bug, might just use an int	
	private short bitField;
	
	public KeyboardState() {
		this.bitField = 0;
	}
	
	// presses
	// first byte
	public void UpPress() {bitField = (short) (bitField | (1 << UP_MOD));}
	public void DownPress() {bitField = (short) (bitField | (1 << DOWN_MOD));}
	public void LeftPress() {bitField = (short) (bitField | (1 << LEFT_MOD));}
	public void RightPress() {bitField = (short) (bitField | (1 << RIGHT_MOD));}
	//second byte
	public void attack1Pressed() {bitField = (short) (bitField | (1 << ATTACK1_MOD));}
	public void attack2Pressed() {bitField = (short) (bitField | (1 << ATTACK2_MOD));}
	public void defendPressed() {bitField = (short) (bitField | (1 << DEFEND_MOD));}

	// releases
	//first byte
	public void UpRelease() {bitField = (short) (bitField & ~(1 << UP_MOD));}
	public void DownRelease() {bitField = (short) (bitField & ~(1 << DOWN_MOD));}
	public void LeftRelease() {bitField = (short) (bitField & ~(1 << LEFT_MOD));}
	public void RightRelease() {bitField = (short) (bitField & ~(1 << RIGHT_MOD));}
	// second byte
	public void defendReleased() {bitField = (short) (bitField & ~(1 << ATTACK1_MOD));}
	public void attack1Released() {bitField = (short) (bitField & ~(1 << ATTACK2_MOD));}
	public void attack2Released() {bitField = (short) (bitField & ~(1 << DEFEND_MOD));}

	
	public synchronized KeysPressed get() {
		KeysPressed pressed = new KeysPressed();
		
		pressed.up = (this.bitField >> UP_MOD) & 1;
		pressed.down = (this.bitField >> DOWN_MOD) & 1;
		pressed.left = (this.bitField >> LEFT_MOD) & 1;
		pressed.right = (this.bitField >> RIGHT_MOD) & 1;
		pressed.defend = (this.bitField >> DEFEND_MOD) & 1;
		pressed.attack1 = (this.bitField >> ATTACK1_MOD) & 1;
		pressed.attack2 = (this.bitField >> ATTACK2_MOD) & 1;
			
		return pressed;
	}
	
	public synchronized void updateData(KeyboardState ks) {
		this.bitField = ks.getField();
	}
	
	public short getField() {
		return this.bitField;
	}
}



















